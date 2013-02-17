package net.karneim.pojobuilder;

import net.karneim.pojobuilder.model.BuilderM;
import net.karneim.pojobuilder.model.ManualBuilderM;

public class Output {
	private BuilderM builder;
	private ManualBuilderM manualBuilder;
	
	public Output() {
	}

	public Output(BuilderM builder) {
		super();
		this.builder = builder;
	}

	public BuilderM getBuilder() {
		return builder;
	}

	public void setBuilder(BuilderM builder) {
		this.builder = builder;
	}

	public ManualBuilderM getManualBuilder() {
		return manualBuilder;
	}

	public void setManualBuilder(ManualBuilderM manualBuilder) {
		this.manualBuilder = manualBuilder;
	}
}
