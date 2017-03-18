package _001.context;

import _001.SimpleReader;
import _001.SimpleWriter;
import _001.annotation.Entity;
import net.bytebuddy.ByteBuddy;
import net.bytebuddy.description.type.TypeDescription;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.config.ConstructorArgumentValues;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.BeanDefinitionRegistryPostProcessor;
import org.springframework.beans.factory.support.GenericBeanDefinition;

import java.util.Set;

public class ReaderWriterRegistrar implements BeanDefinitionRegistryPostProcessor {

    @Override
    public void postProcessBeanDefinitionRegistry(BeanDefinitionRegistry registry) throws BeansException {
        AnnotationCandidateProvider provider = new AnnotationCandidateProvider(Entity.class);
        Set<BeanDefinition> beanDefinitions = provider.findCandidateComponents("_001.entity");

        beanDefinitions.forEach(beanDefinition -> {
            GenericBeanDefinition generic = new GenericBeanDefinition();
            generic.setBeanClass(createReader(beanDefinition));

            final ConstructorArgumentValues values = new ConstructorArgumentValues();
            values.addGenericArgumentValue(toClass(beanDefinition.getBeanClassName()));

            generic.setConstructorArgumentValues(values);

            registry.registerBeanDefinition(beanDefinition.getBeanClassName() + "Reader", generic);
        });
        beanDefinitions.forEach(beanDefinition -> {
            GenericBeanDefinition generic = new GenericBeanDefinition();
            generic.setBeanClass(createWriter(beanDefinition));

            final ConstructorArgumentValues values = new ConstructorArgumentValues();
            values.addGenericArgumentValue(toClass(beanDefinition.getBeanClassName()));

            generic.setConstructorArgumentValues(values);

            registry.registerBeanDefinition(beanDefinition.getBeanClassName() + "Writer", generic);
        });
    }

    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {
        // do nothing
    }

    private Class<?> createReader(BeanDefinition beanDefinition) {
        final ByteBuddy byteBuddy = new ByteBuddy();
        final ClassLoader classLoader = Thread.currentThread().getContextClassLoader();

        return byteBuddy.subclass(TypeDescription.Generic.Builder
                .parameterizedType(SimpleReader.class, toClass(beanDefinition.getBeanClassName()))
                .build())
                .make().load(classLoader).getLoaded();
    }

    private Class<?> createWriter(BeanDefinition beanDefinition) {
        final ByteBuddy byteBuddy = new ByteBuddy();


        final ClassLoader classLoader = Thread.currentThread().getContextClassLoader();

        return byteBuddy.subclass(TypeDescription.Generic.Builder
                .parameterizedType(SimpleWriter.class, toClass(beanDefinition.getBeanClassName()))
                .build())
                .make().load(classLoader).getLoaded();
    }

    private Class toClass(String name) {
        try {
            return Class.forName(name);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

}