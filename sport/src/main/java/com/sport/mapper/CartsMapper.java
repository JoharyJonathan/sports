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
        dto.setId(cart.getId().toHexString());
        dto.setUserId(cart.getUserId().toHexString());
        dto.setCreatedAt(cart.getCreatedAt());
        dto.setStatus(cart.getStatus());

        List<ItemDTO> itemDTOs = cart.getItems().stream()
            .map(item -> {
                ItemDTO itemDTO = new ItemDTO();
                itemDTO.setProductId(item.getProductId().toHexString());
                itemDTO.setQuantity(item.getQuantity());
                return itemDTO;
            }).collect(Collectors.toList());

        dto.setItems(itemDTOs);

        return dto;
    }
}
