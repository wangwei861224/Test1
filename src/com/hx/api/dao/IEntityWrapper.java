package com.hx.api.dao;

public interface IEntityWrapper<E, T>
{
    T wrap(E entity);
}
