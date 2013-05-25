package net.karneim.pojobuilder;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.lang.model.element.Element;
import javax.lang.model.element.ExecutableElement;

import net.karneim.pojobuilder.model.PropertyM;

import org.hamcrest.BaseMatcher;
import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.junit.Assert;
import org.junit.internal.matchers.TypeSafeMatcher;

public class TestBase {
	public static final String SRC_TESTDATA_DIR = "src/test/java";

	public static ExecutableElement getFirstMethodByName(String name, List<ExecutableElement> methods) {
		for (ExecutableElement e : methods) {
			if (name.equals(e.getSimpleName().toString())) {
				return e;
			}
		}
		return null;
	}

    public static List<PropertyM> filterByName(List<PropertyM> properties, String name) {
		List<PropertyM> result = new ArrayList<PropertyM>();
		for (PropertyM p : properties) {
			if (name == null && p.getName() == null) {
				result.add(p);
			} else if (name != null && name.equals(p.getName())) {
				result.add(p);
			}
		}
		return result;
	}

	public static PropertyM getFirstPropertyByName(List<PropertyM> properties, String name) {
		for (PropertyM p : properties) {
			if (name.equals(p.getName())) {
				return p;
			}
		}
		return null;
	}

}
