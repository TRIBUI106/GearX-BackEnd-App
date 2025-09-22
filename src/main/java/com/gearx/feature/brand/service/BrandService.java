package com.gearx.feature.brand.service;

import com.gearx.common.response.PageResponse;
import com.gearx.feature.brand.dto.request.BrandRequest;
import com.gearx.feature.brand.dto.response.BrandResponse;

public interface BrandService {

    int create(BrandRequest req);

    int update(Integer id, BrandRequest req);

    BrandResponse findById(Integer id);

    PageResponse<BrandResponse> pageSearch(
            String q, Boolean active, int page, int size, String sortBy, String dir);

    int softDelete(Integer id, String updatedBy);

    int restore(Integer id, String updatedBy);

    int updateActive(Integer id, short isActive, String updatedBy);

    int insert(BrandRequest req);

    PageResponse<BrandResponse> search(
            String q, Boolean active, int page, int size, String sortBy, String dir);
}
