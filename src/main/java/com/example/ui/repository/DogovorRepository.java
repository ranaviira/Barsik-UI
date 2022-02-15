package com.example.ui.repository;

import com.example.ui.entity.Dogovor;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DogovorRepository extends CrudRepository <Dogovor, Long> {
}
