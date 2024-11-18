package zombicide.util;

/**
 * Enumeration representing ANSI color codes for console output.
 */
public enum Color {
    RESET("\u001B[0m"),
    RED("\u001B[31m"),
    BLACK_BOLD("\u001B[1;30m"),
    BLUE_BOLD("\u001B[1;34m"),
    RED_BOLD_BRIGHT("\u001B[1;91m"),
    GREEN_BOLD_BRIGHT("\u001B[1;92m"),
    CYAN_BOLD_BRIGHT("\u001B[1;96m"),
    GREEN_BG_BRIGHT("\u001B[102m"),
    PURPLE_BG_BRIGHT("\u001B[105m");

    private final String code;

    /**
     * Constructs a Color with the given ANSI escape code.
     *
     * @param code The ANSI escape code for the color.
     */
    Color(String code) {
        this.code = code;
    }

    /**
     * Gets the ANSI escape code for the color.
     *
     * @return The ANSI escape code.
     */
    public String getCode() {
        return code;
    }
}
