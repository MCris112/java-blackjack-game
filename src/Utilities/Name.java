package Utilities;

import java.util.Random;

public class Name {
    private static final String[] NAMES = {
            "Aiden", "Sophia", "Liam", "Olivia", "Noah", "Emma", "Ethan", "Ava",
            "Mason", "Isabella", "Logan", "Mia", "Lucas", "Charlotte", "Jackson", "Amelia",
            "Oliver", "Harper", "Elijah", "Evelyn", "James", "Abigail", "Benjamin", "Ella",
            "Alexander", "Scarlett", "Henry", "Grace", "Sebastian", "Chloe", "Matthew", "Victoria",
            "Samuel", "Aria", "David", "Lily", "Joseph", "Aurora", "Daniel", "Hannah",
            "Wyatt", "Zoe", "Carter", "Penelope", "Jayden", "Layla", "Gabriel", "Riley",
            "Isaac", "Nora", "Julian", "Hazel", "Levi", "Camila", "Owen", "Savannah",
            "Caleb", "Brooklyn", "Nathan", "Paisley", "Ryan", "Ellie", "Luke", "Skylar",
            "Christian", "Anna", "Hunter", "Claire", "Jonathan", "Samantha", "Connor", "Stella",
            "Landon", "Lucy", "Adrian", "Natalie", "Nicholas", "Leah", "Dominic", "Audrey",
            "Colton", "Maya", "Austin", "Violet", "Miles", "Naomi", "Jordan", "Luna",
            "Ian", "Bella", "Adam", "Serenity", "Evan", "Autumn", "Charles", "Ruby",
            "Thomas", "Eva", "Christopher", "Alice"
    };


    public static String generate()
    {
        Random random = new Random();
        int index = random.nextInt(NAMES.length);
        return NAMES[index];
    }
}
