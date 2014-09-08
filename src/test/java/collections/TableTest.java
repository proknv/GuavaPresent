package collections;

import com.google.common.collect.HashBasedTable;
import com.google.common.collect.Table;
import org.junit.Before;
import org.junit.Test;

import java.util.Map;

import static org.assertj.core.api.Assertions.*;

/**
 * Created by proknv on 9/8/14.
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
        assertThat(table.containsColumn(2)).isTrue();
        assertThat(table.containsRow(3)).isTrue();
        assertThat(table.containsValue("value31")).isTrue();

        table.remove(1, 1);
        assertThat(table.containsValue("value11")).isFalse();

        String value31 = table.get(3, 1);
        assertThat(value31).isEqualTo("value31");

    }

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

}
