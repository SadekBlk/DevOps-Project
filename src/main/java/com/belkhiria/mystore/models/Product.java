package com.belkhiria.mystore.models;

import java.util.Date;

import jakarta.persistence.*;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Setter
@Getter
@Entity
@Table(name = "products")
public class Product {
	// test

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	private String name;
	private String brand;
	private String category;
	private double price;
	
	@ManyToOne
	@JoinColumn(name = "user_id")
	private User user;
}
