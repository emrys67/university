package com.foxminded.university.dao.interfaces;

import java.util.List;

public interface Dao<T> {
    T getById(Long id);

    List<T> getAll();

    boolean delete(T person);

    boolean update(T person);

    boolean create(T person);
}
