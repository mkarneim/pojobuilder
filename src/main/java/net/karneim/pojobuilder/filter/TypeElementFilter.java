package net.karneim.pojobuilder.filter;

import javax.lang.model.element.TypeElement;

import net.karneim.pojobuilder.GeneratePojoBuilder;

@GeneratePojoBuilder
public class TypeElementFilter<E extends TypeElement> extends ElementFilter<E> {
	
	public TypeElementFilter(Iterable<? extends E> srcElems) {
		super(srcElems);
	}

	public void setQualifiedName(final String name) {
		add(new Filter<E>() {

			@Override
			public boolean accept(E e) {
				return e.getQualifiedName().contentEquals(name);

			}
		});
	}

}
