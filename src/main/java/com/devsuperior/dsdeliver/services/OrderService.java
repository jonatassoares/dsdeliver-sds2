package com.devsuperior.dsdeliver.services;

import java.time.Instant;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.devsuperior.dsdeliver.dto.OrderDTO;
import com.devsuperior.dsdeliver.dto.ProductDTO;
import com.devsuperior.dsdeliver.entities.Order;
import com.devsuperior.dsdeliver.entities.Product;
import com.devsuperior.dsdeliver.entities.enums.OrderStatus;
import com.devsuperior.dsdeliver.repositories.OrderRepository;
import com.devsuperior.dsdeliver.repositories.ProductRepository;

@Service
public class OrderService {

	@Autowired
	private OrderRepository repo;
	
	@Autowired
	private ProductRepository repoProduct;

	@Transactional(readOnly = true)
	public List<OrderDTO> fubdAll() {
		List<Order> list = repo.findOrdersWithProducts();

		return list.stream().map(x -> new OrderDTO(x)).collect(Collectors.toList());
	}
	
	@Transactional
	public OrderDTO insert(OrderDTO obj) {
		Order order = new Order(null, obj.getAddress(), obj.getLatitude(), obj.getLongitude(), Instant.now(), OrderStatus.PENDING);
		
		for(ProductDTO p : obj.getProducts()) {
			Product product = repoProduct.getOne(p.getId());
			order.getProducts().add(product);
		}
		
		order = repo.save(order);
		
		return new OrderDTO(order);
	}
	
	@Transactional
	public OrderDTO updateDelivered(Long id) {
		Order order = repo.getOne(id);
		order.setStatus(OrderStatus.DELIVERED);
		order = repo.save(order);
		
		return new OrderDTO(order);
	}

}
