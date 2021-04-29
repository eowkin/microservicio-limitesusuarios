package com.bancoexterior.parametros.limitesusuarios.validator;

import org.springframework.validation.BindingResult;

import com.bancoexterior.parametros.limitesusuarios.dto.LimitesPersonalizadosRequestCrear;

public interface ILimitesPersonalizadosValidator {

	public void validarCrear(LimitesPersonalizadosRequestCrear request, BindingResult result);
	
	public void validarActualizar(LimitesPersonalizadosRequestCrear request, BindingResult result);
}
