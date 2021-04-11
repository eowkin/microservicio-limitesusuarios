package com.bancoexterior.parametros.limitesusuarios.service;

import java.util.List;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import com.bancoexterior.parametros.limitesusuarios.config.Codigos.CodRespuesta;
import com.bancoexterior.parametros.limitesusuarios.config.Codigos.Constantes;
import com.bancoexterior.parametros.limitesusuarios.response.Resultado;
import com.bancoexterior.parametros.limitesusuarios.dto.LimitesPersonalizadosDto;
import com.bancoexterior.parametros.limitesusuarios.dto.LimitesPersonalizadosDtoConsulta;
import com.bancoexterior.parametros.limitesusuarios.dto.LimitesPersonalizadosDtoRequestConsulta;
import com.bancoexterior.parametros.limitesusuarios.dto.LimitesPersonalizadosDtoResponse;
import com.bancoexterior.parametros.limitesusuarios.dto.LimitesPersonalizadosRequestConsulta;
import com.bancoexterior.parametros.limitesusuarios.entities.LimitesPersonalizados;
import com.bancoexterior.parametros.limitesusuarios.repository.ILimitesPersonalizadosRepository;

import lombok.extern.slf4j.Slf4j;


@Slf4j
@Service
public class LimitesPersonalizadosServiceImpl implements ILimitesPersonalizadosService{

	@Autowired
	private ILimitesPersonalizadosRepository repo;
	
	@Autowired
	private Environment env;
	
	@Override
	public List<LimitesPersonalizados> findAll() {
		return repo.findAll();
	}

