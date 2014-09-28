package collections;

import com.google.common.collect.*;
import org.junit.Before;
import org.junit.Test;

import java.util.Set;

import static org.assertj.core.api.Assertions.*;

/**
 * If we don't explicitly have a need for a mutable collection, we should always favor using an immutable one
 */
public class ImmutableCollectionsTest {

    private Multimap<Integer, String> multimap;
    private BiMap<Integer, String> biMap;
    private Set<String> setOfValues;
    private Table<Integer, Integer, String> table;

    //Immutable collections have handy builders which allows to fill up the collection in a fluent style
    @Before
    public void setup(){
        multimap = new ImmutableListMultimap.Builder<Integer, String>()
                .put(1, "AAA")
                .putAll(2, "BBB", "CCC")
                .putAll(3, "DDD", "EEE", "FFF")
                .build();
        biMap = new ImmutableBiMap.Builder<Integer, String>()
                .put(1, "1")
                .put(2, "2")
                .put(3, "3")
                .build();
        setOfValues = new ImmutableSet.Builder<String>()
                .add("AAA")
                .add("BBB")
                .add("CCC")
                .build();
        table = new ImmutableTable.Builder<Integer, Integer, String>()
                .put(1, 1, "Value11")
                .put(1, 2, "Value12")
                .put(2, 1, "Value21")
                .put(2, 2, "Value22")
                .build();
    }


    @Test
    public void testImmutableMultiMapCreation(){
        assertThat(multimap.get(1)).contains("AAA");
        assertThat(multimap.get(3)).containsOnly("DDD", "EEE", "FFF");
    }

    @Test
    public void testImmutableBiMapCreation(){
        assertThat(biMap.get(1)).isEqualTo("1");
    }

    @Test
    public void testImmutableSetCreation(){
        assertThat(setOfValues).containsExactly("AAA", "BBB", "CCC");
    }

    @Test
    public void testImmutableTableCreation(){
        assertThat(table.get(1, 1)).isEqualTo("Value11");
        assertThat(table.get(2, 1)).isEqualTo("Value21");
    }

    @Test(expected = UnsupportedOperationException.class)
    public void testImmutableMultiMapImmutability(){
        multimap.put(4, "JJJ");
    }

    @Test(expected = UnsupportedOperationException.class)
    public void testImmutableBiMapImmutability(){
        biMap.put(4, "4");
    }

    @Test(expected = UnsupportedOperationException.class)
    public void testImmutableSetImmutability(){
        setOfValues.add("DDD");
    }

    @Test(expected = UnsupportedOperationException.class)
    public void testImmutableTableImmutability(){
        table.put(3, 1, "Value31");
    }
}
