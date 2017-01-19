//package com.pong.model.entity;
//
//import com.pong.controller.input.Direction;
//import com.pong.model.PongModel;
//import com.pong.system.resource.ResourceManager;
//
//import java.awt.image.BufferedImage;
//
///**
// * The Computer class represents the computer entity in the game.
// *
// * @author LBEVAN
// */
//public class Computer extends Entity {
//
//    private final PongModel pongModel;
//
//    private final double normalMoveSpeed;
//    private final double idleMoveSpeed = 1;
//    private final double sightRange;
//    private Direction idleMoveDirection;
//    private long idleMoveTime = 0;
//
//    /**
//     * Player constructor.
//     *
//     * @param x
//     * @param y
//     * @param width
//     * @param height
//     * @param pongModel
//     */
//    public Computer(int x, int y, int width, int height, final PongModel pongModel) {
//        super(x, y, width, height);
//
//        this.pongModel = pongModel;
//
//        normalMoveSpeed = (int) pongModel.getGameOptions().getDifficulty().getSpeed();
//        sightRange = pongModel.getGameOptions().getDifficulty().getSightRange();
//    }
//
//    /**
//     * {@inheritDoc}
//     */
//    public void update() {
//        modifierSystem.update(this);
//    }
//
//    /**
//     * {@inheritDoc}
//     */
//    public double getSpeed() {
//        return baseSpeed + modifiedSpeed;
//    }
//
//    /**
//     * {@inheritDoc}
//     */
//    @Override
//    public BufferedImage getImage() {
//        return ResourceManager.getInstance().getGraphic("Paddle");
//    }
//}
