package com.openclassrooms.entrevoisins.service;


import com.github.ivanshafran.sharedpreferencesmock.SPMockBuilder;
import com.openclassrooms.entrevoisins.di.DI;
import com.openclassrooms.entrevoisins.model.Neighbour;
import com.openclassrooms.entrevoisins.pref.UserPref;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;


/**
 * Unit test on UserPref
 */
@RunWith(JUnit4.class)
public class UserPrefTest {

    private NeighbourApiService service;
    private final SPMockBuilder spMockBuilder = new SPMockBuilder();
    private UserPref mUserPref;

    @Before
    public void setup() {
        service = DI.getNewInstanceApiService();
        mUserPref = DI.getUserPref().init(spMockBuilder.createContext());
    }

    @Test
    public void setNeighbourToFavoriteAndCheckItInFavoritesList() {
        // get a neighbour from service API
        Neighbour favoriteNeighbour = service.getNeighbours().get(1);

        //Check if it's not in the favorites list
        assertFalse(mUserPref.favoritesContains(favoriteNeighbour.getId()));

        // Add it to the list
        mUserPref.addFavoriteId(favoriteNeighbour.getId());

        // Check it's in the list
        assertTrue(mUserPref.favoritesContains(favoriteNeighbour.getId()));
    }

    @Test
    public void setNeighbourToFavoriteAndRemoveItFromFavoritesList() {
        // get a neighbour from service API
        Neighbour favoriteNeighbour = service.getNeighbours().get(2);

        //Check if it's not in the favorites list
        assertFalse(mUserPref.favoritesContains(favoriteNeighbour.getId()));

        // Add it to the list
        mUserPref.addFavoriteId(favoriteNeighbour.getId());

        // Check it's in the list
        assertTrue(mUserPref.favoritesContains(favoriteNeighbour.getId()));

        // Remove it from the list
        mUserPref.removeFavoriteId(favoriteNeighbour.getId());

        //Check if it's not in the favorites list
        assertFalse(mUserPref.favoritesContains(favoriteNeighbour.getId()));
    }
}
