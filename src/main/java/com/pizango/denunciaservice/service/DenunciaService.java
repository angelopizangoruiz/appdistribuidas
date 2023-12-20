package com.pizango.denunciaservice.service;

import java.util.List;

import org.springframework.data.domain.Pageable;

import com.pizango.denunciaservice.entity.Denuncia;

public interface DenunciaService {
	public List<Denuncia>findAll(Pageable page);
	public List<Denuncia>findByDni(String dni, Pageable page);
	public Denuncia findById(int id);
	public Denuncia save(Denuncia denuncia);
	public Denuncia update(Denuncia denuncia);
	public void delete(int id);
}