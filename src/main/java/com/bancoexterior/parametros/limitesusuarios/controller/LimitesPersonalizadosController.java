package com.bancoexterior.parametros.limitesusuarios.controller;


import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bancoexterior.parametros.limitesusuarios.config.Codigos.Constantes;
import com.bancoexterior.parametros.limitesusuarios.config.Codigos.Servicios;
import com.bancoexterior.parametros.limitesusuarios.dto.LimitesPersonalizadosDtoResponse;
import com.bancoexterior.parametros.limitesusuarios.dto.LimitesPersonalizadosDtoResponseActualizar;
import com.bancoexterior.parametros.limitesusuarios.dto.LimitesPersonalizadosRequestConsulta;
import com.bancoexterior.parametros.limitesusuarios.dto.LimitesPersonalizadosRequestCrear;
import com.bancoexterior.parametros.limitesusuarios.service.ILimitesPersonalizadosService;
import com.bancoexterior.parametros.limitesusuarios.util.Utils;
import com.bancoexterior.parametros.limitesusuarios.validator.ILimitesPersonalizadosValidator;



@RestController
@RequestMapping("${microservicio.path.pre}" + "${microservicio.ambiente}")
public class LimitesPersonalizadosController {
	private static final Logger LOGGER = LogManager.getLogger(LimitesPersonalizadosController.class);
	
	@Autowired
	private ILimitesPersonalizadosService limitesPersonalizadosService;
	
	@Autowired
	private ILimitesPersonalizadosValidator limitesPersonalizadosValidator;
	
	
	
	
	/**
	 * Nombre: listAllLimitesPersonalizadosResponse 
	 * Descripcion: Invocar metodo para listar todos los limite-clientes 
	 * por los parametros enviados
	 * 
	 * @param limitesPersonalizadosRequestConsulta     Objeto tipo LimitesPersonalizadosRequestConsulta   
	 * @param requestHTTP Objeto tipo HttpServletRequest
	 * @return ResponseEntity<Object>
	 * @version 1.0
	 * @author Eugenio Owkin
	 * @throws ApiUnprocessableEntity 
	 * @since 12/04/21
	 */
	@PostMapping(path =Servicios.LIMITESUSUARIOSURLV1+"/consultas", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Object> listAllLimitesPersonalizadosResponse(@RequestBody LimitesPersonalizadosRequestConsulta  limitesPersonalizadosRequestConsulta, 
			HttpServletRequest requestHTTP){
		
		LOGGER.info(Servicios.LIMITESCLIENTECONTROLLERI);
		LOGGER.info(limitesPersonalizadosRequestConsulta);
		LimitesPersonalizadosDtoResponse response;
		HttpStatus estatusCM;
		response = limitesPersonalizadosService.consultaLimitesPersonalizados(limitesPersonalizadosRequestConsulta);
		estatusCM = Utils.getHttpStatus(response.getResultado().getCodigo().trim());
		
		LOGGER.info(estatusCM);
		LOGGER.info(response);
		LOGGER.info(Servicios.LIMITESCLIENTECONTROLLERF);
		if(response.getResultado().getCodigo().trim().substring(0, 1).equalsIgnoreCase(Constantes.SUBSTRING_COD_OK)) {
			return new ResponseEntity<>(response,estatusCM);
		}else {
		
			return new ResponseEntity<>(response.getResultado(),estatusCM);
		}
	}
	
	/**
	 * Nombre: crearLimitesPersonalizados 
	 * Descripcion: Invocar metodo para ingresar un Limite-Cliente nuevo
	 * 
	 * @param request     Objeto tipo LimitesPersonalizadosRequestCrear   
	 * @param result Objeto tipo BindingResult 
	 * @param requestHTTP Objeto tipo HttpServletRequest
	 * @return ResponseEntity<Object>
	 * @version 1.0
	 * @author Eugenio Owkin
	 * @throws ApiUnprocessableEntity 
	 * @since 12/04/21
	 */
	
	@PostMapping(path =Servicios.LIMITESUSUARIOSURLV1, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Object> crearLimitesPersonalizados(@Valid  @RequestBody LimitesPersonalizadosRequestCrear request, BindingResult result, 
			HttpServletRequest requestHTTP){
		
		LOGGER.info(Servicios.LIMITESCLIENTECONTROLLERI);
		LOGGER.info(request);
		
		limitesPersonalizadosValidator.validarCrear(request, result);
		
		LimitesPersonalizadosDtoResponseActualizar response;
		HttpStatus estatusCM;
		response = limitesPersonalizadosService.crear(request, requestHTTP);
		//response = limitesPersonalizadosService.consultaLimitesPersonalizados(limitesPersonalizadosRequestConsulta);
		estatusCM = Utils.getHttpStatus(response.getResultado().getCodigo().trim());
		
		LOGGER.info(estatusCM);
		LOGGER.info(response);
		LOGGER.info(Servicios.LIMITESCLIENTECONTROLLERF);
		if(response.getResultado().getCodigo().trim().substring(0, 1).equalsIgnoreCase(Constantes.SUBSTRING_COD_OK)) {
			return new ResponseEntity<>(response,estatusCM);
		}else {
		
			return new ResponseEntity<>(response.getResultado(),estatusCM);
		}
	}
	
	
	/**
	 * Nombre: actualizarLimitesPersonalizados 
	 * Descripcion: Invocar metodo para actualizar un Limite-Cliente nuevo
	 * 
	 * @param request     Objeto tipo LimitesPersonalizadosRequestCrear   
	 * @param result Objeto tipo BindingResult 
	 * @param requestHTTP Objeto tipo HttpServletRequest
	 * @return ResponseEntity<Object>
	 * @version 1.0
	 * @author Eugenio Owkin
	 * @throws ApiUnprocessableEntity 
	 * @since 12/04/21
	 */
	@PutMapping(path =Servicios.LIMITESUSUARIOSURLV1, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Object> actualizarLimitesPersonalizados(@Valid  @RequestBody LimitesPersonalizadosRequestCrear request, BindingResult result, 
			HttpServletRequest requestHTTP){
		
		LOGGER.info(Servicios.LIMITESCLIENTECONTROLLERI);
		LOGGER.info(request);
		
		limitesPersonalizadosValidator.validarActualizar(request, result);
		
		LimitesPersonalizadosDtoResponseActualizar response;
		HttpStatus estatusCM;
		response = limitesPersonalizadosService.actualizar(request, requestHTTP);
		estatusCM = Utils.getHttpStatus(response.getResultado().getCodigo().trim());
		
		LOGGER.info(estatusCM);
		LOGGER.info(response);
		LOGGER.info(Servicios.LIMITESCLIENTECONTROLLERF);
		if(response.getResultado().getCodigo().trim().substring(0, 1).equalsIgnoreCase(Constantes.SUBSTRING_COD_OK)) {
			return new ResponseEntity<>(response,estatusCM);
		}else {
		
			return new ResponseEntity<>(response.getResultado(),estatusCM);
		}
	}
	

}
