package com.bancoexterior.parametros.limitesusuarios.exception;

public class CodMonedaNoExistException extends BadRequestException{
	

	public CodMonedaNoExistException(String codigo) {
		super(codigo);
	}
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
}
