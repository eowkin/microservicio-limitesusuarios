package com.bancoexterior.parametros.limitesusuarios.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import com.bancoexterior.parametros.limitesusuarios.model.RegistrarAuditoriaRequest;
import com.bancoexterior.parametros.limitesusuarios.interfase.IRegistrarAuditoriaService;
import com.bancoexterior.parametros.limitesusuarios.config.Codigos.CodRespuesta;
import com.bancoexterior.parametros.limitesusuarios.config.Codigos.Constantes;
import com.bancoexterior.parametros.limitesusuarios.config.Codigos.Servicios;
import com.bancoexterior.parametros.limitesusuarios.response.Resultado;
import com.bancoexterior.parametros.limitesusuarios.dto.LimitesPersonalizadosDto;
import com.bancoexterior.parametros.limitesusuarios.dto.LimitesPersonalizadosDtoConsulta;
import com.bancoexterior.parametros.limitesusuarios.dto.LimitesPersonalizadosDtoRequestConsulta;
import com.bancoexterior.parametros.limitesusuarios.dto.LimitesPersonalizadosDtoRequestCrear;
import com.bancoexterior.parametros.limitesusuarios.dto.LimitesPersonalizadosDtoResponse;
import com.bancoexterior.parametros.limitesusuarios.dto.LimitesPersonalizadosDtoResponseActualizar;
import com.bancoexterior.parametros.limitesusuarios.dto.LimitesPersonalizadosRequestConsulta;
import com.bancoexterior.parametros.limitesusuarios.dto.LimitesPersonalizadosRequestCrear;
import com.bancoexterior.parametros.limitesusuarios.entities.LimitesPersonalizados;
import com.bancoexterior.parametros.limitesusuarios.entities.LimitesPersonalizadosPk;
import com.bancoexterior.parametros.limitesusuarios.repository.ILimitesPersonalizadosRepository;

import lombok.extern.slf4j.Slf4j;


@Slf4j
@Service
public class LimitesPersonalizadosServiceImpl implements ILimitesPersonalizadosService{

	private static final Logger LOGGER = LogManager.getLogger(LimitesPersonalizadosServiceImpl.class);
	
	@Autowired
	private ILimitesPersonalizadosRepository repo;
	
