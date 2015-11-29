package com.udacity.gradle;

public class JokeProvider {

    private static String [] jokeArray = {"What's brown and sticky? ... A stick!"};

    public String getJoke(int index) {
        if (index > -1 && index < jokeArray.length) {
            return jokeArray[index];
        } else {
            return "Error: Index out of range";
        }
    }
}
