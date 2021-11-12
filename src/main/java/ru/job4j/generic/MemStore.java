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
        return mem.replace(id, mem.get(id), model);
    }

    @Override
    public boolean delete(String id) {
        return mem.remove(id, mem.get(id));
    }

    @Override
    public T findById(String id) {
        return mem.get(id);
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
