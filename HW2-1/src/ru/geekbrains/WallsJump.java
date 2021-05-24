package ru.geekbrains;

public class WallsJump extends Obstacle{
    private int height;

    public WallsJump(int height) {
        this.height = height;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    @Override
    public String toString() {
        return "WallsJump: " +
                "height = " +
                height;
    }
}


