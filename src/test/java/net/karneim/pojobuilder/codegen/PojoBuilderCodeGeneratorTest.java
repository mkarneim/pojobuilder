package net.karneim.pojobuilder.codegen;

import static org.assertj.core.api.Assertions.assertThat;

import java.io.IOException;
import java.io.StringWriter;
import java.io.Writer;

import net.karneim.pojobuilder.codegen.ArgumentTM;
import net.karneim.pojobuilder.codegen.AssignmentTM;
import net.karneim.pojobuilder.codegen.BuildMethodTM;
import net.karneim.pojobuilder.codegen.BuilderClassTM;
import net.karneim.pojobuilder.codegen.ButMethodTM;
import net.karneim.pojobuilder.codegen.CloneMethodTM;
import net.karneim.pojobuilder.codegen.ConstructorCallTM;
import net.karneim.pojobuilder.codegen.ConstructorTM;
import net.karneim.pojobuilder.codegen.CopyMethodTM;
import net.karneim.pojobuilder.codegen.FactoryCallTM;
import net.karneim.pojobuilder.codegen.FieldAccessorTM;
import net.karneim.pojobuilder.codegen.FieldTM;
import net.karneim.pojobuilder.codegen.GeneratedTM;
import net.karneim.pojobuilder.codegen.ImportTM;
import net.karneim.pojobuilder.codegen.InterfaceTM;
import net.karneim.pojobuilder.codegen.MethodAccessorTM;
import net.karneim.pojobuilder.codegen.PackageTM;
import net.karneim.pojobuilder.codegen.PojoBuilderCodeGenerator;
import net.karneim.pojobuilder.codegen.SelfFieldTM;
import net.karneim.pojobuilder.codegen.SetterCallTM;
import net.karneim.pojobuilder.codegen.SetterTM;
import net.karneim.pojobuilder.codegen.SuperclassTM;

import org.junit.Before;
import org.junit.Test;

public class PojoBuilderCodeGeneratorTest {

    private void log(String message) {
        System.out.println(message);
    }

    PojoBuilderCodeGenerator underTest;
    BuilderClassTM builderClassTM = new BuilderClassTM();

    @Before
    public void init() {
        underTest = new PojoBuilderCodeGenerator(builderClassTM);
    }

    @Test
    public void testGenerateWithName() throws IOException {
        // Given:
        builderClassTM.setName("PojoBuilder");

        // When:
        Writer writer = new StringWriter();
        underTest.generate(writer);

        // Then:
        //@formatter:off
        String generatedCode = writer.toString();
        log(generatedCode);
        assertThat(generatedCode).isEqualTo(
                "public class PojoBuilder {"+"\n"
                +"}");
        //@formatter:on
    }

    @Test
    public void testGenerateWithSelfField() throws IOException {
        // Given:
        builderClassTM.setName("PojoBuilder");
        builderClassTM.setSelfField(new SelfFieldTM("PojoBuilder"));

        // When:
        Writer writer = new StringWriter();
        underTest.generate(writer);

        // Then:
        //@formatter:off
        String generatedCode = writer.toString();
        log(generatedCode);
        assertThat(generatedCode).isEqualTo(
                "public class PojoBuilder {"+"\n"
                + "    protected PojoBuilder self;"+"\n"
                +"}");
        //@formatter:on
    }

    @Test
    public void testGenerateWithGeneratedAnnotation() throws IOException {
        // Given:
        builderClassTM.setName("MyBuilder");
        builderClassTM.setGenerated(new GeneratedTM("PojoBuilder"));

        // When:
        Writer writer = new StringWriter();
        underTest.generate(writer);

        // Then:
        //@formatter:off
        String generatedCode = writer.toString();
        log(generatedCode);
        assertThat(generatedCode).isEqualTo(
                "@Generated(\"PojoBuilder\")"+"\n"
                +"public class MyBuilder {"+"\n"
                +"}");
        //@formatter:on
    }

    @Test
    public void testGenerateWithPackage() throws IOException {
        // Given:
        builderClassTM.setName("PojoBuilder");
        builderClassTM.setPackage(new PackageTM("net.karneim.scribble"));

        // When:
        Writer writer = new StringWriter();
        underTest.generate(writer);

        // Then:
        //@formatter:off
        String generatedCode = writer.toString();
        log(generatedCode);
        assertThat(generatedCode).isEqualTo(
                "package net.karneim.scribble;"+"\n"
                +"\n"
                +"public class PojoBuilder {"+"\n"
                +"}");
        //@formatter:on
    }

    @Test
    public void testGenerateWithConstructor() throws IOException {
        // Given:
        builderClassTM.setName("PojoBuilder<T>");
        ConstructorTM constr = new ConstructorTM();
        constr.setName("PojoBuilder");
        constr.setSelfCast("PojoBuilder<T>");
        builderClassTM.setConstructor(constr);

        // When:
        Writer writer = new StringWriter();
        underTest.generate(writer);

        // Then:
        //@formatter:off
        String generatedCode = writer.toString();
        log(generatedCode);
        assertThat(generatedCode).isEqualTo(
                "public class PojoBuilder<T> {"+"\n"
                +"\n"
                +"    public PojoBuilder() {"+"\n"
                +"        self = (PojoBuilder<T>)this;"+"\n"
                +"    }"+"\n"
                +"}");
        //@formatter:on
    }

    @Test
    public void testGenerateWithBuilderFactory() throws IOException {
        // Given:
        builderClassTM.setName("PojoBuilder");
        BuilderFactoryMethodTM builderFactory = new BuilderFactoryMethodTM();
        builderFactory.setName("$Pojo");
        builderFactory.setReturnType("PojoBuilder");
        builderClassTM.setBuilderFactoryMethod(builderFactory);
        // When:
        Writer writer = new StringWriter();
        underTest.generate(writer);

        // Then:
        //@formatter:off
        String generatedCode = writer.toString();
        log(generatedCode);
        assertThat(generatedCode).isEqualTo(
                "public class PojoBuilder {"+"\n"
                +"\n"
                +"    public static PojoBuilder $Pojo() {"+"\n"
                +"        return new PojoBuilder();"+"\n"
                +"    }"+"\n"
                +"}");
        //@formatter:on
    }

