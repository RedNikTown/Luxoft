package com.luxoft.convertor;

public interface Convertor<T, R> {

    R convert(T v);
}
