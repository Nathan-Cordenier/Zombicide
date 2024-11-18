package zombicide.action.survivor.special;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class FighterTest {
    @Test
    void testThrowOneDie() {
        int dieValue = new Fighter().throwOneDie();
        assertTrue(dieValue >= 2 && dieValue <= 6);
    }
}
