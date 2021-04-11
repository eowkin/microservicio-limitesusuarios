package com.bancoexterior.parametros.limitesusuarios.dto;

import java.io.Serializable;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import com.bancoexterior.parametros.limitesusuarios.config.Codigos.CodRespuesta;
import com.bancoexterior.parametros.limitesusuarios.config.Codigos.ParamConfig;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor @NoArgsConstructor
public class LimitesPersonalizadosDtoConsulta implements Serializable{

	@JsonProperty("codIbs")
	private String codIbs;
	
	@JsonProperty("codMoneda")
	@NotEmpty(message=CodRespuesta.CDE1004)
	@Pattern(regexp=ParamConfig.CODMONEDA, message=CodRespuesta.CDE1004)
	private String codMoneda;
	
	@JsonProperty("tipoTransaccion")
	private String tipoTransaccion;
	
	@JsonProperty("flagActivo")
	private Boolean flagActivo;
	
	public LimitesPersonalizadosDtoConsulta(LimitesPersonalizadosRequestConsulta limitesPersonalizadosRequestConsulta) {
		super();
		this.codIbs = limitesPersonalizadosRequestConsulta.getLimitesPersonalizadosDtoRequestConsulta().getCodIbs();
		this.codMoneda = limitesPersonalizadosRequestConsulta.getLimitesPersonalizadosDtoRequestConsulta().getCodMoneda();
		this.tipoTransaccion = limitesPersonalizadosRequestConsulta.getLimitesPersonalizadosDtoRequestConsulta().getTipoTransaccion();
		this.flagActivo = limitesPersonalizadosRequestConsulta.getLimitesPersonalizadosDtoRequestConsulta().getFlagActivo();
	}
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	
}
