package ru.job4j.io.serialization.json;

public class Coach {
    private final String surname;

    public Coach(String surname) {
        this.surname = surname;
    }

    @Override
    public String toString() {
        return "Coach{"
                + "surname='" + surname + '\''
                + '}';
    }
}
