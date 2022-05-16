package ru.job4j.io.serialization.json;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "coach")
@XmlAccessorType(XmlAccessType.FIELD)
public class Coach {

    @XmlAttribute
    private String surname;

    public String getSurname() {
        return surname;
    }

    public Coach() { }

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
