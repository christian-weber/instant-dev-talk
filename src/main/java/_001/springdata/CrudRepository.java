package _001.springdata;

import _001.springdata.annotation.Crud;

/**
 * Created by christian.weber on 16.03.2017.
 */
public interface CrudRepository<ENTITY> {

    @Crud
    void save(ENTITY entity);

}
