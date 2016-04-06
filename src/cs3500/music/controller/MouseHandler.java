package cs3500.music.controller;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Objects;

/**
 * Takes care of mouse events for interacting with a JFrame
 */
public class MouseHandler implements MouseListener {
    private Runnable leftClick;
    private Runnable middleClick;
    private Runnable rightClick;
    private Runnable leftPress;
    private Runnable middlePress;
    private Runnable rightPress;
    private Runnable leftRelease;
    private Runnable middleRelease;
    private Runnable rightRelease;
    private Runnable mouseEnter;
    private Runnable mouseExit;

    /**
     * Default constructor sets all runnables to do nothing
     */
    public MouseHandler() {
        Runnable r = () -> {};

        leftClick = r;
        middleClick = r;
        rightClick = r;
        leftPress = r;
        middlePress = r;
        rightPress = r;
        leftRelease = r;
        middleRelease = r;
        rightRelease = r;
        mouseEnter = r;
        mouseExit = r;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        Objects.requireNonNull(e);
        switch (e.getButton()) {
            case MouseEvent.BUTTON1:
                this.leftClick.run();
                break;
            case MouseEvent.BUTTON2:
                this.middleClick.run();
                break;
            case MouseEvent.BUTTON3:
                this.rightClick.run();
                break;
            default:
                break;
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {
        Objects.requireNonNull(e);
        switch (e.getButton()) {
            case MouseEvent.BUTTON1:
                this.leftPress.run();
                break;
            case MouseEvent.BUTTON2:
                this.middlePress.run();
                break;
            case MouseEvent.BUTTON3:
                this.rightPress.run();
                break;
            default:
                break;
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        Objects.requireNonNull(e);
        switch (e.getButton()) {
            case MouseEvent.BUTTON1:
                this.leftRelease.run();
                break;
            case MouseEvent.BUTTON2:
                this.middleRelease.run();
                break;
            case MouseEvent.BUTTON3:
                this.rightRelease.run();
                break;
            default:
                break;
        }
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        Objects.requireNonNull(e);
        this.mouseEnter.run();
    }

    @Override
    public void mouseExited(MouseEvent e) {
        Objects.requireNonNull(e);
        this.mouseExit.run();
    }

    /**
     * @param button the button to set
     * @param r the runnable to set it to
     */
    public void setClickEvent(int button, Runnable r) {
        Objects.requireNonNull(r);
        switch (button) {
            case MouseEvent.BUTTON1:
                this.leftClick = r;
                break;
            case MouseEvent.BUTTON2:
                this.middleClick = r;
                break;
            case MouseEvent.BUTTON3:
                this.rightClick = r;
                break;
        }
    }

    /**
     * @param button the button to set
     * @param r the runnable to set the button to
     */
    public void setPressEvent(int button, Runnable r) {
        Objects.requireNonNull(r);
        switch (button) {
            case MouseEvent.BUTTON1:
                this.leftPress = r;
                break;
            case MouseEvent.BUTTON2:
                this.middlePress = r;
                break;
            case MouseEvent.BUTTON3:
                this.rightPress = r;
                break;
        }
    }

    /**
     * @param button the button to set
     * @param r the runnable to set the button to
     */
    public void setReleaseEvent(int button, Runnable r) {
        Objects.requireNonNull(r);
        switch (button) {
            case MouseEvent.BUTTON1:
                this.leftRelease = r;
                break;
            case MouseEvent.BUTTON2:
                this.middleRelease = r;
                break;
            case MouseEvent.BUTTON3:
                this.rightRelease = r;
                break;
        }
    }

    /**
     * @param r the runnable to happen on mouse enter
     */
    public void setMouseEnter(Runnable r) {
        Objects.requireNonNull(r);
        this.mouseEnter = r;
    }

    /**
     * @param r the runnable to happen on mouse exit
     */
    public void setMouseExit(Runnable r) {
        Objects.requireNonNull(r);
        this.mouseExit = r;
    }
}
