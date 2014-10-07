package samples;

import net.karneim.pojobuilder.GeneratePojoBuilder;

public class Item {
  public final int amount;
  public final String article;

  @GeneratePojoBuilder(withBuilderInterface = Builder.class, withBuilderProperties = true)
  public Item(int amount, String article) {
    this.amount = amount;
    this.article = article;
  }

}
