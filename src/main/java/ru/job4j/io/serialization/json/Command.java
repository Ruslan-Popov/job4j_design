package ru.job4j.io.serialization.json;

import java.util.Arrays;

public class Command {
    private final boolean wasChampion;
    private final int budget;
    private final String name;
    private final Coach coach;
    private final String[] team;

    public Command(boolean wasChampion, int budget, String name, Coach coach, String[] team) {
        this.wasChampion = wasChampion;
        this.budget = budget;
        this.name = name;
        this.coach = coach;
        this.team = team;
    }

    @Override
    public String toString() {
        return "Command{"
                + "wasChampion=" + wasChampion
                + ", budget=" + budget
                + ", name='" + name + '\''
                + ", coach=" + coach
                + ", team=" + Arrays.toString(team)
                + '}';
    }
}
