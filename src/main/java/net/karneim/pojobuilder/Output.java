package net.karneim.pojobuilder;

import net.karneim.pojobuilder.model.BuilderM;
import net.karneim.pojobuilder.model.ManualBuilderM;

public class Output {
    private BuilderM builder;
    private ManualBuilderM manualBuilder;

    public Output(BuilderM builder, ManualBuilderM manualBuilder) {
        this.builder = builder;
        this.manualBuilder = manualBuilder;
    }

    public BuilderM getBuilder() {
        return builder;
    }

    public ManualBuilderM getManualBuilder() {
        return manualBuilder;
    }
}
