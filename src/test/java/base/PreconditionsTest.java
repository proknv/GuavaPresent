package base;

import org.junit.Test;
import static org.assertj.core.api.Assertions.*;
import static com.google.common.base.Preconditions.*;

/**
 * Created by proknv on 8/31/14.
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


}