    @Test
    public void testGenerateWithGenericBuilderFactory() throws IOException {
        // Given:
        builderClassTM.setName("PojoBuilder<T extends Number>");
        BuilderFactoryMethodTM builderFactory = new BuilderFactoryMethodTM();
        builderFactory.setName("$Pojo");
        builderFactory.setReturnType("PojoBuilder<T>");
        builderFactory.getGenerics().add("T extends Number");
        builderClassTM.setBuilderFactoryMethod(builderFactory);
        // When:
        Writer writer = new StringWriter();
        underTest.generate(writer);

        // Then:
        //@formatter:off
        String generatedCode = writer.toString();
        log(generatedCode);
        assertThat(generatedCode).isEqualTo(
                "public class PojoBuilder<T extends Number> {"+"\n"
                +"\n"
                +"    public static <T extends Number> PojoBuilder<T> $Pojo() {"+"\n"
                +"        return new PojoBuilder<T>();"+"\n"
                +"    }"+"\n"
                +"}");
        //@formatter:on
    }

    @Test
    public void testGenerateWithGenericBuilderFactoryHavingMoreGenerics() throws IOException {
        // Given:
        builderClassTM.setName("PojoBuilder<T extends Number, L extends List<T>>");
        BuilderFactoryMethodTM builderFactory = new BuilderFactoryMethodTM();
        builderFactory.setName("$Pojo");
        builderFactory.setReturnType("PojoBuilder<T,L>");
        builderFactory.getGenerics().add("T extends Number");
        builderFactory.getGenerics().add("L extends List<T>");
        builderClassTM.setBuilderFactoryMethod(builderFactory);
        // When:
        Writer writer = new StringWriter();
        underTest.generate(writer);

        // Then:
        //@formatter:off
        String generatedCode = writer.toString();
        log(generatedCode);
        assertThat(generatedCode).isEqualTo(
                "public class PojoBuilder<T extends Number, L extends List<T>> {"+"\n"
                +"\n"
                +"    public static <T extends Number, L extends List<T>> PojoBuilder<T,L> $Pojo() {"+"\n"
                +"        return new PojoBuilder<T,L>();"+"\n"
                +"    }"+"\n"
                +"}");
        //@formatter:on
    }

    @Test
    public void testGenerateWithBuildHelper() throws IOException {
        // Given:
        builderClassTM.setName("PojoBuilder");
        BuildHelperMethodTM buildHelper = new BuildHelperMethodTM();
        buildHelper.setName("some");
        buildHelper.setReturnType("Pojo");
        buildHelper.setParameterType("PojoBuilder");

        builderClassTM.setBuildHelperMethod(buildHelper);

        // When:
        Writer writer = new StringWriter();
        underTest.generate(writer);

        // Then:
        //@formatter:off
        String generatedCode = writer.toString();
        log(generatedCode);
        assertThat(generatedCode).isEqualTo(
                "public class PojoBuilder {"+"\n"
                +"\n"
                +"    public static Pojo some(PojoBuilder builder) {"+"\n"
                +"        return builder.build();"+"\n"
                +"    }"+"\n"
                +"}");
        //@formatter:on
    }

    @Test
    public void testGenerateWithBuildHelperHavingGeneric() throws IOException {
        // Given:
        builderClassTM.setName("PojoBuilder<T extends Number>");
        BuildHelperMethodTM buildHelper = new BuildHelperMethodTM();
        buildHelper.setName("some");
        buildHelper.setReturnType("Pojo<T>");
        buildHelper.setParameterType("PojoBuilder<T>");
        buildHelper.getGenerics().add("T extends Number");
        builderClassTM.setBuildHelperMethod(buildHelper);

        // When:
        Writer writer = new StringWriter();
        underTest.generate(writer);

        // Then:
        //@formatter:off
        String generatedCode = writer.toString();
        log(generatedCode);
        assertThat(generatedCode).isEqualTo(
                "public class PojoBuilder<T extends Number> {"+"\n"
                +"\n"
                +"    public static <T extends Number> Pojo<T> some(PojoBuilder<T> builder) {"+"\n"
                +"        return builder.build();"+"\n"
                +"    }"+"\n"
                +"}");
        //@formatter:on
    }

    @Test
    public void testGenerateWithBuildHelperHavingMoreGenerics() throws IOException {
        // Given:
        builderClassTM.setName("PojoBuilder<T extends Number, X extends Comparable>");
        BuildHelperMethodTM buildHelper = new BuildHelperMethodTM();
        buildHelper.setName("some");
        buildHelper.setReturnType("Pojo<T,X>");
        buildHelper.setParameterType("PojoBuilder<T,X>");
        buildHelper.getGenerics().add("T extends Number");
        buildHelper.getGenerics().add("X extends Comparable");
        builderClassTM.setBuildHelperMethod(buildHelper);

        // When:
        Writer writer = new StringWriter();
        underTest.generate(writer);

        // Then:
        //@formatter:off
        String generatedCode = writer.toString();
        log(generatedCode);
        assertThat(generatedCode).isEqualTo(
                "public class PojoBuilder<T extends Number, X extends Comparable> {"+"\n"
                +"\n"
                +"    public static <T extends Number, X extends Comparable> Pojo<T,X> some(PojoBuilder<T,X> builder) {"+"\n"
                +"        return builder.build();"+"\n"
                +"    }"+"\n"
                +"}");
        //@formatter:on
    }

    @Test
    public void testGenerateWithSingleField() throws IOException {
        // Given:
        builderClassTM.setName("PojoBuilder");
        FieldTM field1 = new FieldTM();
        field1.setName("dummy");
        field1.setType("List<String>");
        builderClassTM.getFields().add(field1);

        // When:
        Writer writer = new StringWriter();
        underTest.generate(writer);

        // Then:
        //@formatter:off
        String generatedCode = writer.toString();
        log(generatedCode);
        assertThat(generatedCode).isEqualTo(
                "public class PojoBuilder {"+"\n"
                +"\n"
                +"    private List<String> value$dummy;"+"\n"
                +"    private boolean isSet$dummy = false;"+"\n"
                +"}");
        //@formatter:on
    }

