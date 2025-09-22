package com.gearx.feature.category.controller;

import jakarta.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import com.gearx.common.constants.ApiConstants;
import com.gearx.common.response.ApiResponse;
import com.gearx.common.response.PageResponse;
import com.gearx.common.response.ResponseHandler;
import com.gearx.feature.category.dto.request.CategoryRequest;
import com.gearx.feature.category.dto.response.CategoryResponse;
import com.gearx.feature.category.service.CategoryService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping(ApiConstants.Category.BASE)
@RequiredArgsConstructor
@Validated
public class CategoryController {

    private final CategoryService categoryService;

    @PostMapping(ApiConstants.Category.INSERT)
    public ResponseEntity<ApiResponse<Object>> insert(@Valid @RequestBody CategoryRequest req) {
        return ResponseHandler.success("CREATED", categoryService.insert(req));
    }

    @PutMapping(ApiConstants.Category.UPDATE)
    public ResponseEntity<ApiResponse<Object>> update(
            @PathVariable Integer id, @Valid @RequestBody CategoryRequest req) {
        return ResponseHandler.success("UPDATED", categoryService.update(id, req));
    }

    @GetMapping(ApiConstants.Category.GET_BY_ID)
    public ResponseEntity<ApiResponse<CategoryResponse>> getById(@PathVariable Integer id) {
        return ResponseHandler.success(categoryService.findById(id));
    }

    @GetMapping(ApiConstants.Category.SEARCH_PAGEABLE)
    public ResponseEntity<ApiResponse<PageResponse<CategoryResponse>>> search(
            @RequestParam(required = false) String q,
            @RequestParam(required = false) Boolean active,
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "20") int size,
            @RequestParam(defaultValue = "created_at") String sortBy,
            @RequestParam(defaultValue = "desc") String dir) {
        return ResponseHandler.success(categoryService.search(q, active, page, size, sortBy, dir));
    }

    @DeleteMapping(ApiConstants.Category.SOFT_DELETE)
    public ResponseEntity<ApiResponse<Object>> softDelete(
            @PathVariable Integer id, @RequestParam String updatedBy) {
        return ResponseHandler.success("SOFT_DELETED", categoryService.softDelete(id, updatedBy));
    }

    @PostMapping(ApiConstants.Category.RESTORE)
    public ResponseEntity<ApiResponse<Object>> restore(
            @PathVariable Integer id, @RequestParam String updatedBy) {
        return ResponseHandler.success("RESTORED", categoryService.restore(id, updatedBy));
    }

    @PatchMapping(ApiConstants.Category.ACTIVE)
    public ResponseEntity<ApiResponse<Object>> updateActive(
            @PathVariable Integer id, @RequestParam short value, @RequestParam String updatedBy) {
        return ResponseHandler.success(
                "ACTIVE_UPDATED", categoryService.updateActive(id, value, updatedBy));
    }
}
