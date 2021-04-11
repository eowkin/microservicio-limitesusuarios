package com.bancoexterior.parametros.limitesusuarios.entities;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.PrePersist;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @AllArgsConstructor @NoArgsConstructor
@Builder
@Entity
@Table(name = "\"Limites_personalizados\"", schema = "\"Convenio1\"")
public class LimitesPersonalizados {
	
	@EmbeddedId
	private LimitesPersonalizadosPk id;
	
	@NotEmpty(message = "no puede ser vacio")
	@Column(name="monto_min", nullable = false)
	private Double montoMin;
	
	@NotEmpty(message = "no puede ser vacio")
	@Column(name="monto_max", nullable = false)
	private Double montoMax;
	
	@NotEmpty(message = "no puede ser vacio")
	@Column(name="monto_tope", nullable = false)
	private Double montoTope;
	
	@NotEmpty(message = "no puede ser vacio")
	@Column(name="monto_mensual", nullable = false)
	private Double montoMensual;
	
	@NotEmpty(message = "no puede ser vacio")
	@Column(name="monto_diario", nullable = false)
	private Double montoDiario;
	
	@NotEmpty(message = "no puede ser vacio")
	@Column(name = "flag_activo", nullable = false)
	private Boolean flagActivo;
	
	@NotEmpty(message = "no puede ser vacio")
	@Column(name = "cod_usuario", nullable = false)
	@Size(max = 10)
	private String codUsuario;
	
	@Column(name = "fecha_modificacion", nullable = false)
	@Temporal(TemporalType.TIMESTAMP)
	private Date fechaModificacion;
	
	@PrePersist
	public void prePersist() {
		setFechaModificacion(new Date());
	}
}
