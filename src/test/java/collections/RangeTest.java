package collections;

import com.google.common.base.Function;
import com.google.common.base.Predicate;
import com.google.common.base.Predicates;
import com.google.common.collect.ContiguousSet;
import com.google.common.collect.DiscreteDomain;
import com.google.common.collect.Range;
import com.google.common.primitives.Ints;
import org.junit.Test;

import static org.assertj.core.api.Assertions.*;

/**
 *A Range defines the boundaries around a contiguous span of values of some
 * Comparable type; for example, "integers from 1 to 100 inclusive." Note that it is not
 * possible to iterate over these contained values. To do so, pass this range instance and
 * an appropriate DiscreteDomain to ContiguousSet.create.
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


    public class Person implements Comparable<Person> {
        private String firstName;
        private String lastName;
        private int age;

        public String getFirstName() {
            return firstName;
        }

        public Person setFirstName(String firstName) {
            this.firstName = firstName;
            return this;
        }

        public String getLastName() {
            return lastName;
        }

        public Person setLastName(String lastName) {
            this.lastName = lastName;
            return this;
        }

        public int getAge() {
            return age;
        }

        public Person setAge(int age) {
            this.age = age;
            return this;
        }

        @Override
        public int compareTo(Person o) {
            return Ints.compare(age, o.getAge());
        }
    }

    @Test
    public void testPersonRange(){
        Person person1 = new Person().setFirstName("John").setAge(17);
        Person person2 = new Person().setFirstName("Michael").setAge(37);
        Person person3 = new Person().setFirstName("Andrew").setAge(25);
        Person person4 = new Person().setFirstName("Boris").setAge(41);
        Range<Person> rangeOfPersons = Range.closed(person1, person2);
        assertThat(rangeOfPersons.contains(person3)).isTrue();
        assertThat(rangeOfPersons.contains(person4)).isFalse();
    }

    //Notice Range class implements Predicate interface
    @Test
    public void testPersonRangeAsPredicate(){
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