    @Test
    public void testGenerateWithSingleMandatoryField() throws IOException {
        // Given:
        builderClassTM.setName("PojoBuilder");
        FieldTM field1 = new FieldTM();
        field1.setName("dummy");
        field1.setType("List<String>");
        field1.setMandatory(true);
        builderClassTM.getFields().add(field1);

        // When:
        Writer writer = new StringWriter();
        underTest.generate(writer);

        // Then:
        //@formatter:off
        String generatedCode = writer.toString();
        log(generatedCode);
        assertThat(generatedCode).isEqualTo(
                "public class PojoBuilder {"+"\n"
                +"\n"
                +"    private List<String> value$dummy; // mandatory construction parameter"+"\n"
                +"    private boolean isSet$dummy = false;"+"\n"
                +"}");
        //@formatter:on
    }

    @Test
    public void testGenerateWithMultipleFields() throws IOException {
        // Given:
        builderClassTM.setName("PojoBuilder");
        FieldTM field1 = new FieldTM();
        field1.setName("dummy");
        field1.setType("List<String>");
        builderClassTM.getFields().add(field1);
        FieldTM field2 = new FieldTM();
        field2.setName("foo");
        field2.setType("long");
        builderClassTM.getFields().add(field2);

        // When:
        Writer writer = new StringWriter();
        underTest.generate(writer);

        // Then:
        //@formatter:off
        String generatedCode = writer.toString();
        log(generatedCode);
        assertThat(generatedCode).isEqualTo(
                "public class PojoBuilder {"+"\n"
                +"\n"
                +"    private List<String> value$dummy;"+"\n"
                +"    private boolean isSet$dummy = false;"+"\n"
                +"\n"
                +"    private long value$foo;"+"\n"
                +"    private boolean isSet$foo = false;"+"\n"                
                +"}");
        //@formatter:on
    }

    @Test
    public void testGenerateWithSuperclass() throws IOException {
        // Given:
        builderClassTM.setName("PojoBuilder");
        SuperclassTM superclass = new SuperclassTM();
        superclass.setName("AbstractBuilder");
        builderClassTM.setSuperclass(superclass);

        // When:
        Writer writer = new StringWriter();
        underTest.generate(writer);

        // Then:
        //@formatter:off
        String generatedCode = writer.toString();
        log(generatedCode);
        assertThat(generatedCode).isEqualTo(
                "public class PojoBuilder extends AbstractBuilder {"+"\n"
                +"}");
        //@formatter:on
    }

    @Test
    public void testGenerateWithInterface() throws IOException {
        // Given:
        builderClassTM.setName("Pojo");
        builderClassTM.getInterfaces().add(new InterfaceTM("Cloneable"));

        // When:
        Writer writer = new StringWriter();
        underTest.generate(writer);

        // Then:
        //@formatter:off
        String generatedCode = writer.toString();
        log(generatedCode);
        assertThat(generatedCode).isEqualTo(
                "public class Pojo implements Cloneable {"+"\n"
                +"}");
        //@formatter:on
    }

    @Test
    public void testGenerateWithMultipleInterfaces() throws IOException {
        // Given:
        builderClassTM.setName("Pojo");
        builderClassTM.getInterfaces().add(new InterfaceTM("Cloneable"));
        builderClassTM.getInterfaces().add(new InterfaceTM("Serializable"));

        // When:
        Writer writer = new StringWriter();
        underTest.generate(writer);

        // Then:
        //@formatter:off
        String generatedCode = writer.toString();
        log(generatedCode);
        assertThat(generatedCode).isEqualTo(
                "public class Pojo implements Cloneable, Serializable {"+"\n"
                +"}");
        //@formatter:on
    }

    @Test
    public void testGenerateWithImport() throws IOException {
        // Given:
        builderClassTM.setName("Pojo");
        builderClassTM.getImports().add(new ImportTM("java.util.List"));

        // When:
        Writer writer = new StringWriter();
        underTest.generate(writer);

        // Then:
        //@formatter:off
        String generatedCode = writer.toString();
        log(generatedCode);
        assertThat(generatedCode).isEqualTo(
                 "import java.util.List;"+"\n"
                +"\n"
                +"public class Pojo {"+"\n"
                +"}");
        //@formatter:on
    }

    @Test
    public void testGenerateWithImports() throws IOException {
        // Given:
        builderClassTM.setName("Pojo");
        builderClassTM.getImports().add(new ImportTM("java.util.List"));
        builderClassTM.getImports().add(new ImportTM("java.util.ArrayList"));

        // When:
        Writer writer = new StringWriter();
        underTest.generate(writer);

        // Then:
        //@formatter:off
        String generatedCode = writer.toString();
        log(generatedCode);
        assertThat(generatedCode).isEqualTo(
                 "import java.util.List;"+"\n"
                +"import java.util.ArrayList;"+"\n"
                +"\n"
                +"public class Pojo {"+"\n"
                +"}");
        //@formatter:on
    }

    @Test
    public void testGenerateWithSetterMethod() throws IOException {
        // Given:
        builderClassTM.setName("DummyBuilder");
        SetterTM setter1 = new SetterTM();
        setter1.setName("withName");
        setter1.setFieldname("name");
        setter1.setType("String");
        setter1.setReturnType("DummyBuilder");
        builderClassTM.getSetters().add(setter1);
        // When:
        Writer writer = new StringWriter();
        underTest.generate(writer);

        // Then:
        //@formatter:off
        String generatedCode = writer.toString();
        log(generatedCode);
        assertThat(generatedCode).isEqualTo(
                "public class DummyBuilder {"+"\n"
                +"\n"
                +"    public DummyBuilder withName(String aValue) {"+"\n"
                +"        value$name = aValue;"+"\n"
                +"        isSet$name = true;"+"\n"
                +"        return self;"+"\n"
                +"    }"+"\n"
                +"}");
        //@formatter:on
    }

