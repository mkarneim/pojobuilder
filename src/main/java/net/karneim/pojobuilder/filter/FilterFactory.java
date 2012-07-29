package net.karneim.pojobuilder.filter;

import javax.lang.model.element.Element;
import javax.lang.model.element.TypeElement;


public class FilterFactory<E> {
	public static FilterFactory<Element> filter(Iterable<? extends Element> elems) {
		return new FilterFactory<Element>(elems);
	}
	
	public static TypeElementFilterBuilder<TypeElement> TypeElementFilter() {
		return new TypeElementFilterBuilder<TypeElement>();
	}
	
	public static ElementFilterBuilder<Element> ElementFilter() {
		return new ElementFilterBuilder<Element>();
	}
	
	
	
	private Iterable<? extends E> elems;
	
	public FilterFactory(Iterable<? extends E> elems) {
		this.elems = elems;
	}

	public FilterableList<Element> by(ElementFilterBuilder<Element> builder) {
		return builder.withElements(elems).build();
	}
	 
	public FilterableList<TypeElement> by(TypeElementFilterBuilder<TypeElement> builder) {
		return builder.withElements(elems).build();
	}
	
}