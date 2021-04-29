package com.bancoexterior.parametros.limitesusuarios.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.bancoexterior.parametros.limitesusuarios.dto.LimitesPersonalizadosDto;
import com.bancoexterior.parametros.limitesusuarios.dto.LimitesPersonalizadosDtoConsulta;
import com.bancoexterior.parametros.limitesusuarios.dto.LimitesPersonalizadosDtoResponse;
import com.bancoexterior.parametros.limitesusuarios.dto.LimitesPersonalizadosDtoResponseActualizar;
import com.bancoexterior.parametros.limitesusuarios.dto.LimitesPersonalizadosRequestConsulta;
import com.bancoexterior.parametros.limitesusuarios.dto.LimitesPersonalizadosRequestCrear;
import com.bancoexterior.parametros.limitesusuarios.entities.LimitesPersonalizados;
import com.bancoexterior.parametros.limitesusuarios.entities.LimitesPersonalizadosPk;



public interface ILimitesPersonalizadosService {
	
	public LimitesPersonalizadosDtoResponseActualizar crear(LimitesPersonalizadosRequestCrear request, 
			HttpServletRequest requestHTTP);
	
	public LimitesPersonalizadosDtoResponseActualizar actualizar(LimitesPersonalizadosRequestCrear request, 
			HttpServletRequest requestHTTP);

	public LimitesPersonalizadosDtoResponse consultaLimitesPersonalizados(LimitesPersonalizadosRequestConsulta limitesPersonalizadosRequestConsulta);
	
	public boolean existsById(LimitesPersonalizadosPk id);
	
	public LimitesPersonalizadosDto findById(LimitesPersonalizadosPk id);
	
	public List<LimitesPersonalizadosDto> findAllDtoNuevo(LimitesPersonalizadosDtoConsulta limitesPersonalizadosDtoConsulta);
}
