package net.karneim.pojobuilder;

import net.karneim.pojobuilder.model.PropertyM;

import javax.lang.model.element.ExecutableElement;
import java.util.List;

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

	public static PropertyM getFirstPropertyByName(List<PropertyM> properties, String name) {
		for (PropertyM p : properties) {
			if (name.equals(p.getName())) {
				return p;
			}
		}
		return null;
	}

}
