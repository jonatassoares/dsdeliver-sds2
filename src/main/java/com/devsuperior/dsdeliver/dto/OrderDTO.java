package com.devsuperior.dsdeliver.dto;

import java.io.Serializable;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.devsuperior.dsdeliver.entities.Order;
import com.devsuperior.dsdeliver.entities.enums.OrderStatus;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@NoArgsConstructor
public class OrderDTO implements Serializable {
	private static final long serialVersionUID = 1L;

	@EqualsAndHashCode.Include
	@Getter @Setter
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
	
	@Getter @Setter
	private Double total;
	
	@Getter
	private List<ProductDTO> products = new ArrayList<ProductDTO>();
	
	public OrderDTO(Long id, String address, Double latitude, Double longitude, Instant moment, OrderStatus status, Double total) {
		this.id = id;
		this.address = address;
		this.latitude = latitude;
		this.longitude = longitude;
		this.moment = moment;
		this.status = (status == null) ? null : status.getCod();
		this.total = total;
	}
	
	public OrderDTO(Order entity) {
		id = entity.getId();
		address = entity.getAddress();
		latitude = entity.getLatitude();
		longitude = entity.getLongitude();
		moment = entity.getMoment();
		status = entity.getStatus().getCod();
		total = entity.getTotal();
		products = entity.getProducts().stream().map(x -> new ProductDTO(x)).collect(Collectors.toList());
	}
	
	public OrderStatus getStatus() {
		return OrderStatus.toEnum(status);
	}
	
	public void setStatus(OrderStatus status) {
		this.status = status.getCod();
	}
}
