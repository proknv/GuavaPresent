package base;

import com.google.common.base.Strings;
import org.junit.Test;

import static org.assertj.core.api.Assertions.*;

/**
 * The Strings class provides a few handy utility methods for working with strings.
 */
public class StringsTest {

    @Test
    public void testStringsPadding(){
        //initialization
        String initialString = "test";
        //padding
        String startPaddedString = Strings.padStart(initialString, 7, '_');
        String endPaddedString = Strings.padEnd(initialString, 7, '_');
        //checking
        assertThat(startPaddedString).isEqualTo("___test");
        assertThat(endPaddedString).isEqualTo("test___");
    }

    //nullToEmpty() method takes a string as an argument and returns the original string if the value is not null
    // or has a length greater than 0, otherwise it returns ""
    @Test
    public void testNullToEmpty(){
        //initialization
        String notNullString = "test";
        String nullString = null;
        //nullToEmpty
        String notChangedString = Strings.nullToEmpty(notNullString);
        String emptyString = Strings.nullToEmpty(nullString);
        //checking
        assertThat(notChangedString).isSameAs(notNullString);
        assertThat(emptyString).isEqualTo("");
    }

    @Test
    public void testEmptyToNull(){
        //initialization
        String notEmptyString = "test";
        String emptyString = "";
        //nullToEmpty
        String notChangedString = Strings.emptyToNull(notEmptyString);
        String nullString = Strings.emptyToNull(emptyString);
        //checking
        assertThat(notChangedString).isSameAs(notEmptyString);
        assertThat(nullString == null);
    }

    @Test
    public void testIsNullOrEmpty(){
        //initialization
        String notEmptyString = "test";
        String emptyString = "";
        String nullString = null;
        //checking
        assertThat(!Strings.isNullOrEmpty(notEmptyString));
        assertThat(Strings.isNullOrEmpty(emptyString));
        assertThat(Strings.isNullOrEmpty(nullString));
    }

}
