package com.pizango.denunciaservice.converter;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

@Component

public abstract class AbstractConverter <E,D> {
	public abstract D fromEntity(E entity);
	public abstract E fromDTO(D dto);

	public List<D> fromEntity(List<E> entitys){
		return entitys.stream()
				.map(e -> fromEntity(e))
				.collect(Collectors.toList());
	}
	
	public List<E> fromDTO(List<D> dtos){
		return dtos.stream()
				.map(e -> fromDTO(e))
				.collect(Collectors.toList());
	}
}
