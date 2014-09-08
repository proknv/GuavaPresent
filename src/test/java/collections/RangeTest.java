package collections;

import com.google.common.collect.Range;
import org.junit.Test;

import static org.assertj.core.api.Assertions.*;

/**
 * Created by proknv on 9/8/14.
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

}
