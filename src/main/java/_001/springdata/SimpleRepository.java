package _001.springdata;

/**
 * Created by christian.weber on 16.03.2017.
 */
public class SimpleRepository<ENTITY> implements CrudRepository<ENTITY> {

    @Override
    public void save(ENTITY entity) {
        System.out.println("save " + entity);
    }

}
