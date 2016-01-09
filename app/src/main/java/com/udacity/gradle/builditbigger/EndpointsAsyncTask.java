package com.udacity.gradle.builditbigger;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.util.Pair;
import android.view.View;
import android.widget.ProgressBar;

import com.google.api.client.extensions.android.http.AndroidHttp;
import com.google.api.client.extensions.android.json.AndroidJsonFactory;
import com.udacity.gradle.gcebackend.myJokeApi.MyJokeApi;

import java.io.IOException;

import app.com.udacity.gradle.jokedisplay.JokeActivity;

/**
 * Created by Steven on 28/11/2015.
 */
public class EndpointsAsyncTask extends AsyncTask<Pair<Context, Integer>, Void, String> {
    private final static String ENDPOINTS_TAG = "ENDPOINTS_TAG";
    private EndpointAsyncListener mListener = null;
    private static MyJokeApi myApiService = null;
    private Exception mError = null;
    private Context context;
    private ProgressBar mSpinner;

    public EndpointsAsyncTask(View view) {
        mSpinner = (ProgressBar) view.findViewById(R.id.joke_progress_bar);
    }

    // for the Connected Check as the view isn't needed
    public EndpointsAsyncTask() {
        mSpinner = null;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        if (mSpinner != null) {
            mSpinner.setVisibility(View.VISIBLE);
        }
    }

    @Override
    protected String doInBackground(Pair<Context, Integer>... params) {
        context = params[0].first;
        int index = params[0].second;

        if (myApiService == null) {  // Only do this once
            MyJokeApi.Builder builder = new MyJokeApi.Builder(AndroidHttp.newCompatibleTransport(), new AndroidJsonFactory(), null)
                    .setRootUrl(context.getString(R.string.api_root_url));

            myApiService = builder.build();
        }

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


        if (mSpinner != null) {
            mSpinner.setVisibility(View.GONE);
        }
    }

    @Override
    protected void onCancelled() {
        if (this.mListener != null) {
            mError = new InterruptedException(context.getString(R.string.err_task_cancelled));
            this.mListener.onComplete(null, mError);
        }

        if (mSpinner != null) {
            mSpinner.setVisibility(View.GONE);
        }
    }

    public EndpointsAsyncTask setListener(EndpointAsyncListener listener) {
        this.mListener = listener;
        return this;
    }

    // This listener is used in AsyncTaskAndroidTest to test the AsyncTask
    public interface EndpointAsyncListener {
        void onComplete(String result, Exception e);
    }
}