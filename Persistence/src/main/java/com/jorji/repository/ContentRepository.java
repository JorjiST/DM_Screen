package com.jorji.repository;

import com.jorji.HasID;

public interface ContentRepository {

    <T extends HasID> boolean save(T t);
}
