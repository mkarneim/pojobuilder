package net.karneim.pojobuilder.codegen;

import static org.assertj.core.api.Assertions.assertThat;

import java.io.IOException;
import java.io.StringWriter;
import java.io.Writer;

import org.junit.Before;
import org.junit.Test;

public class PojoBuilderCodeGeneratorTest {

    private void log(String message) {
        // uncomment this to view the log messages during test execution
        //System.out.println(message);
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
        builderClassTM.setName("MyPojoBuilder");

        // When:
        Writer writer = new StringWriter();
        underTest.generate(writer);

        // Then:
        //@formatter:off
        String generatedCode = writer.toString().replace("\r", "");
        
        log(generatedCode);
        assertThat(generatedCode).isEqualTo(
            "public class MyPojoBuilder {"+"\n"
            +"}");
        //@formatter:on
    }

    @Test
    public void testGenerateWithSelfField() throws IOException {
        // Given:
        builderClassTM.setName("MyPojoBuilder");
        builderClassTM.setSelfField(new SelfFieldTM("MyPojoBuilder"));

        // When:
        Writer writer = new StringWriter();
        underTest.generate(writer);

        // Then:
        //@formatter:off
        String generatedCode = writer.toString().replace("\r", "");
        log(generatedCode);
        assertThat(generatedCode).isEqualTo(
            "public class MyPojoBuilder {"+"\n"
            + "    protected MyPojoBuilder self;"+"\n"
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
        String generatedCode = writer.toString().replace("\r", "");
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
        builderClassTM.setName("MyPojoBuilder");
        builderClassTM.setPackage(new PackageTM("com.example"));

        // When:
        Writer writer = new StringWriter();
        underTest.generate(writer);

        // Then:
        //@formatter:off
        String generatedCode = writer.toString().replace("\r", "");
        log(generatedCode);
        assertThat(generatedCode).isEqualTo(
            "package com.example;"+"\n"
            +"\n"
            +"public class MyPojoBuilder {"+"\n"
            +"}");
        //@formatter:on
    }

    @Test
    public void testGenerateWithConstructor() throws IOException {
        // Given:
        builderClassTM.setName("MyPojoBuilder<T>");
        ConstructorTM constr = new ConstructorTM();
        constr.setName("MyPojoBuilder");
        constr.setSelfCast("MyPojoBuilder<T>");
        builderClassTM.setConstructor(constr);

        // When:
        Writer writer = new StringWriter();
        underTest.generate(writer);

        // Then:
        //@formatter:off
        String generatedCode = writer.toString().replace("\r", "");
        log(generatedCode);
        assertThat(generatedCode).isEqualTo(
            "public class MyPojoBuilder<T> {"+"\n"
            +"\n"
            +"    public MyPojoBuilder() {"+"\n"
            +"        self = (MyPojoBuilder<T>)this;"+"\n"
            +"    }"+"\n"
            +"}");
        //@formatter:on
    }

    @Test
    public void testGenerateWithBuilderFactory() throws IOException {
        // Given:
        builderClassTM.setName("MyPojoBuilder");
        BuilderFactoryMethodTM builderFactory = new BuilderFactoryMethodTM();
        builderFactory.setName("$MyPojo");
        builderFactory.setReturnType("MyPojoBuilder");
        builderClassTM.setBuilderFactoryMethod(builderFactory);
        // When:
        Writer writer = new StringWriter();
        underTest.generate(writer);

        // Then:
        //@formatter:off
        String generatedCode = writer.toString().replace("\r", "");
        log(generatedCode);
        assertThat(generatedCode).isEqualTo(
            "public class MyPojoBuilder {"+"\n"
            +"\n"
            +"    public static MyPojoBuilder $MyPojo() {"+"\n"
            +"        return new MyPojoBuilder();"+"\n"
            +"    }"+"\n"
            +"}");
        //@formatter:on
    }

    @Test
    public void testGenerateWithGenericBuilderFactory() throws IOException {
        // Given:
        builderClassTM.setName("MyPojoBuilder<T extends Number>");
        BuilderFactoryMethodTM builderFactory = new BuilderFactoryMethodTM();
        builderFactory.setName("$MyPojo");
        builderFactory.setReturnType("MyPojoBuilder<T>");
        builderFactory.getGenerics().add("T extends Number");
        builderClassTM.setBuilderFactoryMethod(builderFactory);
        // When:
        Writer writer = new StringWriter();
        underTest.generate(writer);

        // Then:
        //@formatter:off
        String generatedCode = writer.toString().replace("\r", "");
        log(generatedCode);
        assertThat(generatedCode).isEqualTo(
            "public class MyPojoBuilder<T extends Number> {"+"\n"
            +"\n"
            +"    public static <T extends Number> MyPojoBuilder<T> $MyPojo() {"+"\n"
            +"        return new MyPojoBuilder<T>();"+"\n"
            +"    }"+"\n"
            +"}");
        //@formatter:on
    }

    @Test
    public void testGenerateWithGenericBuilderFactoryHavingMoreGenerics() throws IOException {
        // Given:
        builderClassTM.setName("MyPojoBuilder<T extends Number, L extends List<T>>");
        BuilderFactoryMethodTM builderFactory = new BuilderFactoryMethodTM();
        builderFactory.setName("$MyPojo");
        builderFactory.setReturnType("MyPojoBuilder<T,L>");
        builderFactory.getGenerics().add("T extends Number");
        builderFactory.getGenerics().add("L extends List<T>");
        builderClassTM.setBuilderFactoryMethod(builderFactory);
        // When:
        Writer writer = new StringWriter();
        underTest.generate(writer);

        // Then:
        //@formatter:off
        String generatedCode = writer.toString().replace("\r", "");
        log(generatedCode);
        assertThat(generatedCode).isEqualTo(
            "public class MyPojoBuilder<T extends Number, L extends List<T>> {"+"\n"
            +"\n"
            +"    public static <T extends Number, L extends List<T>> MyPojoBuilder<T,L> $MyPojo() {"+"\n"
            +"        return new MyPojoBuilder<T,L>();"+"\n"
            +"    }"+"\n"
            +"}");
        //@formatter:on
    }

    @Test
    public void testGenerateWithBuildHelper() throws IOException {
        // Given:
        builderClassTM.setName("MyPojoBuilder");
        BuildHelperMethodTM buildHelper = new BuildHelperMethodTM();
        buildHelper.setName("some");
        buildHelper.setReturnType("MyPojo");
        buildHelper.setParameterType("MyPojoBuilder");

        builderClassTM.setBuildHelperMethod(buildHelper);

        // When:
        Writer writer = new StringWriter();
        underTest.generate(writer);

        // Then:
        //@formatter:off
        String generatedCode = writer.toString().replace("\r", "");
        log(generatedCode);
        assertThat(generatedCode).isEqualTo(
            "public class MyPojoBuilder {"+"\n"
            +"\n"
            +"    public static MyPojo some(MyPojoBuilder builder) {"+"\n"
            +"        return builder.build();"+"\n"
            +"    }"+"\n"
            +"}");
        //@formatter:on
    }

    @Test
    public void testGenerateWithBuildHelperHavingGeneric() throws IOException {
        // Given:
        builderClassTM.setName("MyPojoBuilder<T extends Number>");
        BuildHelperMethodTM buildHelper = new BuildHelperMethodTM();
        buildHelper.setName("some");
        buildHelper.setReturnType("MyPojo<T>");
        buildHelper.setParameterType("MyPojoBuilder<T>");
        buildHelper.getGenerics().add("T extends Number");
        builderClassTM.setBuildHelperMethod(buildHelper);

        // When:
        Writer writer = new StringWriter();
        underTest.generate(writer);

        // Then:
        //@formatter:off
        String generatedCode = writer.toString().replace("\r", "");
        log(generatedCode);
        assertThat(generatedCode).isEqualTo(
            "public class MyPojoBuilder<T extends Number> {"+"\n"
            +"\n"
            +"    public static <T extends Number> MyPojo<T> some(MyPojoBuilder<T> builder) {"+"\n"
            +"        return builder.build();"+"\n"
            +"    }"+"\n"
            +"}");
        //@formatter:on
    }

    @Test
    public void testGenerateWithBuildHelperHavingMoreGenerics() throws IOException {
        // Given:
        builderClassTM.setName("MyPojoBuilder<T extends Number, X extends Comparable>");
        BuildHelperMethodTM buildHelper = new BuildHelperMethodTM();
        buildHelper.setName("some");
        buildHelper.setReturnType("MyPojo<T,X>");
        buildHelper.setParameterType("MyPojoBuilder<T,X>");
        buildHelper.getGenerics().add("T extends Number");
        buildHelper.getGenerics().add("X extends Comparable");
        builderClassTM.setBuildHelperMethod(buildHelper);

        // When:
        Writer writer = new StringWriter();
        underTest.generate(writer);

        // Then:
        //@formatter:off
        String generatedCode = writer.toString().replace("\r", "");
        log(generatedCode);
        assertThat(generatedCode).isEqualTo(
            "public class MyPojoBuilder<T extends Number, X extends Comparable> {"+"\n"
            +"\n"
            +"    public static <T extends Number, X extends Comparable> MyPojo<T,X> some(MyPojoBuilder<T,X> builder) {"+"\n"
            +"        return builder.build();"+"\n"
            +"    }"+"\n"
            +"}");
        //@formatter:on
    }

    @Test
    public void testGenerateWithSingleField() throws IOException {
        // Given:
        builderClassTM.setName("MyPojoBuilder");
        FieldTM field1 = new FieldTM();
        field1.setName("dummy");
        field1.setType("List<String>");
        builderClassTM.getFields().add(field1);

        // When:
        Writer writer = new StringWriter();
        underTest.generate(writer);

        // Then:
        //@formatter:off
        String generatedCode = writer.toString().replace("\r", "");
        log(generatedCode);
        assertThat(generatedCode).isEqualTo(
            "public class MyPojoBuilder {"+"\n"
            +"\n"
            +"    private List<String> value$dummy;"+"\n"
            +"    private boolean isSet$dummy = false;"+"\n"
            +"}");
        //@formatter:on
    }

    @Test
    public void testGenerateWithSingleMandatoryField() throws IOException {
        // Given:
        builderClassTM.setName("MyPojoBuilder");
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
        String generatedCode = writer.toString().replace("\r", "");
        log(generatedCode);
        assertThat(generatedCode).isEqualTo(
            "public class MyPojoBuilder {"+"\n"
            +"\n"
            +"    private List<String> value$dummy; // mandatory construction parameter"+"\n"
            +"    private boolean isSet$dummy = false;"+"\n"
            +"}");
        //@formatter:on
    }

    @Test
    public void testGenerateWithMultipleFields() throws IOException {
        // Given:
        builderClassTM.setName("MyPojoBuilder");
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
        String generatedCode = writer.toString().replace("\r", "");
        log(generatedCode);
        assertThat(generatedCode).isEqualTo(
            "public class MyPojoBuilder {"+"\n"
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
        builderClassTM.setName("MyPojoBuilder");
        SuperclassTM superclass = new SuperclassTM();
        superclass.setName("AbstractBuilder");
        builderClassTM.setSuperclass(superclass);

        // When:
        Writer writer = new StringWriter();
        underTest.generate(writer);

        // Then:
        //@formatter:off
        String generatedCode = writer.toString().replace("\r", "");
        log(generatedCode);
        assertThat(generatedCode).isEqualTo(
            "public class MyPojoBuilder extends AbstractBuilder {"+"\n"
            +"}");
        //@formatter:on
    }

    @Test
    public void testGenerateWithInterface() throws IOException {
        // Given:
        builderClassTM.setName("MyPojo");
        builderClassTM.getInterfaces().add(new InterfaceTM("Cloneable"));

        // When:
        Writer writer = new StringWriter();
        underTest.generate(writer);

        // Then:
        //@formatter:off
        String generatedCode = writer.toString().replace("\r", "");
        log(generatedCode);
        assertThat(generatedCode).isEqualTo(
            "public class MyPojo implements Cloneable {"+"\n"
            +"}");
        //@formatter:on
    }

    @Test
    public void testGenerateWithMultipleInterfaces() throws IOException {
        // Given:
        builderClassTM.setName("MyPojo");
        builderClassTM.getInterfaces().add(new InterfaceTM("Cloneable"));
        builderClassTM.getInterfaces().add(new InterfaceTM("Serializable"));

        // When:
        Writer writer = new StringWriter();
        underTest.generate(writer);

        // Then:
        //@formatter:off
        String generatedCode = writer.toString().replace("\r", "");
        log(generatedCode);
        assertThat(generatedCode).isEqualTo(
            "public class MyPojo implements Cloneable, Serializable {"+"\n"
            +"}");
        //@formatter:on
    }

    @Test
    public void testGenerateWithImport() throws IOException {
        // Given:
        builderClassTM.setName("MyPojo");
        builderClassTM.getImports().add(new ImportTM("java.util.List"));

        // When:
        Writer writer = new StringWriter();
        underTest.generate(writer);

        // Then:
        //@formatter:off
        String generatedCode = writer.toString().replace("\r", "");
        log(generatedCode);
        assertThat(generatedCode).isEqualTo(
             "import java.util.List;"+"\n"
            +"\n"
            +"public class MyPojo {"+"\n"
            +"}");
        //@formatter:on
    }

    @Test
    public void testGenerateWithImports() throws IOException {
        // Given:
        builderClassTM.setName("MyPojo");
        builderClassTM.getImports().add(new ImportTM("java.util.List"));
        builderClassTM.getImports().add(new ImportTM("java.util.ArrayList"));

        // When:
        Writer writer = new StringWriter();
        underTest.generate(writer);

        // Then:
        //@formatter:off
        String generatedCode = writer.toString().replace("\r", "");
        log(generatedCode);
        assertThat(generatedCode).isEqualTo(
             "import java.util.List;"+"\n"
            +"import java.util.ArrayList;"+"\n"
            +"\n"
            +"public class MyPojo {"+"\n"
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
        String generatedCode = writer.toString().replace("\r", "");
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
        String generatedCode = writer.toString().replace("\r", "");
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
        builderClassTM.setName("MyPojoBuilder");
        builderClassTM.setCloneMethod(new CloneMethodTM("MyPojoBuilder"));

        // When:
        Writer writer = new StringWriter();
        underTest.generate(writer);

        // Then:
        //@formatter:off
        String generatedCode = writer.toString().replace("\r", "");
        log(generatedCode);
        assertThat(generatedCode).isEqualTo(
            "public class MyPojoBuilder {"+"\n"
            +"\n"
            +"    @Override"+"\n"
            +"    public Object clone() {"+"\n"
            +"        try {"+"\n"
            +"            @SuppressWarnings(\"unchecked\")"+"\n"
            +"            MyPojoBuilder result = (MyPojoBuilder)super.clone();"+"\n"
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
        builderClassTM.setName("MyPojoBuilder");
        builderClassTM.setButMethod(new ButMethodTM("MyPojoBuilder"));

        // When:
        Writer writer = new StringWriter();
        underTest.generate(writer);

        // Then:
        //@formatter:off
        String generatedCode = writer.toString().replace("\r", "");
        log(generatedCode);
        assertThat(generatedCode).isEqualTo(
            "public class MyPojoBuilder {"+"\n"
            +"\n"
            +"    @SuppressWarnings(\"unchecked\")"+"\n"
            +"    public MyPojoBuilder but() {"+"\n"
            +"        return (MyPojoBuilder)clone();"+"\n"
            +"    }"+"\n"
            +"}");
        //@formatter:on
    }

    @Test
    public void testGenerateWithCopyMethodHavingOneAssignmentWithFieldAccessor() throws IOException {
        // Given:
        builderClassTM.setName("MyPojoBuilder<T>");
        CopyMethodTM copyMethod = new CopyMethodTM();
        copyMethod.setPojoType("MyPojo<T>");
        copyMethod.setReturnType("MyPojoBuilder<T>");
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
        String generatedCode = writer.toString().replace("\r", "");
        log(generatedCode);
        assertThat(generatedCode).isEqualTo(
            "public class MyPojoBuilder<T> {"+"\n"
            +"\n"
            +"    public MyPojoBuilder<T> copy(MyPojo<T> original) {"+"\n"
            +"        withName(original.name);"+"\n"
            +"        return self;"+"\n"
            +"    }"+"\n"
            +"}");
        //@formatter:on
    }

    @Test
    public void testGenerateWithCopyMethodHavingMoreAssignmentsWithFieldAccessor() throws IOException {
        // Given:
        builderClassTM.setName("MyPojoBuilder<T>");
        CopyMethodTM copyMethod = new CopyMethodTM();
        copyMethod.setPojoType("MyPojo<T>");
        copyMethod.setReturnType("MyPojoBuilder<T>");
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
        String generatedCode = writer.toString().replace("\r", "");
        log(generatedCode);
        assertThat(generatedCode).isEqualTo(
            "public class MyPojoBuilder<T> {"+"\n"
            +"\n"
            +"    public MyPojoBuilder<T> copy(MyPojo<T> original) {"+"\n"
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
        builderClassTM.setName("MyPojoBuilder<T>");
        CopyMethodTM copyMethod = new CopyMethodTM();
        copyMethod.setPojoType("MyPojo<T>");
        copyMethod.setReturnType("MyPojoBuilder<T>");
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
        String generatedCode = writer.toString().replace("\r", "");
        log(generatedCode);
        assertThat(generatedCode).isEqualTo(
            "public class MyPojoBuilder<T> {"+"\n"
            +"\n"
            +"    public MyPojoBuilder<T> copy(MyPojo<T> original) {"+"\n"
            +"        withName(original.getName());"+"\n"
            +"        return self;"+"\n"
            +"    }"+"\n"
            +"}");
        //@formatter:on
    }

    @Test
    public void testGenerateWithCopyMethodHavingMoreAssignmentsWithMethodAccessor() throws IOException {
        // Given:
        builderClassTM.setName("MyPojoBuilder<T>");
        CopyMethodTM copyMethod = new CopyMethodTM();
        copyMethod.setPojoType("MyPojo<T>");
        copyMethod.setReturnType("MyPojoBuilder<T>");
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
        String generatedCode = writer.toString().replace("\r", "");
        log(generatedCode);
        assertThat(generatedCode).isEqualTo(
            "public class MyPojoBuilder<T> {"+"\n"
            +"\n"
            +"    public MyPojoBuilder<T> copy(MyPojo<T> original) {"+"\n"
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
        builderClassTM.setName("MyPojoBuilder<T>");
        CopyMethodTM copyMethod = new CopyMethodTM();
        copyMethod.setPojoType("MyPojo<T>");
        copyMethod.setReturnType("MyPojoBuilder<T>");
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
        String generatedCode = writer.toString().replace("\r", "");
        log(generatedCode);
        assertThat(generatedCode).isEqualTo(
            "public class MyPojoBuilder<T> {"+"\n"
            +"\n"
            +"    public MyPojoBuilder<T> copy(MyPojo<T> original) {"+"\n"
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
        builderClassTM.setName("MyPojoBuilder");
        BuildMethodTM buildMethod = new BuildMethodTM();
        buildMethod.setReturnType("MyPojo");
        ConstructorCallTM construction = new ConstructorCallTM();
        construction.setMethodName("MyPojo");
        buildMethod.setConstruction(construction);
        builderClassTM.setBuildMethod(buildMethod);

        // When:
        Writer writer = new StringWriter();
        underTest.generate(writer);

        // Then:
        //@formatter:off
        String generatedCode = writer.toString().replace("\r", "");
        log(generatedCode);
        assertThat(generatedCode).isEqualTo(
            "public class MyPojoBuilder {"+"\n"
            +"\n"
            +"    public MyPojo build() {"+"\n"
            +"        MyPojo result = new MyPojo();"+"\n"
            +"        return result;"+"\n"
            +"    }"+"\n"
            +"}");
        //@formatter:on
    }

    @Test
    public void testGenerateWithBuildMethodWithOverrideAnnotation() throws IOException {
        // Given:
        builderClassTM.setName("MyPojoBuilder");
        BuildMethodTM buildMethod = new BuildMethodTM();
        buildMethod.setReturnType("MyPojo");
        buildMethod.setOverride(true);
        ConstructorCallTM construction = new ConstructorCallTM();
        construction.setMethodName("MyPojo");
        buildMethod.setConstruction(construction);
        builderClassTM.setBuildMethod(buildMethod);

        // When:
        Writer writer = new StringWriter();
        underTest.generate(writer);

        // Then:
        //@formatter:off
        String generatedCode = writer.toString().replace("\r", "");
        log(generatedCode);
        assertThat(generatedCode).isEqualTo(
            "public class MyPojoBuilder {"+"\n"
            +"\n"
            +"    @Override"+"\n"
            +"    public MyPojo build() {"+"\n"
            +"        MyPojo result = new MyPojo();"+"\n"
            +"        return result;"+"\n"
            +"    }"+"\n"
            +"}");
        //@formatter:on
    }

    @Test
    public void testGenerateWithBuildMethodCallingPojoConstructorWithArguments() throws IOException {
        // Given:
        builderClassTM.setName("MyPojoBuilder");
        BuildMethodTM buildMethod = new BuildMethodTM();
        buildMethod.setReturnType("MyPojo");
        ConstructorCallTM construction = new ConstructorCallTM();
        construction.setMethodName("MyPojo");
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
        String generatedCode = writer.toString().replace("\r", "");
        log(generatedCode);
        assertThat(generatedCode).isEqualTo(
            "public class MyPojoBuilder {"+"\n"
            +"\n"
            +"    public MyPojo build() {"+"\n"
            +"        MyPojo result = new MyPojo(value$name);"+"\n"
            +"        return result;"+"\n"
            +"    }"+"\n"
            +"}");
        //@formatter:on
    }

    @Test
    public void testGenerateWithBuildMethodCallingPojoConstructorWithMoreArguments() throws IOException {
        // Given:
        builderClassTM.setName("MyPojoBuilder");
        BuildMethodTM buildMethod = new BuildMethodTM();
        buildMethod.setReturnType("MyPojo");
        ConstructorCallTM construction = new ConstructorCallTM();
        construction.setMethodName("MyPojo");
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
        String generatedCode = writer.toString().replace("\r", "");
        log(generatedCode);
        assertThat(generatedCode).isEqualTo(
            "public class MyPojoBuilder {"+"\n"
            +"\n"
            +"    public MyPojo build() {"+"\n"
            +"        MyPojo result = new MyPojo(value$name, value$date);"+"\n"
            +"        return result;"+"\n"
            +"    }"+"\n"
            +"}");
        //@formatter:on
    }

    @Test
    public void testGenerateWithBuildMethodCallingPojoConstructorWithSetterCall() throws IOException {
        // Given:
        builderClassTM.setName("MyPojoBuilder");
        BuildMethodTM buildMethod = new BuildMethodTM();
        buildMethod.setReturnType("MyPojo");
        ConstructorCallTM construction = new ConstructorCallTM();
        construction.setMethodName("MyPojo");
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
        String generatedCode = writer.toString().replace("\r", "");
        log(generatedCode);
        assertThat(generatedCode).isEqualTo(
            "public class MyPojoBuilder {"+"\n"
            +"\n"
            +"    public MyPojo build() {"+"\n"
            +"        MyPojo result = new MyPojo();"+"\n"
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
        builderClassTM.setName("MyPojoBuilder");
        BuildMethodTM buildMethod = new BuildMethodTM();
        buildMethod.setReturnType("MyPojo");
        ConstructorCallTM construction = new ConstructorCallTM();
        construction.setMethodName("MyPojo");
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
        String generatedCode = writer.toString().replace("\r", "");
        log(generatedCode);
        assertThat(generatedCode).isEqualTo(
            "public class MyPojoBuilder {"+"\n"
            +"\n"
            +"    public MyPojo build() {"+"\n"
            +"        MyPojo result = new MyPojo();"+"\n"
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
        builderClassTM.setName("MyPojoBuilder");
        BuildMethodTM buildMethod = new BuildMethodTM();
        buildMethod.setReturnType("MyPojo");
        FactoryCallTM construction = new FactoryCallTM();
        construction.setMethodName("MyPojoFactory.createMyPojo");
        buildMethod.setConstruction(construction);
        builderClassTM.setBuildMethod(buildMethod);

        // When:
        Writer writer = new StringWriter();
        underTest.generate(writer);

        // Then:
        //@formatter:off
        String generatedCode = writer.toString().replace("\r", "");
        log(generatedCode);
        assertThat(generatedCode).isEqualTo(
            "public class MyPojoBuilder {"+"\n"
            +"\n"
            +"    public MyPojo build() {"+"\n"
            +"        MyPojo result = MyPojoFactory.createMyPojo();"+"\n"
            +"        return result;"+"\n"
            +"    }"+"\n"
            +"}");
        //@formatter:on
    }

    @Test
    public void testGenerateWithBuildMethodCallingPojoFactoryWithArgument() throws IOException {
        // Given:
        builderClassTM.setName("MyPojoBuilder");
        BuildMethodTM buildMethod = new BuildMethodTM();
        buildMethod.setReturnType("MyPojo");
        FactoryCallTM construction = new FactoryCallTM();
        construction.setMethodName("MyPojoFactory.createMyPojo");
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
        String generatedCode = writer.toString().replace("\r", "");
        log(generatedCode);
        assertThat(generatedCode).isEqualTo(
            "public class MyPojoBuilder {"+"\n"
            +"\n"
            +"    public MyPojo build() {"+"\n"
            +"        MyPojo result = MyPojoFactory.createMyPojo(value$name);"+"\n"
            +"        return result;"+"\n"
            +"    }"+"\n"
            +"}");
        //@formatter:on
    }

    @Test
    public void testGenerateWithBuildMethodCallingPojoFactoryWithMoreArguments() throws IOException {
        // Given:
        builderClassTM.setName("MyPojoBuilder");
        BuildMethodTM buildMethod = new BuildMethodTM();
        buildMethod.setReturnType("MyPojo");
        FactoryCallTM construction = new FactoryCallTM();
        construction.setMethodName("MyPojoFactory.createMyPojo");
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
        String generatedCode = writer.toString().replace("\r", "");
        log(generatedCode);
        assertThat(generatedCode).isEqualTo(
            "public class MyPojoBuilder {"+"\n"
            +"\n"
            +"    public MyPojo build() {"+"\n"
            +"        MyPojo result = MyPojoFactory.createMyPojo(value$name, value$date);"+"\n"
            +"        return result;"+"\n"
            +"    }"+"\n"
            +"}");
        //@formatter:on
    }

    @Test
    public void testGenerateWithBuildMethodCallingPojoFactoryWithSetterCall() throws IOException {
        // Given:
        builderClassTM.setName("MyPojoBuilder");
        BuildMethodTM buildMethod = new BuildMethodTM();
        buildMethod.setReturnType("MyPojo");
        FactoryCallTM construction = new FactoryCallTM();
        construction.setMethodName("MyPojoFactory.createMyPojo");
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
        String generatedCode = writer.toString().replace("\r", "");
        log(generatedCode);
        assertThat(generatedCode).isEqualTo(
            "public class MyPojoBuilder {"+"\n"
            +"\n"
            +"    public MyPojo build() {"+"\n"
            +"        MyPojo result = MyPojoFactory.createMyPojo();"+"\n"
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
        builderClassTM.setName("MyPojoBuilder");
        BuildMethodTM buildMethod = new BuildMethodTM();
        buildMethod.setReturnType("MyPojo");
        FactoryCallTM construction = new FactoryCallTM();
        construction.setMethodName("MyPojoFactory.createMyPojo");
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
        String generatedCode = writer.toString().replace("\r", "");
        log(generatedCode);
        assertThat(generatedCode).isEqualTo(
            "public class MyPojoBuilder {"+"\n"
            +"\n"
            +"    public MyPojo build() {"+"\n"
            +"        MyPojo result = MyPojoFactory.createMyPojo();"+"\n"
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

        builderClassTM.setPackage(new PackageTM("com.example"));
        builderClassTM.getImports().add(new ImportTM("com.example.base.AbstractBuilder"));
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
        String generatedCode = writer.toString().replace("\r", "");
        log(generatedCode);
        assertThat(generatedCode).isEqualTo(
            "package com.example;"+"\n"
            +"\n"
            +"import com.example.base.AbstractBuilder;"+"\n"
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
