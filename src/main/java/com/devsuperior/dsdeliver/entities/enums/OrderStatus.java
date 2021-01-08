package com.devsuperior.dsdeliver.entities.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
public enum OrderStatus {
	
	PENDING(0, "Pendente"),
	DELIVERED(1, "Entregue");
	
	@Getter
	private int cod;
	@Getter
	private String descricao;
	
	public static OrderStatus toEnum(Integer cod) {
		if(cod == null) {
			return null;
		}
		
		for(OrderStatus x : OrderStatus.values()) {
			if(cod.equals(x.getCod())) {
				return x;
			}
		}
		
		throw new IllegalArgumentException("Id inválido: " + cod);
	}

}
