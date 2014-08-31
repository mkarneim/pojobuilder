package net.karneim.pojobuilder.analysis;

import java.util.ArrayList;
import java.util.List;

import net.karneim.pojobuilder.model.BuilderM;
import net.karneim.pojobuilder.model.ManualBuilderM;
import net.karneim.pojobuilder.model.TypeM;

public class Output {
  private final Input input;
  private BuilderM builderModel = new BuilderM();
  private ManualBuilderM manualBuilderModel;

  public Output(Input input) {
    this.input = input;
  }

  public Input getInput() {
    return input;
  }

  public BuilderM getBuilderModel() {
    return builderModel;
  }

  public ManualBuilderM getManualBuilderModel() {
    return manualBuilderModel;
  }

  public void setManualBuilderModel(ManualBuilderM manualBuilderModel) {
    this.manualBuilderModel = manualBuilderModel;
  }

  public List<TypeM> getTypesToGenerate() {
    List<TypeM> result = new ArrayList<TypeM>();
    if (builderModel != null) {
      result.add(builderModel.getType());
    }
    if (manualBuilderModel != null) {
      result.add(manualBuilderModel.getType());
    }
    return result;
  }

}
