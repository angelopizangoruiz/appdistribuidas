package com.pizango.denunciaservice.converter;

import org.springframework.stereotype.Component;

import com.pizango.denunciaservice.dto.DenunciaDTO;
import com.pizango.denunciaservice.entity.Denuncia;

@Component
public class DenunciaConverter extends AbstractConverter<Denuncia, DenunciaDTO> {

	@Override
	public DenunciaDTO fromEntity(Denuncia entity) {
		if(entity==null) return null;
		return DenunciaDTO.builder()
				.id(entity.getId())
				.dni(entity.getDni())
				.fecha(entity.getFecha())
				.titulo(entity.getTitulo())
				.direccion(entity.getDireccion())
				.descripcion(entity.getDescripcion())
				.build();
	}

	@Override
	public Denuncia fromDTO(DenunciaDTO dto) {
		if(dto==null) return null;
		return Denuncia.builder()
				.id(dto.getId())
				.numerodocumento(dto.getNumerodocumento())
				.fecha(dto.getFecha())
				.titulo(dto.getTitulo())
				.direccion(dto.getDireccion())
				.descripcion(dto.getDescripcion())
				.build();
	}

}