    @Test
    public void testGenerateWithMultipleSetters() throws IOException {
        // Given:
        builderClassTM.setName("ContactBuilder");
        builderClassTM.setSelfField(new SelfFieldTM("ContactBuilder"));
        SetterTM setter1 = new SetterTM();
        setter1.setName("withName");
        setter1.setFieldname("name");
        setter1.setType("String");
        setter1.setReturnType("ContactBuilder");
        builderClassTM.getSetters().add(setter1);
        SetterTM setter2 = new SetterTM();
        setter2.setName("withDateOfBirth");
        setter2.setFieldname("dateOfBirth");
        setter2.setType("Date");
        setter2.setReturnType("ContactBuilder");
        builderClassTM.getSetters().add(setter2);
        // When:
        Writer writer = new StringWriter();
        underTest.generate(writer);

        // Then:
        //@formatter:off
        String generatedCode = writer.toString();
        log(generatedCode);
        assertThat(generatedCode).isEqualTo(
                "public class ContactBuilder {"+"\n"
                +"    protected ContactBuilder self;"+"\n"
                +"\n"
                +"    public ContactBuilder withName(String aValue) {"+"\n"
                +"        value$name = aValue;"+"\n"
                +"        isSet$name = true;"+"\n"
                +"        return self;"+"\n"
                +"    }"+"\n"
                +"\n"
                +"    public ContactBuilder withDateOfBirth(Date aValue) {"+"\n"
                +"        value$dateOfBirth = aValue;"+"\n"
                +"        isSet$dateOfBirth = true;"+"\n"
                +"        return self;"+"\n"
                +"    }"+"\n"
                +"}");
        //@formatter:on

    }

    @Test
    public void testGenerateWithClone() throws IOException {
        // Given:
        builderClassTM.setName("PojoBuilder");
        builderClassTM.setCloneMethod(new CloneMethodTM("PojoBuilder"));

        // When:
        Writer writer = new StringWriter();
        underTest.generate(writer);

        // Then:
        //@formatter:off
        String generatedCode = writer.toString();
        log(generatedCode);
        assertThat(generatedCode).isEqualTo(
                "public class PojoBuilder {"+"\n"
                +"\n"
                +"    @Override"+"\n"
                +"    public Object clone() {"+"\n"
                +"        try {"+"\n"
                +"            @SuppressWarnings(\"unchecked\")"+"\n"
                +"            PojoBuilder result = (PojoBuilder)super.clone();"+"\n"
                +"            result.self = result;"+"\n"
                +"            return result;"+"\n"
                +"        } catch (CloneNotSupportedException e) {"+"\n"
                +"            throw new InternalError(e.getMessage());"+"\n"
                +"        }"+"\n"
                +"    }"+"\n"
                +"}");
        //@formatter:on
    }

    @Test
    public void testGenerateWithButMethod() throws IOException {
        // Given:
        builderClassTM.setName("PojoBuilder");
        builderClassTM.setButMethod(new ButMethodTM("PojoBuilder"));

        // When:
        Writer writer = new StringWriter();
        underTest.generate(writer);

        // Then:
        //@formatter:off
        String generatedCode = writer.toString();
        log(generatedCode);
        assertThat(generatedCode).isEqualTo(
                "public class PojoBuilder {"+"\n"
                +"\n"
                +"    @SuppressWarnings(\"unchecked\")"+"\n"
                +"    public PojoBuilder but() {"+"\n"
                +"        return (PojoBuilder)clone();"+"\n"
                +"    }"+"\n"
                +"}");
        //@formatter:on
    }

    @Test
    public void testGenerateWithCopyMethodHavingOneAssignmentWithFieldAccessor() throws IOException {
        // Given:
        builderClassTM.setName("PojoBuilder<T>");
        CopyMethodTM copyMethod = new CopyMethodTM();
        copyMethod.setPojoType("Pojo<T>");
        copyMethod.setReturnType("PojoBuilder<T>");
        AssignmentTM assignment = new AssignmentTM();
        assignment.setSetter("withName");
        assignment.setAccessor(new FieldAccessorTM("name"));
        copyMethod.getAssignments().add(assignment);
        builderClassTM.setCopyMethod(copyMethod);

        // When:
        Writer writer = new StringWriter();
        underTest.generate(writer);

        // Then:
        //@formatter:off
        String generatedCode = writer.toString();
        log(generatedCode);
        assertThat(generatedCode).isEqualTo(
                "public class PojoBuilder<T> {"+"\n"
                +"\n"
                +"    public PojoBuilder<T> copy(Pojo<T> original) {"+"\n"
                +"        withName(original.name);"+"\n"
                +"        return self;"+"\n"
                +"    }"+"\n"
                +"}");
        //@formatter:on
    }

    @Test
    public void testGenerateWithCopyMethodHavingMoreAssignmentsWithFieldAccessor() throws IOException {
        // Given:
        builderClassTM.setName("PojoBuilder<T>");
        CopyMethodTM copyMethod = new CopyMethodTM();
        copyMethod.setPojoType("Pojo<T>");
        copyMethod.setReturnType("PojoBuilder<T>");
        AssignmentTM assignment1 = new AssignmentTM();
        assignment1.setSetter("withName");
        assignment1.setAccessor(new FieldAccessorTM("name"));
        copyMethod.getAssignments().add(assignment1);
        AssignmentTM assignment2 = new AssignmentTM();
        assignment2.setSetter("withDate");
        assignment2.setAccessor(new FieldAccessorTM("date"));
        copyMethod.getAssignments().add(assignment2);
        builderClassTM.setCopyMethod(copyMethod);

        // When:
        Writer writer = new StringWriter();
        underTest.generate(writer);

        // Then:
        //@formatter:off
        String generatedCode = writer.toString();
        log(generatedCode);
        assertThat(generatedCode).isEqualTo(
                "public class PojoBuilder<T> {"+"\n"
                +"\n"
                +"    public PojoBuilder<T> copy(Pojo<T> original) {"+"\n"
                +"        withName(original.name);"+"\n"
                +"        withDate(original.date);"+"\n"
                +"        return self;"+"\n"
                +"    }"+"\n"
                +"}");
        //@formatter:on
    }

    @Test
    public void testGenerateWithCopyMethodHavingOneAssignmentWithMethodAccessor() throws IOException {
        // Given:
        builderClassTM.setName("PojoBuilder<T>");
        CopyMethodTM copyMethod = new CopyMethodTM();
        copyMethod.setPojoType("Pojo<T>");
        copyMethod.setReturnType("PojoBuilder<T>");
        AssignmentTM assignment = new AssignmentTM();
        assignment.setSetter("withName");
        assignment.setAccessor(new MethodAccessorTM("getName"));
        copyMethod.getAssignments().add(assignment);
        builderClassTM.setCopyMethod(copyMethod);

        // When:
        Writer writer = new StringWriter();
        underTest.generate(writer);

        // Then:
        //@formatter:off
        String generatedCode = writer.toString();
        log(generatedCode);
        assertThat(generatedCode).isEqualTo(
                "public class PojoBuilder<T> {"+"\n"
                +"\n"
                +"    public PojoBuilder<T> copy(Pojo<T> original) {"+"\n"
                +"        withName(original.getName());"+"\n"
                +"        return self;"+"\n"
                +"    }"+"\n"
                +"}");
        //@formatter:on
    }

