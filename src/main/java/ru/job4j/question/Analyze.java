package ru.job4j.question;

import java.util.*;

public class Analyze {
    public static Info diff(Set<User> previous, Set<User> current) {
        Info info = new Info(0, 0, 0);

        for (User p : previous) {
            info.setDeleted(info.getDeleted() + 1);
            for (User c : current) {
                if (c.getId() == p.getId()) {
                    info.setDeleted(info.getDeleted() - 1);
                }

                if (c.getId() == p.getId() && !c.getName().equals(p.getName())) {
                    info.setChanged(info.getChanged() + 1);
                }
            }
        }

        for (User c : current) {
            info.setAdded(info.getAdded() + 1);
            for (User p : previous) {
                if (c.getId() == (p.getId())) {
                    info.setAdded(info.getAdded() - 1);
                }
            }
        }
        return info;
    }
}