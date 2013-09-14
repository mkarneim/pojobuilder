package net.karneim.pojobuilder.modelproducers;

import net.karneim.pojobuilder.model.BaseBuilderM;

/**
 * Produces null
 */
public class DummyModelProducer<M extends BaseBuilderM> implements ModelProducer<M> {

    public static final <M extends BaseBuilderM> DummyModelProducer<M> dummyModelProducer() {
        return new DummyModelProducer<M>();
    }

    @Override
    public M produce() {
        return null;
    }
}