	@Override
	public List<LimitesPersonalizadosDto> findAllDto() {
		
		return repo.getAll();
	}

	
	@Override
	public List<LimitesPersonalizadosDto> findAllDto(LimitesPersonalizadosDtoConsulta limitesPersonalizadosDtoConsulta) {
		List<LimitesPersonalizadosDto> listLimitesPersonalizadosDto = null;
		log.info("codIbs: "+limitesPersonalizadosDtoConsulta.getCodIbs());
		log.info("codMoneda: "+limitesPersonalizadosDtoConsulta.getCodMoneda());
		log.info("tipoTransaccion: "+limitesPersonalizadosDtoConsulta.getTipoTransaccion());
		log.info("flagActivo: "+limitesPersonalizadosDtoConsulta.getFlagActivo());
		
		//Todas
		if (limitesPersonalizadosDtoConsulta.getCodIbs() != null && limitesPersonalizadosDtoConsulta.getCodMoneda() != null 
				&& limitesPersonalizadosDtoConsulta.getTipoTransaccion() != null && limitesPersonalizadosDtoConsulta.getFlagActivo() != null) {
			listLimitesPersonalizadosDto = repo.getByCodIbsAndCodMonedaAndTipoTransaccionAndFlagActivo(limitesPersonalizadosDtoConsulta.getCodIbs(), limitesPersonalizadosDtoConsulta.getCodMoneda(), 
					limitesPersonalizadosDtoConsulta.getTipoTransaccion(), limitesPersonalizadosDtoConsulta.getFlagActivo());
			log.info(""+listLimitesPersonalizadosDto.size());
		}
		
		//codIbs
		if (limitesPersonalizadosDtoConsulta.getCodIbs() != null && limitesPersonalizadosDtoConsulta.getCodMoneda() == null 
				&& limitesPersonalizadosDtoConsulta.getTipoTransaccion() == null && limitesPersonalizadosDtoConsulta.getFlagActivo() == null) {
			listLimitesPersonalizadosDto = repo.getByCodIbs(limitesPersonalizadosDtoConsulta.getCodIbs());
			log.info(""+listLimitesPersonalizadosDto.size());
		}
		
		//codIbsAndCodMoneda
		if (limitesPersonalizadosDtoConsulta.getCodIbs() != null && limitesPersonalizadosDtoConsulta.getCodMoneda() != null 
			&& limitesPersonalizadosDtoConsulta.getTipoTransaccion() == null && limitesPersonalizadosDtoConsulta.getFlagActivo() == null) {
				listLimitesPersonalizadosDto = repo.getByCodIbsAndCodMoneda(limitesPersonalizadosDtoConsulta.getCodIbs(), limitesPersonalizadosDtoConsulta.getCodMoneda());
				log.info(""+listLimitesPersonalizadosDto.size());
		}
		
		//codIbsAndTipoTransaccion
		if (limitesPersonalizadosDtoConsulta.getCodIbs() != null && limitesPersonalizadosDtoConsulta.getCodMoneda() == null 
			&& limitesPersonalizadosDtoConsulta.getTipoTransaccion() != null && limitesPersonalizadosDtoConsulta.getFlagActivo() == null) {
				listLimitesPersonalizadosDto = repo.getByCodIbsAndTipoTransaccion(limitesPersonalizadosDtoConsulta.getCodIbs(), limitesPersonalizadosDtoConsulta.getTipoTransaccion());
				log.info(""+listLimitesPersonalizadosDto.size());
		}
		
		//codIbsAndFlagActivo
		if (limitesPersonalizadosDtoConsulta.getCodIbs() != null && limitesPersonalizadosDtoConsulta.getCodMoneda() == null 
			&& limitesPersonalizadosDtoConsulta.getTipoTransaccion() == null && limitesPersonalizadosDtoConsulta.getFlagActivo() != null) {
				listLimitesPersonalizadosDto = repo.getByCodIbsAndFlagActivo(limitesPersonalizadosDtoConsulta.getCodIbs(), limitesPersonalizadosDtoConsulta.getFlagActivo());
				log.info(""+listLimitesPersonalizadosDto.size());
		}
		
		//codIbsAndCodMonedaAndTipoTransaccion
		if (limitesPersonalizadosDtoConsulta.getCodIbs() != null && limitesPersonalizadosDtoConsulta.getCodMoneda() != null 
			&& limitesPersonalizadosDtoConsulta.getTipoTransaccion() != null && limitesPersonalizadosDtoConsulta.getFlagActivo() == null) {
				listLimitesPersonalizadosDto = repo.getById(limitesPersonalizadosDtoConsulta.getCodIbs(), limitesPersonalizadosDtoConsulta.getTipoTransaccion(),
						limitesPersonalizadosDtoConsulta.getCodMoneda());
				log.info(""+listLimitesPersonalizadosDto.size());
		}
		
		//codIbsAndCodMonedaAndFlagActivo
		if (limitesPersonalizadosDtoConsulta.getCodIbs() != null && limitesPersonalizadosDtoConsulta.getCodMoneda() != null 
			&& limitesPersonalizadosDtoConsulta.getTipoTransaccion() == null && limitesPersonalizadosDtoConsulta.getFlagActivo() != null) {
				listLimitesPersonalizadosDto = repo.getByCodIbsAndCodMonedaAndFalgActivo(limitesPersonalizadosDtoConsulta.getCodIbs(), 
						limitesPersonalizadosDtoConsulta.getCodMoneda(), limitesPersonalizadosDtoConsulta.getFlagActivo());
				log.info(""+listLimitesPersonalizadosDto.size());
		}
		
		//codIbsAndTipoTransaccionAndFlagActivo
		if (limitesPersonalizadosDtoConsulta.getCodIbs() != null && limitesPersonalizadosDtoConsulta.getCodMoneda() == null 
			&& limitesPersonalizadosDtoConsulta.getTipoTransaccion() != null && limitesPersonalizadosDtoConsulta.getFlagActivo() != null) {
				listLimitesPersonalizadosDto = repo.getByCodIbsAndCodMonedaAndFalgActivo(limitesPersonalizadosDtoConsulta.getCodIbs(), 
						limitesPersonalizadosDtoConsulta.getTipoTransaccion(), limitesPersonalizadosDtoConsulta.getFlagActivo());
				log.info(""+listLimitesPersonalizadosDto.size());
		}
		
		//codMoneda
		if (limitesPersonalizadosDtoConsulta.getCodIbs() == null && limitesPersonalizadosDtoConsulta.getCodMoneda() != null 
			&& limitesPersonalizadosDtoConsulta.getTipoTransaccion() == null && limitesPersonalizadosDtoConsulta.getFlagActivo() == null) {
				listLimitesPersonalizadosDto = repo.getByCodMoneda(limitesPersonalizadosDtoConsulta.getCodMoneda());
				log.info(""+listLimitesPersonalizadosDto.size());
		}
		
		//codMonedaAndTipoTransaccion
		if (limitesPersonalizadosDtoConsulta.getCodIbs() == null && limitesPersonalizadosDtoConsulta.getCodMoneda() != null 
			&& limitesPersonalizadosDtoConsulta.getTipoTransaccion() != null && limitesPersonalizadosDtoConsulta.getFlagActivo() == null) {
				listLimitesPersonalizadosDto = repo.getByCodMonedaAndTipoTransaccion(limitesPersonalizadosDtoConsulta.getCodMoneda(), limitesPersonalizadosDtoConsulta.getTipoTransaccion());
				log.info(""+listLimitesPersonalizadosDto.size());
		}
		
		//codMonedaAndFlagActivo
		if (limitesPersonalizadosDtoConsulta.getCodIbs() == null && limitesPersonalizadosDtoConsulta.getCodMoneda() != null 
			&& limitesPersonalizadosDtoConsulta.getTipoTransaccion() == null && limitesPersonalizadosDtoConsulta.getFlagActivo() != null) {
				listLimitesPersonalizadosDto = repo.getByCodMonedaAndFlagActivo(limitesPersonalizadosDtoConsulta.getCodMoneda(), limitesPersonalizadosDtoConsulta.getFlagActivo());
				log.info(""+listLimitesPersonalizadosDto.size());
		}
		
		//codMonedaAndTipoTransaccionAndFlagActivo
		if (limitesPersonalizadosDtoConsulta.getCodIbs() == null && limitesPersonalizadosDtoConsulta.getCodMoneda() != null 
			&& limitesPersonalizadosDtoConsulta.getTipoTransaccion() != null && limitesPersonalizadosDtoConsulta.getFlagActivo() != null) {
				listLimitesPersonalizadosDto = repo.getByCodMonedaAndTipoTransaccionAndFlagActivo(limitesPersonalizadosDtoConsulta.getCodMoneda(), 
						limitesPersonalizadosDtoConsulta.getTipoTransaccion(),limitesPersonalizadosDtoConsulta.getFlagActivo());
				log.info(""+listLimitesPersonalizadosDto.size());
		}
		
		//tipoTransaccion
		if (limitesPersonalizadosDtoConsulta.getCodIbs() == null && limitesPersonalizadosDtoConsulta.getCodMoneda() == null 
			&& limitesPersonalizadosDtoConsulta.getTipoTransaccion() != null && limitesPersonalizadosDtoConsulta.getFlagActivo() == null) {
				listLimitesPersonalizadosDto = repo.getByTipoTransaccion(limitesPersonalizadosDtoConsulta.getTipoTransaccion());
				log.info(""+listLimitesPersonalizadosDto.size());
		}
		
		//tipoTransaccionAndFlagActivo
		if (limitesPersonalizadosDtoConsulta.getCodIbs() == null && limitesPersonalizadosDtoConsulta.getCodMoneda() == null 
			&& limitesPersonalizadosDtoConsulta.getTipoTransaccion() != null && limitesPersonalizadosDtoConsulta.getFlagActivo() != null) {
				listLimitesPersonalizadosDto = repo.getByTipoTransaccionAndFlagActivo(limitesPersonalizadosDtoConsulta.getTipoTransaccion(), 
						limitesPersonalizadosDtoConsulta.getFlagActivo());
				log.info(""+listLimitesPersonalizadosDto.size());
		}
		
		//flagActivo
		if (limitesPersonalizadosDtoConsulta.getCodIbs() == null && limitesPersonalizadosDtoConsulta.getCodMoneda() == null 
			&& limitesPersonalizadosDtoConsulta.getTipoTransaccion() == null && limitesPersonalizadosDtoConsulta.getFlagActivo() != null) {
				listLimitesPersonalizadosDto = repo.getByTipoTransaccion(limitesPersonalizadosDtoConsulta.getTipoTransaccion());
				log.info(""+listLimitesPersonalizadosDto.size());
		}
		
		//All Todos los valores null
		if (limitesPersonalizadosDtoConsulta.getCodIbs() == null && limitesPersonalizadosDtoConsulta.getCodMoneda() == null 
			&& limitesPersonalizadosDtoConsulta.getTipoTransaccion() == null && limitesPersonalizadosDtoConsulta.getFlagActivo() == null) {
				listLimitesPersonalizadosDto = repo.getAll();
				log.info(""+listLimitesPersonalizadosDto.size());
		}
		return listLimitesPersonalizadosDto;
	}
	
	
	@Override
	public LimitesPersonalizadosDtoResponse findAllDtoResponse() {
		LimitesPersonalizadosDtoResponse response = new LimitesPersonalizadosDtoResponse();
		Resultado resultado = new Resultado();
		resultado.setCodigo(CodRespuesta.C0000);
		resultado.setDescripcion(Constantes.BLANK);
		List<LimitesPersonalizadosDto> listLimitesPersonalizadosDto = repo.getAll();
		
		if (listLimitesPersonalizadosDto.isEmpty()) {
			resultado.setCodigo(CodRespuesta.C0001);
			resultado.setDescripcion(env.getProperty(Constantes.RES + resultado.getCodigo(), resultado.getCodigo()));
		}else {
			resultado.setDescripcion(env.getProperty(Constantes.RES + resultado.getCodigo(), resultado.getCodigo()));
		}
		response.setResultado(resultado);
		response.setListLimitesPersonalizadosDto(listLimitesPersonalizadosDto);
		return response;
	}

