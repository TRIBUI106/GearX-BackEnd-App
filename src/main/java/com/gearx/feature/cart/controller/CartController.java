package com.gearx.feature.cart.controller;

import jakarta.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import com.gearx.common.constants.ApiConstants;
import com.gearx.feature.cart.dto.request.*;
import com.gearx.feature.cart.dto.response.*;
import com.gearx.feature.cart.service.CartService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping(ApiConstants.Cart.BASE)
@RequiredArgsConstructor
public class CartController {

    private final CartService cartService;

    private Integer resolveUserId(Integer userIdFromParam, Object principal) {
        if (userIdFromParam != null) return userIdFromParam;
        return 1;
    }

    @GetMapping
    public ResponseEntity<CartPageResponse> getMyCart(
            @RequestParam(required = false) Integer userId,
            @Valid CartPageQueryRequest query,
            @AuthenticationPrincipal Object principal) {
        return ResponseEntity.ok(cartService.getMyCart(resolveUserId(userId, principal), query));
    }

    @PostMapping(ApiConstants.Cart.ITEMS)
    public ResponseEntity<AddItemResponse> addItem(
            @RequestParam(required = false) Integer userId,
            @RequestBody @Valid AddItemRequest req,
            @AuthenticationPrincipal Object principal) {
        var res = cartService.addItem(resolveUserId(userId, principal), req);
        return ResponseEntity.ok(res);
    }

    @PutMapping(ApiConstants.Cart.UPDATE_QTY)
    public ResponseEntity<UpdateItemQtyResponse> updateQty(
            @RequestParam(required = false) Integer userId,
            @RequestBody @Valid UpdateItemQtyRequest req,
            @AuthenticationPrincipal Object principal) {
        var res = cartService.updateItemQty(resolveUserId(userId, principal), req);
        return ResponseEntity.ok(res);
    }

    @DeleteMapping(ApiConstants.Cart.ITEMS_BY_ID)
    public ResponseEntity<RemoveItemResponse> removeItem(
            @RequestParam(required = false) Integer userId,
            @PathVariable Integer itemId,
            @AuthenticationPrincipal Object principal) {
        var res = cartService.removeItem(resolveUserId(userId, principal), itemId);
        return ResponseEntity.ok(res);
    }

    @DeleteMapping(ApiConstants.Cart.CLEAR)
    public ResponseEntity<ClearCartResponse> clear(
            @RequestParam(required = false) Integer userId,
            @AuthenticationPrincipal Object principal) {
        var res = cartService.clearMyCart(resolveUserId(userId, principal));
        return ResponseEntity.ok(res);
    }

    @GetMapping(ApiConstants.Cart.TOTALS)
    public ResponseEntity<CartTotalsResponse> totals(
            @RequestParam(required = false) Integer userId,
            @AuthenticationPrincipal Object principal) {
        return ResponseEntity.ok(cartService.totals(resolveUserId(userId, principal)));
    }
}
