package com.bancoexterior.parametros.limitesusuarios.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bancoexterior.parametros.limitesusuarios.entities.ClientesPersonalizados;

@Repository
public interface IClientesPersonalizadosRepository extends JpaRepository<ClientesPersonalizados, String>{

}
