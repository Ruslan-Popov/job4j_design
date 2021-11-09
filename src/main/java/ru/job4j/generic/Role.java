package ru.job4j.generic;

import java.util.Objects;

public class Role extends Base {
    private String title;
    private int rightsLevel;

    public Role(String id, String title, int rightsLevel) {
        super(id);
        this.title = title;
        this.rightsLevel = rightsLevel;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getRightsLevel() {
        return rightsLevel;
    }

    public void setRightsLevel(int rightsLevel) {
        this.rightsLevel = rightsLevel;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Role role = (Role) o;
        return Objects.equals(title, role.title)
                && rightsLevel == role.rightsLevel;
    }

    @Override
    public int hashCode() {
        return Objects.hash(title, rightsLevel);
    }

    @Override
    public String toString() {
        return "Role {"
                + "title = '" + title + '\''
                + ", rightsLevel = " + rightsLevel
                + "}";
    }
}
