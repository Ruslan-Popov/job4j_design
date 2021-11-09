package ru.job4j.generic;

import java.util.Map;

public class RoleStore implements Store<Role> {
    private final Store<Role> store = new MemStore<>();

    @Override
    public void add(Role role) {
        store.add(role);
    }

    @Override
    public boolean replace(String id, Role role) {
        return store.replace(id, role);
    }

    @Override
    public boolean delete(String id) {
        return store.delete(id);
    }

    @Override
    public Role findById(String id) {
        return store.findById(id);
    }
/*    public static void main(String[] args) {
        RoleStore rstore = new RoleStore();
        rstore.add(new Role("11", "User", 1));
        rstore.add(new Role("22", "Admin", 10));
        rstore.replace("11", new Role("111", "Semen", 31));
        rstore.delete("11");
        System.out.println(rstore.findById("22"));
    }*/
}
