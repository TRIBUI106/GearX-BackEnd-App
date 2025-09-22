package com.gearx.feature.brand.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.gearx.feature.brand.entity.Brand;

@Mapper
public interface BrandMapper {

    int insert(Brand brand);

    int updateById(Brand brand);

    int softDeleteById(@Param("brandId") Integer brandId, @Param("updatedBy") String updatedBy);

    int restoreById(@Param("brandId") Integer brandId, @Param("updatedBy") String updatedBy);

    boolean existsByName(@Param("name") String name);

    int updateActive(
            @Param("brandId") Integer brandId,
            @Param("isActive") Short isActive,
            @Param("updatedBy") String updatedBy);

    Brand findById(@Param("brandId") Integer brandId);

    List<Brand> pageSearch(Map<String, Object> params);

    int countPageSearch(Map<String, Object> params);
}
