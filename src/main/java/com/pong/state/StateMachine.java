package com.pong.state;

/**
 * Interface defining behaviour for state machines.
 *
 * @author LBEVAN
 */
public interface StateMachine<T, S extends State<T>> {

    /**
     * Perform an update for an object (T) and state (S).
     */
    void update();

    /**
     * Change the current active state.
     *
     * @param state
     */
    void changeState(S state);

    /**
     * Set the inital state of the state machine.
     *
     * @param state
     */
    void setInitialState(S state);
}
