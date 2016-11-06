package com.pong.state;

import com.pong.model.entity.Computer;

/**
 * Enum that defines states that the computer entity can be in during a game.
 *
 * @author LBEVAN
 */
public enum ComputerState implements State<Computer> {

    MOVE() {
        public void update(Computer computer) {
            if(computer.isBallInSightRange()) {
                computer.moveAndTrackBall();
            } else {
                computer.stateMachine.changeState(IDLE_MOVE);
            }
        }
    },

    IDLE_MOVE() {
        public void update(Computer computer) {
            if(computer.isBallInSightRange()) {
                computer.stateMachine.changeState(MOVE);
            } else {
                computer.idleMove();
            }
        }
    };

}
