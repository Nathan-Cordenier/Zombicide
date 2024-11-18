package zombicide.util.listchooser;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

public class RandomListChooserTest {

    @Test
    public void testChoose() {
        RandomListChooser<Integer> chooser = new RandomListChooser<>();
        List<Integer> list = new ArrayList<>();

        assertNull(chooser.choose(list));

        list.add(1);
        assertNotNull(chooser.choose(list));
    }
}
