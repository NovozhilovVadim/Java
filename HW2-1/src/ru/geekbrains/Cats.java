package ru.geekbrains;

public class Cats extends Spormens {
    private int maxRunDistance = 150;
    private int maxJumpHeight = 5;


    public Cats(String name) {
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
        return "Cat: " +
                " name = " + getName() +
                ", maxRunDistance = " + maxRunDistance +
                ", maxJumpHeight = " + maxJumpHeight;
    }
}
