package functional;

import com.google.common.base.Predicate;
import com.google.common.base.Predicates;
import org.junit.Test;

import static org.assertj.core.api.Assertions.*;

/**
 * Created by proknv on 8/31/14.
 *
 * Predicates are very useful when it comes working with collections
 */
public class PredicatesTest {

    private static class City {
        private int population;
        private int averageTemperature;

        public int getPopulation() {
            return population;
        }

        public void setPopulation(int population) {
            this.population = population;
        }

        public int getAverageTemperature() {
            return averageTemperature;
        }

        public void setAverageTemperature(int averageTemperature) {
            this.averageTemperature = averageTemperature;
        }
    }

    @Test
    public void testPredicates(){
        City city = new City();
        city.setPopulation(10000);
        city.setAverageTemperature(25);
        //predicates
        Predicate<City> largeCitiesPredicate = new Predicate<City>() {
            @Override
            public boolean apply(City input) {
                return input.getPopulation() > 30000;
            }
        };
        Predicate<City> hotCitiesPredicate = new Predicate<City>() {
            @Override
            public boolean apply(City input) {
                return input.getAverageTemperature() > 20;
            }
        };
        //checking
        Predicate<City> largeAndHotCityPredicate = Predicates.and(largeCitiesPredicate, hotCitiesPredicate);
        assertThat(largeAndHotCityPredicate.apply(city)).isFalse();
        assertThat(Predicates.or(largeCitiesPredicate, hotCitiesPredicate).apply(city)).isTrue();
        assertThat(Predicates.not(largeCitiesPredicate).apply(city)).isTrue();
    }


}
