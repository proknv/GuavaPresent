package collections;

import com.google.common.collect.ImmutableListMultimap;
import com.google.common.collect.Multimap;
import org.junit.Before;
import org.junit.Test;

import static org.assertj.core.api.Assertions.*;

/**
 * If we don't explicitly have a need for a mutable collection, we should always favor using an immutable one
 */
public class ImmutableCollectionsTest {

    private Multimap<Integer, String> multimap;

    @Before
    public void setup(){
        multimap = new ImmutableListMultimap.Builder<Integer, String>()
                .put(1, "AAA")
                .putAll(2, "BBB", "CCC")
                .putAll(3, "DDD", "EEE", "FFF")
                .build();
    }

    @Test
    public void testCollectionCreation(){
        assertThat(multimap.get(1)).contains("AAA");
        assertThat(multimap.get(3)).containsOnly("DDD", "EEE", "FFF");
    }

    @Test(expected = UnsupportedOperationException.class)
    public void testCollectionImmutability(){
        multimap.put(4, "JJJ");
    }

}
