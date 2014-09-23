package collections;

import com.google.common.collect.Collections2;
import com.google.common.collect.Ordering;
import com.google.common.primitives.Ints;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import static org.assertj.core.api.Assertions.*;

/**
 * Created by proknv on 9/22/14.
 */
public class CollectionsOrderingTest {

    private static class City {
        private String name;
        private Integer population;
        private Integer averageRainfall;

        public String getName() {
            return name;
        }

        public City setName(String name) {
            this.name = name;
            return this;
        }

        public Integer getPopulation() {
            return population;
        }

        public City setPopulation(Integer population) {
            this.population = population;
            return this;
        }

        public Integer getAverageRainfall() {
            return averageRainfall;
        }

        public City setAverageRainfall(Integer averageRainfall) {
            this.averageRainfall = averageRainfall;
            return this;
        }
    }

    private Comparator<City> cityByPopulationComparator;
    private Comparator<City> cityByAvarageRainfallComparator;
    private List<City> cities;

    @Before
    public void setup(){
        cityByPopulationComparator = new Comparator<City>() {
            @Override
            public int compare(City o1, City o2) {
                return Ints.compare(o1.getPopulation(), o2.getPopulation());
            }
        };
        cityByAvarageRainfallComparator = new Comparator<City>() {
            @Override
            public int compare(City o1, City o2) {
                return Ints.compare(o1.getAverageRainfall(), o2.getAverageRainfall());
            }
        };
        cities = new ArrayList<City>();
        cities.add(new City().setName("Kiev").setPopulation(4000000).setAverageRainfall(125));
        cities.add(new City().setName("London").setPopulation(8000000).setAverageRainfall(250));
        cities.add(new City().setName("Odessa").setPopulation(1000000).setAverageRainfall(85));
    }

    @Test
    public void testSorting(){
        Ordering<City> cityOrdering = Ordering.from(cityByPopulationComparator);
        List<City> orderedByCityPopulation = cityOrdering.immutableSortedCopy(cities);
        assertThat(orderedByCityPopulation).hasSize(3).extracting("population").containsSequence(1000000, 4000000, 8000000);
    }

    @Test
    public void testReverseSorting(){
        Ordering<City> cityOrdering = Ordering.from(cityByPopulationComparator).reverse();
        List<City> orderedByCityPopulation = cityOrdering.immutableSortedCopy(cities);
        assertThat(orderedByCityPopulation).hasSize(3).extracting("population").containsSequence(8000000, 4000000, 1000000);
    }

    @Test
    public void testSecondarySorting(){
        cities.add(new City().setName("Boston").setPopulation(1000000).setAverageRainfall(105));
        Ordering<City> cityOrdering = Ordering.from(cityByPopulationComparator).compound(cityByAvarageRainfallComparator);
        List<City> orderedByCityPopulation = cityOrdering.immutableSortedCopy(cities);
        assertThat(orderedByCityPopulation).hasSize(4).extracting("name").containsSequence(
                "Odessa", "Boston", "Kiev", "London");
    }
}
