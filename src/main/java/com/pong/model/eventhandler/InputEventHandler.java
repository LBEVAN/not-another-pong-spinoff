package com.pong.model.eventhandler;

import com.pong.controller.input.Direction;
import com.pong.model.modifier.AbstractModifier;

/**
 * @author LBEVAN
 */
public interface InputEventHandler {

    void onMoveAction(int deltaY, Direction direction);

    void onConsumeModifierAction(AbstractModifier modifier);
}
