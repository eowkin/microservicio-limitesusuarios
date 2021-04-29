package com.bancoexterior.parametros.limitesusuarios.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bancoexterior.parametros.limitesusuarios.entities.Moneda;

@Repository
public interface IMonedaRepository extends JpaRepository<Moneda, String>{

}
