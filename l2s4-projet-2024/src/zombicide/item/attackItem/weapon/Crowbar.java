package zombicide.item.attackItem.weapon;

import zombicide.item.attackItem.AttackItem;

public class Crowbar extends AttackItem implements Weapon {
	private static final int NB_DICE_THROWS = 1;
	private static final int DICE_THRESHOLD = 4;
	private static final int DAMAGE = 1;
	private static final int MIN_HITTING_RANGE = 0;
	private static final int MAX_HITTING_RANGE = 0;
	
	public Crowbar() {
		super(NB_DICE_THROWS, DICE_THRESHOLD, DAMAGE, MIN_HITTING_RANGE, MAX_HITTING_RANGE);
		this.canOpen = true;
	}

	public String toString(){
		return "crowbar";
	}

}
