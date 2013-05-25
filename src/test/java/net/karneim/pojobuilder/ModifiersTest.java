package net.karneim.pojobuilder;

import java.util.Collection;

import javax.annotation.processing.ProcessingEnvironment;
import javax.lang.model.element.TypeElement;
import javax.lang.model.util.Elements;

import net.karneim.pojobuilder.model.BuilderM;
import net.karneim.pojobuilder.model.PropertyM;
import net.karneim.pojobuilder.model.TypeM;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import testdata.modifiers.Contact;
import testenv.AddToSourceTree;
import testenv.ProcessingEnvironmentRunner;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

@RunWith(ProcessingEnvironmentRunner.class)
@AddToSourceTree({ TestBase.SRC_TESTDATA_DIR })
public class ModifiersTest extends TestBase {

	private static final String BUILDER = "ContactBuilder";

	private Elements elements;

	private BuilderModelProducer underTest;

	@Before
	public void setup() {
		ProcessingEnvironment env = ProcessingEnvironmentRunner.getProcessingEnvironment();
		elements = env.getElementUtils();
		TypeMUtils typeMUtils = new TypeMUtils();
		underTest = new BuilderModelProducer(env, typeMUtils);
	}

	@Test
	public void testProduceReturnsBuilderWithCorrectProductType() {
		// Given:
		String pojoClassname = Contact.class.getName();
		TypeElement pojoType = elements.getTypeElement(pojoClassname);

		// When:
		Output output = underTest.produce(new Input(pojoType));
		BuilderM builder = output.getBuilder();

		// Then:
		assertEquals("productType", TypeM.get(pojoClassname), builder.getProductType());
	}

	@Test
	public void testProduceReturnsBuilderWithCorrectBuilderType() {
		// Given:
		String pojoClassname = Contact.class.getName();
		TypeElement pojoType = elements.getTypeElement(pojoClassname);

		// When:
		Output output = underTest.produce(new Input(pojoType));
		BuilderM builder = output.getBuilder();

		// Then:
		assertEquals("type", BUILDER, builder.getType().getSimpleName());
	}

	@Test
	public void testProduceReturnsBuilderWithCorrectSuperType() {
		// Given:
		String pojoClassname = Contact.class.getName();
		TypeElement pojoType = elements.getTypeElement(pojoClassname);

		// When:
		Output output = underTest.produce(new Input(pojoType));
		BuilderM builder = output.getBuilder();

		// Then:
		assertEquals("type", TypeM.get(Object.class.getName()), builder.getSuperType());
	}

	@Test
	public void testProduceReturnsBuilderWithCorrectSelfType() {
		// Given:
		String pojoClassname = Contact.class.getName();
		TypeElement pojoType = elements.getTypeElement(pojoClassname);

		// When:
		Output output = underTest.produce(new Input(pojoType));
		BuilderM builder = output.getBuilder();

		// Then:
		assertEquals("selfType", BUILDER, builder.getSelfType().getSimpleName());
	}

	@Test
	public void testProduceReturnsBuilderWithCorrectProperties() {
		// Given:
		String pojoClassname = Contact.class.getName();
		TypeElement pojoType = elements.getTypeElement(pojoClassname);

		// When:
		Output output = underTest.produce(new Input(pojoType));
		BuilderM builder = output.getBuilder();

		// Then:
		assertEquals("size", 3, builder.getProperties().size());
		assertThat(builder.getProperties(), containsPropertyWithName("surname"));
		assertThat(builder.getProperties(), containsPropertyWithName("firstname"));
		assertThat(builder.getProperties(), containsPropertyWithName("email"));

		PropertyM p0 = getFirstPropertyByName(builder.getProperties(), "surname");
		assertEquals("type of surname", "java.lang.String", p0.getType().getQualifiedName());
		PropertyM p1 = getFirstPropertyByName(builder.getProperties(), "firstname");
		assertEquals("type of firstname", "java.lang.String", p1.getType().getQualifiedName());
		PropertyM p2 = getFirstPropertyByName(builder.getProperties(), "email");
		assertEquals("type of email", "java.lang.String", p2.getType().getQualifiedName());

		assertEquals("selfType", BUILDER, builder.getSelfType().getSimpleName());

		Collection<PropertyM> setterProps = builder.getPropertiesForSetters();
		assertEquals("size of properties to set", 0, setterProps.size());

		Collection<PropertyM> assignProps = builder.getPropertiesForAssignment();
		assertEquals("size of properties to assign", 2, assignProps.size());
	}

}
