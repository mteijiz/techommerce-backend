package com.techommerce.backend.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "carts", schema = "ecommerce")
@Data
@NoArgsConstructor
public class Cart {

	

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long cartId;
	
	@NotNull
	@Min(0)
	private Float totalAmount;
	
	@NotNull
	private Boolean state;
	
	//temporal, se encuentra en el controller de cart
	public Cart(int i, boolean b) {
		// TODO Auto-generated constructor stub
		this.totalAmount = new Float(0.0);
		this.state = true;
	}
	
}
