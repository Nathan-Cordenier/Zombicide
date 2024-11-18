package zombicide.util.listchooser;

import java.util.List;

public interface ListChooser<T> {

    T choose(List<? extends T> list);
}
