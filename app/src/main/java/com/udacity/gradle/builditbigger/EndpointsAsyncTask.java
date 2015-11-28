package com.udacity.gradle.builditbigger;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.util.Pair;

import com.google.api.client.extensions.android.http.AndroidHttp;
import com.google.api.client.extensions.android.json.AndroidJsonFactory;
import com.udacity.gradle.gcebackend.jokeAPI.JokeAPI;

import java.io.IOException;

import app.com.udacity.gradle.jokedisplay.JokeActivity;

/**
 * Created by Steven on 28/11/2015.
 */
public class EndpointsAsyncTask extends AsyncTask<Pair<Context, String>, Void, String> {
    private static JokeAPI myApiService = null;
    private Context context;

    @Override
    protected String doInBackground(Pair<Context, String>... params) {
        if(myApiService == null) {  // Only do this once
            JokeAPI.Builder builder = new JokeAPI.Builder(AndroidHttp.newCompatibleTransport(), new AndroidJsonFactory(), null)
                    .setRootUrl("https://build-it-bigger-1143.appspot.com/_ah/api/");

            myApiService = builder.build();
        }

        context = params[0].first;

        try {
            return myApiService.jokeAPI().loadJoke().execute().getData();
        } catch (IOException e) {
            return e.getMessage();
        }
    }

    @Override
    protected void onPostExecute(String result) {
        Intent jokeIntent = new Intent(context, JokeActivity.class);
        jokeIntent.putExtra(JokeActivity.JOKE_TAG, result);
        context.startActivity(jokeIntent);
    }
}