package com.bancoexterior.parametros.limitesusuarios.model;

import com.bancoexterior.parametros.limitesusuarios.response.Resultado;

public class RegistrarAuditoriaResponse {
	
	private Resultado resultado;


	public RegistrarAuditoriaResponse() {
		super();
		this.resultado = new Resultado();
	}

	public Resultado getResultado() {
		return resultado;
	}

	public void setResultado(Resultado resultado) {
		this.resultado = resultado;
	}
	
	@Override
    public String toString() {
          return "RegistrarAuditoriaResponse [resultado=" + resultado +"]";

    }
}
