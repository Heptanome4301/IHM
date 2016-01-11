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

    private static final int COUNT = 30;

    private static final String BLABLA = "Faites vibrer vos sens grâce à ce savoureux repas fait avec amour dans une chambre étudiante au charme atypique dans une ambiance intime." +
            " Au menu de ce repas : du manger. Malgré son titre peu original, ne vous y trompez pas, ce repas il est bien, si, si. Et maintenant, des trucs en latin (y'a un site qui fait ça, c'est cool)." +
            "\nIudicare omnibus posse et iudicarent sermo rebus de generis quod diligentiores experiendi constantes iudicium homines Ita (saepe autem rebus quaedam et iudicarent Sunt amicitias quaedam quod Et esse nec oves cuius iudicarent neglegentis (saepe quot firmi quisque (saepe diligentiores mdr le pire truc iudicarent omnibus potestatem in est dicere diligentiores cuius dicere autem adhibere sane qui et igitur sermo Et diligentiores quot autem iudicare essent posse ad capras est amicitia (saepe ipsa est non iudicare quaedam esse et et et in est stabiles firmi redeo experiendum quasi quibus haberet expertum nec quot Et adhibere amicis esse Sunt eligendis et praecurrit firmi enim rebus qui.";

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
        int day = (int)Math.floor(Math.random()*30+1);
        float rating= (float) Math.random() * 5;
        int seatsTaken = 1+(int) Math.floor(Math.random()*4);
        int totalSeats = 5;
        int distance = (int)Math.floor(Math.random()*400+100);
        float price = (float) Math.random() * 9 + 1;
        String user = "Utilisateur "+position;
        String title = "Repas "+position;
        String location = "Localisation "+position;
        String date = day+" février à 12h";
        String description =BLABLA;

        int idImg=-1;
        switch ((int) Math.floor(Math.random()*4)) {
            case 0:
                idImg = R.drawable.tartiflette;
                break;
            case 1:
                idImg = R.drawable.repas2;
                break;
            case 2:
                idImg = R.drawable.repas3;
                break;
            case 3:
                idImg = R.drawable.repas4;
                break;
        }

        return new RestaurantItem(String.valueOf(position),title,description,user,location,date,day,rating,seatsTaken,totalSeats,price,distance,idImg);

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

    public static void sortByDate() {
        Collections.sort(ITEMS, new Comparator<RestaurantItem>() {
            @Override
            public int compare(RestaurantItem lhs, RestaurantItem rhs) {
                if (lhs.day > rhs.day)
                    return 1;
                else
                    return -1;
            }
        });
    }

    public static void sortByDistance() {
        Collections.sort(ITEMS, new Comparator<RestaurantItem>() {
            @Override
            public int compare(RestaurantItem lhs, RestaurantItem rhs) {
                if (lhs.distance > rhs.distance)
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
        public final int day;
        public final String description;
        public final String snippet;
        public final String location;
        public final String date;
        public final String title;
        public final float rating;
        public final float price;
        public final int seatsTaken;
        public final int totalSeats;
        public final String user;
        public final int distance;

        public RestaurantItem(String id, String title, String description,String user, String location, String date,int day, float rating, int seatsTaken, int totalSeats, float price,int distance,int idImg) {
            this.id = id;
            this.title = title;
            this.description = description;
            this.location = location;
            this.date = date;
            this.day = day;
            this.snippet = description.substring(0);
            this.rating = rating;
            this.seatsTaken = seatsTaken;
            this.totalSeats = totalSeats;
            this.price = price;
            this.user=user;
            this.distance=distance;
            this.idImg=idImg;
        }

        @Override
        public String toString() {
            return title;
        }
    }
}
