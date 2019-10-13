package boston.codingdojo;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class AlternativeNoParameterAgedBrieTest {
	


	private GildedRose inn;

	@Test
	public void testAgedBrieIncrements() {
		int daysToExpire = 5;
		int quality = 7;
		int expectedQuality = 8;
		Item item = createInnWithStandardAgedBrie(daysToExpire, quality);
		inn.updateQuality();
		assertEquals(expectedQuality, item.quality);
		System.out.format("%s Quality => %s %s%n",daysToExpire,quality,expectedQuality);
	}


	@Test
	public void testAgedBrieDaysToExpireDecrements() {
		int daysToExpire = 5;
		int quality = 7;
		int expectedDaysToExpire = daysToExpire + -1;
		Item item = createInnWithStandardAgedBrie(daysToExpire, quality);
		inn.updateQuality();
		assertEquals(expectedDaysToExpire, item.sell_in);
	}
	
	@Test
	public void testAgedBrieQualityCapsAt50() {
		int daysToExpire = 5;
		int quality = 50;
		int expectedQuality = quality;
		Item item = createInnWithStandardAgedBrie(daysToExpire, quality);
		inn.updateQuality();
		assertEquals(expectedQuality, item.quality);
		System.out.format("%s Quality => %s %s%n",daysToExpire,quality,expectedQuality);
	}
	
	@Test
	public void testAgedBrieQualityStableAfterDaysToExpireAt50() {
		int daysToExpire = 0;
		int quality = 50;
		Item item = createInnWithStandardAgedBrie(daysToExpire, quality);
		inn.updateQuality();
		assertEquals(quality, item.quality);
		System.out.format("%s Quality => %s %s%n",daysToExpire,quality,quality);
	}
	
	private Item createInnWithStandardAgedBrie(int daysToExpire, int quality) {
		Item[] items = {new Item("Aged Brie",daysToExpire,quality)};
		inn = new GildedRose(items);
		return items[0];
	}

	@Test
	public void testAgedBrieQualityIncreasesByTwoAfterDaysToExpireUnder50() {
		int daysToExpire = 0;
		int quality = 46;
		int expectedQuality = quality + 2;
		Item item = createInnWithStandardAgedBrie(daysToExpire, quality);
		inn.updateQuality();
		assertEquals(expectedQuality, item.quality);
		System.out.format("%s Quality => %s %s",daysToExpire,quality,expectedQuality);
	}
}
