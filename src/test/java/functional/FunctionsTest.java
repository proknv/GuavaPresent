package functional;

import com.google.common.base.CharMatcher;
import com.google.common.base.Function;
import com.google.common.base.Functions;
import com.google.common.collect.Maps;
import org.junit.Test;

import java.util.Map;

import static org.assertj.core.api.Assertions.*;

/**
 * Created by proknv on 8/31/14.
 */
public class FunctionsTest {

    @Test
    public void testFunctionsForMap(){
        //initialization
        Map<String, String> dataMap = Maps.newHashMap();
        dataMap.put("Key1", "Value1");
        dataMap.put("Key2", "Value2");
        dataMap.put("Key3", "Value3");
        //execution
        Function<String, String> mapFunction = Functions.forMap(dataMap, null);
        //checking
        for(String testKey : dataMap.keySet()){
            assertThat(mapFunction.apply(testKey)).isEqualTo(dataMap.get(testKey));
        }
        String testKey = "Key5";
        assertThat(mapFunction.apply(testKey)).isNull();
    }

    @Test
    public void testComposeFunctions(){
        //initialization
        //first function
        Map<String, String> dataMap = Maps.newHashMap();
        dataMap.put("Key1", "Value1");
        dataMap.put("Key2", "Value2");
        dataMap.put("Key3", "Value3");
        Function<String, String> firstFunction = Functions.forMap(dataMap, null);
        //second function
        Function<String, Integer> secondFunction = new Function<String, Integer>() {
            @Override
            public Integer apply(String input) {
                return Integer.valueOf(CharMatcher.JAVA_DIGIT.retainFrom(input));
            }
        };
        //execution
        Function<String, Integer> compositeFunction = Functions.compose(secondFunction, firstFunction);
        //checking
        String testKey = "Key3";
        assertThat(compositeFunction.apply(testKey)).isEqualTo(3);
    }

}
