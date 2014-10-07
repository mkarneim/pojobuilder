package samples;

import java.util.List;

import net.karneim.pojobuilder.GeneratePojoBuilder;

@GeneratePojoBuilder(withBuilderInterface = Builder.class, withBuilderProperties = true)
public class Order {
  public Recipient recipient;
  public List<Item> items;
}
