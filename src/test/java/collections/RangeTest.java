package collections;

import com.google.common.base.Function;
import com.google.common.base.Predicate;
import com.google.common.base.Predicates;
import com.google.common.collect.Range;
import org.junit.Test;

import static org.assertj.core.api.Assertions.*;

/**
 *
 */
public class RangeTest {

    @Test
    public void testIntegerRanges(){
        Range<Integer> numberRange = Range.closed(1, 10);
        assertThat(numberRange.contains(1)).isTrue();
        assertThat(numberRange.contains(3)).isTrue();
        assertThat(numberRange.contains(10)).isTrue();

        Range<Integer> numberRange1 = Range.openClosed(1, 10);
        assertThat(numberRange1.contains(1)).isFalse();
        assertThat(numberRange1.contains(3)).isTrue();
        assertThat(numberRange1.contains(10)).isTrue();
    }


    public class Person {
        private String firstName;
        private String lastName;
        private int age;

        public String getFirstName() {
            return firstName;
        }

        public void setFirstName(String firstName) {
            this.firstName = firstName;
        }

        public String getLastName() {
            return lastName;
        }

        public void setLastName(String lastName) {
            this.lastName = lastName;
        }

        public int getAge() {
            return age;
        }

        public void setAge(int age) {
            this.age = age;
        }
    }

    @Test
    public void testPersonRange(){
        //initializing
        Range<Integer> personAges = Range.closed(35, 50);
        Function<Person, Integer> ageFunction = new Function<Person, Integer>() {
            @Override
            public Integer apply(Person input) {
                return input.age;
            }
        };
        Predicate<Person> agePredicate = Predicates.compose(personAges, ageFunction);
        //checking
        Person person = new Person();
        person.setLastName("Smith");
        person.setFirstName("John");
        person.setAge(40);
        assertThat(agePredicate.apply(person)).isTrue();
        person.setAge(25);
        assertThat(agePredicate.apply(person)).isFalse();
    }

}
