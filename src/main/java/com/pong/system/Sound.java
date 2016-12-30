package com.pong.system;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * Runtime annotation for executing sound commands around a method invocation.
 * This is annotated on methods and used in the SoundAspect class.
 *
 * @author LBEVAN
 */
@Retention(RetentionPolicy.RUNTIME)
public @interface Sound {
    String soundKey();
    SoundCommand soundCommand();
}
