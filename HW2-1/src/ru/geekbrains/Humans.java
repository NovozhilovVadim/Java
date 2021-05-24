package ru.geekbrains;

public class Humans extends Spormens {
    private int maxRunDistance = 500;
    private int maxJumpHeight = 2;

    public Humans(String name, int maxRunDistance, int maxJumpHeight) {
        super(name, maxRunDistance, maxJumpHeight);

    }

    public Humans(String name) {
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
        return "Human: " +
                " name = " + getName() +
                ", maxRunDistance = " + maxRunDistance +
                ", maxJumpHeight = " + maxJumpHeight;
    }
}
