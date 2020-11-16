package com.openclassrooms.entrevoisins.service;

import android.util.Log;

import com.openclassrooms.entrevoisins.di.DI;
import com.openclassrooms.entrevoisins.model.Neighbour;

import org.hamcrest.collection.IsIterableContainingInAnyOrder;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.util.List;

import static android.content.ContentValues.TAG;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertThat;

/**
 * Unit test on Neighbour service
 */
@RunWith(JUnit4.class)
public class NeighbourServiceTest {

    private NeighbourApiService service;

    @Before
    public void setup() {
        service = DI.getNewInstanceApiService();
    }

    @Test
    public void getNeighboursWithSuccess() {
        List<Neighbour> neighbours = service.getNeighbours();
        List<Neighbour> expectedNeighbours = DummyNeighbourGenerator.DUMMY_NEIGHBOURS;
        assertThat(neighbours, IsIterableContainingInAnyOrder.containsInAnyOrder(expectedNeighbours.toArray()));
    }

    @Test
    public void deleteNeighbourWithSuccess() {
        Neighbour neighbourToDelete = service.getNeighbours().get(0);
        service.deleteNeighbour(neighbourToDelete);
        assertFalse(service.getNeighbours().contains(neighbourToDelete));
    }

    @Test
    public void updateNeighbourWithSuccess(){
        Neighbour neighbourToUpdate = service.getNeighbours().get(0);
        Neighbour neighbourCopy =  new Neighbour(neighbourToUpdate.getId(), neighbourToUpdate.getName(), neighbourToUpdate.getAvatarUrl(), neighbourToUpdate.getAddress(),neighbourToUpdate.getPhoneNumber(),neighbourToUpdate.getAboutMe(), neighbourToUpdate.getWebUrl() );

        neighbourToUpdate.setName("Tintin");
        assertNotEquals(neighbourCopy.getName(), neighbourToUpdate.getName());

        service.updateNeighbour(neighbourToUpdate);
        assertEquals("Tintin", service.getNeighbours().get(0).getName());
    }

    @Test
    public void defaultFavoriteStateIsFalse() {
        assertFalse(service.getNeighbours().get(0).isFavorite());
    }

    @Test
    public void setNeighbourToFavoriteAndTakeItInFavoritesList() {
        Neighbour favoriteNeighbour = service.getNeighbours().get(0);
        favoriteNeighbour.setFavorite(true);
        assertEquals(favoriteNeighbour, service.getFavoritesNeighbours().get(0));
    }
}
