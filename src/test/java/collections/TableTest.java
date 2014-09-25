package collections;

import com.google.common.collect.HashBasedTable;
import com.google.common.collect.Table;
import org.junit.Before;
import org.junit.Test;

import java.util.Map;

import static org.assertj.core.api.Assertions.*;

/**
 * A Table is a collection that takes two keys, and maps those keys to a single value.
 */
public class TableTest {

    private Table<Integer, Integer, String> table;

    @Before
    public void setup(){
        table = HashBasedTable.create();
        table.put(1, 1, "value11");
        table.put(1, 2, "value12");
        table.put(2, 1, "value21");
        table.put(3, 1, "value31");
    }

    @Test
    public void testTableOperations(){

        //checking
        assertThat(table.contains(1, 2)).isTrue();
        assertThat(table.contains(3, 2)).isFalse();
        assertThat(table.containsColumn(2)).isTrue();
        assertThat(table.containsRow(3)).isTrue();
        assertThat(table.containsValue("value31")).isTrue();

        table.remove(1, 1);
        assertThat(table.containsValue("value11")).isFalse();

        String value31 = table.get(3, 1);
        assertThat(value31).isEqualTo("value31");

    }

    //The table provides some great methods for obtaining different views of the underlying data in the table:
    //The column() method returns a map where the keys are all row-value mappings with the given column's key value.
    //The row() method returns the converse, returning column-value mappings with the given row's key value.
    //Notice, the maps returned are live views.
    @Test
    public void testTableViews(){
        //column view
        Map<Integer, String> columnView = table.column(1);
        assertThat(columnView).hasSize(3).containsValue("value11").containsValue("value21").containsValue("value31");
        //row view
        Map<Integer, String> rowView = table.row(1);
        assertThat(rowView).hasSize(2).containsValue("value11").containsValue("value12");
        //testing column/row view liveness
        columnView.put(1, "value_11");
        assertThat(table.get(1, 1)).isEqualTo("value_11");
    }


    //There are other implementations of the table in Guava:
    // 1. ArrayTable is an implementation of the table backed by a two-dimensional array.
    // 2. There is an ImmutableTable implementation. Since ImmutableTable can't be updated after it's created,
    //      the row, key, and values are added using ImmutableTable.Builder, which leverages a fluent interface
    //      for ease of construction.
    // 3. A TreeBasedTable table where the row and column keys are ordered, either by the natural order or by
    // specified comparators for the row and column keys.


}
