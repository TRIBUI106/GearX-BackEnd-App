package com.gearx.feature.category.service;

import com.gearx.common.response.PageResponse;
import com.gearx.feature.category.dto.request.CategoryRequest;
import com.gearx.feature.category.dto.response.CategoryResponse;

public interface CategoryService {

    int create(CategoryRequest req);

    int update(Integer id, CategoryRequest req);

    CategoryResponse findById(Integer id);

    PageResponse<CategoryResponse> pageSearch(
            String q, Boolean active, int page, int size, String sortBy, String dir);

    int softDelete(Integer id, String updatedBy);

    int restore(Integer id, String updatedBy);

    int updateActive(Integer id, short isActive, String updatedBy);

    int insert(CategoryRequest req);

    PageResponse<CategoryResponse> search(
            String q, Boolean active, int page, int size, String sortBy, String dir);
}
