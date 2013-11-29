package net.karneim.pojobuilder.codegen;

/**
 * @since 3.0
 * @author karneim
 */
public class ConstructorCallTM extends ConstructionTM {

	public ConstructorCallTM() {
	}

	public ConstructorCallTM(String methodName, ArgumentTM... arguments) {
		super(methodName, arguments);
	}

	@Override
	public boolean isConstructorCall() {
		return true;
	}

}
