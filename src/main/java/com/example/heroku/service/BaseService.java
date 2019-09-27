package com.example.heroku.service;

import java.util.List;

public interface BaseService<T> {
    T getById(Long id);
    List<T> findAll();

    void add(T model);
    void update(T model);
    void delete(T model);
    void delete(Long id);
}
