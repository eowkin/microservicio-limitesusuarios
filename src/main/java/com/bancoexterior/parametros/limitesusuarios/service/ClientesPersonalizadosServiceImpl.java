package com.bancoexterior.parametros.limitesusuarios.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bancoexterior.parametros.limitesusuarios.repository.IClientesPersonalizadosRepository;


@Service
public class ClientesPersonalizadosServiceImpl implements IClientesPersonalizadosService{

	@Autowired
	private IClientesPersonalizadosRepository repo;
	
	/**
	 * Nombre: existsById 
	 * Descripcion: Invocar metodo para buscar si existe o no 
	 * un Cliente personalizado por id.
	 * @param codIbs String    
	 * @return boolean
	 * @version 1.0
	 * @author Eugenio Owkin
	 * @since 12/04/21
	 */
	@Override
	public boolean existsById(String codIbs) {
		return repo.existsById(codIbs);
	}

}
