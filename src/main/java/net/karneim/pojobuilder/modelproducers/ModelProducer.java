package net.karneim.pojobuilder.modelproducers;

import net.karneim.pojobuilder.model.BaseBuilderM;

/**
 * Denotes a producer of Model classes
 */
public interface ModelProducer<M extends BaseBuilderM> {
    M produce();
}
