package com.pong.model.entity.component;

import com.pong.model.entity.Entity;

/**
 * @author LBEVAN
 */
public interface InputComponent<T extends Entity> {

    void update(T entity);
}
