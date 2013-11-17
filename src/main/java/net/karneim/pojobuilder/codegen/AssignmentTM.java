package net.karneim.pojobuilder.codegen;

/**
 * @since 3.0
 * @author karneim
 */
public class AssignmentTM {

	private String setter;
	private AccessorTM accessor;

	public AssignmentTM() {
	}

	public AssignmentTM(String setter, AccessorTM accessor) {
		this.setter = setter;
		this.accessor = accessor;
	}

	public void setSetter(String setter) {
		this.setter = setter;
	}

	public String getSetter() {
		return setter;
	}

	public void setAccessor(AccessorTM readAccess) {
		this.accessor = readAccess;
	}

	public AccessorTM getAccessor() {
		return accessor;
	}

}
