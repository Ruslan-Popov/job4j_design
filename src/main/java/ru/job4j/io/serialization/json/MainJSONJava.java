package ru.job4j.io.serialization.json;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class MainJSONJava {
    public static void main(String[] args) {
        JSONObject jsonBudget = new JSONObject("{\"budget\":12}");
        JSONObject jsonName = new JSONObject("{\"name\":\"Spartak\"}");
        JSONObject jsonCoach = new JSONObject("{\"surname\":\"Karpin\"}");

        List<String> players = new ArrayList<>();
        players.add("Boyarintsev");
        players.add("Velliton");
        JSONArray jsonPlayers = new JSONArray(players);

        final Command loko = new Command(true, 10, "Lokomotiv", new Coach("Semin"),
                new String[]{"Bilyaletdinov", "Sychev"});

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("wasChampion", loko.isWasChampion());
        jsonObject.put("budget", jsonBudget.get("budget"));
        jsonObject.put("name", jsonName.get("name"));
        jsonObject.put("coach", jsonCoach);
        jsonObject.put("players", jsonPlayers);

        System.out.println(jsonObject.toString());
        System.out.println(new JSONObject(loko).toString());
    }
}
