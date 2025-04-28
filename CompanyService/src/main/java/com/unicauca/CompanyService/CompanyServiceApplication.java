package com.unicauca.CompanyService;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.swing.*;

@SpringBootApplication
public class CompanyServiceApplication {

	public static void main(String[] args) {

		//SpringApplication.run(CompanyServiceApplication.class, args);
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				try {
					// Crear una instancia de la ventana de ingreso de datos
					com.unicauca.CompanyService.presentation.CompanyForm window = new com.unicauca.CompanyService.presentation.CompanyForm();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

}
