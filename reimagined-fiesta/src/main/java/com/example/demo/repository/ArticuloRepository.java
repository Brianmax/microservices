package com.example.demo.repository;

import com.example.demo.entity.ArticuloEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ArticuloRepository extends JpaRepository<ArticuloEntity, Integer> {
}
