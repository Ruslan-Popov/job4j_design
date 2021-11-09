package ru.job4j.generic;

public class UserStore implements Store<User> {
    private final Store<User> store = new MemStore<>();

    @Override
    public void add(User model) {
        store.add(model);
    }

    @Override
    public boolean replace(String id, User model) {
        return store.replace(id, model);
    }

    @Override
    public boolean delete(String id) {
        return store.delete(id);
    }

    @Override
    public User findById(String id) {
        return store.findById(id);
    }
/*    public static void main(String[] args) {
        UserStore uuser = new UserStore();
        uuser.add(new User("11", "Andrey", 23));
        uuser.add(new User("22", "Boris", 35));
        uuser.replace("11", new User("111", "Semen", 31));
        uuser.delete("11");
        System.out.println(uuser.findById("22"));
    }*/
}