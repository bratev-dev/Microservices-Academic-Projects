package com.unicauca.CompanyService;

//import com.unicauca.CompanyService.presentation.CompanyForm;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.swing.*;
import java.awt.*;

@SpringBootApplication
public class CompanyServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(CompanyServiceApplication.class, args);
		/*
		// Solo abrimos el formulario si estamos en entorno gráfico
		if (!GraphicsEnvironment.isHeadless()) {
			javax.swing.SwingUtilities.invokeLater(() -> {
				new CompanyForm().setVisible(true);
			});
		} else {
			System.out.println("No se puede abrir el formulario en entorno sin interfaz gráfica.");
		}*/
	}



}
