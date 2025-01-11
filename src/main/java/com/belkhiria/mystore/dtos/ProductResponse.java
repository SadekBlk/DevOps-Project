package com.belkhiria.mystore.dtos;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductResponse {
    private int id;
    private String name;
    private String brand;
    private String category;
    private double price;
}
