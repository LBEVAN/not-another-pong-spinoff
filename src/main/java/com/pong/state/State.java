package com.pong.state;

/**
 * Interface defining behaviour individual states must have.
 *
 * @author LBEVAN
 */
public interface State<T> {

    /**
     * Perform an update.
     *
     * @param t
     */
    void update(T t);
}
