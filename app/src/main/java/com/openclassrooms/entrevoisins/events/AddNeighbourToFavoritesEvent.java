package com.openclassrooms.entrevoisins.events;

import com.openclassrooms.entrevoisins.model.Neighbour;

/**
 * Event fired when a user deletes a Neighbour
 */
public class AddNeighbourToFavoritesEvent {

    /**
     * Neighbour to delete
     */
    public Neighbour neighbour;

    /**
     * Constructor.
     * @param neighbour
     */
    public AddNeighbourToFavoritesEvent(Neighbour neighbour) {
        this.neighbour = neighbour;
    }
}
