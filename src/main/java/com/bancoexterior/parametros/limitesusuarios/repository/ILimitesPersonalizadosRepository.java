package com.bancoexterior.parametros.limitesusuarios.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.bancoexterior.parametros.limitesusuarios.entities.LimitesPersonalizados;
import com.bancoexterior.parametros.limitesusuarios.entities.LimitesPersonalizadosPk;
import com.bancoexterior.parametros.limitesusuarios.dto.LimitesPersonalizadosDto;




@Repository
public interface ILimitesPersonalizadosRepository extends JpaRepository<LimitesPersonalizados, LimitesPersonalizadosPk>{
	
	
	
	String queryNativo = "SELECT cod_ibs, cod_moneda, tipo_transaccion, monto_min, monto_max, monto_tope, monto_mensual, monto_diario,"
			+ "flag_activo, cod_usuario, fecha_modificacion "
			+ "FROM \"Convenio1\".\"Limites_personalizados\" "
			+ "where cod_ibs= (case when ?1 = '' then cod_ibs else ?1 end) "
			+ "and cod_moneda= (case when ?2 = '' then cod_moneda else ?2 end) "
			+ "and tipo_transaccion= (case when ?3 = '' then tipo_transaccion else ?3 end) "
			+ "and "
			+ "	case when  ?4 = 'si' then		"
			+ "		flag_activo= ?5 "
			+ "	else 	"
			+ "		flag_activo = flag_activo "
			+ "	end";
	
		//Todas
		@Query(value = queryNativo, nativeQuery = true)
		public List<LimitesPersonalizados> getLimitesClientesByNuevo(String codIbs, String codMoneda, String tipoTransaccion, String flag,  boolean flagActivo);
	
	
	
		
	
}
