package com.bancoexterior.parametros.limitesusuarios.entities;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.PreUpdate;
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
//@Table(name = "Monedas",  schema = "Convenio1") "\"AIRPORTS\""
@Table(name = "\"Clientes_personalizados\"", schema = "\"Convenio1\"")
public class ClientesPersonalizados {
	@Id
	@Column(name = "cod_ibs" , nullable = false)
	@NotEmpty(message = "no puede ser vacio")
	@Size(min = 1, max = 10)
	private String codIbs;
	
	@Column(name = "nro_id_cliente" , nullable = false)
	@NotEmpty(message = "no puede ser vacio")
	@Size(min = 1, max = 15)
	private String nroIdCliente;
	
	@Column(name = "nombre_rif" , nullable = false)
	@NotEmpty(message = "no puede ser vacio")
	private String nombreRif;
	
	
	@NotEmpty(message = "no puede ser vacio")
	@Column(name = "cod_usuario", nullable = false)
	@Size(max = 10)
	private String codUsuario;
	
	@Column(name = "flag_activo")
	private Boolean flagActivo;
	
	@Column(name = "fecha_modificacion", nullable = false)
	@Temporal(TemporalType.TIMESTAMP)
	private Date fechaModificacion;
	
	
	
	@PreUpdate
	public void preUpdate() {
		setFechaModificacion(new Date());
	}
}
