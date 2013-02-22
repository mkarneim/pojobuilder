package samples.with.generationgap.andfactory;

import net.karneim.pojobuilder.GeneratePojoBuilder;

public class ContactFactory {

	@GeneratePojoBuilder(withGenerationGap = true)
	public static Contact newContact() {
		return new Contact();
	}
}
