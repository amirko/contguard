package com.contguard.track.dao;

import java.io.Reader;
import java.util.List;

public interface DAO<T> {

    List<T> fetchAll(Reader reader);
}
