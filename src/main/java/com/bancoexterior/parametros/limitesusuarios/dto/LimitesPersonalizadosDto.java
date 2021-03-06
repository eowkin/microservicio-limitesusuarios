package com.bancoexterior.parametros.limitesusuarios.dto;

import java.io.Serializable;
import java.math.BigDecimal;
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
	private BigDecimal montoMin;
	
	@JsonProperty("montoMax")
	private BigDecimal montoMax;
	
	@JsonProperty("montoTope")
	private BigDecimal montoTope;
	
	@JsonProperty("montoMensual")
	private BigDecimal montoMensual;
	
	@JsonProperty("montoDiario")
	private BigDecimal montoDiario;
	
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
