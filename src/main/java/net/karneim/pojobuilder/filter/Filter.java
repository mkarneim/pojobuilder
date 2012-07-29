package net.karneim.pojobuilder.filter;


public interface Filter<T> {

	boolean accept(T e);

}
