package com.sport.mapper;

import java.util.List;
import java.util.stream.Collectors;

import com.sport.dto.CartDTO;
import com.sport.dto.CartDTO.ItemDTO;
import com.sport.models.Cart;

public class CartMapper {

    public static CartDTO toDTO(Cart cart) {
        if (cart == null) return null;

        CartDTO dto = new CartDTO();
        dto.setId(cart.getId() != null ? cart.getId().toHexString() : null);
        dto.setUserId(cart.getUserId() != null ? cart.getUserId().toHexString() : null);
        dto.setCreatedAt(cart.getCreatedAt());
        dto.setStatus(cart.getStatus());

        List<ItemDTO> items = cart.getItems().stream().map(item -> {
            ItemDTO i = new ItemDTO();
            if (item.getProductId() != null) {
                i.setProductId(item.getProductId().toHexString());
            } else {
                i.setProductId(null);
                System.err.println("Warning: CartItem with null productId found in cart " + cart.getId().toHexString());
            }
            i.setQuantity(item.getQuantity());
            return i;
        }).collect(Collectors.toList());

        dto.setItems(items);
        return dto;
    }
}