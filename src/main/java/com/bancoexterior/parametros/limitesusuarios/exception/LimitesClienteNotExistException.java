package com.bancoexterior.parametros.limitesusuarios.exception;

public class LimitesClienteNotExistException extends BadRequestException{


	public LimitesClienteNotExistException(String codigo) {
		super(codigo);
	}
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

}
