package zombicide.actor.zombie;

import zombicide.action.Action;
import zombicide.city.City;

import java.util.List;

public class Walker extends Zombie {
	private static final int ATTACK_POINTS = 1;
	private static final int LIFE_POINTS = 1;
	private static final int ACTION_POINTS = 1;
	private static final boolean IS_STRONG = false;
	
	public Walker(List<Action<Zombie>> zombieActions, City city) {
		super(zombieActions, ATTACK_POINTS, LIFE_POINTS, ACTION_POINTS, IS_STRONG, city);
	}

	public String toString() {
		return "Walker";
	}
	
}

