package samples.bestpractice.builderfactory;

import samples.bestpractice.Order;
import static samples.bestpractice.builderfactory.BuilderFactories.*;

public class UsageExample {
	public static void main(String[] args) {
		Order anOrder = $Order().withId( 100).build();

		System.out.println("anOrder="+anOrder.toString());
	}
}
