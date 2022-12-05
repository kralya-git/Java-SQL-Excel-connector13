package com.test.idea.third;

import java.util.Scanner;
import java.util.concurrent.ThreadLocalRandom;

class Listik {
    protected static Object random() {
        int min = 0;
        int max = 10;
        StringBuffer new_arr = new StringBuffer();
        for (int i = 0; i < 100; i++) {
            int randomNum = ThreadLocalRandom.current().nextInt(min, max + 1);
            new_arr.append(randomNum).append(" ");
        }
        return new_arr;
    }

    protected static Object input() {
        StringBuffer new_arr = new StringBuffer();
        for (int i = 0; i < 3; i++) {
            Scanner text = new Scanner(System.in);
            System.out.println("Введите текст: ");
            new_arr.append(text.next()).append(" ");

        }
        return new_arr;
    }
}