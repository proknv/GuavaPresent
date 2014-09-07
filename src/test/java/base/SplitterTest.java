package base;

import com.google.common.base.Splitter;
import org.assertj.core.data.MapEntry;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.*;

/**
 * Created by proknv on 8/30/14.
 *
 * The Splitter class performs the inverse of the functions of the Joiner class.
 * A Splitter class can split on a single character, a fixed string, a java.util.regex.Pattern package,
 * a string representing a regular expression, or a CharMatcher class.
 * A Splitter instance is created by calling the on method and specifying the separator to be used.
 * Once you have the Splitter instance, you will call the split() method, which returns an iterable object,
 * containing the individual string parts from the source.
 *
 */
public class SplitterTest {

    @Test
    public void testSplitterSimple(){
        //initialization
        String stringToSplit = "First, Second, Third";
        String delimiter = ", ";
        List<String> expectedResult = Arrays.asList("First", "Second", "Third");
        //splitting
        Iterable<String> splitResult = Splitter.on(delimiter).split(stringToSplit);
        //checking
        assertThat(splitResult).containsExactlyElementsOf(expectedResult);
    }

    @Test
    public void testSplitterOnRegularExpression(){
        //initialization
        String stringToSplit = "First123Second3567Third";
        String delimiter = "\\d+";
        List<String> expectedResult = Arrays.asList("First", "Second", "Third");
        //splitting
        Iterable<String> splitResult = Splitter.onPattern(delimiter).split(stringToSplit);
        //checking
        assertThat(splitResult).containsExactlyElementsOf(expectedResult);
    }

    //The Splitter class also has an option for dealing with any leading or trailing whitespace in the trimResults method.
    @Test
    public void testSplitterWithTrimming(){
        //initialization
        String stringToSplit = "First123  Second  3567 Third";
        String delimiter = "\\d+";
        List<String> expectedResult = Arrays.asList("First", "Second", "Third");
        //splitting
        Iterable<String> splitResult = Splitter.onPattern(delimiter).trimResults().split(stringToSplit);
        //checking
        assertThat(splitResult).containsExactlyElementsOf(expectedResult);
    }

    //Just like the Joiner class, Splitter is immutable on creation, so care must be taken to not call the trimResults
    // method after creating the original Splitter class
    @Test
    public void testSplitterImmutability(){
        //initialization
        String stringToSplit = "First123  Second  3567 Third";
        String delimiter = "\\d+";
        List<String> expectedResult = Arrays.asList("First", "  Second  ", " Third");
        //splitting
        Splitter splitter = Splitter.onPattern(delimiter);
        splitter.trimResults();
        Iterable<String> splitResult = splitter.split(stringToSplit);
        //checking
        assertThat(splitResult).containsExactlyElementsOf(expectedResult);
    }

    //The Splitter class, like Joiner with its accompanying MapJoiner class, has a MapSplitter class.
    @Test
    public void testMapSplitter(){
        //initialization
        String stringToSplit = "First=123;Second=3567;Third=679";
        //splitting
        Map<String, String> splitResult = Splitter.on(";").withKeyValueSeparator("=").split(stringToSplit);
        //checking
        assertThat(splitResult).containsExactly(MapEntry.entry("First", "123"), MapEntry.entry("Second", "3567"),
                MapEntry.entry("Third", "679"));
    }

}
