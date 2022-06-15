package com.AMQApp;

import com.AMQApp.enums.Pais;
import com.AMQApp.enums.Sexo;
import com.AMQApp.errores.ErrorServicio;
import com.AMQApp.servicios.UsuarioServicio;
import java.util.Date;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class AmqAppApplication {

	public static void main(String[] args) {
		SpringApplication.run(AmqAppApplication.class, args);                
                
	}

}
