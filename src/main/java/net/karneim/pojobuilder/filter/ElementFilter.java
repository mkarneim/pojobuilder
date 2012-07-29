package net.karneim.pojobuilder.filter;

import java.lang.annotation.Annotation;

import javax.lang.model.element.Element;
import javax.lang.model.element.ElementKind;

import net.karneim.pojobuilder.GeneratePojoBuilder;

@GeneratePojoBuilder
public class ElementFilter<E extends Element> extends FilterableList<E> {

	public ElementFilter(Iterable<? extends E> srcElems) {
		super(srcElems);
	}

	public void setAnnotation(final Class<? extends Annotation> annotation) {
		add(new Filter<E>() {

			@Override
			public boolean accept(E e) {
				return e.getAnnotation(annotation) != null;
			}
		});
	}

	public void setKind(final ElementKind kind) {
		add(new Filter<E>() {

			@Override
			public boolean accept(E e) {
				return kind == e.getKind();
			}
		});
	}

	public void setSimpleNameMatches(final String regex) {
		add(new Filter<E>() {

			@Override
			public boolean accept(E e) {
				return e.getSimpleName().toString().matches(regex);

			}
		});
	}

}