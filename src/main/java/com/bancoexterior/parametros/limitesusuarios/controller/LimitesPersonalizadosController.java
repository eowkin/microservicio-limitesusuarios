package com.bancoexterior.parametros.limitesusuarios.controller;

import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bancoexterior.parametros.limitesusuarios.config.Codigos.Constantes;
import com.bancoexterior.parametros.limitesusuarios.response.ResponseBad;
import com.bancoexterior.parametros.limitesusuarios.config.Codigos.Servicios;
import com.bancoexterior.parametros.limitesusuarios.dto.DatosRequestConsulta;
import com.bancoexterior.parametros.limitesusuarios.dto.LimitesPersonalizadosDto;
import com.bancoexterior.parametros.limitesusuarios.dto.LimitesPersonalizadosDtoResponse;
import com.bancoexterior.parametros.limitesusuarios.dto.LimitesPersonalizadosRequestConsulta;
import com.bancoexterior.parametros.limitesusuarios.entities.LimitesPersonalizados;
import com.bancoexterior.parametros.limitesusuarios.service.ILimitesPersonalizadosService;
import com.bancoexterior.parametros.limitesusuarios.util.Utils;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
//@RequestMapping("/api/des/v1/parametros/limites-personalizados")
@RequestMapping("${microservicio.path.pre}" + "${microservicio.ambiente}")
public class LimitesPersonalizadosController {
	
	@Autowired
	private ILimitesPersonalizadosService limitesPersonalizadosService;
	
	@Autowired
	private Environment env;
	
	@GetMapping(path =Servicios.LIMITESUSUARIOSURLV1, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Object> listAllLimitesPersonalizadosResponse(@RequestBody LimitesPersonalizadosRequestConsulta  limitesPersonalizadosRequestConsulta, 
			HttpServletRequest requestHTTP){
		
		log.info("[==== INICIO Convenio n° 1 LimitesPersonalizados - Controller ====]");
		log.info("datosRequestConsulta: " + limitesPersonalizadosRequestConsulta);
		LimitesPersonalizadosDtoResponse response;
		HttpStatus estatusCM;
		response = limitesPersonalizadosService.consultaLimitesPersonalizados(limitesPersonalizadosRequestConsulta);
		estatusCM = Utils.getHttpStatus(response.getResultado().getCodigo().trim());
		
		log.info("estatusCM: "+estatusCM);
		log.info("response: "+response);
		log.info("[==== FIN Convenio n° 1 LimitesPersonalizados - Controller ====]");
		return ResponseEntity.ok(response);
	}
	
	@GetMapping(path =Servicios.LIMITESUSUARIOSURLV1+"/todas", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Object> getAllLimitesPersonalizadosResponse(@Valid @RequestBody DatosRequestConsulta datosRequestConsulta, BindingResult result, 
			HttpServletRequest requestHTTP){
		log.info("[==== INICIO Convenio n° 1 LimitesPersonalizados - Controller ====]");
		log.info("datosRequestConsulta: " + datosRequestConsulta);
		if (result.hasErrors()) {
			
			ResponseBad responseBad = new ResponseBad();	
			List<String> errors = result
	                .getFieldErrors()
	                .stream()
	                .map(FieldError::getDefaultMessage)
	                .collect(Collectors.toList());
	    	log.info("errors: "+errors);
			
			HttpStatus httpStatusError = Utils.getHttpStatus(errors.get(0));
			log.info("httpStatusError: "+httpStatusError);
			responseBad.getResultadoBAD().setCodigo(errors.get(0));
	    	responseBad.getResultadoBAD().setDescripcion(env.getProperty(Constantes.RES+errors.get(0),errors.get(0)));
			return new ResponseEntity(responseBad, httpStatusError);

		}
		LimitesPersonalizadosDtoResponse response = limitesPersonalizadosService.findAllDtoResponse();
		log.info("[==== FIN Convenio n° 1 LimitesPersonalizados - Controller ====]");
		return ResponseEntity.ok(response);
	}

	
	@GetMapping(path =Servicios.LIMITESUSUARIOSPARAMETERIDURLV1, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Object> getIdLimitesPersonalizadosResponse(@Valid @RequestBody DatosRequestConsulta datosRequestConsulta, BindingResult result, @PathVariable String codMoneda,
			@PathVariable String tipoTransaccion,@PathVariable String codIbs, 
			HttpServletRequest requestHTTP){
		log.info("[==== INICIO Convenio n° 1 LimitesPersonalizados - Controller ====]");
		log.info("datosRequestConsulta: " + datosRequestConsulta);
		log.info("codMoneda: "+codMoneda);
		log.info("tipoTransaccion: "+tipoTransaccion);
		log.info("codIbs: "+codIbs);
		if (result.hasErrors()) {
			
			ResponseBad responseBad = new ResponseBad();	
			List<String> errors = result
	                .getFieldErrors()
	                .stream()
	                .map(FieldError::getDefaultMessage)
	                .collect(Collectors.toList());
	    	log.info("errors: "+errors);
			
			HttpStatus httpStatusError = Utils.getHttpStatus(errors.get(0));
			log.info("httpStatusError: "+httpStatusError);
			responseBad.getResultadoBAD().setCodigo(errors.get(0));
	    	responseBad.getResultadoBAD().setDescripcion(env.getProperty(Constantes.RES+errors.get(0),errors.get(0)));
			return new ResponseEntity(responseBad, httpStatusError);

		}
		LimitesPersonalizadosDtoResponse response = limitesPersonalizadosService.findByIdDtoResponse(codMoneda, tipoTransaccion, codIbs);
		log.info("[==== FIN Convenio n° 1 LimitesPersonalizados - Controller ====]");
		return ResponseEntity.ok(response);
	}
	
	
	
	@GetMapping(path =Servicios.LIMITESUSUARIOSURLV1+"/prueba", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public List<LimitesPersonalizados> findAll(){
		return limitesPersonalizadosService.findAll();
	}
	
	
	@GetMapping(path =Servicios.LIMITESUSUARIOSURLV1+"/dto", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public List<LimitesPersonalizadosDto> findAllDto(){
		return limitesPersonalizadosService.findAllDto();
	}
	

}
