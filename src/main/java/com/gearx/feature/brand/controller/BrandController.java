package com.gearx.feature.brand.controller;

import jakarta.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import com.gearx.common.constants.ApiConstants;
import com.gearx.common.response.ApiResponse;
import com.gearx.common.response.PageResponse;
import com.gearx.common.response.ResponseHandler;
import com.gearx.feature.brand.dto.request.BrandRequest;
import com.gearx.feature.brand.dto.response.BrandResponse;
import com.gearx.feature.brand.service.BrandService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping(ApiConstants.Brand.BASE)
@RequiredArgsConstructor
@Validated
public class BrandController {

    private final BrandService brandService;

    @PostMapping(ApiConstants.Brand.INSERT)
    public ResponseEntity<ApiResponse<Object>> insert(@Valid @RequestBody BrandRequest req) {
        return ResponseHandler.success("CREATED", brandService.insert(req));
    }

    @PutMapping(ApiConstants.Brand.UPDATE)
    public ResponseEntity<ApiResponse<Object>> update(
            @PathVariable Integer id, @Valid @RequestBody BrandRequest req) {
        return ResponseHandler.success("UPDATED", brandService.update(id, req));
    }

    @GetMapping(ApiConstants.Brand.GET_BY_ID)
    public ResponseEntity<ApiResponse<BrandResponse>> getById(@PathVariable Integer id) {
        return ResponseHandler.success(brandService.findById(id));
    }

    @GetMapping(ApiConstants.Brand.SEARCH_PAGEABLE)
    public ResponseEntity<ApiResponse<PageResponse<BrandResponse>>> search(
            @RequestParam(required = false) String q,
            @RequestParam(required = false) Boolean active,
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "20") int size,
            @RequestParam(defaultValue = "created_at") String sortBy,
            @RequestParam(defaultValue = "desc") String dir) {
        return ResponseHandler.success(brandService.search(q, active, page, size, sortBy, dir));
    }

    @DeleteMapping(ApiConstants.Brand.SOFT_DELETE)
    public ResponseEntity<ApiResponse<Object>> softDelete(
            @PathVariable Integer id, @RequestParam String updatedBy) {
        return ResponseHandler.success("SOFT_DELETED", brandService.softDelete(id, updatedBy));
    }

    @PostMapping(ApiConstants.Brand.RESTORE)
    public ResponseEntity<ApiResponse<Object>> restore(
            @PathVariable Integer id, @RequestParam String updatedBy) {
        return ResponseHandler.success("RESTORED", brandService.restore(id, updatedBy));
    }

    @PatchMapping(ApiConstants.Brand.ACTIVE)
    public ResponseEntity<ApiResponse<Object>> updateActive(
            @PathVariable Integer id, @RequestParam short value, @RequestParam String updatedBy) {
        return ResponseHandler.success(
                "ACTIVE_UPDATED", brandService.updateActive(id, value, updatedBy));
    }
}
