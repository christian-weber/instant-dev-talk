package _001;

import java.util.Collection;

/**
 * Created by christian.weber on 16.03.2017.
 */
public class SimpleWriter<ENTITY> implements IWriter<ENTITY> {

    public SimpleWriter(Class<ENTITY> cls) {
        if (cls == null)
            throw new IllegalArgumentException();
    }

    @Override
    public void write(ENTITY entity) {
        System.out.println("write " + entity);
    }

    @Override
    public void writeAll(Collection<ENTITY> collection) {
        System.out.println("write " + collection);
    }
}
