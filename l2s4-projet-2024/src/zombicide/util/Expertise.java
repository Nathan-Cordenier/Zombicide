package zombicide.util;

/**
 * Enum representing different expertise stages.
 */
public enum Expertise {
    STAGE_THREE(3),
    STAGE_SEVEN(7),
    STAGE_ELEVEN(11);

    /**
     * The threshold value for this expertise stage.
     */
    private final int threshold;

    /**
     * Constructs an Expertise enum with the given threshold.
     *
     * @param threshold The threshold value for the expertise stage.
     */
    Expertise(int threshold) {
        this.threshold = threshold;
    }

    /**
     * Gets the threshold value for this expertise stage.
     *
     * @return The threshold value.
     */
    public int getThreshold() {
        return threshold;
    }

    /**
     * Retrieves the Expertise stage based on the given value.
     *
     * @param value The value to check against the thresholds.
     * @return The Expertise stage corresponding to the value, or null if none matches.
     */
    public static Expertise getStage(int value) {
        for (Expertise stage : Expertise.values()) {
            if (stage.getThreshold() == value) {
                return stage;
            }
        }
        return null;
    }
}
