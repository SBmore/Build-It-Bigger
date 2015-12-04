package com.udacity.gradle.builditbigger;

import android.app.Application;
import android.content.Context;
import android.test.ApplicationTestCase;
import android.text.TextUtils;
import android.util.Log;
import android.util.Pair;

import java.util.concurrent.CountDownLatch;

/**
 * Created by Steven on 30/11/2015.
 * Built with the help of this tutorial:
 * http://marksunghunpark.blogspot.co.uk/2015/05/how-to-test-asynctask-in-android.html
 */
public class AsyncTaskAndroidTest extends ApplicationTestCase<Application> {

    private static final String TEST_JOKE_STRING = "What's brown and sticky? ... A stick!";
    String mJokeString = null;
    Exception mError = null;
    CountDownLatch signal = null;

    public AsyncTaskAndroidTest() {
        super(Application.class);
    }

    @Override
    protected void setUp() throws Exception {
        signal = new CountDownLatch(1);
    }

    @Override
    protected void tearDown() throws Exception {
        signal.countDown();
    }

    public void testEndpointGetJoke() throws InterruptedException {
        EndpointsAsyncTask task = new EndpointsAsyncTask();

        //noinspection unchecked
        task.setListener(new EndpointsAsyncTask.EndpointAsyncListener() {
            @Override
            public void onComplete(String result, Exception e) {
                mJokeString = result;
                mError = e;
                signal.countDown();
            }
        }).execute(new Pair<Context, Integer>(getContext(), MainActivity.TEST_JOKE));

        signal.await();

        assertNull(mError);
        assertFalse(TextUtils.isEmpty(mJokeString));
        assertTrue(mJokeString.equals(TEST_JOKE_STRING));
        Log.i("", TEST_JOKE_STRING);
    }
}