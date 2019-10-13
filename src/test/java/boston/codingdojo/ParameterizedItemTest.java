package boston.codingdojo;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

import java.util.Arrays;

import static org.junit.Assert.assertEquals;

@RunWith(Parameterized.class)
public class ParameterizedItemTest {

	private GildedRose inn;

	private String testDescription;
	private String product;
	private int initialDaysToExpire;
	private int initialQuality;
	private int expectedDaysToExpire;
	private int expectedQuality;
	public static final String BACKSTAGE_PASSES = "Backstage passes to a TAFKAL80ETC concert";
	public static final String AGED_BRIE = "Aged Brie";
	public static final String SULFURAS = "Sulfuras, Hand of Ragnaros";

//TODO Copy tests from other product types into our data array
	
	@Parameters(name="{index}: {0}")
	public static Iterable<Object[]> data() {
		return(Arrays.asList(new Object[][] {
			// description, product, initial daysToExpire, initial quality, expected quality
				// normal items
                {"Normal item degrades by 1 when not expired (5)",         "foo", 5, 7, 7-1},
				{"Normal item degrades by 1 when not expired (1)",         "foo", 1, 7, 7-1},

				{"Normal item degrades double when expire = 0",            "foo", 0, 7, 7-2},
				{"Normal item degrades double when expire < 0",            "foo",-1, 7, 7-2},

				{"Normal item quality does not degrade when quality zero", "foo", 4, 0, 0},
				{"Normal item quality does not degrade when quality zero", "foo", 0, 0, 0},

				// Sulfuras
				{"Sulfuras quality does not change",             SULFURAS, 3, 80, 80},
                {"Sulfuras quality does not change when expire", SULFURAS, -1, 80, 80},

				// AgedBrie
				{"AgedBrie quality +1 (5)", "Aged Brie", 5,  7, 7+1},
				{"AgedBrie quality +1 (1)", "Aged Brie", 1,  7, 7+1},

				{"AgedBrie quality +2 when expire (0)", "Aged Brie", 0, 41, 41+2},
				{"AgedBrie quality +2 when expire (-2)", "Aged Brie",-2, 46, 46+2},

				{"AgedBrie quality caps at 50 when expire (0)", "Aged Brie", 0, 50, 50},
                {"AgedBrie quality caps at 50 when expire (-1)", "Aged Brie",-1, 50, 50},
                {"AgedBrie quality caps at 50 when expire and q=49", "Aged Brie", 0, 49, 50},

				{"AgedBrie quality caps at 50", "Aged Brie", 2, 50, 50}, // not 50+1
				{"AgedBrie quality caps at 50", "Aged Brie", -1, 49, 50}, // not 49+2

				// Backstage passes
				{"Backstage passes increase by 1 when > 10",     BACKSTAGE_PASSES, 11, 40, 40+1},

				{"Backstage passes increase by 2 when 6-10 (6)", BACKSTAGE_PASSES,  6, 40, 40+2},
				{"Backstage passes increase by 2 when 6-10 (8)", BACKSTAGE_PASSES,  8, 40, 40+2},
				{"Backstage passes increase by 2 when 6-10 (10)", BACKSTAGE_PASSES,10, 40, 40+2},

				{"Backstage passes increase by 3 when 6-10 (1)", BACKSTAGE_PASSES,  1, 40, 40+3},
				{"Backstage passes increase by 3 when 6-10 (3)", BACKSTAGE_PASSES,  3, 40, 40+3},
				{"Backstage passes increase by 3 when 6-10 (5)", BACKSTAGE_PASSES,  5, 40, 40+3},

				{"Backstage caps at 50 (3)",  BACKSTAGE_PASSES,  3, 48, 50 }, // not 48+3
				{"Backstage caps at 50 (7)",  BACKSTAGE_PASSES,  7, 49, 50 }, // not 49+2
				{"Backstage caps at 50 (12)", BACKSTAGE_PASSES,  1, 50, 50 }, // not 50+1

		}));
	}
	
	public ParameterizedItemTest(
			String testDescription,
			String product,
			int initialDaysToExpire,
			int initialQuality,
			int expectedQuality )
	{
		this.testDescription = testDescription;
		this.product = product;
		this.initialDaysToExpire = initialDaysToExpire;
		this.initialQuality = initialQuality;
		this.expectedQuality = expectedQuality;
	}

	@Test
	public void parameterizedItemTest() {
		Item[] items = {new Item(product,initialDaysToExpire,initialQuality)};
		inn = new GildedRose(items);
		Item item = items[0];
		inn.updateQuality();
		assertEquals(expectedQuality, item.quality);
		if (product == SULFURAS) {
			assertEquals (initialDaysToExpire, item.sell_in);
		} else {
			assertEquals (initialDaysToExpire-1, item.sell_in);
		}
		// assertEquals(expectedDaysToExpire, item.daysToExpire);
	}


   


}
