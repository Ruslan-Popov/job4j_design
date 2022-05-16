package ru.job4j.io.serialization.json;

import javax.xml.bind.annotation.*;
import java.util.Arrays;

@XmlRootElement(name = "person")
@XmlAccessorType(XmlAccessType.FIELD)
public class Command {
    @XmlAttribute
    private boolean wasChampion;
    @XmlAttribute
    private int budget;
    @XmlAttribute
    private String name;
    private Coach coach;
    @XmlElementWrapper
    @XmlElement(name = "player")
    private String[] players;

    public boolean isWasChampion() {
        return wasChampion;
    }

    public int getBudget() {
        return budget;
    }

    public String getName() {
        return name;
    }

    public Coach getCoach() {
        return coach;
    }

    public String[] getPlayers() {
        return players;
    }

    public Command() { }

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
