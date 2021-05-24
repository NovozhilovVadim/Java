package ru.geekbrains;

public class JoggingTrack extends Obstacle{
    private int distance;

    public JoggingTrack(int distance) {
        this.distance = distance;
    }

    public int getDistance() {
        return distance;
    }

    public void setDistance(int distance) {
        this.distance = distance;
    }

    @Override
    public String toString() {
        return "JoggingTrack : " +
                "distance = " +
                distance;
    }
}
