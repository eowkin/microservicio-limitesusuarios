package com.bancoexterior.parametros.limitesusuarios.dto;

import java.io.Serializable;

import com.bancoexterior.parametros.limitesusuarios.response.Resultado;

import lombok.Data;


@Data
public class LimitesPersonalizadosDtoResponseActualizar implements Serializable{

	
	private Resultado resultado;
	
	
	
	public LimitesPersonalizadosDtoResponseActualizar() {
		super();
		this.resultado = new Resultado();
	}



	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

}
