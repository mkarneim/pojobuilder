package net.karneim.pojobuilder.filter;

import java.util.ArrayList;
import java.util.List;

public class FilterableList<E> extends ArrayList<Filter<? super E>> {

	private Iterable<? extends E> srcElems;

	/**
	 * @param srcElems
	 */
	public FilterableList(Iterable<? extends E> srcElems) {
		super();
		this.srcElems = srcElems;
	}

	public <T extends E> List<T> into( List<? super T> destElems) {
		outerLoop: for (E e : srcElems) {
			for (Filter<? super E> filter : this) {
				if (!filter.accept(e)) {
					continue outerLoop;
				}
			}
			destElems.add((T) e);
		}
		return (List<T>) destElems;
	}
	
	public <T extends E> List<T> asListOfType(Class<? extends T> cls) {
		return into( new ArrayList<T>());
	}
	

}
