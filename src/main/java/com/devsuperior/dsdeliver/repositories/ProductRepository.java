package com.devsuperior.dsdeliver.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.devsuperior.dsdeliver.entities.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
	
	@Transactional(readOnly = true)
	public List<Product> findAllByOrderByName();

}
