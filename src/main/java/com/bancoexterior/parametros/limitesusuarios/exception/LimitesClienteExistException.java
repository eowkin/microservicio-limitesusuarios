package com.bancoexterior.parametros.limitesusuarios.exception;

public class LimitesClienteExistException extends BadRequestException{


	public LimitesClienteExistException(String codigo) {
		super(codigo);
	}
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

}
