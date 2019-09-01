package com.company;

import java.util.Random;

public class Main {

    public static int[] health = {700, 250, 250, 250, 250};
    public static int[] damage = {50, 20, 20, 20, 20};
    public static String[] hitTypes = {"Physical", "Physical", "Magical", "Mental", "Medical"};


    public static void main(String[] args) {
        printStatistics();
        while (!isFinished()) {
            hitTypes[0] = generateBossDefenceType();
            round();
        }
    }

    public static boolean isFinished() {

        if (health[0] == 0) {
            System.out.println("Герои won!!!");
            return true;
        }
        if (health[1] <= 0 && health[2] <= 0 && health[3] <= 0 && health[4] <= 0) {
            System.out.println("Boss won!!!");
            return true;
        }
        return false;
    }

    public static void round() {

        for (int i = 1; i <= 4; i++) {

            health[i] = hitPlayer(i); // Босс бьет героя
            health[0] = hitBoss(i); // герой бьет Босса
            health[i] = helpMedical(i); // Медик помогает героям


        }
        System.out.println(" ");
        System.out.println("Медик помогает герою");
        printStatistics();
    }

    public static int helpMedical(int playerIndex) {

        if (health[playerIndex] > 0 || health[4] > 0) {

            return health[playerIndex] + damage[4];
        }
        return health[playerIndex];
    }


    public static String generateBossDefenceType() {

        Random r = new Random();
        int randomNumber = r.nextInt(4) + 1;
        return hitTypes[randomNumber];
    }


    public static int hitBoss(int playerIndex) {

        if (health[0] <= 0) {
            return health[0] = 0;
        }

        if (hitTypes[playerIndex].equals(hitTypes[4])) {
            return health[0];
        }


        Random r = new Random();
        int randomNumber = r.nextInt(3) + 1;// генерируем случайное число от 1 до 5

        if (hitTypes[0].equals(hitTypes[playerIndex])) {

            System.out.println(hitTypes[playerIndex] + " нанес критический удар " +
                    damage[playerIndex] * randomNumber);


            return health[0] - damage[playerIndex] * randomNumber;
        }


        return health[0] - damage[playerIndex];
    }


    public static int hitPlayer(int playerIndex) {
        return health[playerIndex] - damage[0];
    }

    public static void printStatistics() {
        System.out.println("_________________________");
        System.out.println("Boss health " + health[0]);
        System.out.println("Warrior health " + health[1]);
        System.out.println("Magic health " + health[2]);
        System.out.println("Kinetic health " + health[3]);
        System.out.println("Medic health " + health[4]);
        System.out.println("_________________________");
    }
}
