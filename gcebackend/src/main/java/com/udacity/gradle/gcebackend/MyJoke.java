package com.udacity.gradle.gcebackend;

import com.udacity.gradle.JokeProvider;

/**
 * Created by Steven on 29/11/2015.
 */
public class MyJoke {

    private String theJoke;

    public String getMyJoke() {
        return theJoke;
    }

    public void setMyJoke(int index) {
        JokeProvider jokeProvider = new JokeProvider();
        theJoke = jokeProvider.getJoke(index);
    }
}
