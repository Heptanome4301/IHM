package com.example.andri.bonappetit.dummy;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Helper class for providing sample content for user interfaces created by
 * Android template wizards.
 * <p/>
 * TODO: Replace all uses of this class before publishing your app.
 */
public class RestaurantContent {

    /**
     * An array of sample (dummy) items.
     */
    public static final List<RestaurantItem> ITEMS = new ArrayList<RestaurantItem>();

    /**
     * A map of sample (dummy) items, by ID.
     */
    public static final Map<String, RestaurantItem> ITEM_MAP = new HashMap<String, RestaurantItem>();

    private static final int COUNT = 25;

    static {
        // Add some sample items.
        for (int i = 1; i <= COUNT; i++) {
            addItem(createDummyItem(i));
        }
    }

    private static void addItem(RestaurantItem item) {
        ITEMS.add(item);
        ITEM_MAP.put(item.id, item);
    }

    private static RestaurantItem createDummyItem(int position) {
        return new RestaurantItem(String.valueOf(position), "Item " + position, makeDetails(position),"Repas " + position,
                "Découvez de nouvelles saveurs grâce à ce savoureux repas et tout et tout, wech ma gueule dlmkdqlskdlqskdmlqksdlkqsdmlkqsmldkqsmldkqsmldkmqlsd",
                "Quelque part " + position,"3 janvier à 12h",position%5);
    }

    private static String makeDetails(int position) {
        StringBuilder builder = new StringBuilder();
        builder.append("Details about Item: ").append(position);
        for (int i = 0; i < position; i++) {
            builder.append("\nMore details information here.");
        }
        return builder.toString();
    }

    /**
     * A dummy item representing a piece of content.
     */
    public static class RestaurantItem {
        public final String id;
        public final String content;
        public final String details;
        
        public final String description;
        public final String snippet;
        public final String location;
        public final String date;
        public final String title;
        public final float rating;

        public RestaurantItem(String id, String content, String details, String title,String description, String location, String date, float rating) {
            this.id = id;
            this.content = content;
            this.details = details;
            this.title=title;
            this.description=description;
            this.location=location;
            this.date=date;
            this.snippet=description.substring(0);
            this.rating=rating;
        }

        @Override
        public String toString() {
            return content;
        }
    }
}
