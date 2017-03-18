package _001;

import java.util.Collection;

/**
 * Interface definition for entity writer classes.
 * Classes which implements this interface can be used to persist entities into a database.
 */
public interface IWriter<ENTITY> {

    /**
     * Writes a single entity instance.
     *
     * @param entity the entity
     */
    void write(ENTITY entity);

    /**
     * Writes all given entity instances.
     *
     * @param entities the entities
     */
    void writeAll(Collection<ENTITY> entities);

}
