package _001.context;

import _001.annotation.Entity;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.ClassPathScanningCandidateComponentProvider;
import org.springframework.core.type.AnnotationMetadata;

import java.lang.annotation.Annotation;
import java.util.Set;

public class AnnotationCandidateProvider extends ClassPathScanningCandidateComponentProvider {

    public AnnotationCandidateProvider(Class<? extends Annotation> annotationClass) {
        super(false);
        addIncludeFilter((metadataReader, metadataReaderFactory) -> {
            final AnnotationMetadata annotationMetadata = metadataReader.getAnnotationMetadata();
            return annotationMetadata.hasAnnotation(annotationClass.getName());
        });
    }


    public static void main(String[] args) {
        AnnotationCandidateProvider provider = new AnnotationCandidateProvider(Entity.class);
        Set<BeanDefinition> beanDefinitionSet = provider.findCandidateComponents("_001.entity");
    }

}