    @Test
    public void testGenerateWithCopyMethodHavingMoreAssignmentsWithMethodAccessor() throws IOException {
        // Given:
        builderClassTM.setName("PojoBuilder<T>");
        CopyMethodTM copyMethod = new CopyMethodTM();
        copyMethod.setPojoType("Pojo<T>");
        copyMethod.setReturnType("PojoBuilder<T>");
        AssignmentTM assignment1 = new AssignmentTM();
        assignment1.setSetter("withName");
        assignment1.setAccessor(new MethodAccessorTM("getName"));
        copyMethod.getAssignments().add(assignment1);
        AssignmentTM assignment2 = new AssignmentTM();
        assignment2.setSetter("withDate");
        assignment2.setAccessor(new MethodAccessorTM("getDate"));
        copyMethod.getAssignments().add(assignment2);

        builderClassTM.setCopyMethod(copyMethod);

        // When:
        Writer writer = new StringWriter();
        underTest.generate(writer);

        // Then:
        //@formatter:off
        String generatedCode = writer.toString();
        log(generatedCode);
        assertThat(generatedCode).isEqualTo(
                "public class PojoBuilder<T> {"+"\n"
                +"\n"
                +"    public PojoBuilder<T> copy(Pojo<T> original) {"+"\n"
                +"        withName(original.getName());"+"\n"
                +"        withDate(original.getDate());"+"\n"
                +"        return self;"+"\n"
                +"    }"+"\n"
                +"}");
        //@formatter:on
    }

    @Test
    public void testGenerateWithCopyMethodHavingMoreAssignmentsWithMixedAccessors() throws IOException {
        // Given:
        builderClassTM.setName("PojoBuilder<T>");
        CopyMethodTM copyMethod = new CopyMethodTM();
        copyMethod.setPojoType("Pojo<T>");
        copyMethod.setReturnType("PojoBuilder<T>");
        AssignmentTM assignment1 = new AssignmentTM();
        assignment1.setSetter("withName");
        assignment1.setAccessor(new FieldAccessorTM("name"));
        copyMethod.getAssignments().add(assignment1);
        AssignmentTM assignment2 = new AssignmentTM();
        assignment2.setSetter("withDate");
        assignment2.setAccessor(new MethodAccessorTM("getDate"));
        copyMethod.getAssignments().add(assignment2);

        builderClassTM.setCopyMethod(copyMethod);

        // When:
        Writer writer = new StringWriter();
        underTest.generate(writer);

        // Then:
        //@formatter:off
        String generatedCode = writer.toString();
        log(generatedCode);
        assertThat(generatedCode).isEqualTo(
                "public class PojoBuilder<T> {"+"\n"
                +"\n"
                +"    public PojoBuilder<T> copy(Pojo<T> original) {"+"\n"
                +"        withName(original.name);"+"\n"
                +"        withDate(original.getDate());"+"\n"
                +"        return self;"+"\n"
                +"    }"+"\n"
                +"}");
        //@formatter:on
    }

    @Test
    public void testGenerateWithBuildMethodCallingPojoConstructor() throws IOException {
        // Given:
        builderClassTM.setName("PojoBuilder");
        BuildMethodTM buildMethod = new BuildMethodTM();
        buildMethod.setReturnType("Pojo");
        ConstructorCallTM construction = new ConstructorCallTM();
        construction.setMethodName("Pojo");
        buildMethod.setConstruction(construction);
        builderClassTM.setBuildMethod(buildMethod);

        // When:
        Writer writer = new StringWriter();
        underTest.generate(writer);

        // Then:
        //@formatter:off
        String generatedCode = writer.toString();
        log(generatedCode);
        assertThat(generatedCode).isEqualTo(
                "public class PojoBuilder {"+"\n"
                +"\n"
                +"    public Pojo build() {"+"\n"
                +"        Pojo result = new Pojo();"+"\n"
                +"        return result;"+"\n"
                +"    }"+"\n"
                +"}");
        //@formatter:on
    }

    @Test
    public void testGenerateWithBuildMethodWithOverrideAnnotation() throws IOException {
        // Given:
        builderClassTM.setName("PojoBuilder");
        BuildMethodTM buildMethod = new BuildMethodTM();
        buildMethod.setReturnType("Pojo");
        buildMethod.setOverride(true);
        ConstructorCallTM construction = new ConstructorCallTM();
        construction.setMethodName("Pojo");
        buildMethod.setConstruction(construction);
        builderClassTM.setBuildMethod(buildMethod);

        // When:
        Writer writer = new StringWriter();
        underTest.generate(writer);

        // Then:
        //@formatter:off
        String generatedCode = writer.toString();
        log(generatedCode);
        assertThat(generatedCode).isEqualTo(
                "public class PojoBuilder {"+"\n"
                +"\n"
                +"    @Override"+"\n"
                +"    public Pojo build() {"+"\n"
                +"        Pojo result = new Pojo();"+"\n"
                +"        return result;"+"\n"
                +"    }"+"\n"
                +"}");
        //@formatter:on
    }

    @Test
    public void testGenerateWithBuildMethodCallingPojoConstructorWithArguments() throws IOException {
        // Given:
        builderClassTM.setName("PojoBuilder");
        BuildMethodTM buildMethod = new BuildMethodTM();
        buildMethod.setReturnType("Pojo");
        ConstructorCallTM construction = new ConstructorCallTM();
        construction.setMethodName("Pojo");
        ArgumentTM arg = new ArgumentTM();
        arg.setName("name");
        construction.getArguments().add(arg);
        buildMethod.setConstruction(construction);
        builderClassTM.setBuildMethod(buildMethod);

        // When:
        Writer writer = new StringWriter();
        underTest.generate(writer);

        // Then:
        //@formatter:off
        String generatedCode = writer.toString();
        log(generatedCode);
        assertThat(generatedCode).isEqualTo(
                "public class PojoBuilder {"+"\n"
                +"\n"
                +"    public Pojo build() {"+"\n"
                +"        Pojo result = new Pojo(value$name);"+"\n"
                +"        return result;"+"\n"
                +"    }"+"\n"
                +"}");
        //@formatter:on
    }

