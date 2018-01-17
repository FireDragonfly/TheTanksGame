package com.hvitalii.thetanksgame.MyOwnUi;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Array;

public class MORadioButtonGroup {

    private Array<MOImgRadioButton> buttons;
    private MOImgRadioButton currentActive;
    private boolean changed;

    public MORadioButtonGroup() {
        buttons = new Array<MOImgRadioButton>();
        changed = false;
    }

    public MORadioButtonGroup(int size) {
        buttons = new Array<MOImgRadioButton>(size);
        changed = false;
    }

    public void add(MOImgRadioButton button) {
        if (buttons.indexOf(button, true) == -1) {
            button.deactivate();
            buttons.add(button);
        }
    }

    public void remove(MOImgRadioButton button) {
        buttons.removeValue(button, true);
    }

    public void activate(MOImgRadioButton button) {
        int index = buttons.indexOf(button, true);
        if (index != -1) {
            if (currentActive != button) {
                if (currentActive != null) {
                    currentActive.deactivate();
                }
                currentActive = button;
            } else {
                button.deactivate();
            }
        }
    }

    public void deactivate() {
        if (currentActive != null) {
            currentActive.deactivate();
        }
    }

    public void deactivate(MOImgRadioButton button) {
        if (currentActive == button) {
            currentActive = null;
        }
    }

    public void update() {
        int id = getActivatedId();
        for (int i = 0; i < buttons.size; i++) {
            buttons.get(i).update();
        }
        changed = id != getActivatedId();
    }

    public void draw(SpriteBatch batch) {
        for (int i = 0; i < buttons.size; i++) {
            buttons.get(i).draw(batch);
        }
    }

    public int getActivatedId() {
        if (currentActive != null) {
            return currentActive.getId();
        }
        return -1;
    }

    public MOImgRadioButton getCurrentActive() {
        return currentActive;
    }

    public Array<MOImgRadioButton> getButtons() {
        return buttons;
    }

    public boolean isChanged() {
        return changed;
    }

}
