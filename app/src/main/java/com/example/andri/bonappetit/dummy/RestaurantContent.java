package com.example.andri.bonappetit.dummy;

import com.example.andri.bonappetit.R;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
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
        return new RestaurantItem(String.valueOf(position), "Repas " + position,
                "Découvez de nouvelles saveurs grâce à ce savoureux repas et tout et tout, wech ma gueule dlmkdqlskdlqskdmlqksdlkqsdmlkqsmldkqsmldkqsmldkmqlsd",
                "Quelque part " + position, "3 janvier à 12h", (float) Math.random() * 5, (int) Math.floor(Math.random() * 5), 5, (float) Math.random() * 9 + 1);
    }

    public static void sortByRating() {
        Collections.sort(ITEMS, new Comparator<RestaurantItem>() {
            @Override
            public int compare(RestaurantItem lhs, RestaurantItem rhs) {
                if (lhs.rating > rhs.rating)
                    return 1;
                else
                    return -1;
            }
        });
        //desc order
        Collections.reverse(ITEMS);
    }

    public static void sortByTitle() {
        Collections.sort(ITEMS, new Comparator<RestaurantItem>() {
            @Override
            public int compare(RestaurantItem lhs, RestaurantItem rhs) {
                return lhs.title.compareTo(rhs.title);
            }
        });
    }

    public static void sortByPrice() {
        Collections.sort(ITEMS, new Comparator<RestaurantItem>() {
            @Override
            public int compare(RestaurantItem lhs, RestaurantItem rhs) {
                if (lhs.price > rhs.price)
                    return 1;
                else
                    return -1;
            }
        });
    }


    /**
     * A dummy item representing a piece of content.
     */
    public static class RestaurantItem {
        public final String id;
        public int idImg;
        public final String description;
        public final String snippet;
        public final String location;
        public final String date;
        public final String title;
        public final float rating;
        public final float price;
        public final int seatsTaken;
        public final int totalSeats;

        public RestaurantItem(String id, String title, String description, String location, String date, float rating, int seatsTaken, int totalSeats, float price) {
            this.id = id;
            this.title = title;
            this.description = description;
            this.location = location;
            this.date = date;
            this.snippet = description.substring(0);
            this.rating = rating;
            this.seatsTaken = seatsTaken;
            this.totalSeats = totalSeats;
            this.price = price;
            switch ((int) Math.floor(Math.random()*2)) {
                case 0:
                    idImg = R.drawable.tartiflette;
                    break;
                case 1:
                    idImg = R.drawable.repas2;
                    break;
            }
        }

        @Override
        public String toString() {
            return title;
        }
    }
}
