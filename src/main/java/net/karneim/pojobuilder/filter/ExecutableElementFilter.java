package net.karneim.pojobuilder.filter;

import java.beans.ConstructorProperties;
import java.util.List;
import java.util.Set;

import javax.lang.model.element.ExecutableElement;
import javax.lang.model.element.Modifier;
import javax.lang.model.element.VariableElement;
import javax.lang.model.type.TypeKind;

import net.karneim.pojobuilder.GeneratePojoBuilder;
@GeneratePojoBuilder
public class ExecutableElementFilter<E extends ExecutableElement> extends ElementFilter<E> {

	@ConstructorProperties({"elements"})
	public ExecutableElementFilter(Iterable<? extends E> srcElems) {
		super(srcElems);
	}

	public ExecutableElementFilter withReturnTypeOfKind(final TypeKind kind) {
		add(new Filter<E>() {

			@Override
			public boolean accept(E e) {
				return e.getReturnType().getKind() == kind;
			}
		});
		return this;
	}

	public ExecutableElementFilter withReturnTypeContainsModifier(final Modifier modifier) {
		add(new Filter<E>() {

			@Override
			public boolean accept(E e) {
				Set<Modifier> modifiers = e.getModifiers();
				return modifiers.contains(modifier);
			}
		});
		return this;
	}

	public ExecutableElementFilter withParameterSize(final int size) {
		add(new Filter<ExecutableElement>() {

			@Override
			public boolean accept(ExecutableElement e) {
				List<? extends VariableElement> parameters = e.getParameters();
				return parameters.size() == size;
			}
		});
		return this;
	}

}
