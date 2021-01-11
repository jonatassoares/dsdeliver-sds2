package com.devsuperior.dsdeliver.entities;

import java.io.Serializable;
import java.time.Instant;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import com.devsuperior.dsdeliver.entities.enums.OrderStatus;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@NoArgsConstructor
@Entity @Table(name = "tb_order")
public class Order implements Serializable {
	private static final long serialVersionUID = 1L;

	@EqualsAndHashCode.Include
	@Getter @Setter
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Getter @Setter
	private String address;
	
	@Getter @Setter
	private Double latitude;
	
	@Getter @Setter
	private Double longitude;
	
	@Getter @Setter
	private Instant moment;

	private Integer status;
	
	@Getter
	@ManyToMany @JoinTable(name = "tb_order_product", joinColumns = @JoinColumn(name = "order_id"), inverseJoinColumns = @JoinColumn(name = "product_id"))
	private Set<Product> products = new HashSet<Product>();
	
	public Order(Long id, String address, Double latitude, Double longitude, Instant moment, OrderStatus status) {
		super();
		this.id = id;
		this.address = address;
		this.latitude = latitude;
		this.longitude = longitude;
		this.moment = moment;
		this.status = (status == null) ? null : status.getCod();
	}
	
	public OrderStatus getStatus() {
		return OrderStatus.toEnum(status);
	}
	
	public void setStatus(OrderStatus status) {
		this.status = status.getCod();
	}
	
	public Double getTotal() {
		double sum = 0.0;
		
		for (Product p : products) {
			sum += p.getPrice();
		}
		
		return sum;
	}

}
