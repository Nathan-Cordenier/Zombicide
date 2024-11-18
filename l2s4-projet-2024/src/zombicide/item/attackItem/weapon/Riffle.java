package zombicide.item.attackItem.weapon;

import zombicide.item.attackItem.AttackItem;

public class Riffle extends AttackItem implements Weapon {
	private static final int NB_DICE_THROWS = 2;
	private static final int DICE_THRESHOLD = 4;
	private static final int DAMAGE = 1;
	private static final int MIN_HITTING_RANGE = 1;
	private static final int MAX_HITTING_RANGE = 3;
	
	public Riffle() {
		super(NB_DICE_THROWS, DICE_THRESHOLD, DAMAGE, MIN_HITTING_RANGE, MAX_HITTING_RANGE);
		this.isNoisyUse = true;
	}

	public String toString(){
		return "riffle";
	}

}
