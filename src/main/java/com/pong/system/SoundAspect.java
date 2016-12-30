package com.pong.system;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;

/**
 * An aspect that covers executing sound commands around method invocation.
 *
 * @author LBEVAN
 */
@Aspect
public class SoundAspect {

    @Around("execution(@com.pong.system.Sound * *(..)) && @annotation(soundAnnotation)")
    public Object playSound(ProceedingJoinPoint joinPoint, Sound soundAnnotation) throws Throwable {
        // execute the method and get the result
        Object result = joinPoint.proceed();

        final SoundCommand soundCommand = soundAnnotation.soundCommand();
        final String soundKey = soundAnnotation.soundKey();
        final SoundManager smInstance = SoundManager.getInstance();

        switch (soundCommand) {
            case PLAY_MUSIC:
                smInstance.playMusic(soundKey);
                break;
            case STOP_MUSIC:
                smInstance.stopMusic();
                break;
            case PLAY_SOUND:
                smInstance.playSound(soundKey);
                break;
            case PLAY_SOUND_OVER_MUSIC:
                smInstance.playSoundOverMusic(soundKey);
                break;
        }

        return result;
    }
}
