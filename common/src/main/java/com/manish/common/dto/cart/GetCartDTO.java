package com.manish.common.dto.cart;

import java.util.Date;
import java.util.Set;

public class GetCartDTO {
    private String cartId;
    private Set<Item> items;
    private String createdBy;
    private Date createdAt;
    private String updatedBy;
    private Date updatedAt;
}