    @Test
    public void testGenerateWithBuildMethodCallingPojoConstructorWithMoreArguments() throws IOException {
        // Given:
        builderClassTM.setName("PojoBuilder");
        BuildMethodTM buildMethod = new BuildMethodTM();
        buildMethod.setReturnType("Pojo");
        ConstructorCallTM construction = new ConstructorCallTM();
        construction.setMethodName("Pojo");
        ArgumentTM arg1 = new ArgumentTM();
        arg1.setName("name");
        construction.getArguments().add(arg1);
        ArgumentTM arg2 = new ArgumentTM();
        arg2.setName("date");
        construction.getArguments().add(arg2);
        buildMethod.setConstruction(construction);
        builderClassTM.setBuildMethod(buildMethod);

        // When:
        Writer writer = new StringWriter();
        underTest.generate(writer);

        // Then:
        //@formatter:off
        String generatedCode = writer.toString();
        log(generatedCode);
        assertThat(generatedCode).isEqualTo(
                "public class PojoBuilder {"+"\n"
                +"\n"
                +"    public Pojo build() {"+"\n"
                +"        Pojo result = new Pojo(value$name, value$date);"+"\n"
                +"        return result;"+"\n"
                +"    }"+"\n"
                +"}");
        //@formatter:on
    }

    @Test
    public void testGenerateWithBuildMethodCallingPojoConstructorWithSetterCall() throws IOException {
        // Given:
        builderClassTM.setName("PojoBuilder");
        BuildMethodTM buildMethod = new BuildMethodTM();
        buildMethod.setReturnType("Pojo");
        ConstructorCallTM construction = new ConstructorCallTM();
        construction.setMethodName("Pojo");
        buildMethod.setConstruction(construction);
        SetterCallTM setterCall = new SetterCallTM();
        setterCall.setMethodName("setName");
        setterCall.setFieldname("name");
        buildMethod.getSetterCalls().add(setterCall);
        builderClassTM.setBuildMethod(buildMethod);

        // When:
        Writer writer = new StringWriter();
        underTest.generate(writer);

        // Then:
        //@formatter:off
        String generatedCode = writer.toString();
        log(generatedCode);
        assertThat(generatedCode).isEqualTo(
                "public class PojoBuilder {"+"\n"
                +"\n"
                +"    public Pojo build() {"+"\n"
                +"        Pojo result = new Pojo();"+"\n"
                +"        if (isSet$name) {"+"\n"
                +"            result.setName(value$name);"+"\n"
                +"        }"+"\n"
                +"        return result;"+"\n"
                +"    }"+"\n"
                +"}");
        //@formatter:on
    }

    @Test
    public void testGenerateWithBuildMethodCallingPojoConstructorWithMultipleSetterCalls() throws IOException {
        // Given:
        builderClassTM.setName("PojoBuilder");
        BuildMethodTM buildMethod = new BuildMethodTM();
        buildMethod.setReturnType("Pojo");
        ConstructorCallTM construction = new ConstructorCallTM();
        construction.setMethodName("Pojo");
        buildMethod.setConstruction(construction);
        SetterCallTM setterCall1 = new SetterCallTM();
        setterCall1.setMethodName("setName");
        setterCall1.setFieldname("name");
        buildMethod.getSetterCalls().add(setterCall1);
        SetterCallTM setterCall2 = new SetterCallTM();
        setterCall2.setMethodName("setDate");
        setterCall2.setFieldname("date");
        buildMethod.getSetterCalls().add(setterCall2);
        builderClassTM.setBuildMethod(buildMethod);

        // When:
        Writer writer = new StringWriter();
        underTest.generate(writer);

        // Then:
        //@formatter:off
        String generatedCode = writer.toString();
        log(generatedCode);
        assertThat(generatedCode).isEqualTo(
                "public class PojoBuilder {"+"\n"
                +"\n"
                +"    public Pojo build() {"+"\n"
                +"        Pojo result = new Pojo();"+"\n"
                +"        if (isSet$name) {"+"\n"
                +"            result.setName(value$name);"+"\n"
                +"        }"+"\n"
                +"        if (isSet$date) {"+"\n"
                +"            result.setDate(value$date);"+"\n"
                +"        }"+"\n"
                +"        return result;"+"\n"
                +"    }"+"\n"
                +"}");
        //@formatter:on
    }

    @Test
    public void testGenerateWithBuildMethodCallingPojoFactory() throws IOException {
        // Given:
        builderClassTM.setName("PojoBuilder");
        BuildMethodTM buildMethod = new BuildMethodTM();
        buildMethod.setReturnType("Pojo");
        FactoryCallTM construction = new FactoryCallTM();
        construction.setMethodName("PojoFactory.createPojo");
        buildMethod.setConstruction(construction);
        builderClassTM.setBuildMethod(buildMethod);

        // When:
        Writer writer = new StringWriter();
        underTest.generate(writer);

        // Then:
        //@formatter:off
        String generatedCode = writer.toString();
        log(generatedCode);
        assertThat(generatedCode).isEqualTo(
                "public class PojoBuilder {"+"\n"
                +"\n"
                +"    public Pojo build() {"+"\n"
                +"        Pojo result = PojoFactory.createPojo();"+"\n"
                +"        return result;"+"\n"
                +"    }"+"\n"
                +"}");
        //@formatter:on
    }

    @Test
    public void testGenerateWithBuildMethodCallingPojoFactoryWithArgument() throws IOException {
        // Given:
        builderClassTM.setName("PojoBuilder");
        BuildMethodTM buildMethod = new BuildMethodTM();
        buildMethod.setReturnType("Pojo");
        FactoryCallTM construction = new FactoryCallTM();
        construction.setMethodName("PojoFactory.createPojo");
        ArgumentTM arg = new ArgumentTM();
        arg.setName("name");
        construction.getArguments().add(arg);
        buildMethod.setConstruction(construction);
        builderClassTM.setBuildMethod(buildMethod);

        // When:
        Writer writer = new StringWriter();
        underTest.generate(writer);

        // Then:
        //@formatter:off
        String generatedCode = writer.toString();
        log(generatedCode);
        assertThat(generatedCode).isEqualTo(
                "public class PojoBuilder {"+"\n"
                +"\n"
                +"    public Pojo build() {"+"\n"
                +"        Pojo result = PojoFactory.createPojo(value$name);"+"\n"
                +"        return result;"+"\n"
                +"    }"+"\n"
                +"}");
        //@formatter:on
    }

