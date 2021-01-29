package com.leo.springbootmongodb.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
class Property{
    private String name;
    private String value;
    private String category;
    private String description;
}
