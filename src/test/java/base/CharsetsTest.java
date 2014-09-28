package base;

import com.google.common.base.Charsets;
import org.junit.Test;

import static org.assertj.core.api.Assertions.*;

/**
 * In Java, there are six standard character sets that are supported on every Java platform.
 * The Charsets class provides static final references to the six character sets supported on the Java platform.
 */
public class CharsetsTest {

    @Test
    public void testCharsets(){
        //initialization
        String stringToTransform = "Test String";
        byte[] expectedUtf8Bytes = new byte[] {84,101,115,116,32,83,116,114,105,110,103};
        byte[] expectedUtf16Bytes = new byte[] {-2,-1,0,84,0,101,0,115,0,116,0,32,0,83,0,116,0,114,0,105,0,110,0,103};
        //transformation
        byte[] utf8Bytes = stringToTransform.getBytes(Charsets.UTF_8);
        byte[] utf16Bytes = stringToTransform.getBytes(Charsets.UTF_16);
        //checking
        assertThat(utf8Bytes).containsExactly(expectedUtf8Bytes);
        assertThat(utf16Bytes).containsExactly(expectedUtf16Bytes);
    }

}
