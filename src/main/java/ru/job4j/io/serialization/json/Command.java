package ru.job4j.io.serialization.json;

import java.util.Arrays;

public class Command {
    private final boolean wasChampion;
    private final int budget;
    private final String name;
    private final Coach coach;
    private final String[] players;

    public Command(boolean wasChampion, int budget, String name, Coach coach, String[] players) {
        this.wasChampion = wasChampion;
        this.budget = budget;
        this.name = name;
        this.coach = coach;
        this.players = players;
    }

    @Override
    public String toString() {
        return "Command{"
                + "wasChampion=" + wasChampion
                + ", budget=" + budget
                + ", name='" + name + '\''
                + ", coach=" + coach
                + ", players=" + Arrays.toString(players)
                + '}';
    }
}
