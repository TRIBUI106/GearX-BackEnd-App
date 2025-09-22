package com.gearx.feature.category.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.gearx.feature.category.entity.Category;

@Mapper
public interface CategoryMapper {

    int insert(Category category);

    boolean existsByName(@Param("name") String name);

    int updateById(Category category);

    int softDeleteById(
            @Param("categoryId") Integer categoryId, @Param("updatedBy") String updatedBy);

    int restoreById(@Param("categoryId") Integer categoryId, @Param("updatedBy") String updatedBy);

    int updateActive(
            @Param("categoryId") Integer categoryId,
            @Param("isActive") Short isActive,
            @Param("updatedBy") String updatedBy);

    Category findById(@Param("categoryId") Integer categoryId);

    List<Category> pageSearch(Map<String, Object> params);

    long countPageSearch(Map<String, Object> params);
}
