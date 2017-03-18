package _001.springdata;

import _001.entity.Person;
import _001.springdata.annotation.Query;

/**
 * Created by christian.weber on 16.03.2017.
 */
public interface IPersonRepository extends CrudRepository<Person> {

    @Query("SELECT * FROM Product WHERE ID = ?")
    Person findById(Number id);

}
