package _001

import _001.context.ReaderWriterRegistrar
import _001.entity.Person
import _001.entity.Product
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.ApplicationContext
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.core.ResolvableType
import org.springframework.test.context.ContextConfiguration
import spock.lang.Specification

@ContextConfiguration(classes = TestConfiguration)
class ContextTest extends Specification {

    @Autowired
    private IReader<Person> personReader
    @Autowired
    private IWriter<Person> personWriter
    @Autowired
    private ApplicationContext applicationContext

    def "test context"() {
        expect:
            personReader != null
            personWriter != null

            ResolvableType type1 = ResolvableType.forClassWithGenerics(IReader, Product)
            ResolvableType type2 = ResolvableType.forClassWithGenerics(IWriter, Product)
            applicationContext.getBeanNamesForType(type1).length > 0
            applicationContext.getBeanNamesForType(type2).length > 0
    }

    @Configuration
    static class TestConfiguration {

        @Bean
        public ReaderWriterRegistrar readerWriterRegistrar() {
            return new ReaderWriterRegistrar();
        }

    }

}