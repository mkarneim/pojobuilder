package samples.dsl;

import java.util.List;

import org.junit.Test;

import samples.Address;
import samples.Builder;
import samples.Item;
import samples.Order;
import samples.OrderBuilder;
import samples.Recipient;
import samples.RecipientBuilder;

/**
 * This test class demonstrates how you could use the static factory methods provided by {@link TestDataFactory}
 * and {@link TestDslBase} .
 */
public class TestDslTest extends TestDataFactory {

  /**
   * The method {@link #some(samples.Builder)} is just a convenient shortcut to 'builder.build()'.
   * It makes the code more fluent readable.
   * <p>
   * The method {@link #$String()} returns a generated {@link StringBuilder} that can build String
   * objects.
   * <p>
   * Every call produces a unique String.
   */
  @Test
  public void testSome$String_CreatesUniqueValue() {
    // When:
    String a = some($String());
    String b = some($String());

    // Then:
    assertThat(a).isNotEqualTo(b);
  }

  /**
   * The method {@link #$Long()} returns a hand-written {@link LongBuilder} that can build Long
   * objects.
   * <p>
   * Every call produces a unique Long.
   */
  @Test
  public void testSome$Long_CreatesUniqueValue() {
    // When:
    Long a = some($Long());
    Long b = some($Long());

    // Then:
    assertThat(a).isNotEqualTo(b);
  }

  /**
   * The method {@link #$Recipient()} returns a generated {@link RecipientBuilder}.
   * <p>
   * This builder is configured with some default values. In this case it defines the default value
   * for the recipient's name, but not the recipient's address.
   */
  @Test
  public void testSome$Recipient() {
    // Given:

    // When:
    Recipient act = some($Recipient());

    // Then:
    assertThat(act.getName()).isNotEmpty();
    assertThat(act.getAddress()).isNull();
  }

  /**
   * Repeated calls to some($Recipient()) will produce unique {@link Recipient} instances with
   * different names.
   * <p>
   * This is because {@link #$Recipient()} uses a {@link StringBuilder} to define the recipient's
   * name, which generates a unique value on every call.
   */
  @Test
  public void testSome$Recipient_CreatesUniqueValues() {
    // Given:

    // When:
    Recipient a = some($Recipient());
    Recipient b = some($Recipient());

    // Then:
    assertThat(a).isNotEqualTo(b);
    assertThat(a.getName()).isNotEqualTo(b.getName());
  }

  /**
   * We can override the default name that is defined in {@link #$Recipient()} like this.
   */
  @Test
  public void testSome$Recipient_WithName() {
    // Given:
    String name = some($String());

    // When:
    Recipient act = some($Recipient().withName(name));

    // Then:
    assertThat(act.getName()).isEqualTo(name);
  }

  /**
   * We can define the recipient's address by using {@link #$Address()}.
   * <p>
   * Please note, that {@link #$Address()} conveniently defines default values for all properties of
   * {@link Address}.
   */
  @Test
  public void testSome$Recipient_With$Address() {
    // Given:
    String name = some($String());

    // When:
    Recipient act = some($Recipient().withName(name).withAddress($Address()));

    // Then:
    assertThat(act.getName()).isEqualTo(name);
    assertThat(act.getAddress()).isNotNull();
    assertThat(act.getAddress().getStreet()).isNotNull();
    assertThat(act.getAddress().getCity()).isNotNull();
    assertThat(act.getAddress().getPostCode()).isNotNull();
  }

  /**
   * The method {@link #$listOf(samples.Builder...)} returns a list of the given builder instances.
   * <p>
   * By wrapping it into a call to {@link #some(samples.Builder)} you can produce a list of pojos
   * built by these builders.
   * <p>
   * The number of pojos is equal to the number of builders.
   */
  @Test
  public void testSome$ListOf$Recipient_And$Recipient() {
    // Given:

    // When:
    List<Recipient> act = some($listOf($Recipient(), $Recipient()));

    // Then:
    assertThat(act).hasSize(2);
  }

  /**
   * The method {@link #$listOf(int, samples.Builder)} produces a list of builders with the given
   * number of elements.
   * <p>
   * By wrapping it into a call to {@link #some(samples.Builder)} you can produce a list of pojos
   * built by these builders.
   * <p>
   * This is especially useful if you want to create a large number of pojos.
   */
  @Test
  public void testSome$ListOf10$Recipients() {
    // Given:

    // When:
    List<Recipient> act = some($listOf(10, $Recipient()));

    // Then:
    assertThat(act).hasSize(10);
  }

  /**
   * The method {@link #$Order()} returns a generated {@link OrderBuilder} with some default values.
   * <p>
   * In this case the order's recipient is defined, but no order items.
   */
  @Test
  public void testSome$Order() {
    // Given:

    // When:
    Order act = some($Order());

    // Then:
    assertThat(act.recipient).isNotNull();
    assertThat(act.items).isNull();
  }

  /**
   * Of course we can call {@link #$listOf(int, samples.Builder)} inline and assign the produced
   * list of item builders directly to the order builder.
   * <p>
   * That means, that on every call to some($Order()) we will get a new {@link Order} with a
   * complete unique list of items.
   */
  @Test
  public void testSome$Order_With$ListOf5$Items() {
    // Given:

    // When:
    Order act = some($Order().withItems($listOf(5, $Item())));

    // Then:
    assertThat(act.recipient).isNotNull();
    assertThat(act.items).hasSize(5);
  }

  /**
   * We also could call {@link #$listOf(samples.Builder...)} inline. Here we can enumerate the
   * concrete list of item builders that should be used for creating the list of items.
   * <p>
   * As you can see, you can override the default values of the items' properties.
   */
  @Test
  public void testSome$Order_With$ListOf$Item_And$Item() {
    // Given:

    // When:
    Order act = some($Order().withItems($listOf($Item().withAmount(10), $Item().withAmount(20))));

    // Then:
    assertThat(act.recipient).isNotNull();
    assertThat(act.items).hasSize(2);
    assertThat(act.items.get(0).amount).isEqualTo(10);
    assertThat(act.items.get(1).amount).isEqualTo(20);
  }

  /**
   * If you want to override the default values of many builders you can use the method
   * {@link #$from(Object...)}.
   * <p>
   * Essentially this method returns a generic {@link Builder} whose build() method cycles through
   * the given values and returns them one-by-one.
   */
  @Test
  public void testSome$ListOf5$Items_WithAmount_$OneOf_Enumerated_Values() {
    // Given:

    // When:
    List<Item> act = some($listOf(5, $Item().withAmount($oneOf(10, 20, 30, 40, 50))));

    // Then:
    assertThat(act.get(0).amount).isEqualTo(10);
    assertThat(act.get(1).amount).isEqualTo(20);
    assertThat(act.get(2).amount).isEqualTo(30);
    assertThat(act.get(3).amount).isEqualTo(40);
    assertThat(act.get(4).amount).isEqualTo(50);
  }

  /**
   * If you want to cycle through a list of builders, you can use the method
   * {@link #$from(Builder...)}.
   */
  @Test
  public void testSome$ListOf2$Order_WithRecipient_$OneOf_Enumerated_Builders() {
    // Given:

    // When:
    List<Order> act =
        some($listOf(
            2,
            $Order().withRecipient(
                $oneOf($Recipient().withName("Peter"), $Recipient().withName("Paul")))));

    // Then:
    assertThat(act.get(0).recipient.getName()).isEqualTo("Peter");
    assertThat(act.get(1).recipient.getName()).isEqualTo("Paul");
  }



}
