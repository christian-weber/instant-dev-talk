package _001;

import java.util.Collection;

/**
 * Interface definition for entity reader classes.
 * Classes which implements this interface can be used to read entities from a database.
 */
public interface IReader<ENTITY> {

    /**
     * Reads a single entity instance.
     *
     * @param id the entity id
     */
    void read(Number id);

    /**
     * Reads all entities by the given ids.
     *
     * @param ids the entity ids
     */
    void readAll(Collection<Number> ids);

}
