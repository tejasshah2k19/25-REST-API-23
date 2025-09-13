package com.entity;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

@FieldDefaults(level = AccessLevel.PRIVATE)
@Getter
@Setter
public class ProductEntity {

	Integer productId;
	String productName;
	String category;
	Integer qty;
	Float price;

}