	@Autowired
	private IRegistrarAuditoriaService registrarA;
	
	
	@Autowired
	private Environment env;
	
	

	

	
	
	
	@Override
	public List<LimitesPersonalizadosDto> findAllDtoNuevo(
			LimitesPersonalizadosDtoConsulta limitesPersonalizadosDtoConsulta) {
		
		String codIbs = Constantes.BLANK;
		String codMoneda = Constantes.BLANK;
		String tipoTransaccion = Constantes.BLANK;
		String flag = Constantes.BLANK;
		boolean flagActivo = false;
		
		if (limitesPersonalizadosDtoConsulta.getCodIbs() != null) {
			codIbs = limitesPersonalizadosDtoConsulta.getCodIbs();
		}
		
		if (limitesPersonalizadosDtoConsulta.getCodMoneda() != null) {
			codMoneda = limitesPersonalizadosDtoConsulta.getCodMoneda();
		}
		
		if (limitesPersonalizadosDtoConsulta.getTipoTransaccion() != null) {
			tipoTransaccion = limitesPersonalizadosDtoConsulta.getTipoTransaccion();
		}
		
		if (limitesPersonalizadosDtoConsulta.getFlagActivo() != null) {
			flag = "si";
			flagActivo = limitesPersonalizadosDtoConsulta.getFlagActivo();
		}
		
		List<LimitesPersonalizados> listLimitesPersonalizados = repo.getLimitesClientesByNuevo(codIbs, codMoneda, tipoTransaccion, flag, flagActivo);
		List<LimitesPersonalizadosDto> listLimitesPersonalizadosDto = new ArrayList<>();
		
		for (LimitesPersonalizados limitesPersonalizados : listLimitesPersonalizados) {
			LimitesPersonalizadosDto limitesPersonalizadosDto = new LimitesPersonalizadosDto();
			limitesPersonalizadosDto.setCodIbs(limitesPersonalizados.getId().getCodIbs());
			limitesPersonalizadosDto.setCodMoneda(limitesPersonalizados.getId().getCodMoneda());
			limitesPersonalizadosDto.setTipoTransaccion(limitesPersonalizados.getId().getTipoTransaccion());
			limitesPersonalizadosDto.setMontoMin(limitesPersonalizados.getMontoMin());
			limitesPersonalizadosDto.setMontoMax(limitesPersonalizados.getMontoMax());
			limitesPersonalizadosDto.setMontoTope(limitesPersonalizados.getMontoTope());
			limitesPersonalizadosDto.setMontoMensual(limitesPersonalizados.getMontoMensual());
			limitesPersonalizadosDto.setMontoDiario(limitesPersonalizados.getMontoDiario());
			limitesPersonalizadosDto.setCodUsuario(limitesPersonalizados.getCodUsuario());
			limitesPersonalizadosDto.setFlagActivo(limitesPersonalizados.getFlagActivo());
			limitesPersonalizadosDto.setFechaModificacion(limitesPersonalizados.getFechaModificacion());
			
			listLimitesPersonalizadosDto.add(limitesPersonalizadosDto);
		}
		
		return listLimitesPersonalizadosDto;
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
				//listLimitesPersonalizadosDto = this.findAllDto(limitesPersonalizadosDtoConsulta);
				listLimitesPersonalizadosDto = this.findAllDtoNuevo(limitesPersonalizadosDtoConsulta);
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

	@Override
	public LimitesPersonalizadosDtoResponseActualizar crear(LimitesPersonalizadosRequestCrear request,
			HttpServletRequest requestHTTP) {
		LOGGER.info(Servicios.LIMITESCLIENTESERVICEICREAR);
		LOGGER.info(request);
		String microservicio = Servicios.LIMITESCLIENTE;
		
		RegistrarAuditoriaRequest reAU = null;
		
		reAU = new RegistrarAuditoriaRequest(request, microservicio, requestHTTP);
		String errorM = Constantes.BLANK;
		String codigo =  CodRespuesta.C0000;
		
		LimitesPersonalizados obj = new LimitesPersonalizados();
		LimitesPersonalizadosDtoResponseActualizar response = new LimitesPersonalizadosDtoResponseActualizar();
		Resultado resultado = new Resultado();
		resultado.setCodigo(CodRespuesta.C0000);
		resultado.setDescripcion(env.getProperty(Constantes.RES+CodRespuesta.C0000,CodRespuesta.C0000).replace(Constantes.ERROR, Constantes.BLANK));
		
		try {
			LimitesPersonalizadosDtoRequestCrear dtoRequestCrear = request.getLimitesPersonalizadosDtoRequestCrear();
			LimitesPersonalizadosPk id = new LimitesPersonalizadosPk();
			id.setCodIbs(dtoRequestCrear.getCodIbs());
			id.setCodMoneda(dtoRequestCrear.getCodMoneda());
			id.setTipoTransaccion(dtoRequestCrear.getTipoTransaccion());
			obj.setId(id);
			obj.setMontoMin(dtoRequestCrear.getMontoMin());
			obj.setMontoMax(dtoRequestCrear.getMontoMax());
			obj.setMontoTope(dtoRequestCrear.getMontoTope());
			obj.setMontoMensual(dtoRequestCrear.getMontoMensual());
			obj.setMontoDiario(dtoRequestCrear.getMontoDiario());
			obj.setCodUsuario(request.getCodUsuarioMR());
			obj.setFlagActivo(dtoRequestCrear.getFlagActivo());
			LOGGER.info(obj);
			repo.save(obj);
			response.setResultado(resultado);
			
			
		} catch (Exception e) {
			LOGGER.error(e);
			codigo = CodRespuesta.CME6001;
			errorM = Constantes.EXC+e;
			response.getResultado().setCodigo(CodRespuesta.CME6001);
			response.getResultado().setDescripcion(env.getProperty(Constantes.RES+CodRespuesta.CME6001,CodRespuesta.CME6001));
		}
		
		resultado.setCodigo(codigo);
		resultado.setDescripcion(env.getProperty(Constantes.RES+codigo,codigo).replace(Constantes.ERROR, errorM));
		
		if(reAU != null) {
			reAU.setIdCliente(Constantes.RIF);
			reAU.setCedula(Constantes.CEDULA);
			reAU.setTelefono(Constantes.TELEFONO);
			reAU.setIdCanal(request.getCanalCM());
			registrarAuditoriaBD(reAU, resultado, errorM);
		}
		
		LOGGER.info(Servicios.LIMITESCLIENTESERVICEFCREAR);
		return response;
	}
	
	
	@Override
	public LimitesPersonalizadosDtoResponseActualizar actualizar(LimitesPersonalizadosRequestCrear request,
			HttpServletRequest requestHTTP) {
		LOGGER.info(Servicios.LIMITESCLIENTESERVICEIACTUALIZAR);
		LOGGER.info(request);
		String microservicio = Servicios.LIMITESCLIENTEACTUALIZAR;
		
		RegistrarAuditoriaRequest reAU = null;
		
		reAU = new RegistrarAuditoriaRequest(request, microservicio, requestHTTP);
		String errorM = Constantes.BLANK;
		String codigo =  CodRespuesta.C0000;
		
		LimitesPersonalizados obj = new LimitesPersonalizados();
		LimitesPersonalizadosDtoResponseActualizar response = new LimitesPersonalizadosDtoResponseActualizar();
		Resultado resultado = new Resultado();
		resultado.setCodigo(CodRespuesta.C0000);
		resultado.setDescripcion(env.getProperty(Constantes.RES+CodRespuesta.C0000,CodRespuesta.C0000).replace(Constantes.ERROR, Constantes.BLANK));
		
		try {
			LimitesPersonalizadosDtoRequestCrear dtoRequestCrear = request.getLimitesPersonalizadosDtoRequestCrear();
			LimitesPersonalizadosPk id = new LimitesPersonalizadosPk();
			id.setCodIbs(dtoRequestCrear.getCodIbs());
			id.setCodMoneda(dtoRequestCrear.getCodMoneda());
			id.setTipoTransaccion(dtoRequestCrear.getTipoTransaccion());
			
			LimitesPersonalizadosDto limitesPersonalizadosDto = this.findById(id);
			
			
			obj.setId(id);
			obj.setMontoMin(dtoRequestCrear.getMontoMin());
			obj.setMontoMax(dtoRequestCrear.getMontoMax());
			obj.setMontoTope(dtoRequestCrear.getMontoTope());
			obj.setMontoMensual(dtoRequestCrear.getMontoMensual());
			obj.setMontoDiario(dtoRequestCrear.getMontoDiario());
			obj.setCodUsuario(request.getCodUsuarioMR());
			obj.setFlagActivo(dtoRequestCrear.getFlagActivo());
			obj.setFechaModificacion(limitesPersonalizadosDto.getFechaModificacion());
			LOGGER.info(obj);
			repo.save(obj);
			response.setResultado(resultado);
			
			
		} catch (Exception e) {
			LOGGER.error(e);
			codigo = CodRespuesta.CME6001;
			errorM = Constantes.EXC+e;
			response.getResultado().setCodigo(CodRespuesta.CME6001);
			response.getResultado().setDescripcion(env.getProperty(Constantes.RES+CodRespuesta.CME6001,CodRespuesta.CME6001));
		}
		
		resultado.setCodigo(codigo);
		resultado.setDescripcion(env.getProperty(Constantes.RES+codigo,codigo).replace(Constantes.ERROR, errorM));
		
		if(reAU != null) {
			reAU.setIdCliente(Constantes.RIF);
			reAU.setCedula(Constantes.CEDULA);
			reAU.setTelefono(Constantes.TELEFONO);
			reAU.setIdCanal(request.getCanalCM());
			registrarAuditoriaBD(reAU, resultado, errorM);
		}
		
		LOGGER.info(Servicios.LIMITESCLIENTESERVICEFACTUALIZAR);
		return response;
	}
	
	
	
	
	/**
     * Nombre:                 registrarAuditoriaBD
     * Descripcion:            Registrar Auditoria en Web Service
     *
     * @param  req  Objeto RegistrarAuditoriaRequest
     * @param  codigo   Codigo de respuesta
     * @param descripcion Descripcion del resultado
     * @version 1.0
     * @author Eugenio Owkin
	 * @since 12/04/21
	 */
	private void registrarAuditoriaBD(RegistrarAuditoriaRequest registrarAu,Resultado response, String errorAdicional) {
			
		        registrarA.registrarAuditoria(registrarAu, response.getCodigo(),response.getDescripcion(),errorAdicional);	
	}




	@Override
	public boolean existsById(LimitesPersonalizadosPk id) {
		return repo.existsById(id);
	}




	@Override
	public LimitesPersonalizadosDto findById(LimitesPersonalizadosPk id) {
		
		LimitesPersonalizados limitesPersonalizados = repo.findById(id).orElse(null);
		
		if(limitesPersonalizados != null) {
			LimitesPersonalizadosDto limitesPersonalizadosDto = new LimitesPersonalizadosDto();
			limitesPersonalizadosDto.setCodIbs(limitesPersonalizados.getId().getCodIbs());
			limitesPersonalizadosDto.setCodMoneda(limitesPersonalizados.getId().getCodMoneda());
			limitesPersonalizadosDto.setTipoTransaccion(limitesPersonalizados.getId().getTipoTransaccion());
			limitesPersonalizadosDto.setMontoMin(limitesPersonalizados.getMontoMin());
			limitesPersonalizadosDto.setMontoMax(limitesPersonalizados.getMontoMax());
			limitesPersonalizadosDto.setMontoTope(limitesPersonalizados.getMontoTope());
			limitesPersonalizadosDto.setMontoMensual(limitesPersonalizados.getMontoMensual());
			limitesPersonalizadosDto.setMontoDiario(limitesPersonalizados.getMontoDiario());
			limitesPersonalizadosDto.setCodUsuario(limitesPersonalizados.getCodUsuario());
			limitesPersonalizadosDto.setFlagActivo(limitesPersonalizados.getFlagActivo());
			limitesPersonalizadosDto.setFechaModificacion(limitesPersonalizados.getFechaModificacion());
			
			return limitesPersonalizadosDto;
			
		}else {
			return null;
		}
		
		
	}




	




	
}
