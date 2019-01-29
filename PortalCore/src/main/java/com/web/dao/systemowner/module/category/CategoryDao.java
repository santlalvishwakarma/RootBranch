package com.web.dao.systemowner.module.category;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryDao extends JpaRepository<CategoryDao, Integer> {

}
