package org.pets.dao;

import java.sql.SQLException;
import java.util.ArrayList;

public interface DAO<T> {
    T get(int id) throws SQLException;

    ArrayList<T> getAll() throws SQLException;

    int insert(T t) throws SQLException;

    int update (T t) throws SQLException;

    int delete(int id) throws SQLException;
}
