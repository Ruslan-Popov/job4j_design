package ru.job4j.io.serialization.json;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class MyMain {
    public static void main(String[] args) {
        final Command command = new Command(true, 10, "Lokomotiv", new Coach("Semin"),
                new String[]{"Bilyaletdinov", "Sychev"});
        final Gson gson = new GsonBuilder().create();
        System.out.println(gson.toJson(command));

        final String commandJson =
                "{"
                        + "\"wasChampion\":true,"
                        + "\"budget\":10,"
                        + "\"name\":\"Lokomotiv\","
                        + "\"coach\":{\"surname\":\"Semin\"},"
                        + "\"team\":"
                        + "[\"Bilyaletdinov\",\"Sychev\"]"
                        + "}";
        final Command commandMod = gson.fromJson(commandJson, Command.class);
        System.out.println(commandMod);
    }
}
