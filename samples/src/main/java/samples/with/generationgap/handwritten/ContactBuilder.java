package samples.with.generationgap.handwritten;

import java.util.ArrayList;
import java.util.List;

/**
 * The {@link ContactBuilder} is a Builder for {@link Contact} objects.
 * <p>
 * This class has been moved out of the generated-sources folder 
 * to prevent it from being overwritten! 
 * It contains handwritten extensions to the {@link AbstractContactBuilder}.
 * 
 * @created initially by the PojoBuilder generator 
 */
public class ContactBuilder extends AbstractContactBuilder implements Cloneable {

	/**
	 * Creates a new {@link ContactBuilder}.
	 */
	public ContactBuilder() {
	}

	public ContactBuilder withEmailAddress(String aValue) {
		List<String> list = new ArrayList<String>();
		list.add(aValue);
		return this.withEmailAddresses(list);
	}

}