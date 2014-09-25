package collections;

import com.google.common.base.Function;
import com.google.common.base.Joiner;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.collect.Range;
import com.google.common.collect.Sets;
import org.assertj.core.data.MapEntry;
import org.junit.Before;
import org.junit.Test;

import java.util.*;

import static org.assertj.core.api.Assertions.*;

/**
 * Created by proknv on 9/7/14.
 */
public class ListsSetsMapsTest {

    Person person1;
    Person person2;
    Person person3;
    Person person4;
    List<Person> persons;

    @Before
    public void setUp() {
        person1 = new Person("Wilma", "Flintstone", 30);
        person2 = new Person("Fred", "Flintstone", 32);
        person3 = new Person("Betty", "Rubble", 31);
        person4 = new Person("Barney", "Rubble", 33);
        persons = Lists.newArrayList(person1, person2, person3, person4);
    }

    @Test
    public void testListSetMapCreation(){
        //lists
        List<Person> arrayListOfPersons = Lists.newArrayList(person1, person2, person3);
        assertThat(arrayListOfPersons).isExactlyInstanceOf(ArrayList.class);
        List<Person> linkedListOfPersons = Lists.newLinkedList(arrayListOfPersons);
        assertThat(linkedListOfPersons).isExactlyInstanceOf(LinkedList.class);
        //sets
        Set<String> hashSetOfStrings = Sets.newHashSet();
        assertThat(hashSetOfStrings).isExactlyInstanceOf(HashSet.class);
        Set<String> linkedHashSetOfStrings = Sets.newLinkedHashSet();
        assertThat(linkedHashSetOfStrings).isExactlyInstanceOf(LinkedHashSet.class);
        Set<String> treeSetOfStrings = Sets.newTreeSet();
        assertThat(treeSetOfStrings).isExactlyInstanceOf(TreeSet.class);
        //maps
        Map<String, String> hashMap = Maps.newHashMap();
        assertThat(hashMap).isExactlyInstanceOf(HashMap.class);
        Map<String, String> linkedHashMap = Maps.newLinkedHashMap();
        assertThat(linkedHashMap).isExactlyInstanceOf(LinkedHashMap.class);
        Map<String, String> treeMap = Maps.newTreeMap();
        assertThat(treeMap).isExactlyInstanceOf(TreeMap.class);
    }

    //The Lists.partition() method returns sublists of size n from a given list.
    @Test
    public void testListPartition(){
        List<List<Person>> sublists = Lists.partition(persons, 2);
        assertThat(sublists).hasSize(2);
        assertThat(sublists.get(0)).containsOnly(person1, person2);
        assertThat(sublists.get(1)).containsOnly(person3, person4);
    }

    //SetView is a static, abstract inner class of the Sets class and
    // represents an unmodifiable view of a given Set instance.
    @Test
    public void testSets(){
        Set<String> s1 = Sets.newHashSet("1","2","3");
        Set<String> s2 = Sets.newHashSet("2","3","4");
        //difference
        Sets.SetView<String> sDiff = Sets.difference(s1, s2);
        assertThat(sDiff).containsOnly("1");
        //symetric difference
        Sets.SetView<String> sSymDiff = Sets.symmetricDifference(s1, s2);
        assertThat(sSymDiff).containsOnly("1", "4");
        //intersection
        Sets.SetView<String> sIntersect = Sets.intersection(s1, s2);
        assertThat(sIntersect).containsOnly("2", "3");
        //union
        Sets.SetView<String> sUnion = Sets.union(s1, s2);
        assertThat(sUnion).containsOnly("1", "2", "3", "4");
    }

    //It's a very common practice to have a collection of objects and have the need to create a map of those objects,
    // usually to serve as some sort of cache or to enable fast lookups.
    @Test
    public void testMapsUniqueIndex(){
        Map<String, Person> personsMap = Maps.uniqueIndex(persons, new Function<Person, String>() {
            @Override
            public String apply(Person input) {
                return Joiner.on(',').join(input.getLastName(), input.getFirstName());
            }
        });
        assertThat(personsMap).hasSize(persons.size()).containsOnly(
                MapEntry.entry("Flintstone,Wilma", person1),
                MapEntry.entry("Flintstone,Fred", person2),
                MapEntry.entry("Rubble,Betty", person3),
                MapEntry.entry("Rubble,Barney", person4)
        );
    }

    //The Maps.toMap() method takes a set of objects to be used as keys, and Function is applied to each key object
    // to generate the value for entry into a map instance.
    @Test
    public void testMapsToMap(){
        Map<Person, Integer> personAges = Maps.toMap(persons, new Function<Person, Integer>() {
            @Override
            public Integer apply(Person input) {
                return Integer.valueOf(input.getAge());
            }
        });
        assertThat(personAges).hasSize(4).containsOnly(
                MapEntry.entry(person1, 30),
                MapEntry.entry(person2, 32),
                MapEntry.entry(person3, 31),
                MapEntry.entry(person4, 33)
        );
    }

    //Map transformation methods
    @Test
    public void testMapsTransformations(){
        Map<Person, Integer> personAges = Maps.toMap(persons, new Function<Person, Integer>() {
            @Override
            public Integer apply(Person input) {
                return Integer.valueOf(input.getAge());
            }
        });
        //transform values
        Map<Person, String> personAgesStrings = Maps.transformValues(personAges, new Function<Integer, String>() {
            @Override
            public String apply(Integer input) {
                return input.toString();
            }
        });
        assertThat(personAgesStrings).hasSize(4).containsOnly(
                MapEntry.entry(person1, "30"),
                MapEntry.entry(person2, "32"),
                MapEntry.entry(person3, "31"),
                MapEntry.entry(person4, "33")
        );
        //transform entries
        Map<Person, String> personDetails = Maps.transformEntries(personAges, new Maps.EntryTransformer<Person, Integer, String>() {
            @Override
            public String transformEntry(Person key, Integer value) {
                return Joiner.on(", ").join(key.getLastName(), value.toString());
            }
        });
        assertThat(personDetails).hasSize(4).containsOnly(
                MapEntry.entry(person1, "Flintstone, 30"),
                MapEntry.entry(person2, "Flintstone, 32"),
                MapEntry.entry(person3, "Rubble, 31"),
                MapEntry.entry(person4, "Rubble, 33")
        );
    }

}
