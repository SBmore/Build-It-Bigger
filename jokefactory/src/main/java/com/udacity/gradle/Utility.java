package com.udacity.gradle;

import java.util.Random;

/**
 * Created by Steven on 06/12/2015.
 */
public class Utility {
    public static int getRandBetween(int min, int max) {
        Random rand = new Random();
        return rand.nextInt(max - min) + min;
    }
}
