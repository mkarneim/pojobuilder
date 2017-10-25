package net.karneim.pojobuilder.analysis;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.lang.model.element.AnnotationValue;
import javax.lang.model.element.Element;

import net.karneim.pojobuilder.GeneratePojoBuilder;
import net.karneim.pojobuilder.Visibility;

public class Directives {
  private boolean generateCopyMethod = false;
  private String copyMethodName = null;
  private String intoPackage = GeneratePojoBuilder.DEFAULT_PACKAGE;
  private String builderName = GeneratePojoBuilder.DEFAULT_NAME;
  private String baseclassName = Object.class.getName();
  private String builderInterfaceName = Void.class.getName();
  private boolean generateBuilderProperties = false;
  private boolean generationGap = false;
  private String setterNamePattern = GeneratePojoBuilder.DEFAULT_SETTER_NAME;
  private String staticFactoryMethod = GeneratePojoBuilder.DEFAULT_FACTORY_METHOD;
  private Visibility constructor = null;
  private String validatorClassname = Void.class.getName();
  private String optionalClassname = Void.class.getName();
  private List<PropertyPattern> includeProperties = new ArrayList<PropertyPattern>();
  private List<PropertyPattern> excludeProperties = new ArrayList<PropertyPattern>();

  @SuppressWarnings("unchecked")
  public Directives(Map<String, Object> valueMap) {
    if (valueMap == null) {
      throw new NullPointerException("valueMap must not be null!");
    }
    setBaseclassName((String) valueMap.get("withBaseclass"));
    setBuilderInterfaceName((String) valueMap.get("withBuilderInterface"));
    setGenerateBuilderProperties((Boolean) valueMap.get("withBuilderProperties"));
    setBuilderName((String) valueMap.get("withName"));
    setIntoPackage((String) valueMap.get("intoPackage"));
    setGenerationGap((Boolean) valueMap.get("withGenerationGap"));
    setGenerateCopyMethod((Boolean) valueMap.get("withCopyMethod"));
    setSetterNamePattern((String) valueMap.get("withSetterNamePattern"));
    setStaticFactoryMethod((String) valueMap.get("withFactoryMethod"));
    setConstructor(toVisibility((Element) valueMap.get("withConstructor")));
    setValidatorClassname((String) valueMap.get("withValidator"));
    setOptionalClassname((String) valueMap.get("withOptionalProperties"));
    setIncludeProperties(newList((List<AnnotationValue>) valueMap.get("includeProperties")));
    setExcludeProperties(newList((List<AnnotationValue>) valueMap.get("excludeProperties")));

    if (constructor == Visibility.PRIVATE && staticFactoryMethod.isEmpty()) {
      throw new AnalysisException(
          "Value for attribute 'withConstructor' must not be PRIVATE if value for attribute 'withFactoryMethod' is blank.");
    }
  }

  private Visibility toVisibility(Element object) {
    String name = object.getSimpleName().toString();
    Visibility visibility = Visibility.valueOf(name);
    return visibility;
  }

  private List<PropertyPattern> newList(List<AnnotationValue> array) {
    List<PropertyPattern> result = new ArrayList<PropertyPattern>(array.size());
    for (AnnotationValue elem : array) {
      result.add(new PropertyPattern(String.valueOf(elem.getValue())));
    }
    return result;
  }

  public boolean isGenerateCopyMethod() {
    return generateCopyMethod;
  }

  public void setGenerateCopyMethod(boolean generateCopyMethod) {
    this.generateCopyMethod = generateCopyMethod;
    if (generateCopyMethod) {
      copyMethodName = "copy"; // TODO make configurable in annotation!
    } else {
      copyMethodName = null;
    }
  }

  public String getCopyMethodName() {
    return copyMethodName;
  }

  public void setCopyMethodName(String copyMethodName) {
    this.copyMethodName = copyMethodName;
  }

  public String getValidatorClassname() {
    return validatorClassname;
  }

  public void setValidatorClassname(String validatorClassname) {
    this.validatorClassname = validatorClassname;
  }

  public boolean isGenerationGap() {
    return generationGap;
  }

  public boolean isGenerateBuilderProperties() {
    return generateBuilderProperties;
  }

  public void setGenerateBuilderProperties(boolean generateBuilderProperties) {
    this.generateBuilderProperties = generateBuilderProperties;
  }

  public void setGenerationGap(boolean generationGap) {
    this.generationGap = generationGap;
  }

  public void setIntoPackage(String intoPackage) {
    if (intoPackage == null) {
      throw new IllegalArgumentException("intoPackage==null");
    }
    if (intoPackage.startsWith("java.")) {
      throw new AnalysisException("Value for attribute 'intoPackage' must not start with 'java.'");
    }
    this.intoPackage = intoPackage;
  }

  public String getIntoPackage() {
    return intoPackage;
  }

  public String getBuilderName() {
    return builderName;
  }

  public void setBuilderName(String builderName) {
    if (builderName == null || builderName.trim().isEmpty()) {
      throw new AnalysisException("Value for attribute 'withName' must not be empty.");
    }
    this.builderName = builderName;
  }

  public void setBaseclassName(String baseclassName) {
    this.baseclassName = baseclassName;
  }

  public String getBaseclassName() {
    return baseclassName;
  }

  public String getBuilderInterfaceName() {
    return builderInterfaceName;
  }

  public void setBuilderInterfaceName(String builderInterfaceName) {
    this.builderInterfaceName = builderInterfaceName;
  }

  public String getSetterNamePattern() {
    return setterNamePattern;
  }

  public void setSetterNamePattern(String pattern) {
    this.setterNamePattern = pattern;
  }

  public String getOptionalClassname() {
    return optionalClassname;
  }

  public void setOptionalClassname(String optionalClassname) {
    this.optionalClassname = optionalClassname;
  }

  public String getStaticFactoryMethod() {
    return staticFactoryMethod;
  }

  public void setStaticFactoryMethod(String staticFactoryMethod) {
    this.staticFactoryMethod = staticFactoryMethod;
  }

  public Visibility getConstructor() {
    return constructor;
  }

  public void setConstructor(Visibility constructor) {
    this.constructor = constructor;
  }

  public List<PropertyPattern> getExcludeProperties() {
    return excludeProperties;
  }

  public void setExcludeProperties(List<PropertyPattern> excludeProperties) {
    this.excludeProperties = excludeProperties;
  }

  public List<PropertyPattern> getIncludeProperties() {
    return includeProperties;
  }

  public void setIncludeProperties(List<PropertyPattern> includeProperties) {
    this.includeProperties = includeProperties;
  }

  @Override
  public String toString() {
    return "Directives [generateCopyMethod=" + generateCopyMethod + ", copyMethodName=" + copyMethodName
        + ", intoPackage=" + intoPackage + ", builderName=" + builderName + ", baseclassName=" + baseclassName
        + ", builderInterfaceName=" + builderInterfaceName + ", generateBuilderProperties=" + generateBuilderProperties
        + ", generationGap=" + generationGap + ", setterNamePattern=" + setterNamePattern + ", staticFactoryMethod="
        + staticFactoryMethod + ", constructor=" + constructor + ", validatorClassname=" + validatorClassname
        + ", optionalClassname=" + optionalClassname + ", includeProperties=" + includeProperties
        + ", excludeProperties=" + excludeProperties + "]";
  }

}
