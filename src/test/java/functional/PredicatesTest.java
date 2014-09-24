package functional;

import com.google.common.base.Function;
import com.google.common.base.Functions;
import com.google.common.base.Predicate;
import com.google.common.base.Predicates;
import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static org.assertj.core.api.Assertions.*;

/**
 * Created by proknv on 8/31/14.
 *
 * Predicates are very useful when it comes working with collections. The Predicate interface is used to filter objects.
 *
 * public interface Predicate<T> {
 *      boolean apply(T input)
 *      boolean equals(Object object)
 * }
 *
 * The Predicates class is a collection of useful methods for working with Predicate instances
 *
 */
public class PredicatesTest {

    private static class City {
        private int population;
        private int averageTemperature;

        public int getPopulation() {
            return population;
        }

        public City setPopulation(int population) {
            this.population = population;
            return this;
        }

        public int getAverageTemperature() {
            return averageTemperature;
        }

        public City setAverageTemperature(int averageTemperature) {
            this.averageTemperature = averageTemperature;
            return this;
        }
    }

    private City city;
    private Predicate<City> largeCitiesPredicate;
    private Predicate<City> hotCitiesPredicate;
    private Function<String, City> cityByNameFunction;

    @Before
    public void setup(){
        city = new City().setPopulation(10000).setAverageTemperature(25);
        //predicates
        largeCitiesPredicate = new Predicate<City>() {
            @Override
            public boolean apply(City input) {
                return input.getPopulation() > 30000;
            }
        };
        hotCitiesPredicate = new Predicate<City>() {
            @Override
            public boolean apply(City input) {
                return input.getAverageTemperature() > 20;
            }
        };
        //configuring cityByNameFunction function
        Map<String, City> cityByNameMap = new HashMap<String, City>();
        cityByNameMap.put("Kiev", new City().setPopulation(4000000).setAverageTemperature(10));
        cityByNameMap.put("London", new City().setPopulation(8000000).setAverageTemperature(15));
        cityByNameMap.put("Odessa", new City().setPopulation(1000000).setAverageTemperature(21));
        cityByNameFunction = Functions.forMap(cityByNameMap);
    }

    @Test
    public void testPredicatesAnd(){
        Predicate<City> largeAndHotCityPredicate = Predicates.and(largeCitiesPredicate, hotCitiesPredicate);
        assertThat(largeAndHotCityPredicate.apply(city)).isFalse();
    }

    @Test
    public void testPredicatesOr(){
        Predicate<City> largeOrHotCityPredicate = Predicates.or(largeCitiesPredicate, hotCitiesPredicate);
        assertThat(largeOrHotCityPredicate.apply(city)).isTrue();
    }

    @Test
    public void testPredicatesNot(){
        assertThat(Predicates.not(largeCitiesPredicate).apply(city)).isTrue();
    }

    @Test
    public void testPredicatesCompose(){
        Predicate<String> hotCityNamePredicate = Predicates.compose(hotCitiesPredicate, cityByNameFunction);
        assertThat(hotCityNamePredicate.apply("Kiev")).isFalse();
        assertThat(hotCityNamePredicate.apply("London")).isFalse();
        assertThat(hotCityNamePredicate.apply("Odessa")).isTrue();
    }

}
