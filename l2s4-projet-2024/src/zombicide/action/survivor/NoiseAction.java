package zombicide.action.survivor;

import zombicide.action.Action;
import zombicide.actor.survivor.Survivor;
import zombicide.city.area.Area;

/**
 * An action representing a Survivor creating noise in their current area.
 * This action increases the noise level in the Survivor's current area.
 */
public class NoiseAction implements Action<Survivor> {

    private int noiseLevel;

    /**
     * Sets the noise level to be added when performing the noise action.
     *
     * @param noiseLevel The noise level to add.
     */
    public void setNoiseLevel(int noiseLevel) {
        this.noiseLevel = noiseLevel;
    }

    /**
     * Performs the action of creating noise in the Survivor's current area.
     * This action increases the noise level of the area.
     *
     * @param survivor The Survivor creating noise.
     */
    public void doSomething(Survivor survivor) {
        Area currentArea = survivor.getArea();
        if (currentArea != null) {
            currentArea.increaseNoiseLevel(this.noiseLevel);
        }
        System.out.println(survivor.getName()+" makes some noise !!!");
        survivor.removeActionPoint();
    }

    public String toString() {
        return "Noise action";
    }
}
