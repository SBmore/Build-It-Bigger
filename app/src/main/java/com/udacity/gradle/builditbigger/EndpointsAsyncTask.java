package com.udacity.gradle.builditbigger;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.util.Pair;

import com.google.api.client.extensions.android.http.AndroidHttp;
import com.google.api.client.extensions.android.json.AndroidJsonFactory;
import com.udacity.gradle.gcebackend.myJokeApi.MyJokeApi;

import java.io.IOException;

import app.com.udacity.gradle.jokedisplay.JokeActivity;

/**
 * Created by Steven on 28/11/2015.
 */
public class EndpointsAsyncTask extends AsyncTask<Pair<Context, Integer>, Void, String> {
    private EndpointAsyncListener mListener = null;
    private static MyJokeApi myApiService = null;
    private Exception mError = null;
    private Context context;

    @Override
    protected String doInBackground(Pair<Context, Integer>... params) {
        if(myApiService == null) {  // Only do this once
            MyJokeApi.Builder builder = new MyJokeApi.Builder(AndroidHttp.newCompatibleTransport(), new AndroidJsonFactory(), null)
                    .setRootUrl("https://build-it-bigger-1143.appspot.com/_ah/api/");

            myApiService = builder.build();
        }

        context = params[0].first;
        int index  = params[0].second;

        try {
            return myApiService.setMyJoke(index).execute().getMyJoke();
        } catch (IOException e) {
            mError = e;
            return e.getMessage();
        }
    }

    @Override
    protected void onPostExecute(String result) {
        if (this.mListener != null) {
            this.mListener.onComplete(result, mError);
        }
        Intent jokeIntent = new Intent(context, JokeActivity.class);
        jokeIntent.putExtra(JokeActivity.JOKE_TAG, result);

        // only starting an activity when the context is prevents an error when running connectedCheck
        if (context instanceof Activity) {
            context.startActivity(jokeIntent);
        }
    }

    @Override
    protected void onCancelled() {
        if (this.mListener != null) {
            mError = new InterruptedException("EndpointsAsyncTask cancelled");
            this.mListener.onComplete(null, mError);
        }
    }

    public EndpointsAsyncTask setListener(EndpointAsyncListener listener) {
        this.mListener = listener;
        return this;
    }

    // This listener is used in AsyncTaskAndroidTest to test the AsyncTask
    public interface EndpointAsyncListener {
        void onComplete(String jsonString, Exception e);
    }
}