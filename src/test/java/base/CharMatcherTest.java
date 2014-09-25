package base;

import com.google.common.base.CharMatcher;
import org.junit.Test;

import static org.assertj.core.api.Assertions.*;

/**
 * The CharMatcher class provides functionality for working with characters based on the presence or absence
 * of a type of character or a range of characters.
 *
 * The CharMatcher class is powerful and is very useful class for text processing.
 */
public class CharMatcherTest {

    @Test
    public void testCollapseWhiteSpaces(){
        //initialization
        String stringWithSpacesAndTabs = "String  with      spaces    and\ttabs";
        String expected = "String with spaces and tabs";
        //execution
        String scrubbed = CharMatcher.WHITESPACE.collapseFrom(stringWithSpacesAndTabs,' ');
        //checking
        assertThat(scrubbed).isEqualTo(expected);
    }

    @Test
    public void testRemovingLineBreaks(){
        //initialization
        String stringWithLineBreaks = "String\nwith\nline\nbreaks";
        String expected = "String with line breaks";
        //execution
        String scrubbed = CharMatcher.BREAKING_WHITESPACE.replaceFrom(stringWithLineBreaks, ' ');
        //checking
        assertThat(scrubbed).isEqualTo(expected);
    }

    @Test
    public void testRetainFrom(){
        //initialization
        String stringWithLettersAndNumbers = "xyz12345fed976qwerty098";
        String expectedString = "12345976098";
        //execution
        String retained = CharMatcher.JAVA_DIGIT.retainFrom(stringWithLettersAndNumbers);
        //checking
        assertThat(retained).isEqualTo(expectedString);
    }

    //You can use also a combination of CharMatcher classes
    @Test
    public void testCombinationOfCharMatcher(){
        //initialization
        String stringWithLettersAndNumbersAndWhitespaces = "xyz12\t345 fed9 76    qwerty0   98";
        String expectedString = "xyzfedqwerty";
        //execution
        String cleaned = CharMatcher.JAVA_DIGIT.or(CharMatcher.WHITESPACE).removeFrom(
                stringWithLettersAndNumbersAndWhitespaces);
        //checking
        assertThat(cleaned).isEqualTo(expectedString);
    }
}
