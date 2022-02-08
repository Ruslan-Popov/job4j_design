package ru.job4j.question;

import java.util.*;

public class Analyze {
    public static Info diff(Set<User> previous, Set<User> current) {

        Info info = new Info(0, 0, 0);
        Map<Integer, String> prev = new HashMap<>();
        Map<Integer, String> curr = new HashMap<>();

        for (User p : previous) {
            prev.put(p.getId(), p.getName());
        }
        for (User c : current) {
            curr.put(c.getId(), c.getName());
        }
        for (Integer i : curr.keySet()) {
            if (!prev.containsKey(i)) {
                info.setAdded(info.getAdded() + 1);
            }
            if (prev.containsKey(i) && !prev.containsValue(curr.get(i))) {
                info.setChanged(info.getChanged() + 1);
            }
        }
        for (Integer i : prev.keySet()) {
            if (!curr.containsKey(i)) {
                info.setDeleted(info.getDeleted() + 1);
            }
        }
        return info;
    }
}