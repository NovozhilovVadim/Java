package ru.geekbrains;

public class Spormens implements Ability{
    private String name;
    private int maxRunDistance;
    private int maxJumpHeight;


    public Spormens(String name, int maxRunDistance, int maxJumpHeight) {
        this.name = name;
        this.maxRunDistance = maxRunDistance;
        this.maxJumpHeight = maxJumpHeight;
    }

    public Spormens(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getMaxRunDistance() {
        return maxRunDistance;
    }

    public void setMaxRunDistance(int maxRunDistance) {
        this.maxRunDistance = maxRunDistance;
    }

    public int getMaxJumpHeight() {
        return maxJumpHeight;
    }

    public void setMaxJumpHeight(int maxJumpHeight) {
        this.maxJumpHeight = maxJumpHeight;
    }


    @Override
    public void run(int distance){
        if (distance <= getMaxRunDistance()){
            System.out.println(name + " " + " успешно справился с кросс");
        } else {
            System.out.println(name + " " + " не смог преодолеть кросс");

        }
    }

    @Override
    public void jump(int height){
        if (height <= getMaxJumpHeight()){
            System.out.println(name + " " + " успешно справился с препятствием");
        } else {
            System.out.println(name + " " + " не смог преодолеть препятствие");

        }
    }



    @Override
    public String toString() {
        return "Spormens: " +
                " name = '" + name + '\'' +
                ", maxRunDistance = " + maxRunDistance +
                ", maxJumpHeight = " + maxJumpHeight;
    }

}
