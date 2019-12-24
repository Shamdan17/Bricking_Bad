package ui.drawables.effects;

import ui.drawables.Drawable;

public class CachedEffect {

    private final int LIFE_LENGTH = 15;
    private int life;
    private Drawable drawable;

    public CachedEffect(Drawable drawable) {
        this.drawable = drawable;
        this.life = LIFE_LENGTH;
    }

    public void hit() {
        life--;
    }

    public boolean isDead() {
        return life == 0;
    }

    public Drawable getDrawable() {
        return drawable;
    }

}