    @Test
    public void testGenerateWithBuildMethodCallingPojoFactoryWithMoreArguments() throws IOException {
        // Given:
        builderClassTM.setName("PojoBuilder");
        BuildMethodTM buildMethod = new BuildMethodTM();
        buildMethod.setReturnType("Pojo");
        FactoryCallTM construction = new FactoryCallTM();
        construction.setMethodName("PojoFactory.createPojo");
        ArgumentTM arg1 = new ArgumentTM();
        arg1.setName("name");
        construction.getArguments().add(arg1);
        ArgumentTM arg2 = new ArgumentTM();
        arg2.setName("date");
        construction.getArguments().add(arg2);
        buildMethod.setConstruction(construction);
        builderClassTM.setBuildMethod(buildMethod);

        // When:
        Writer writer = new StringWriter();
        underTest.generate(writer);

        // Then:
        //@formatter:off
        String generatedCode = writer.toString();
        log(generatedCode);
        assertThat(generatedCode).isEqualTo(
                "public class PojoBuilder {"+"\n"
                +"\n"
                +"    public Pojo build() {"+"\n"
                +"        Pojo result = PojoFactory.createPojo(value$name, value$date);"+"\n"
                +"        return result;"+"\n"
                +"    }"+"\n"
                +"}");
        //@formatter:on
    }

    @Test
    public void testGenerateWithBuildMethodCallingPojoFactoryWithSetterCall() throws IOException {
        // Given:
        builderClassTM.setName("PojoBuilder");
        BuildMethodTM buildMethod = new BuildMethodTM();
        buildMethod.setReturnType("Pojo");
        FactoryCallTM construction = new FactoryCallTM();
        construction.setMethodName("PojoFactory.createPojo");
        buildMethod.setConstruction(construction);
        SetterCallTM setterCall = new SetterCallTM();
        setterCall.setFieldname("name");
        setterCall.setMethodName("setName");
        buildMethod.getSetterCalls().add(setterCall);
        builderClassTM.setBuildMethod(buildMethod);

        // When:
        Writer writer = new StringWriter();
        underTest.generate(writer);

        // Then:
        //@formatter:off
        String generatedCode = writer.toString();
        log(generatedCode);
        assertThat(generatedCode).isEqualTo(
                "public class PojoBuilder {"+"\n"
                +"\n"
                +"    public Pojo build() {"+"\n"
                +"        Pojo result = PojoFactory.createPojo();"+"\n"
                +"        if (isSet$name) {"+"\n"
                +"            result.setName(value$name);"+"\n"
                +"        }"+"\n"
                +"        return result;"+"\n"
                +"    }"+"\n"
                +"}");
        //@formatter:on
    }

    @Test
    public void testGenerateWithBuildMethodCallingPojoFactoryWithMultipleSetterCalls() throws IOException {
        // Given:
        builderClassTM.setName("PojoBuilder");
        BuildMethodTM buildMethod = new BuildMethodTM();
        buildMethod.setReturnType("Pojo");
        FactoryCallTM construction = new FactoryCallTM();
        construction.setMethodName("PojoFactory.createPojo");
        buildMethod.setConstruction(construction);
        SetterCallTM setterCall1 = new SetterCallTM();
        setterCall1.setFieldname("name");
        setterCall1.setMethodName("setName");
        buildMethod.getSetterCalls().add(setterCall1);
        SetterCallTM setterCall2 = new SetterCallTM();
        setterCall2.setFieldname("date");
        setterCall2.setMethodName("setDate");
        buildMethod.getSetterCalls().add(setterCall2);
        builderClassTM.setBuildMethod(buildMethod);

        // When:
        Writer writer = new StringWriter();
        underTest.generate(writer);

        // Then:
        //@formatter:off
        String generatedCode = writer.toString();
        log(generatedCode);
        assertThat(generatedCode).isEqualTo(
                "public class PojoBuilder {"+"\n"
                +"\n"
                +"    public Pojo build() {"+"\n"
                +"        Pojo result = PojoFactory.createPojo();"+"\n"
                +"        if (isSet$name) {"+"\n"
                +"            result.setName(value$name);"+"\n"
                +"        }"+"\n"
                +"        if (isSet$date) {"+"\n"
                +"            result.setDate(value$date);"+"\n"
                +"        }"+"\n"
                +"        return result;"+"\n"
                +"    }"+"\n"
                +"}");
        //@formatter:on
    }

