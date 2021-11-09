package ru.job4j.generic;

import java.util.HashMap;
import java.util.Map;

public final class MemStore<T extends Base> implements Store<T> {

    private final Map<String, T> mem = new HashMap<>();

    @Override
    public void add(T model) {
        mem.put(model.getId(), model);
    }

    @Override
    public boolean replace(String id, T model) {
        for (String i : mem.keySet()) {
            if (id.equals(i)) {
                mem.put(id, model);
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean delete(String id) {
        for (String i : mem.keySet()) {
            if (id.equals(i)) {
                mem.remove(id);
                return true;
            }
        }
        return false;
    }

    @Override
    public T findById(String id) {
        for (String i : mem.keySet()) {
            if (id.equals(i)) {
                return mem.get(id);
            }
        }
        return null;
    }
/*    public static void main(String[] args) {
        MemStore<User> muser = new MemStore<>();
        muser.add(new User("11", "Andrey", 23));
        muser.add(new User("22", "Boris", 35));
        muser.replace("11", new User("111", "Semen", 31));
        muser.delete("11");
        System.out.println(muser.findById("22"));
    }*/
}
