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
	
	String queryAll = "select new com.bancoexterior.parametros.limitesusuarios.dto.LimitesPersonalizadosDto "
			+ "(t.id.codIbs, t.id.codMoneda, t.id.tipoTransaccion, t.montoMin, t.montoMax, t.montoTope, "
			+ "t.montoMensual, t.montoDiario, t.flagActivo, t.codUsuario, t.fechaModificacion) "
			+ " from LimitesPersonalizados t"
			+ " where 1=1";
	
	@Query(value = queryAll)
	public List<LimitesPersonalizadosDto> getAll();
	
	//Todas
	@Query(value = queryAll + " and t.id.codIbs = ?1 and t.id.codMoneda = ?2 and t.id.tipoTransaccion = ?3  and t.flagActivo = ?4")
	public List<LimitesPersonalizadosDto> getByCodIbsAndCodMonedaAndTipoTransaccionAndFlagActivo(String codIbs, String codMoneda, String tipoTransaccion,  boolean flagActivo);
	
	//codIbs
	@Query(value = queryAll + " and t.id.codIbs = ?1 ")
	public List<LimitesPersonalizadosDto> getByCodIbs(String codIbs);
	
	//codIbsAndCodMoneda
	@Query(value = queryAll + " and t.id.codIbs = ?1 and t.id.codMoneda = ?2")
	public List<LimitesPersonalizadosDto> getByCodIbsAndCodMoneda(String codIbs, String codMoneda);
	
	//codIbsAndTipoTransaccion
	@Query(value = queryAll + " and t.id.codIbs = ?1 and t.id.tipoTransaccion = ?2")
	public List<LimitesPersonalizadosDto> getByCodIbsAndTipoTransaccion(String codIbs, String tipoTransaccion);
	
	//codIbsAndFlagActivo
	@Query(value = queryAll + " and t.id.codIbs = ?1 and t.flagActivo = ?2")
	public List<LimitesPersonalizadosDto> getByCodIbsAndFlagActivo(String codIbs, boolean flagActivo);
	
	
	//codIbsAndCodMonedaAndTipoTransaccion
	@Query(value = queryAll + " and t.id.codIbs = ?1 and t.id.tipoTransaccion = ?2 and t.id.codMoneda = ?3")
	public List<LimitesPersonalizadosDto> getById(String codIbs, String tipoTransaccion, String codMoneda);
	
	//codIbsAndCodMonedaAndFlagActivo
	@Query(value = queryAll + " and t.id.codIbs = ?1 and t.id.codMoneda = ?2 and t.flagActivo = ?3")
	public List<LimitesPersonalizadosDto> getByCodIbsAndCodMonedaAndFalgActivo(String codIbs, String codMoneda, boolean flagActivo);
	
	//codMoneda
	@Query(value = queryAll + " and t.id.codMoneda = ?1 ")
	public List<LimitesPersonalizadosDto> getByCodMoneda(String codMoneda);
	
	//codMonedaAndTipoTransaccion
	@Query(value = queryAll + " and t.id.codMoneda = ?1 and t.id.tipoTransaccion = ?2")
	public List<LimitesPersonalizadosDto> getByCodMonedaAndTipoTransaccion(String codMoneda, String tipoTransaccion);
	
	//codMonedaAndFalgActivo
	@Query(value = queryAll + " and t.id.codMoneda = ?1 and t.flagActivo = ?2")
	public List<LimitesPersonalizadosDto> getByCodMonedaAndFlagActivo(String codMoneda, boolean flagActivo);
	
	//codMonedaAndTipoTransaccionAndFalgActivo
	@Query(value = queryAll + " and t.id.codMoneda = ?1 and t.id.tipoTransaccion = ?2 and t.flagActivo = ?3")
	public List<LimitesPersonalizadosDto> getByCodMonedaAndTipoTransaccionAndFlagActivo(String codMoneda, String tipoTransaccion,boolean flagActivo);
	
	//tipoTransaccion
	@Query(value = queryAll + " and t.id.tipoTransaccion = ?1 ")
	public List<LimitesPersonalizadosDto> getByTipoTransaccion(String tipoTransaccion);
	
	//tipoTransaccionAndFlagActivo
	@Query(value = queryAll + " and t.id.tipoTransaccion = ?1 and t.flagActivo = ?2")
	public List<LimitesPersonalizadosDto> getByTipoTransaccionAndFlagActivo(String tipoTransaccion, boolean flagActivo);
	
	//FlagActivo
	@Query(value = queryAll + " and t.flagActivo = ?1")
	public List<LimitesPersonalizadosDto> getByFlagActivo(boolean flagActivo);
	
	
}
