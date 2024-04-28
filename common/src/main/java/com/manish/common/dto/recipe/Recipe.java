package com.manish.common.dto.recipe;

import java.util.Date;
import java.util.List;

public class Recipe {
    private String recipeId;
    private String name;
    private List<String> contents;
    private List<String> products;
    private List<String> reviews;
    private String createBy;
    private Date createAt;
    private String updatedBy;
    private Date updatedAt;
}
