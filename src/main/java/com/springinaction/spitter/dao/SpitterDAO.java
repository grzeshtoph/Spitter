package com.springinaction.spitter.dao;

import com.springinaction.spitter.model.Spitter;

/**
 * Main DAO for operation on Spitter.
 */
public interface SpitterDAO {
    /**
     * Adds new Spitter to the data source.
     *
     * @param spitter spitter to add
     */
    void addSpitter(Spitter spitter);

    /**
     * Updates the existing Spitter in the data source.
     *
     * @param spitter spitter to update
     */
    void saveSpitter(Spitter spitter);

    /**
     * Gets Spitter by its ID.
     *
     * @param id Spitter's ID
     * @return Spitter
     */
    Spitter getSpitterById(long id);
}
