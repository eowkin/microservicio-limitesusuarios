package com.bancoexterior.parametros.limitesusuarios.service;

import java.util.List;

import com.bancoexterior.parametros.limitesusuarios.dto.LimitesPersonalizadosDto;
import com.bancoexterior.parametros.limitesusuarios.dto.LimitesPersonalizadosDtoConsulta;
import com.bancoexterior.parametros.limitesusuarios.dto.LimitesPersonalizadosDtoResponse;
import com.bancoexterior.parametros.limitesusuarios.dto.LimitesPersonalizadosRequestConsulta;
import com.bancoexterior.parametros.limitesusuarios.entities.LimitesPersonalizados;



public interface ILimitesPersonalizadosService {

	public LimitesPersonalizadosDtoResponse consultaLimitesPersonalizados(LimitesPersonalizadosRequestConsulta limitesPersonalizadosRequestConsulta);
	
	public LimitesPersonalizadosDtoResponse findAllDtoResponse(); 
	
	public List<LimitesPersonalizados> findAll();
	
	public List<LimitesPersonalizadosDto> findAllDto();
	
	public List<LimitesPersonalizadosDto> findAllDto(LimitesPersonalizadosDtoConsulta limitesPersonalizadosDtoConsulta);
	
	public LimitesPersonalizadosDtoResponse findByIdDtoResponse(String codMoneda, String tipoTransaccion, String codIbs);
}