	@Override
	public LimitesPersonalizadosDtoResponse findByIdDtoResponse(String codMoneda, String tipoTransaccion, String codIbs) {
		LimitesPersonalizadosDtoResponse response = new LimitesPersonalizadosDtoResponse();
		Resultado resultado = new Resultado();
		resultado.setCodigo(CodRespuesta.C0000);
		resultado.setDescripcion(Constantes.BLANK);
		List<LimitesPersonalizadosDto> listLimitesPersonalizadosDto = repo.getById(codMoneda, tipoTransaccion, codIbs);
		
		if (listLimitesPersonalizadosDto.isEmpty()) {
			resultado.setCodigo(CodRespuesta.C0001);
			resultado.setDescripcion(env.getProperty(Constantes.RES + resultado.getCodigo(), resultado.getCodigo()));
		}else {
			resultado.setDescripcion(env.getProperty(Constantes.RES + resultado.getCodigo(), resultado.getCodigo()));
		}
		response.setResultado(resultado);
		response.setListLimitesPersonalizadosDto(listLimitesPersonalizadosDto);
		return response;
	}

	@Override
	public LimitesPersonalizadosDtoResponse consultaLimitesPersonalizados(
			LimitesPersonalizadosRequestConsulta request) {
		log.info("\"==== INICIO Convenio 1 - LimitesPersonalizados Consultas ====\"");
		LimitesPersonalizadosDtoResponse response = new LimitesPersonalizadosDtoResponse();
		Resultado resultado = new Resultado();
		String codigo = CodRespuesta.C0000;
		String errorCM = Constantes.BLANK;
		List<LimitesPersonalizadosDto> listLimitesPersonalizadosDto;
		LimitesPersonalizadosDtoConsulta limitesPersonalizadosDtoConsulta = new LimitesPersonalizadosDtoConsulta(request);
		LimitesPersonalizadosDtoRequestConsulta limitesPersonalizadosDtoRequestConsulta = request.getLimitesPersonalizadosDtoRequestConsulta();
		log.info("codIbs: "+limitesPersonalizadosDtoRequestConsulta.getCodIbs());
		log.info("codMoneda: "+limitesPersonalizadosDtoRequestConsulta.getCodMoneda());
		log.info("tipoTransaccion: "+limitesPersonalizadosDtoRequestConsulta.getTipoTransaccion());
		log.info("flagActivo: "+limitesPersonalizadosDtoRequestConsulta.getFlagActivo());
		
		try {
			codigo = validaDatosConsulta(request);
			log.info("codigo: "+codigo);
			if(codigo.equalsIgnoreCase(CodRespuesta.C0000)) {
				log.info("codIbs: "+limitesPersonalizadosDtoConsulta.getCodIbs());
				log.info("codMonedaDto: "+limitesPersonalizadosDtoConsulta.getCodMoneda());
				log.info("tipoTransaccionDto: "+limitesPersonalizadosDtoConsulta.getTipoTransaccion());
				log.info("flagActivoDto: "+limitesPersonalizadosDtoConsulta.getFlagActivo());
				
				//consulta BD
				listLimitesPersonalizadosDto = this.findAllDto(limitesPersonalizadosDtoConsulta);
				response.setListLimitesPersonalizadosDto(listLimitesPersonalizadosDto);
				log.info("antes de llamara validaConsulta");
				//Validar Respuesta
				resultado = validaConsulta(listLimitesPersonalizadosDto);
				log.info("luego de llamara validaConsulta");
				log.info("resultado: "+resultado);
				codigo = resultado.getCodigo();
				log.info("codigo: "+codigo);
				errorCM = resultado.getDescripcion();
				log.info("errorCM: "+errorCM);
			}
			
			
		} catch (Exception e) {
			log.error(""+e);
			codigo = CodRespuesta.CME6000;
			errorCM = Constantes.EXC+e;
		}
		log.info("response: "+response);
		response.getResultado().setCodigo(codigo);
		response.getResultado().setDescripcion(env.getProperty(Constantes.RES+codigo,codigo).replace(Constantes.ERROR, errorCM));
		
		log.info("tasaDtoResponse: "+response);
		log.info("==== FIN Convenio 1 - LimitesPersonalizados ====");
		return response;
	}
	
