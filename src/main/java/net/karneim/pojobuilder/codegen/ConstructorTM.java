package net.karneim.pojobuilder.codegen;

/**
 * @since 3.0
 * @author karneim
 */
public class ConstructorTM {
	private String name;
	private String selfCast;

	public ConstructorTM() {
	}

	public ConstructorTM(String name, String selfCast) {
		super();
		this.name = name;
		this.selfCast = selfCast;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSelfCast() {
		return selfCast;
	}

	public void setSelfCast(String selfCast) {
		this.selfCast = selfCast;
	}

}
