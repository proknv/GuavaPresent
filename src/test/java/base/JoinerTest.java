package base;

import com.google.common.base.Joiner;
import com.google.common.collect.Maps;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.*;

/**
 * Created by proknv on 8/30/14.
 *
 * Using the Joiner class
 *
 * Once created, a Joiner class is immutable, and therefore thread-safe
 */
public class JoinerTest {

    @Test
    public void testJoinerWithSkippedNulls(){
        //initialization
        String expectedString = "First, Second, Third, Fourth";
        List<String> listOfStrings = Arrays.asList("First", "Second", "Third", null, "Fourth", null);
        String delimiter = ", ";
        //joining
        String joinedString = Joiner.on(delimiter).skipNulls().join(listOfStrings);
        //checking
        assertThat(joinedString).isEqualTo(expectedString);
    }

    @Test
    public void testJoinerWithNullsReplaced(){
        //initialization
        String expectedString = "First, Second, Third, Empty, Fourth, Empty";
        List<String> listOfStrings = Arrays.asList("First", "Second", "Third", null, "Fourth", null);
        String delimiter = ", ";
        //joining
        String joinedString = Joiner.on(delimiter).useForNull("Empty").join(listOfStrings);
        //checking
        assertThat(joinedString).isEqualTo(expectedString);
    }

    @Test(expected = UnsupportedOperationException.class)
    public void testJoinerImmutability(){
        //initialization
        String expectedString = "First, Second, Third, Fourth";
        List<String> listOfStrings = Arrays.asList("First", "Second", "Third", null, "Fourth", null);
        String delimiter = ", ";
        Joiner joiner = Joiner.on(delimiter).skipNulls();
        //we can't use useForNull here for already configured joiner
        joiner.useForNull("Empty");
        //joining
        String joinedString = joiner.join(listOfStrings);
        //checking
        assertThat(joinedString).isEqualTo(expectedString);

    }

    //The Joiner class can be used with classes that implement the Appendable interface.
    @Test
    public void testJoinerWithAppendable(){
        //initialization
        String expectedString = "First, Second, Third, Fourth";
        List<String> listOfStrings = Arrays.asList("First", "Second", "Third", null, "Fourth", null);
        String delimiter = ", ";
        //joining
        StringBuilder stringBuilder = new StringBuilder();
        Joiner joiner = Joiner.on(delimiter).skipNulls();
        joiner.appendTo(stringBuilder, listOfStrings);
        //checking
        assertThat(stringBuilder.toString()).isEqualTo(expectedString);
    }

    //The Joiner.MapJoiner class works in the same way as the Joiner class
    // but it joins the given strings as key value pairs with a specified delimiter.
    @Test
    public void testMapJoiner(){
        //initialization
        String expectedString = "k1=v1;k2=v2;k3=v3";
        Map<String, String> testMap = Maps.newLinkedHashMap();
        testMap.put("k1", "v1");
        testMap.put("k2", "v2");
        testMap.put("k3", "v3");
        //joining
        String resultString = Joiner.on(";").withKeyValueSeparator("=").join(testMap);
        //checking
        assertThat(resultString).isEqualTo(expectedString);
    }

}
