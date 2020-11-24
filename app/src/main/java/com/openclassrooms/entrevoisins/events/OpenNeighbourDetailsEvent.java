package com.openclassrooms.entrevoisins.events;

import com.openclassrooms.entrevoisins.model.Neighbour;

/**
 * Event fired when a user open a Neighbour
 */
public class OpenNeighbourDetailsEvent {

    /**
     * Neighbour to Open
     */
    public Neighbour neighbour;

    /**
     * Constructor.
     * @param neighbour
     */
    public OpenNeighbourDetailsEvent(Neighbour neighbour) {
        this.neighbour = neighbour;
    }
}
