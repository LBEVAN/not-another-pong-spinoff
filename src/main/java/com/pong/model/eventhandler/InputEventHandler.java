package com.pong.model.eventhandler;

import com.pong.controller.input.Direction;

/**
 * @author LBEVAN
 */
public interface InputEventHandler {

    void onMoveAction(int deltaY, Direction direction);
}
