package samples.with.generationgap;

import java.beans.ConstructorProperties;
import java.util.Arrays;
import java.util.List;

import net.karneim.pojobuilder.GeneratePojoBuilder;

/**
 * This is a demonstration of the "generation gap" feature.
 * <p>
 * "One of the difficulties of code generation is that generated code and
 * handwritten code need to be treated differently. Generated code should never
 * be edited by hand, otherwise you can't safely regenerate it.
 * 
 * Generation Gap is about keeping the generated and handwritten parts separate
 * by putting them in different classes linked by inheritance. " (Martin Fowler,
 * http://martinfowler.com/dslCatalog/generationGap.html)
 * </p>
 * 
 * The PojoBuilder will generate two classes if you set the "withGenerationGap"
 * directive to "true".
 * <ul>
 * <li>The "AbstractContactBuilder" contains the generated code and souldn't be
 * modified.</li>
 * <li>The "ContactBuilder" is an extension of the abstract builder and can be
 * modified manually. To prevent this class from being overwritten please move
 * it out of the generated-sources folder.</li>
 * </ul>
 * 
 */
@GeneratePojoBuilder(withGenerationGap = true)
public class Contact {
    private final String name;
    private List<String> emailAddresses;

    @ConstructorProperties({ "name" })
    public Contact(String aName) {
        this.name = aName;
    }

    public List<String> getEmailAddresses() {
        return emailAddresses;
    }

    public void setEmailAddresses(List<String> emailAddresses) {
        this.emailAddresses = emailAddresses;
    }

    public String getName() {
        return name;
    }

    public String toString() {
        return "Contact: name=" + name + ", emailAddresses="
                + (emailAddresses == null ? null : Arrays.toString(emailAddresses.toArray()));
    }
}
