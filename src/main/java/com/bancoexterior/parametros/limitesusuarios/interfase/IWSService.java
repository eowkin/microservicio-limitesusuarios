package com.bancoexterior.parametros.limitesusuarios.interfase;

import com.bancoexterior.parametros.limitesusuarios.model.WSRequest;
import com.bancoexterior.parametros.limitesusuarios.model.WSResponse;

public interface  IWSService {
	WSResponse post(WSRequest request) ;
}
