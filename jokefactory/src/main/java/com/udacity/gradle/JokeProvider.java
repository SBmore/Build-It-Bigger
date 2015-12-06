package com.udacity.gradle;

public class JokeProvider {

    // Leave index 0 as the Test Joke
    private final static String [] jokeArray = {
            "What's brown and sticky? ... A stick!",
            "What happens to a frog's car when it breaks down? ... It gets toad away!",
            "Why was six scared of seven? ... Because seven 'ate' nine!"};

    public String getJoke(int index) {
        if (index > -1 && index < jokeArray.length) {
            return jokeArray[index];
        } else {
            Integer randomIndex = Utility.getRandBetween(0, jokeArray.length);
            return jokeArray[randomIndex];
        }
    }
}
