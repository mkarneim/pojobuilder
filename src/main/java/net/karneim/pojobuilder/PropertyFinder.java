package net.karneim.pojobuilder;

import java.beans.ConstructorProperties;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.processing.ProcessingEnvironment;
import javax.lang.model.element.Element;
import javax.lang.model.element.ElementKind;
import javax.lang.model.element.ExecutableElement;
import javax.lang.model.element.Modifier;
import javax.lang.model.element.TypeElement;
import javax.lang.model.element.VariableElement;
import javax.lang.model.type.DeclaredType;
import javax.lang.model.type.TypeKind;
import javax.lang.model.type.TypeMirror;
import javax.lang.model.util.ElementScanner6;

import net.karneim.pojobuilder.model.PropertyM;
import net.karneim.pojobuilder.model.TypeM;
import net.karneim.pojobuilder.util.PropertyMBuilder;

public class PropertyFinder extends ElementScanner6<Void, Void> {
	private Map<String, PropertyMBuilder> properties = new HashMap<String, PropertyMBuilder>();
	private ProcessingEnvironment env;

	public PropertyFinder(ProcessingEnvironment env) {
		this.env = env;
	}

	public Collection<PropertyM> getProperties() {
		List<PropertyM> result = new ArrayList<PropertyM>();
		for (PropertyMBuilder builder : properties.values()) {
			result.add(builder.build());
		}
		return result;
	}

	@Override
	public Void visitType(TypeElement e, Void p) {
		// System.out.println("visit: " + e);
		return super.visitType(e, p);
	}

	@Override
	public Void visitExecutable(ExecutableElement e, Void p) {
		// System.out.println("visit: " + e);
		ElementKind k = e.getKind();
		switch (k) {
		case CONSTRUCTOR:
			foundExecutableAsConstructor(e, p);
			break;

		case METHOD:
			foundExecutableAsMethod(e, p);
			break;

		default:

		}

		return super.visitExecutable(e, p);
	}

	@Override
	public Void visitVariable(VariableElement e, Void p) {
		// System.out.println("visit: " + e);
		ElementKind k = e.getKind();
		switch (k) {
		case FIELD:
			foundVariableAsField(e, p);
		default:

		}

		return super.visitVariable(e, p);
	}

	private void foundExecutableAsMethod(ExecutableElement e, Void p) {
		System.out.println("foundExecutableAsMethod : " + e);
		String methodName = e.getSimpleName().toString();

		// We are note interested into getter methods here
		// if (isGetter(e)) {
		// String propName = getPropertyName(methodName);
		// addPropertyGetter(propName, e);
		//
		// }

		if (PropertyFinder.isSetter(e)) {
			String propName = PropertyFinder.getPropertyName(methodName);

			addPropertySetter(propName, e);
		}

	}

	private void foundExecutableAsConstructor(ExecutableElement e, Void p) {
		System.out.println("foundExecutableAsConstructor : " + e);
		ConstructorProperties anno = e
				.getAnnotation(ConstructorProperties.class);
		if (anno == null) {
			return;
		}
		String[] paramNames = anno.value();
		List<? extends VariableElement> params = e.getParameters();
		int i = 0;
		for (VariableElement param : params) {
			String name = paramNames[i];
			addProperty(name, i, param);
			++i;
		}

	}

	private void addProperty(String propName, int paramPos,
			VariableElement param) {
		// TODO Auto-generated method stub
		String type = getType(param);
		PropertyMBuilder p = getPropertyMBuilder(propName, type);
		p.withType(TypeM.get(type)).withParameterPos(paramPos);
	}

	private void foundVariableAsField(VariableElement e, Void v) {
		System.out.println("foundVariableAsField : " + e);
		if (e.getModifiers().contains(Modifier.STATIC)
				|| e.getModifiers().contains(Modifier.FINAL)) {
			return;
		}
		if (e.getModifiers().contains(Modifier.PROTECTED)
				|| e.getModifiers().contains(Modifier.PUBLIC)) {
			String type = getType(e);
			String propName = e.getSimpleName().toString();
			PropertyMBuilder p = getPropertyMBuilder(propName, type);

			p.withType(TypeM.get(type)).withAccessible(true);
		}
	}

