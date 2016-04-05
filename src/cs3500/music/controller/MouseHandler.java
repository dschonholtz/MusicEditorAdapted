package cs3500.music.controller;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

/**
 * Created by Ari on 4/4/2016.
 */
public class MouseHandler implements MouseListener {
    Runnable leftClick;
    Runnable middleClick;
    Runnable rightClick;

    Runnable leftPress;
    Runnable middlePress;
    Runnable rightPress;

    Runnable leftRelease;
    Runnable middleRelease;
    Runnable rightRelease;
    private int BUTTON1;

    public MouseHandler() {
        Runnable r = new Runnable() {
            @Override
            public void run() {

            }
        };

        leftClick = r;
        middleClick = r;
        rightClick = r;
        leftPress = r;
        middlePress = r;
        rightPress = r;
        leftRelease = r;
        middleRelease = r;
        rightRelease = r;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        switch (e.getButton()) { //TODO actually add functionality
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
        switch (e.getButton()) { //TODO actually add functionality
            case MouseEvent.:
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
        switch (e.getButton()) { //TODO actually add functionality
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
        //todo do we actually need this for anything?
    }

    @Override
    public void mouseExited(MouseEvent e) {
        //todo do we actually need this for anything?
    }

    public void setClickEvent(int button, Runnable r) {
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

    public void setPressEvent(int button, Runnable r) {
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

    public void setReleaseEvent(int button, Runnable r) {
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
}
