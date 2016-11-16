import com.alibaba.fastjson.JSON;
import com.github.javafaker.Address;
import com.github.javafaker.Faker;
import io.codearte.jfairy.Fairy;
import io.codearte.jfairy.producer.person.Person;
import io.github.benas.randombeans.EnhancedRandomBuilder;
import io.github.benas.randombeans.api.EnhancedRandom;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.util.Locale;


@Slf4j
public class EnhancedRandomFactoryBeanTest {

    @Test
    public void t(){
        Fairy fairy = Fairy.create();
        Person person = fairy.person();

        System.out.println(person.fullName());
    }

    @Test
    public void testFake(){
        Faker faker = new Faker(new Locale("zh","CN"));

        long num = faker.number().randomNumber();
        Address addr = faker.address();
        System.out.println(JSON.toJSONString( addr ));
    }

    @Test
    public void testEnhancedRandomFactoryBeanWithDefaultRandomizers() {

        EnhancedRandom enhancedRandom = EnhancedRandomBuilder.aNewEnhancedRandomBuilder().build();

        Foo foo = enhancedRandom.nextObject(Foo.class);

//        log.info(JSON.toJSONString(foo));

        System.out.println(JSON.toJSONString(foo));

    }


}