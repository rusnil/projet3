package com.openclassrooms.entrevoisins.service;

import com.openclassrooms.entrevoisins.model.Neighbour;

import java.util.ArrayList;
import java.util.List;

/**
 * Dummy mock for the Api
 */
public class DummyNeighbourApiService implements  NeighbourApiService {

    private final List<Neighbour> neighbours = DummyNeighbourGenerator.generateNeighbours();


    /**
     * {@inheritDoc}
     */
    @Override
    public List<Neighbour> getNeighbours() {
        return neighbours;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void deleteNeighbour(Neighbour neighbour) {
        neighbours.remove(neighbour);
    }

    /**
     * {@inheritDoc}
     * @param neighbour
     */
    @Override
    public void createNeighbour(Neighbour neighbour) {
        neighbours.add(neighbour);
    }

    @Override
    public Neighbour getNeighbour(long id) {
        for (Neighbour neighbour:neighbours){
            if (neighbour.getId()==id){
                return neighbour;
            }
        }
        return null;
    }

    @Override
    public void addFavorite(Neighbour neighbour) {
        neighbour.setFavorite(true);
    }

    @Override
    public void removeFavorite(Neighbour neighbour) {
        neighbour.setFavorite(false);
    }

    @Override
    public List<Neighbour> getFavorite() {
        List<Neighbour> favoriteList = new ArrayList<>();
        for (Neighbour neighbour:neighbours)
                if (neighbour.isFavorite()){
                    favoriteList.add(neighbour);
                }
        return favoriteList;
    }
}
