package com.pong.state;

import com.pong.model.entity.Entity;

/**
 * StateMachine implementation for entity AI.
 *
 * @author LBEVAN
 */
public class AIStateMachine<T extends Entity, S extends State<T>> implements StateMachine {

    private State state;
    private final Entity entity;

    /**
     * Constructor.
     *
     * @param entity
     */
    public AIStateMachine(final Entity entity) {
        this.entity = entity;
    }

    /**
     * {@inheritDoc}
     */
    public void update() {
        state.update(entity);
    }

    /**
     * {@inheritDoc}
     */
    public void changeState(State state) {
        this.state = state;
    }

    /**
     * {@inheritDoc}
     */
    public void setInitialState(State state) {
        this.state = state;
    }
}
