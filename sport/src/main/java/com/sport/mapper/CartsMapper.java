package com.sport.mapper;

import java.util.List;
import java.util.stream.Collectors;

import com.sport.dto.CartDTO;
import com.sport.dto.CartDTO.ItemDTO;
import com.sport.models.Cart;

public class CartsMapper {
    public static CartDTO toDTO(Cart cart) {
        if (cart == null) return null;

        CartDTO dto = new CartDTO();
        dto.setId(cart.getId() != null ? cart.getId().toHexString() : null);
        dto.setUserId(cart.getUserId() != null ? cart.getUserId().toHexString() : null);
        dto.setCreatedAt(cart.getCreatedAt());
        dto.setStatus(cart.getStatus());

        List<ItemDTO> itemDTOs = cart.getItems().stream()
            .map(item -> {
                ItemDTO itemDTO = new ItemDTO();
                if (item.getProductId() != null) {
                    itemDTO.setProductId(item.getProductId().toHexString());
                } else {
                    System.err.println("Warning: CartItem with null productId found in cart. Cart ID: " + dto.getId());
                }
                itemDTO.setQuantity(item.getQuantity());
                return itemDTO;
            }).collect(Collectors.toList());

        dto.setItems(itemDTOs);

        return dto;
    }
}