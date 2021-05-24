package ru.geekbrains;

public class Robots extends Spormens {
    private int maxRunDistance = 1000;
    private int maxJumpHeight = 10;

    public Robots(String name) {
        super(name);
    }

    @Override
    public int getMaxRunDistance() {
        return maxRunDistance;
    }

    @Override
    public void setMaxRunDistance(int maxRunDistance) {
        this.maxRunDistance = maxRunDistance;
    }

    @Override
    public int getMaxJumpHeight() {
        return maxJumpHeight;
    }

    @Override
    public void setMaxJumpHeight(int maxJumpHeight) {
        this.maxJumpHeight = maxJumpHeight;
    }

    @Override
    public String toString() {
        return "Robot: " +
                " name = " + getName() +
                ", maxRunDistance = " + maxRunDistance +
                ", maxJumpHeight = " + maxJumpHeight;
    }
}
