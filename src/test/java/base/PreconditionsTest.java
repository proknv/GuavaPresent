package base;

import org.junit.Test;
import static org.assertj.core.api.Assertions.*;
import static com.google.common.base.Preconditions.*;

/**
 * The Preconditions class is a collection of static methods used to verify the state of our code.
 * Preconditions are very important because they guarantee our expectations for successful code execution are met.
 */
public class PreconditionsTest {

    @Test(expected = NullPointerException.class)
    public void testCheckNotNull(){
        Object nullObject = null;
        checkNotNull(nullObject, "Must not be null!");
    }

    @Test(expected = IllegalStateException.class)
    public void testObjectHasIllegalState(){
        class ObjClass {
            public int state;
        }
        ObjClass object = new ObjClass();
        object.state = 0;
        checkState(object.state > 0, "Object has illegal state!");
    }

    @Test(expected = IllegalArgumentException.class)
    public void testInvalidArgumentError(){
        int passedArgument = -1;
        checkArgument(passedArgument > 0, "Invalid argument has been passed!");
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void testIndexOutOfBounds(){
        int arrayIndex = 5;
        int[] array = new int[4];
        checkElementIndex(arrayIndex, array.length, "Invalid element index used!");
    }

}
