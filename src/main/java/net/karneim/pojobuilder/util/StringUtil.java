package net.karneim.pojobuilder.util;

public class StringUtil {

	public static String getBasename(String classname) {
		if (classname == null) {
			return null;
		}
		int idx = classname.lastIndexOf('.');
		if (idx == -1) {
			return classname;
		} else {
			return classname.substring(idx + 1);
		}
	}

	public static String getPackage(String classname) {
		if (classname == null) {
			return null;
		}
		int idx = classname.lastIndexOf('.');
		if (idx == -1) {
			return null;
		} else {
			return classname.substring(0, idx);
		}
	}

}