	private String validaDatosConsulta(LimitesPersonalizadosRequestConsulta request) {
		log.info("dentro de validarDatosConsulta");
		log.info(""+request);
		String codigo = CodRespuesta.C0000;
		String codMoneda;
		String tipoTransaccion;
		String codIbs;
		boolean flagActivo;
		
		codIbs = request.getLimitesPersonalizadosDtoRequestConsulta().getCodIbs() == null ? "000":request.getLimitesPersonalizadosDtoRequestConsulta().getCodIbs();
		codMoneda = request.getLimitesPersonalizadosDtoRequestConsulta().getCodMoneda() == null ? "000":request.getLimitesPersonalizadosDtoRequestConsulta().getCodMoneda();
		tipoTransaccion = request.getLimitesPersonalizadosDtoRequestConsulta().getTipoTransaccion() == null ? "000":request.getLimitesPersonalizadosDtoRequestConsulta().getTipoTransaccion();
		flagActivo = request.getLimitesPersonalizadosDtoRequestConsulta().getFlagActivo() == null ? Boolean.parseBoolean(Constantes.TRUE) : request.getLimitesPersonalizadosDtoRequestConsulta().getFlagActivo();
		
		request.getLimitesPersonalizadosDtoRequestConsulta().setCodIbs(codIbs);
		request.getLimitesPersonalizadosDtoRequestConsulta().setCodMoneda(codMoneda);
		request.getLimitesPersonalizadosDtoRequestConsulta().setTipoTransaccion(tipoTransaccion);
		request.getLimitesPersonalizadosDtoRequestConsulta().setFlagActivo(flagActivo);
		
		log.info("antes de llamar factory");
		ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
		Validator validator = factory.getValidator();
		Set<ConstraintViolation<LimitesPersonalizadosRequestConsulta>> errores = validator.validate(request);
		
	
			for (ConstraintViolation<LimitesPersonalizadosRequestConsulta> cv : errores) {
				
				if ( !cv.getMessage().equalsIgnoreCase(Constantes.BLANK)) {
					codigo = cv.getMessage();
					 break;
				}

			}

		
		return codigo;
	}

	private Resultado validaConsulta(List<LimitesPersonalizadosDto> listLimitesPersonalizadosDto) {
		Resultado resultado = new Resultado();
		resultado.setCodigo(CodRespuesta.C0000);
		resultado.setDescripcion(Constantes.BLANK);
		
		if(listLimitesPersonalizadosDto.isEmpty()) {
			resultado.setCodigo(CodRespuesta.C0001);
			return resultado;
		}

		/*
	    if(monedasBD.get(0).getCodMonedaBD().equalsIgnoreCase(Constantes.SERROR)) {
	    	resultado.setCodigo(CodRespuesta.CME6002);
	    	resultado.setDescripcion(monedasBD.get(0).getDescripcionBD());
	    	 LOGGER.error(resultado);
	    	return resultado;
	    }*/

	    
	    log.info(""+resultado);
		return resultado;
		
	}

}
