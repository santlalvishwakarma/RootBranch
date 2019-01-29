package com.web.dao.systemowner.module.category;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.web.model.dvo.CategoryDVO;

@Repository
public interface CategoryDao extends JpaRepository<CategoryDVO, Integer> {

}
