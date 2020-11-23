package com.openclassrooms.entrevoisins.di;

import com.openclassrooms.entrevoisins.pref.UserPref;
import com.openclassrooms.entrevoisins.service.DummyNeighbourApiService;
import com.openclassrooms.entrevoisins.service.NeighbourApiService;

/**
 * Dependency injector to get instance of services
 */
public class DI {
    private static UserPref mUserPref = new UserPref();
    private static NeighbourApiService service = new DummyNeighbourApiService();

    /**
     * Get an instance on @{@link UserPref}
     */
    public static UserPref getUserPref() {
        return mUserPref;
    }

    /**
     * Get an instance on @{@link NeighbourApiService}
     * @return
     */
    public static NeighbourApiService getNeighbourApiService() {
        return service;
    }

    /**
     * Get always a new instance on @{@link NeighbourApiService}. Useful for tests, so we ensure the context is clean.
     * @return
     */
    public static NeighbourApiService getNewInstanceApiService() {
        return new DummyNeighbourApiService();
    }
}
