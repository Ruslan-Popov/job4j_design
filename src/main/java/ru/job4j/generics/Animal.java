package ru.job4j.generics;

import java.util.Objects;

public class Animal {
    private int age;
    private String type;
    private boolean canSwim;

    public Animal(int age, String type, boolean canSwim) {
        this.age = age;
        this.type = type;
        this.canSwim = canSwim;
    }

    public void setAge(int a) {
        this.age = a;
    }

    public void setType(String t) {
        this.type = t;
    }

    public void setCanSwim(boolean c) {
        this.canSwim = c;
    }

    public int getAge() {
        return age;
    }

    public String getType() {
        return type;
    }

    public boolean isCanSwim() {
        return canSwim;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Animal animal = (Animal) o;
        return age == animal.age
                && canSwim == animal.canSwim
                && type.equals(animal.type);
    }

    @Override
    public int hashCode() {
        return Objects.hash(age, type, canSwim);
    }

    @Override
    public String toString() {
        return "Animal {"
                + "age = " + age
                + ", type = '" + type + "'"
                + ", canSwim = " + canSwim
                + "}";
    }
}
