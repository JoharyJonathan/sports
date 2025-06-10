package com.sport.mapper;

import java.util.List;

import com.sport.dto.CartDTO;
import com.sport.dto.CartDTO.ItemDTO;
import com.sport.models.Cart;

public class CartMapper {

    public static CartDTO toDTO(Cart cart) {
        CartDTO dto = new CartDTO();
        dto.setId(cart.getId().toHexString());
        dto.setUserId(cart.getUserId().toHexString());
        dto.setCreatedAt(cart.getCreatedAt());
        dto.setStatus(cart.getStatus());

        List<ItemDTO> items = cart.getItems().stream().map(item -> {
            ItemDTO i = new ItemDTO();
            i.setProductId(item.getProductId().toHexString());
            i.setQuantity(item.getQuantity());
            return i;
        }).toList();

        dto.setItems(items);
        return dto;
    }
}
