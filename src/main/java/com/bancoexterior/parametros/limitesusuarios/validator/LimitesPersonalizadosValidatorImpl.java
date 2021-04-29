package com.bancoexterior.parametros.limitesusuarios.validator;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

import com.bancoexterior.parametros.limitesusuarios.exception.CodMonedaNoExistException;
import com.bancoexterior.parametros.limitesusuarios.exception.FieldErrorValidationException;
import com.bancoexterior.parametros.limitesusuarios.exception.LimitesClienteExistException;
import com.bancoexterior.parametros.limitesusuarios.exception.LimitesClienteNotExistException;
import com.bancoexterior.parametros.limitesusuarios.config.Codigos.CodRespuesta;
import com.bancoexterior.parametros.limitesusuarios.exception.TipoTransaccionNotValidException;
import com.bancoexterior.parametros.limitesusuarios.service.ILimitesPersonalizadosService;
import com.bancoexterior.parametros.limitesusuarios.service.IMonedaService;
import com.bancoexterior.parametros.limitesusuarios.dto.LimitesPersonalizadosRequestCrear;
import com.bancoexterior.parametros.limitesusuarios.entities.LimitesPersonalizadosPk;



@Component
public class LimitesPersonalizadosValidatorImpl implements ILimitesPersonalizadosValidator{

	@Autowired
	private ILimitesPersonalizadosService limiteService;
	
	@Autowired
	private IMonedaService monedaService;
	
	@Override
	public void validarCrear(LimitesPersonalizadosRequestCrear request, BindingResult result) {
		//Validando los valores de entrada
		if (result.hasErrors()) {
			List<String> errors = result.getFieldErrors().stream().map(FieldError::getDefaultMessage)
					.collect(Collectors.toList());
			throw new FieldErrorValidationException(errors.get(0));			
		}
		
		String tipoTransaccion = request.getLimitesPersonalizadosDtoRequestCrear().getTipoTransaccion();
		String codMoneda = request.getLimitesPersonalizadosDtoRequestCrear().getCodMoneda();
		String codIbs = request.getLimitesPersonalizadosDtoRequestCrear().getCodIbs();
		
		
		if(!tipoTransaccion.equals("C") && !tipoTransaccion.equals("V")) {
			throw new TipoTransaccionNotValidException(CodRespuesta.CDE1006);
		}
		
		if(!monedaService.existsById(codMoneda)) {
			throw new CodMonedaNoExistException(CodRespuesta.CDE2003);
		}
		
		LimitesPersonalizadosPk id = new LimitesPersonalizadosPk();
		id.setCodIbs(codIbs);
		id.setCodMoneda(codMoneda);
		id.setTipoTransaccion(tipoTransaccion);
		
		if(limiteService.existsById(id)) {
			throw new LimitesClienteExistException(CodRespuesta.CDE2001);
		}
		
	}

	@Override
	public void validarActualizar(LimitesPersonalizadosRequestCrear request, BindingResult result) {
		//Validando los valores de entrada
		if (result.hasErrors()) {
			List<String> errors = result.getFieldErrors().stream().map(FieldError::getDefaultMessage)
					.collect(Collectors.toList());
			throw new FieldErrorValidationException(errors.get(0));			
		}
		
		String tipoTransaccion = request.getLimitesPersonalizadosDtoRequestCrear().getTipoTransaccion();
		String codMoneda = request.getLimitesPersonalizadosDtoRequestCrear().getCodMoneda();
		String codIbs = request.getLimitesPersonalizadosDtoRequestCrear().getCodIbs();
		
		if(!tipoTransaccion.equals("C") && !tipoTransaccion.equals("V")) {
			throw new TipoTransaccionNotValidException(CodRespuesta.CDE1006);
		}
		
		LimitesPersonalizadosPk id = new LimitesPersonalizadosPk();
		id.setCodIbs(codIbs);
		id.setCodMoneda(codMoneda);
		id.setTipoTransaccion(tipoTransaccion);
		
		if(!limiteService.existsById(id)) {
			throw new LimitesClienteNotExistException(CodRespuesta.CDE2000);
		}
	}

}
