package gg.archipelago.APClient.parts;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.*;

public class DataPackage implements Serializable {

    @SerializedName("games")
    HashMap<String, Game> games = new HashMap<>();

    @SerializedName("version")
    int verison = -1;

    HashMap<Integer, String> itemIdToName = new HashMap<>();

    HashMap<Integer, String> locationIdToName = new HashMap<>();

    public String uuid = UUID.randomUUID().toString();

    public String getItem(int itemID) {
        for (Map.Entry<String, Game> game : games.entrySet()) {
            for (Map.Entry<String, Integer> item : game.getValue().itemNameToId.entrySet()) {
                if(item.getValue() == itemID)
                    return item.getKey();
            }
        }
        return String.format("Unknown Item [%d]", itemID);
    }

    public String getLocation(int locationID) {
        for (Map.Entry<String, Game> game : games.entrySet()) {
            for (Map.Entry<String, Integer> item : game.getValue().locationNameToId.entrySet()) {
                if(item.getValue() == locationID)
                    return item.getKey();
            }
        }
        return String.format("Unknown Location [%d]", locationID);
    }

    public Map<String, Integer> getVersions() {
        HashMap<String, Integer> versions = new HashMap<>();
        games.forEach((key, value) -> versions.put(key, value.version));
        return versions;
    }

    public Set<String> getGames() {
        return games.keySet();
    }

    public HashMap<Integer, String> getItems() {
        if(itemIdToName.isEmpty()) {
            for (Map.Entry<String, Game> gameEntry : games.entrySet()) {
                for (Map.Entry<String, Integer> items : gameEntry.getValue().itemNameToId.entrySet()) {
                    itemIdToName.put(items.getValue(), items.getKey());
                }
            }
        }
        return itemIdToName;
    }

    public HashMap<Integer, String> getLocations() {
        if(locationIdToName.isEmpty()) {
            for (Map.Entry<String, Game> gameEntry : games.entrySet()) {
                for (Map.Entry<String, Integer> items : gameEntry.getValue().locationNameToId.entrySet()) {
                    itemIdToName.put(items.getValue(), items.getKey());
                }
            }
        }
        return itemIdToName;
    }

    public int getVerison() {
        return verison;
    }

    public String getUUID() {
        return uuid;
    }
}
