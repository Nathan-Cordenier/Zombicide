package zombicide.action;

import zombicide.actor.Actor;

public interface Action<T extends Actor> {
    void doSomething(T actor);
}
