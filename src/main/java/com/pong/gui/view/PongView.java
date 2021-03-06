package com.pong.gui.view;

import com.pong.gui.view.pongview.GameUIPanel;
import com.pong.model.PongModel;
import com.pong.model.entity.Ball;
import com.pong.model.entity.player.Player;
import com.pong.model.entity.player.PlayerId;
import com.pong.model.environment.EnvironmentBall;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;

/**
 * The PongView is the view component of the game.
 * It handles the repainting every tick.
 *
 * @author LBEVAN
 */
public class PongView extends View {

    // region data
    private PongModel model;

    private GameUIPanel gameUIPanel;
    // endregion

    // region init
    /**
     * Constructor.
     *
     * @param model
     * @param width
     * @param height
     */
    public PongView(final PongModel model, int width, int height) {
        super(width, height);
        this.model = model;

        setOpaque(true);
        enableEvents(AWTEvent.KEY_EVENT_MASK);
        requestFocus();
        setFocusable(true);

        gameUIPanel = new GameUIPanel(width, height);
        add(gameUIPanel);
    }
    // endregion

    // region public API
    /**
     * {@inheritDoc}
     */
    @Override
    public void paintComponent(Graphics graphics) {
        super.paintComponent(graphics);

        Graphics2D graphics2D = ((Graphics2D) graphics);

        paintArena(graphics);
        paintBall(graphics2D);
        paintPlayer(graphics2D, PlayerId.ONE);
        paintPlayer(graphics2D, PlayerId.TWO);
        paintEnvironmentBall(graphics2D);

        gameUIPanel.update(graphics2D, model);
    }
    // endregion

    // region private API
    /**
     * Paint the arena bounds and its details (e.g. center line).
     *
     * @param graphics
     */
    private void paintArena(Graphics graphics) {
        // draw the background
        graphics.drawImage(model.getEnvironment().getImage(), 0, 0, null);

        // paint the center line
        graphics.setColor(Color.WHITE);
        graphics.drawLine(width / 2, 0, width / 2, height);
    }

    /**
     * Paint the Ball entity.
     *
     * @param graphics
     */
    private void paintBall(Graphics2D graphics) {
        final Ball ball = model.getBall();

        graphics.fill(new Ellipse2D.Double(ball.getX(), ball.getY(), ball.getWidth(), ball.getHeight()));
    }

    /**
     * Paint the Player with the given Id.
     *
     * @param graphics
     */
    private void paintPlayer(Graphics2D graphics, PlayerId playerId) {
        final Player player = model.getPlayerById(playerId);

//        AffineTransform transform = new AffineTransform();
//        transform.translate(player.getX(), player.getY());
//
//        // the scaling below is a fix for modified height. When using AffineTransform, needed for double precision,
//        // we need to scale as there is no other way to affect the height of the image
//        if(player.getHeight() > player.getImage().getHeight()) {
//            transform.scale(1, 2);
//        }
//
//        graphics.drawImage(player.getImage(), transform, null);

        graphics.setColor(player.getColour());
        graphics.fill(new Rectangle2D.Double(player.getX(), player.getY(), player.getWidth(), player.getHeight()));
    }

    /**
     * Paint the environmental ball.
     *
     * @param graphics
     */
    private void paintEnvironmentBall(Graphics2D graphics) {
        final EnvironmentBall environmentBall = model.getEnvironmentBall();
        if(environmentBall != null) {
            final BufferedImage image = environmentBall.getImage();
            AffineTransform transform = new AffineTransform();
            transform.translate(environmentBall.getX(), environmentBall.getY());
            graphics.drawImage(image, transform, null);
        }
    }
    // endregion
}