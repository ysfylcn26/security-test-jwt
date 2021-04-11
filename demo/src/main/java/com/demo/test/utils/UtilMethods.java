package com.demo.test.utils;

import lombok.experimental.UtilityClass;

import java.util.Collection;
import java.util.stream.Stream;

@UtilityClass
public class UtilMethods {
    public static boolean isNullOrBlankCheck(String value) {
        return value == null || value.trim().isEmpty();
    }

    public static <T> boolean isNullOrEmptyCollection(Collection<T> setData) {
        return setData == null || setData.isEmpty();
    }

    public static <T> Stream<T> isNullOrEmptyCollectionSafely(Collection<T> setData) {
        return setData == null ? Stream.empty() : setData.stream();
    }
}
