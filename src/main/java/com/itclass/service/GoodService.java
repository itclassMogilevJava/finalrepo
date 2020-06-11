package com.itclass.service;

import com.itclass.domain.Good;

import java.util.List;
import java.util.Optional;

public interface GoodService {

    List<Good> findAll();
    Optional<Good> findById(Long id);
}
