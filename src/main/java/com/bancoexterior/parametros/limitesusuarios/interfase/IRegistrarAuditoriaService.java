package com.bancoexterior.parametros.limitesusuarios.interfase;

import com.bancoexterior.parametros.limitesusuarios.model.RegistrarAuditoriaRequest;

public interface IRegistrarAuditoriaService {
	
	void registrarAuditoria(RegistrarAuditoriaRequest auditoria,  String codigo, String mensaje, String errorAdicional);

}
