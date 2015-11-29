package com.udacity.gradle.gcebackend;

import com.google.api.server.spi.config.Api;
import com.google.api.server.spi.config.ApiMethod;
import com.google.api.server.spi.config.ApiNamespace;

import java.util.logging.Logger;

import javax.inject.Named;

/**
 * An endpoint class we are exposing
 */
@Api(
        name = "myJokeApi",
        version = "v1",
        resource = "myJoke",
        namespace = @ApiNamespace(
                ownerDomain = "gcebackend.gradle.udacity.com",
                ownerName = "gcebackend.gradle.udacity.com",
                packagePath = ""
        )
)
public class MyJokeEndpoint {

    private static final Logger logger = Logger.getLogger(MyJokeEndpoint.class.getName());

    /**
     * This method gets the <code>MyJoke</code> object associated with the specified <code>id</code>.
     *
     * @param id The id of the object to be returned.
     * @return The <code>MyJoke</code> associated with <code>id</code>.
     */
    @ApiMethod(name = "setMyJoke")
    public MyJoke setMyJoke(@Named("id") int id) {
        MyJoke myJoke = new MyJoke();
        myJoke.setMyJoke(id);
        logger.info("Info: Calling setMyJoke method");
        return myJoke;
    }
}