package com.clientapi.mapper;

import java.util.List;

public interface BaseMapper<T, R> {
    R toEntity(T dto);

    List<R> toEntity(List<T> dto);

    T toDto(R entity);

    List<T> toDto(List<R> entity);
}
