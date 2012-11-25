package study.buildermodelproducer;

import net.karneim.pojobuilder.model.BuilderM;

public class Output {
	private BuilderM builderModel;
	private ManualBuilderM manualBuilderModel;
	
	public Output() {
	}

	public Output(BuilderM builderModel) {
		super();
		this.builderModel = builderModel;
	}

	public BuilderM getBuilderModel() {
		return builderModel;
	}

	public void setBuilderModel(BuilderM builderModel) {
		this.builderModel = builderModel;
	}

	public ManualBuilderM getManualBuilderModel() {
		return manualBuilderModel;
	}

	public void setManualBuilderModel(ManualBuilderM manualBuilderModel) {
		this.manualBuilderModel = manualBuilderModel;
	}
}
