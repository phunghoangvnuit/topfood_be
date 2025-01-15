package com.fpt.topfood_be.request;

import com.fpt.topfood_be.model.IngredientsItem;
import lombok.Data;

import java.util.List;

@Data
public class AddCartItemRequest {

    private Long foodId;
    private int quantity;

    private List<IngredientsItem> ingredients;
}
