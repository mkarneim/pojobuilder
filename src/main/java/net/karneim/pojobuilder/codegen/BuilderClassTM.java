package net.karneim.pojobuilder.codegen;

import java.util.ArrayList;
import java.util.List;

/**
 * The {@link BuilderClassTM} is the template model of a pojo builder class.
 * 
 * @since 3.0
 * @author karneim
 */
public class BuilderClassTM {
	private PackageTM _package;
	private List<ImportTM> imports = new ArrayList<ImportTM>();
	private GeneratedTM generated;
	private String name;
	private SelfFieldTM selfField;
	private List<InterfaceTM> interfaces = new ArrayList<InterfaceTM>();
	private SuperclassTM superclass;
	private ConstructorTM constructor;
	private List<FieldTM> fields = new ArrayList<FieldTM>();
	private List<SetterTM> setters = new ArrayList<SetterTM>();
	private CloneMethodTM cloneMethod;
	private ButMethodTM butMethod;
	private CopyMethodTM copyMethod;
	private BuildMethodTM buildMethod;
	private BuilderFactoryMethodTM builderFactoryMethod;
	private BuildHelperMethodTM buildHelperMethod;

	public PackageTM getPackage() {
		return _package;
	}

	public void setPackage(PackageTM _package) {
		this._package = _package;
	}

	public List<ImportTM> getImports() {
		return imports;
	}

	public void setImports(List<ImportTM> imports) {
		this.imports = imports;
	}

	public GeneratedTM getGenerated() {
		return generated;
	}

	public void setGenerated(GeneratedTM generated) {
		this.generated = generated;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public SelfFieldTM getSelfField() {
		return selfField;
	}

	public void setSelfField(SelfFieldTM selfField) {
		this.selfField = selfField;
	}

	public List<InterfaceTM> getInterfaces() {
		return interfaces;
	}

	public void setInterfaces(List<InterfaceTM> interfaces) {
		this.interfaces = interfaces;
	}

	public SuperclassTM getSuperclass() {
		return superclass;
	}

	public void setSuperclass(SuperclassTM superclass) {
		this.superclass = superclass;
	}

	public ConstructorTM getConstructor() {
		return constructor;
	}

	public void setConstructor(ConstructorTM constructor) {
		this.constructor = constructor;
	}

	public List<FieldTM> getFields() {
		return fields;
	}

	public void setFields(List<FieldTM> fields) {
		this.fields = fields;
	}

	public List<SetterTM> getSetters() {
		return setters;
	}

	public void setSetters(List<SetterTM> setters) {
		this.setters = setters;
	}

	public CloneMethodTM getCloneMethod() {
		return cloneMethod;
	}

	public void setCloneMethod(CloneMethodTM cloneMethod) {
		this.cloneMethod = cloneMethod;
	}

	public ButMethodTM getButMethod() {
		return butMethod;
	}

	public void setButMethod(ButMethodTM butMethod) {
		this.butMethod = butMethod;
	}

	public CopyMethodTM getCopyMethod() {
		return copyMethod;
	}

	public void setCopyMethod(CopyMethodTM copyMethod) {
		this.copyMethod = copyMethod;
	}

	public BuildMethodTM getBuildMethod() {
		return buildMethod;
	}

	public void setBuildMethod(BuildMethodTM buildMethod) {
		this.buildMethod = buildMethod;
	}

	public void setBuilderFactoryMethod(BuilderFactoryMethodTM builderFactory) {
		this.builderFactoryMethod = builderFactory;
	}

	public BuilderFactoryMethodTM getBuilderFactoryMethod() {
		return builderFactoryMethod;
	}

	public void setBuildHelperMethod(BuildHelperMethodTM buildHelperMethod) {
		this.buildHelperMethod = buildHelperMethod;
	}

	public BuildHelperMethodTM getBuildHelperMethod() {
		return buildHelperMethod;
	}

}
