package com.bancoexterior.parametros.limitesusuarios.config;

import javax.sql.DataSource;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import com.bancoexterior.parametros.limitesusuarios.seguridad.MiCipher;



@Configuration
@PropertySource( value = "file://"+"${${microservicio.ambiente}"+".seed.ruta}"+"application.properties", ignoreResourceNotFound = false)
public class DataSourceConfig {
    
	private static final Logger LOGGER = LogManager.getLogger(DataSourceConfig.class);
	
	@Value("${microservicio.ambiente}")
	private String ambiente;
	
	@Value("${spring.datasource.username}")
	private String usuario;
	
	@Value("${spring.datasource.password}")
	private String password;
	
	@Value("${${microservicio.ambiente}"+".seed.limitecliente}")
    private String seedLimite;
	
	@Value("${sconfig.deskey}")
    private String sconfigDesKey;
	
	@Value("${sconfig.prokey}")
    private String sconfigProKey;
	
    @Bean
    public DataSource getDataSource() {
    	
    	
    	
    	
    	
    	LOGGER.info(ambiente);
    	LOGGER.info(usuario);
    	LOGGER.info(password);
    	LOGGER.info(sconfigDesKey);
    	LOGGER.info(sconfigProKey);
    	LOGGER.info(seedLimite);

    	
    	String usuarioEncryptBanco = MiCipher.encrypt("C14405", sconfigDesKey);
    	String passwordEncryptBanco = MiCipher.encrypt("Cumana01*", sconfigDesKey);
    	LOGGER.info(usuarioEncryptBanco);
    	LOGGER.info(passwordEncryptBanco);
    	
    	
    	String usuarioDecrypt = MiCipher.decrypt(usuario, seedLimite);
    	String passwordDecrypt = MiCipher.decrypt(password, seedLimite);
    	
    	LOGGER.info(usuarioDecrypt);
    	LOGGER.info(passwordDecrypt);
    	
        DataSourceBuilder<?> dataSourceBuilder = DataSourceBuilder.create();
        dataSourceBuilder.driverClassName("org.postgresql.Driver");
        dataSourceBuilder.url("jdbc:postgresql://172.19.148.50:5432/Convenio1");
        dataSourceBuilder.username(usuarioDecrypt);
        dataSourceBuilder.password(passwordDecrypt);
        return dataSourceBuilder.build();
    }
}
