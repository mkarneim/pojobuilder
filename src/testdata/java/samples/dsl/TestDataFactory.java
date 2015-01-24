package samples.dsl;

import samples.AddressBuilder;
import samples.ItemBuilder;
import samples.OrderBuilder;
import samples.RecipientBuilder;

/**
 * This is the domain-specific test data factory.
 * <p>
 * It provides factory methods for creating pre-configured builders.
 * <p>
 * It makes sense that these factories define typical default values for the builders.
 */
public class TestDataFactory extends TestDslBase {

  public static RecipientBuilder $Recipient() {
    return new RecipientBuilder().withName($String());
  }

  public static AddressBuilder $Address() {
    return new AddressBuilder().withCity($String().withFormat("City-%s"))
        .withPostCode($String().withFormat("PostCode-%s")).withStreet($String().withFormat("Street-%s"));
  }

  public static OrderBuilder $Order() {
    return new OrderBuilder().withRecipient($Recipient());
  }

  public static ItemBuilder $Item() {
    return new ItemBuilder().withArticle($String().withFormat("Item-%s")).withAmount($Integer());
  }

}
