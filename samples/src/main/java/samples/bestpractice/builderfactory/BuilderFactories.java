package samples.bestpractice.builderfactory;

import java.util.ArrayList;
import java.util.Date;

import samples.bestpractice.Item;
import samples.bestpractice.OrderBuilder;

public class BuilderFactories {
	/**
	* This is a builder factory that creates a new OrderBuilder with some default values.
	*/
	public static OrderBuilder $Order() {
		return new OrderBuilder().withDate(new Date()).withItems(new ArrayList<Item>());
	}
}
