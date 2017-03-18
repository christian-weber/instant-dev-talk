package _001;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;

import java.util.Collection;

/**
 * Created by christian.weber on 16.03.2017.
 */
public class SimpleReader <ENTITY> implements IReader<ENTITY> {

    @Autowired
    private ApplicationContext applicationContext;

    public SimpleReader(Class<ENTITY>cls) {
        if (cls == null)
            throw new IllegalArgumentException();
    }

    @Override
    public void read(Number id) {
        System.out.println("read " + id);
    }

    @Override
    public void readAll(Collection<Number> ids) {
        System.out.println("read " + ids);
    }

}