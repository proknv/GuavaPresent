package base;

import com.google.common.base.MoreObjects;
import com.google.common.base.Objects;
import com.google.common.collect.ComparisonChain;
import org.junit.Test;
import static org.assertj.core.api.Assertions.*;

/**
 * Created by proknv on 8/31/14.
 */
public class ObjectsTest {

    private static class TestClass implements Comparable<TestClass>{
        private String name;
        private String value;

        public String getName() {
            return name;
        }
        public void setName(String name) {
            this.name = name;
        }
        public String getValue() {
            return value;
        }
        public void setValue(String value) {
            this.value = value;
        }

        @Override
        public String toString() {
            return MoreObjects.toStringHelper(this)
                    .omitNullValues()
                    .add("name", name)
                    .add("value", value)
                    .toString();
        }

        @Override
        public int compareTo(TestClass o) {
            return ComparisonChain.start()
                    .compare(this.getName(), o.getName())
                    .compare(this.getValue(), o.getValue())
                    .result();
        }
    }

    @Test
    public void testToString(){
        TestClass test = new TestClass();
        test.setName("AAA");
        test.setValue("BBB");
        String toStringTest = test.toString();
        assertThat(toStringTest).isEqualTo("TestClass{name=AAA, value=BBB}");
    }

    @Test
    public void testFirstNonNull(){
        String nullString = null;
        String notNullString = "test";
        String usefulString = MoreObjects.firstNonNull(nullString, notNullString);
        assertThat(usefulString).isNotNull();
    }

    @Test(expected = NullPointerException.class)
    public void testFirstNonNullWithBothNulls(){
        String nullString = null;
        String nullString1 = null;
        String usefulString = MoreObjects.firstNonNull(nullString, nullString1);
        assertThat(usefulString).isNull();
    }

    @Test
    public void testFirstNonNullWithBothNotNulls(){
        String string = "test1";
        String string1 = "test2";
        String usefulString = MoreObjects.firstNonNull(string, string1);
        assertThat(usefulString).isEqualTo(string);
    }

    @Test
    public void testHashCodeBuilder(){
        TestClass test = new TestClass();
        test.setName("Name");
        test.setValue("Value");
        TestClass test1 = new TestClass();
        test1.setName("Name1");
        test1.setValue("Value");
        TestClass test2 = new TestClass();
        test2.setName("Name");
        test2.setValue("Value");

        int testHashCode =  Objects.hashCode(test.getName(), test.getValue());
        int testHashCode1 =  Objects.hashCode(test1.getName(), test1.getValue());
        int testHashCode2 =  Objects.hashCode(test2.getName(), test2.getValue());

        assertThat(testHashCode).isNotEqualTo(testHashCode1);
        assertThat(testHashCode).isEqualTo(testHashCode2);
    }


    @Test
    public void testComparisonChain(){
        TestClass test = new TestClass();
        test.setName("Name");
        test.setValue("Value");
        TestClass test1 = new TestClass();
        test1.setName("Name1");
        test1.setValue("Value");
        TestClass test2 = new TestClass();
        test2.setName("Name");
        test2.setValue("Value");

        assertThat(test.compareTo(test1) != 0);
        assertThat(test.compareTo(test2) == 0);
    }

}
