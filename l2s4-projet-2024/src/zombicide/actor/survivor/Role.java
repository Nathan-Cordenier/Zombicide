package zombicide.actor.survivor;

import zombicide.action.Action;
import zombicide.action.survivor.special.Fighter;
import zombicide.action.survivor.special.Healer;
import zombicide.action.survivor.special.Lucky;
import zombicide.action.survivor.special.Snooper;

public enum Role {
    FIGHTER(new Fighter()),
    HEALER(new Healer()),
    LUCKY(new Lucky()),
    SNOOPER(new Snooper());

    private final Action<Survivor> action;

    Role(Action<Survivor> action) {
        this.action = action;
    }

    public Action<Survivor> getAction() {
        return action;
    }
}
