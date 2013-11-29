package net.karneim.pojobuilder.codegen;

/**
 * @since 3.0
 * @author karneim
 */
public class SetterCallTM {

	private String fieldname;
	private String methodName;

	public SetterCallTM() {
		super();
	}

	public SetterCallTM(String methodname, String fieldname) {
		this.methodName = methodname;
		this.fieldname = fieldname;
	}

	public void setFieldname(String fieldname) {
		this.fieldname = fieldname;
	}

	public String getFieldname() {
		return fieldname;
	}

	public void setMethodName(String methodName) {
		this.methodName = methodName;
	}

	public String getMethodName() {
		return methodName;
	}

}
