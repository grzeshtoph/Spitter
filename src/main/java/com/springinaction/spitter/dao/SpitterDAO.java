package com.springinaction.spitter.dao;

import com.springinaction.spitter.exceptions.DAOException;
import com.springinaction.spitter.model.Spitter;

/**
 * Main DAO for operation on Spitter.
 */
public interface SpitterDAO {
    /**
     * Adds new Spitter to the data source.
     *
     * @param spitter spitter to add
     *
     * @throws DAOException might be thrown in case of insertion error
     */
    void addSpitter(Spitter spitter) throws DAOException;

    /**
     * Updates the existing Spitter in the data source.
     *
     * @param spitter spitter to update
     *
     * @throws DAOException might be thrown in case of update error
     */
    void saveSpitter(Spitter spitter) throws DAOException;

    /**
     * Gets Spitter by its ID.
     *
     * @param username user's name
     * @return Spitter
     *
     * @throws DAOException might be thrown in case of querying error
     */
    Spitter getSpitter(String username) throws DAOException;
}
