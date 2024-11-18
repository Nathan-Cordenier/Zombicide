package zombicide.item.attackItem.weapon;

import zombicide.item.attackItem.AttackItem;

public class Chainsaw extends AttackItem implements Weapon {
	private static final int NB_DICE_THROWS = 2;
	private static final int DICE_THRESHOLD = 5;
	private static final int DAMAGE = 3;
	private static final int MIN_HITTING_RANGE = 0;
	private static final int MAX_HITTING_RANGE = 0;
	
	public Chainsaw() {
		super(NB_DICE_THROWS, DICE_THRESHOLD, DAMAGE, MIN_HITTING_RANGE, MAX_HITTING_RANGE);
		this.canOpen = true;
		this.isNoisyUse = true;
	}

	public String toString(){
		return "chainsaw";
	}

}
