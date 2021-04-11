package com.bancoexterior.parametros.limitesusuarios.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import lombok.Data;

@Embeddable
@Data
public class LimitesPersonalizadosPk implements Serializable{
	
	@NotEmpty(message = "no puede ser vacio")
	@Column(name= "cod_ibs",nullable = false)
	@Size(max = 10)
	private String codIbs;

	@NotEmpty(message = "no puede ser vacio")
	@Column(name= "cod_moneda", nullable = false)
	@Size(max = 3)
	private String codMoneda;
	
	@NotEmpty(message = "no puede ser vacio")
	@Column(name= "tipo_transaccion", nullable = false)
	@Size(min = 1, max = 1)
	private String tipoTransaccion;

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

}
