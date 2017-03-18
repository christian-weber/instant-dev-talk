package _001.springdata;

import _001.entity.Person;
import _001.springdata.annotation.Crud;
import _001.springdata.annotation.Query;
import net.bytebuddy.ByteBuddy;
import net.bytebuddy.description.type.TypeDefinition;
import net.bytebuddy.description.type.TypeDescription;
import net.bytebuddy.implementation.FixedValue;
import net.bytebuddy.implementation.MethodDelegation;
import net.bytebuddy.matcher.ElementMatchers;

/**
 * Created by christian.weber on 16.03.2017.
 */
public class Main {

    public static void main(String[] args) throws Exception {
        ByteBuddy byteBuddy = new ByteBuddy();

        final ClassLoader classLoader = Thread.currentThread().getContextClassLoader();

        Class cls = byteBuddy.subclass(IPersonRepository.class
        )

                .method(ElementMatchers.named("toString"))
                .intercept(FixedValue.value("Hallo"))

                .method(ElementMatchers.isAnnotatedWith(Crud.class))
                .intercept(MethodDelegation.to(new SimpleRepository()))

                .method(ElementMatchers.isAnnotatedWith(Query.class))
                .intercept(MethodDelegation.to(new RepositoryInterceptor()))
                .make().load(classLoader).getLoaded();

        IPersonRepository repository = (IPersonRepository) cls.newInstance();

        repository.save(new Person());
        repository.findById(1);
    }

}