    @Test
    public void testGenerateFullBlownPojoUsingConstructorCall() throws IOException {
        // Given:
        String classname = "DataBuilder";
        String genericContraint = "T extends Number";
        String classNameDeclaration = classname + "<" + genericContraint + ">";
        String selfType = classname + "<T>";
        String pojoClassname = "Data";
        String factoryMethodName = "$Data";
        String pojoType = pojoClassname + "<T>";
        String field1Name = "list$java$util$List";
        String field1Type = "List<T>";
        String field2Name = "name$java$lang$String";
        String field2Type = "String";
        String field3Name = "date$java$util$Date";
        String field3Type = "Date";

        builderClassTM.setPackage(new PackageTM("net.karneim.scribble"));
        builderClassTM.getImports().add(new ImportTM("net.karneim.base.AbstractBuilder"));
        builderClassTM.getImports().add(new ImportTM("java.util.Date"));
        builderClassTM.getImports().add(new ImportTM("java.util.List"));
        builderClassTM.getImports().add(new ImportTM("javax.annotation.Generated"));
        builderClassTM.setGenerated(new GeneratedTM("PojoBuilder"));
        builderClassTM.setName(classNameDeclaration);
        builderClassTM.setSuperclass(new SuperclassTM("AbstractBuilder"));
        builderClassTM.getInterfaces().add(new InterfaceTM("Cloneable"));
        builderClassTM.setSelfField(new SelfFieldTM(selfType));
        builderClassTM.setConstructor(new ConstructorTM(classname, selfType));
        builderClassTM.getFields().add(new FieldTM(field1Name, field1Type, true));
        builderClassTM.getFields().add(new FieldTM(field2Name, field2Type, true));
        builderClassTM.getFields().add(new FieldTM(field3Name, field3Type));
        builderClassTM.getSetters().add(new SetterTM("withName", field2Name, field2Type, selfType));
        builderClassTM.getSetters().add(new SetterTM("withList", field1Name, field1Type, selfType));
        builderClassTM.getSetters().add(new SetterTM("withDate", field3Name, field3Type, selfType));
        builderClassTM.setCloneMethod(new CloneMethodTM(selfType));
        builderClassTM.setButMethod(new ButMethodTM(selfType));
        CopyMethodTM copyMethod = new CopyMethodTM(selfType, pojoType);
        copyMethod.getAssignments().add(new AssignmentTM("withName", new FieldAccessorTM("name")));
        copyMethod.getAssignments().add(new AssignmentTM("withList", new MethodAccessorTM("getList")));
        copyMethod.getAssignments().add(new AssignmentTM("withDate", new FieldAccessorTM("date")));
        builderClassTM.setCopyMethod(copyMethod);
        BuildMethodTM buildMethod = new BuildMethodTM(pojoType, new ConstructorCallTM(pojoClassname, new ArgumentTM(
                field1Name), new ArgumentTM(field2Name)));
        buildMethod.getSetterCalls().add(new SetterCallTM("setDate", field3Name));
        builderClassTM.setBuildMethod(buildMethod);
        builderClassTM
                .setBuilderFactoryMethod(new BuilderFactoryMethodTM(factoryMethodName, selfType, genericContraint));
        builderClassTM.setBuildHelperMethod(new BuildHelperMethodTM("some", "Data<T>", "DataBuilder<T>",
                genericContraint));
        // When:
        Writer writer = new StringWriter();
        underTest.generate(writer);

        // Then:
        //@formatter:off
        String generatedCode = writer.toString();
        log(generatedCode);
        assertThat(generatedCode).isEqualTo(
                "package net.karneim.scribble;"+"\n"
                +"\n"
                +"import net.karneim.base.AbstractBuilder;"+"\n"
                +"import java.util.Date;"+"\n"
                +"import java.util.List;"+"\n"
                +"import javax.annotation.Generated;"+"\n"
                +"\n"
                +"@Generated(\"PojoBuilder\")"+"\n"
                +"public class DataBuilder<T extends Number> extends AbstractBuilder implements Cloneable {"+"\n"
                +"    protected DataBuilder<T> self;"+"\n"
                +"\n"
                +"    private List<T> value$list$java$util$List; // mandatory construction parameter"+"\n"
                +"    private boolean isSet$list$java$util$List = false;"+"\n"
                +"\n"
                +"    private String value$name$java$lang$String; // mandatory construction parameter"+"\n"
                +"    private boolean isSet$name$java$lang$String = false;"+"\n"
                +"\n"
                +"    private Date value$date$java$util$Date;"+"\n"
                +"    private boolean isSet$date$java$util$Date = false;"+"\n"
                +"\n"
                +"    public DataBuilder() {"+"\n"
                +"        self = (DataBuilder<T>)this;"+"\n"
                +"    }"+"\n"
                +"\n"
                +"    public DataBuilder<T> withName(String aValue) {"+"\n"
                +"        value$name$java$lang$String = aValue;"+"\n"
                +"        isSet$name$java$lang$String = true;"+"\n"
                +"        return self;"+"\n"
                +"    }"+"\n"
                +"\n"
                +"    public DataBuilder<T> withList(List<T> aValue) {"+"\n"
                +"        value$list$java$util$List = aValue;"+"\n"
                +"        isSet$list$java$util$List = true;"+"\n"
                +"        return self;"+"\n"
                +"    }"+"\n"
                +"\n"
                +"    public DataBuilder<T> withDate(Date aValue) {"+"\n"
                +"        value$date$java$util$Date = aValue;"+"\n"
                +"        isSet$date$java$util$Date = true;"+"\n"
                +"        return self;"+"\n"
                +"    }"+"\n"
                +"\n"
                +"    @Override"+"\n"
                +"    public Object clone() {"+"\n"
                +"        try {"+"\n"
                +"            @SuppressWarnings(\"unchecked\")"+"\n"
                +"            DataBuilder<T> result = (DataBuilder<T>)super.clone();"+"\n"
                +"            result.self = result;"+"\n"
                +"            return result;"+"\n"
                +"        } catch (CloneNotSupportedException e) {"+"\n"
                +"            throw new InternalError(e.getMessage());"+"\n"
                +"        }"+"\n"
                +"    }"+"\n"
                +"\n"
                +"    @SuppressWarnings(\"unchecked\")"+"\n"
                +"    public DataBuilder<T> but() {"+"\n"
                +"        return (DataBuilder<T>)clone();"+"\n"
                +"    }"+"\n"
                +"\n"
                +"    public DataBuilder<T> copy(Data<T> original) {"+"\n"
                +"        withName(original.name);"+"\n"
                +"        withList(original.getList());"+"\n"
                +"        withDate(original.date);"+"\n"
                +"        return self;"+"\n"
                +"    }"+"\n"
                +"\n"
                +"    public Data<T> build() {"+"\n"
                +"        Data<T> result = new Data(value$list$java$util$List, value$name$java$lang$String);"+"\n"
                +"        if (isSet$date$java$util$Date) {"+"\n"
                +"            result.setDate(value$date$java$util$Date);"+"\n"
                +"        }"+"\n"
                +"        return result;"+"\n"
                +"    }"+"\n"
                +"\n"
                +"    public static <T extends Number> DataBuilder<T> $Data() {"+"\n"
                +"        return new DataBuilder<T>();"+"\n"
                +"    }"+"\n"
                +"\n"
                +"    public static <T extends Number> Data<T> some(DataBuilder<T> builder) {"+"\n"
                +"        return builder.build();"+"\n"
                +"    }"+"\n"
                +"}");
        //@formatter:on
    }

}
