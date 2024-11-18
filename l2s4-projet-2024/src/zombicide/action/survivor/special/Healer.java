package zombicide.action.survivor.special;

import zombicide.action.survivor.HealAction;
import zombicide.actor.survivor.Survivor;

import java.util.List;
import java.util.Random;

public class Healer extends HealAction {
    public static final int NB_LIFE_POINTS = 1;
    private static final Random RANDOM = new Random();

    /**
     * Adds a specified number of life points to a randomly selected survivor from the given list.
     *
     * @param survivors The list of survivors to choose from.
     */
    private void addLifePoints(List<Survivor> survivors) {
        int index = RANDOM.nextInt(survivors.size());
        Survivor s = survivors.get(index);
        s.addLifePoints(NB_LIFE_POINTS);
        System.out.println(s.getName()+" won "+NB_LIFE_POINTS+" life point(s), he has now "+s.getLifePoints()+" life point(s)");
    }


    @Override
    public void doSomething(Survivor survivor) {
        System.out.println(survivor.getName()+" uses his healing talent");
        List<Survivor> survivors = survivor.getArea().getSurvivors();
        addLifePoints(survivors);
        survivor.removeActionPoint();
    }
}
