package com.bancoexterior.parametros.limitesusuarios.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.bancoexterior.parametros.limitesusuarios.entities.LimitesPersonalizados;
import com.bancoexterior.parametros.limitesusuarios.entities.LimitesPersonalizadosPk;
import com.bancoexterior.parametros.limitesusuarios.config.Codigos.SQLUtils;





@Repository
public interface ILimitesPersonalizadosRepository extends JpaRepository<LimitesPersonalizados, LimitesPersonalizadosPk>{
	
		@Query(value = SQLUtils.SELECTLIMITESCLIENTES, nativeQuery = true)
		public List<LimitesPersonalizados> getLimitesClientesByNuevo(String codIbs, String codMoneda, String tipoTransaccion, String flag,  boolean flagActivo);
		
}
