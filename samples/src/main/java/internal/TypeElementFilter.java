package internal;

import java.beans.ConstructorProperties;

import javax.lang.model.element.TypeElement;

import net.karneim.pojobuilder.GeneratePojoBuilder;
import net.karneim.pojobuilder.filter.Filter;

@GeneratePojoBuilder
public class TypeElementFilter<E extends TypeElement> extends ElementFilter<E> {
	
	@ConstructorProperties({"elements"})
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
