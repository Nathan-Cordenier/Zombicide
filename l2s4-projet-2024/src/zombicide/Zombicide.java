package zombicide;

import zombicide.action.Action;
import zombicide.action.survivor.*;
import zombicide.action.survivor.special.Fighter;
import zombicide.action.survivor.special.Healer;
import zombicide.action.survivor.special.Lucky;
import zombicide.action.survivor.special.Snooper;
import zombicide.actor.survivor.Survivor;
import zombicide.city.City;
import zombicide.game.Game;
import zombicide.util.listchooser.ListChooser;
import zombicide.util.listchooser.RandomListChooser;

import java.util.Arrays;
import java.util.List;

public class Zombicide {

    private City city;
    private Game game;

    public static void main(String[] args) {
        Zombicide zombicide = new Zombicide();
        zombicide.start(args);
    }

    private void start(String[] args) {
        if (args.length != 3) {
            throw new IllegalArgumentException("Il faut 3 arguments (longueur, largeur, nombre de survivants");
        }
        city = new City(parseInt(args[0]), parseInt(args[1]));
        game = new Game(this.city);
        createSurvivors(parseInt(args[2]));
        initTrainCity();
    }

    private void createSurvivors(int n) {
        List<Action<Survivor>> fighterAction = Arrays.asList(
                null,
                new AttackZombieAction(),
                new RummageAction(),
                new BackPackAction(),
                new DoorAction(),
                new ItemAction(),
                new NoiseAction(),
                new LookAction(),
                new Fighter(),
                new SurvivorMoveAction()
        );

        List<Action<Survivor>> healerAction = Arrays.asList(
                null,
                new AttackZombieAction(),
                new RummageAction(),
                new BackPackAction(),
                new DoorAction(),
                new ItemAction(),
                new NoiseAction(),
                new LookAction(),
                new Healer(),
                new SurvivorMoveAction()
        );

        List<Action<Survivor>> luckyAction = Arrays.asList(
                null,
                new AttackZombieAction(),
                new RummageAction(),
                new BackPackAction(),
                new DoorAction(),
                new ItemAction(),
                new NoiseAction(),
                new LookAction(),
                new Lucky(),
                new SurvivorMoveAction()
        );

        List<Action<Survivor>> snooperAction = Arrays.asList(
                null,
                new RummageAction(),
                new AttackZombieAction(),
                new BackPackAction(),
                new DoorAction(),
                new ItemAction(),
                new NoiseAction(),
                new LookAction(),
                new Snooper(),
                new SurvivorMoveAction()
        );

        List<List<Action<Survivor>>> roles = Arrays.asList(
                fighterAction,
                healerAction,
                luckyAction,
                snooperAction
        );

        ListChooser<List<Action<Survivor>>> chooser = new RandomListChooser<>();

        for (int i=0; i<n; i++) {
            Survivor survivor = new Survivor(chooser.choose(roles), game.getCity());
            survivor.setName("s" + (i + 1));
            game.addSurvivor(survivor);
        }
    }

    private void initTrainCity() {
        city.display();
        game.play();
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
