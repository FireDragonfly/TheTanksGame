package com.hvitalii.thetanksgame.MyOwn;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Array;

public class MORadioButtonGroup {

    private Array<MOImgRadioButton> buttons;
    private MOImgRadioButton currentActive;

    public MORadioButtonGroup() {
        buttons = new Array<MOImgRadioButton>();
    }

    public MORadioButtonGroup(int size) {
        buttons = new Array<MOImgRadioButton>(size);
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

    public void activated(MOImgRadioButton button) {
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

    public void deactivated(MOImgRadioButton button) {
        if (currentActive == button) {
            currentActive = null;
        }
    }

    public void update() {
        for (int i = 0; i < buttons.size; i++) {
            buttons.get(i).update();
        }
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

}
