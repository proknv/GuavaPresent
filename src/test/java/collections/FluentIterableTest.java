package collections;

import com.google.common.base.Function;
import com.google.common.base.Joiner;
import com.google.common.base.Predicate;
import com.google.common.collect.FluentIterable;
import com.google.common.collect.Lists;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.Collection;
import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.*;

/**
 * The FluentIterable class presents a powerful interface for working with Iterable instances
 * in the fluent style of programming.
 *
 * The fluent programming style allows us to chain method calls together, making for a more readable code.
 */
public class FluentIterableTest {

    Person person1;
    Person person2;
    Person person3;
    Person person4;
    Collection<Person> persons;

    @Before
    public void setUp() {
        person1 = new Person("Wilma", "Flintstone", 30);
        person2 = new Person("Fred", "Flintstone", 32);
        person3 = new Person("Betty", "Rubble", 31);
        person4 = new Person("Barney", "Rubble", 33);
        persons = Lists.newArrayList(person1, person2, person3, person4);
    }

    @Test
    public void testFilter() throws Exception {
        Iterable<Person> personsFilteredByAge= FluentIterable.from(persons).filter(
                new Predicate<Person>() {
                    @Override
                    public boolean apply(Person input) {
                        return input.getAge() > 31;
                    }
                });

        assertThat(personsFilteredByAge).containsOnly(person2, person4);
    }

    //The FluentIterable.transform() method is a mapping operation where Function is applied to each element.
    //Notice using toList() method to have final result as a list. There are a bunch of methods like the toSet(), toMap(),
    // to SortedList(), toSortedSet(), uniqueIndex() available.
    @Test
    public void testTransform(){
        List<String> personDetails = FluentIterable.from(persons).transform(new Function<Person, String>() {
            @Override
            public String apply(Person input) {
                return Joiner.on(", ").join(input.getLastName(), input.getFirstName(), input.getAge());
            }
        }).toList();
        assertThat(personDetails).hasSize(persons.size()).contains("Rubble, Betty, 31");
    }

    @Test
    public void testFluentIterableToMap(){
        Map<String, Person> personDetails = FluentIterable.from(persons).uniqueIndex(new Function<Person, String>() {
            @Override
            public String apply(Person input) {
                return Joiner.on(' ').join(input.getLastName(), input.getFirstName());
            }
        });
        assertThat(personDetails).hasSize(persons.size());
        assertThat(personDetails.get("Rubble Betty")).isEqualTo(person3);
    }

}
