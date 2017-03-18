package _001.springdata;

import _001.springdata.annotation.Query;
import net.bytebuddy.implementation.bind.annotation.AllArguments;
import net.bytebuddy.implementation.bind.annotation.Origin;
import net.bytebuddy.implementation.bind.annotation.RuntimeType;

import java.lang.reflect.Method;

/**
 * Created by christian.weber on 16.03.2017.
 */
public class RepositoryInterceptor {

    @RuntimeType
    public Object intercept(@Origin Method m, @AllArguments Object[] args) {
        Query query = m.getAnnotation(Query.class);
        System.out.println("intercept method with query " + query.value());

        return null;
    }

}
