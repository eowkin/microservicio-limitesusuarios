package com.bancoexterior.parametros.limitesusuarios.dto;

import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@AllArgsConstructor @NoArgsConstructor
public class LimitesPersonalizadosDto implements Serializable{

	@JsonProperty("codIbs")
	private String codIbs;
	
	@JsonProperty("codMoneda")
	private String codMoneda;
	
	@JsonProperty("tipoTransaccion")
	private String tipoTransaccion;
	
	@JsonProperty("montoMin")
	private Double montoMin;
	
	@JsonProperty("montoMax")
	private Double montoMax;
	
	@JsonProperty("montoTope")
	private Double montoTope;
	
	@JsonProperty("montoMensual")
	private Double montoMensual;
	
	@JsonProperty("montoDiario")
	private Double montoDiario;
	
	@JsonProperty("flagActivo")
	private Boolean flagActivo;
	
	@JsonProperty("codUsuario")
	private String codUsuario;
	
	@JsonProperty("fechaModificacion")
	private Date fechaModificacion;
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

}