	private void addPropertySetter(String propName, ExecutableElement e) {
		String methodName = e.getSimpleName().toString();
		String paramType = getType(e.getParameters().get(0));
		PropertyMBuilder p = getPropertyMBuilder(propName, paramType);
		p.withSetter(methodName).withType(TypeM.get(paramType));
	}

	private String getType(VariableElement variableElement) {
		// TypeMirror typeMirror = env.getTypeUtils().capture(
		// variableElement.asType());
		TypeMirror typeMirror = variableElement.asType();
		String name = getTypeName(typeMirror);
		return name;
	}

	private String getTypeName(TypeMirror typeMirror) {
		TypeKind kind = typeMirror.getKind();
		switch (kind) {
		case BOOLEAN:
			return Boolean.TYPE.getName();
		case CHAR:
			return Character.TYPE.getName();
		case BYTE:
			return Byte.TYPE.getName();
		case SHORT:
			return Short.TYPE.getName();
		case INT:
			return Integer.TYPE.getName();
		case LONG:
			return Long.TYPE.getName();
		case FLOAT:
			return Float.TYPE.getName();
		case DOUBLE:
			return Double.TYPE.getName();
		case DECLARED:
			if (typeMirror instanceof DeclaredType) {
				DeclaredType dt = (DeclaredType) typeMirror;
				List<? extends TypeMirror> typeArgs = dt.getTypeArguments();
				if (!typeArgs.isEmpty()) {
					// HERE! We have the type args!!!
					System.out.println("typeArgs="
							+ Arrays.toString(typeArgs.toArray()));
				}
			}
			Element element = env.getTypeUtils().asElement(typeMirror);
			System.out.println("getTypeName: element=" + element);
			if (element instanceof TypeElement) {
				TypeElement el = (TypeElement) element;
				return el.getQualifiedName().toString();
			} else {
				throw new IllegalStateException("element=" + element);
			}
		case VOID:
			return Void.TYPE.getName();
		case ARRAY:
			return typeMirror.toString();
		default:
			throw new UnsupportedOperationException("typeMirror="
					+ typeMirror.getClass().getName());
		}

	}

	private PropertyMBuilder getPropertyMBuilder(String propName, String type) {
		String fieldname = PropertyFinder.toFieldname(propName, type);
		PropertyMBuilder result = properties.get(fieldname);
		if (result == null) {
			result = new PropertyMBuilder().withName(propName).withFieldname(
					fieldname);
			properties.put(fieldname, result);
		}
		return result;
	}

	private static String toFieldname(String propName, String type) {
		String typeString = type.replaceAll("\\.", "\\$");
		typeString = typeString.replaceAll("\\[\\]", "\\$");
		return propName + "$" + typeString;
	}

	private static String convertToPropertyName(String name) {
		char[] vals = name.toCharArray();
		vals[0] = Character.toLowerCase(vals[0]);
		return String.valueOf(vals);
	}

	private static String getPropertyName(String setterMethodName) {
		if (setterMethodName == null) {
			return null;
		}
		return PropertyFinder.convertToPropertyName(setterMethodName
				.substring(3));
	}

	private static boolean isSetter(ExecutableElement ee) {
		String methodName = ee.getSimpleName().toString();
		TypeMirror retType = ee.getReturnType();

		if (ee.getModifiers().contains(Modifier.PRIVATE)
				|| ee.getModifiers().contains(Modifier.STATIC)) {
			return false;
		}

		return methodName.startsWith("set")
				&& methodName.length() > "set".length()
				&& retType.getKind() == TypeKind.VOID
				&& ee.getParameters().size() == 1;
	}

}