package zombicide;

import zombicide.action.Action;
import zombicide.action.survivor.*;
import zombicide.action.survivor.special.*;
import zombicide.actor.survivor.Survivor;
import zombicide.actor.zombie.Walker;
import zombicide.actor.zombie.Zombie;
import zombicide.city.City;
import zombicide.city.TrainCity;
import zombicide.item.Map;
import zombicide.item.careItem.HealingFiask;
import zombicide.item.attackItem.weapon.Riffle;
import zombicide.util.Direction;
import zombicide.util.listchooser.RandomListChooser;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Livrable2 {

	private final City city = new City(10,10);
	private final TrainCity trainCity = new TrainCity();

	public static void main(String[] args) {
		Livrable2 livrable2 = new Livrable2();
		livrable2.start(args);
	}

	private void start(String[] args) {

		if (args.length < 2) {
			initCity(this.city.getWidth(), this.city.getHeight());
		} else {
			int width = parseInt(args[0]);
			int height = parseInt(args[1]);
			initCity(width, height);
		}

		initTrainCity();

		chooseRandomSurvivorAction();
	}

	private void initCity(int width, int height) {
		City city = new City(width, height);

		city.getAreas()[0][0].getDoor(Direction.DOWN).open();
		city.getAreas()[0][1].getDoor(Direction.DOWN).open();
		city.getAreas()[0][1].getDoor(Direction.LEFT).open();

		new Survivor(List.of(new Fighter()), this.trainCity);
		new Survivor(List.of(new Healer()), this.trainCity);
		new Survivor(List.of(new Lucky()), this.trainCity);
		new Survivor(List.of(new Snooper()), this.trainCity);

		city.display();
	}

	private void initTrainCity() {
		System.out.println("Plateau d'entraînement :");

		trainCity.getAreas()[0][0].getDoor(Direction.DOWN).open();
		trainCity.getAreas()[0][1].getDoor(Direction.DOWN).open();
		trainCity.getAreas()[0][1].getDoor(Direction.LEFT).open();

		new Survivor(List.of(new Fighter()), this.trainCity);
		new Survivor(List.of(new Healer()), this.trainCity);
		new Survivor(List.of(new Lucky()), this.trainCity);
		new Survivor(List.of(new Snooper()), this.trainCity);

		List<Survivor> survivors = trainCity.getSpawn().getSurvivors();

		// Give healing fiask to survivors
		giveHealingFiask(survivors);
		// Add map to
		addMapInBackPack(survivors);

		// Adding walker in all city's areas
		for (int i = 0; i < trainCity.getHeight(); i++) {
			for (int j = 0; j < trainCity.getWidth(); j++) {
				Zombie walker = new Walker(Collections.singletonList(null), this.city);
				walker.setArea(trainCity.getAreas()[j][i]);
			}
		}

		trainCity.display();

		// Displaying survivors with their roles, their items in hand and a map in their backpack.
		System.out.println();
		int i = 1;
		for (Survivor survivor : survivors) {
			System.out.printf("Survivant %d : Rôles : %s | Item en main : %s | Contenu sac : %s%n",
					i++,
					survivor.getRoles(),
					survivor.getItemHeld(),
					survivor.getBackpack().getItems()
			);
		}

		// Displaying items in some rooms
		System.out.println();
		trainCity.getRooms().get(0).displayItems();
		trainCity.getRooms().get(1).displayItems();
		trainCity.getRooms().get(2).displayItems();

		System.out.println("Plateau d'entraînement avec les survivants montés de 1 case :");

		for (int ignored = 0; ignored < 4; ignored++)
			survivors.get(0).setArea(trainCity.getCellUp(2, 2));

		trainCity.display();
	}

	private void addMapInBackPack(List<Survivor> survivors) {
		for (Survivor survivor : survivors)
			survivor.getBackpack().addItem(new Map());
	}

	private void giveHealingFiask(List<Survivor> survivors) {
		for (Survivor survivor : survivors)
			survivor.setItemHeld(new HealingFiask());
	}

	private void chooseRandomSurvivorAction() {
		Survivor survivor = new Survivor(this.trainCity);
		survivor.setArea(this.trainCity.getArea(3,3));
		Map m = new Map();
		survivor.setItemHeld(m);
		for(int i =0 ; i < 5 ; i++){
			survivor.getBackpack().addItem(new Riffle());
		}
		System.out.println("Item en main avant action :"+survivor.getItemHeld());
		System.out.println("BackPack avant action");
		System.out.println(survivor.getBackpack().getItems());
		RandomListChooser<Action<Survivor>> survivorActionChooser = new RandomListChooser<>();

		List<Action<Survivor>> survivorActions = Arrays.asList(
				null,
				new RummageAction(),
				new BackPackAction(),
				new DoorAction(),
				new ItemAction(),
				new NoiseAction(),
				new AreaAction(),
				new LookAction()
		);

		Action<Survivor> action = survivorActionChooser.choose(survivorActions);
		if (action != null) {
			action.doSomething(survivor);
			this.trainCity.display();
			System.out.println("BackPack après action");
			System.out.println("Item en main après action :"+survivor.getItemHeld());
			System.out.println(survivor.getBackpack().getItems());
		}
	}

	private static int parseInt(String s) {
		String errorMsg = "Les arguments passés en paramètre doivent être des entiers supérieurs à 4 !";
		try {
			int n = Integer.parseInt(s);
			if (n < 5)
				throw new IllegalArgumentException(errorMsg);
			return n;
		} catch (NumberFormatException e) {
			throw new NumberFormatException(errorMsg);
		}
	}
}
