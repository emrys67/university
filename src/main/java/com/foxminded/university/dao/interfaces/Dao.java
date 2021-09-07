package com.foxminded.university.dao.interfaces;

import java.util.List;

public interface Dao<T> {
    T getById(Long id);

    List<T> getAll();

    void delete(Long id);

    void update(T person);

    void create(T person);
}
