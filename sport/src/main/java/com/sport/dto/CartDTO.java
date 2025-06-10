package com.sport.dto;

import java.util.Date;
import java.util.List;

public class CartDTO {
    private String id;
    private String userId;
    private List<ItemDTO> items;
    private String status;
    private Date createdAt;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public List<ItemDTO> getItems() {
        return items;
    }

    public void setItems(List<ItemDTO> items) {
        this.items = items;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public static class ItemDTO {
        private String productId;
        private int quantity;
    
        public String getProductId() {
            return productId;
        }
    
        public void setProductId(String productId) {
            this.productId = productId;
        }
    
        public int getQuantity() {
            return quantity;
        }
    
        public void setQuantity(int quantity) {
            this.quantity = quantity;
        }
    }
}
