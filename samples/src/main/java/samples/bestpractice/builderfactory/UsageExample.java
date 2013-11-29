package samples.bestpractice.builderfactory;

import static samples.bestpractice.builderfactory.BuilderFactories.$Order;
import samples.bestpractice.Order;

public class UsageExample {
    public static void main(String[] args) {
        Order anOrder = $Order().withId(100).build();

        System.out.println("anOrder=" + anOrder.toString());
    }
}
