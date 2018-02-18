package com.lift.util;

public interface SoToModel<T,U> {

	abstract U getModelFromSo(T SO);
}
