package com.uca.capas.tarea3.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class MainController {

	@RequestMapping("/ingresar")
	public ModelAndView ingresar() {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("ingreso");
		return mav;
	}

	@RequestMapping("/informacion")
	public ModelAndView alumno(
			@RequestParam String nombres, 
			@RequestParam String apellidos,
			@RequestParam String fechaNacimiento,
			@RequestParam String lugarNacimiento,
			@RequestParam String colegio,
			@RequestParam String telFijo,
			@RequestParam String telMovil) throws ParseException {
		
		ModelAndView mav = new ModelAndView();
		
		ArrayList<String> errores = new ArrayList<String>();
		
		if(!(nombres.length() >= 1 && nombres.length() <= 25)) {
			errores.add("*- La longitud del nombre debe ser mayor a 1 y menor a 25");
		}
		if(!(apellidos.length() >= 1 && apellidos.length() <= 25)) {
			errores.add("*- La longitud del apellido debe ser mayor a 1 y menor a 25");
		}
		if(validarFecha(fechaNacimiento)){
			errores.add("*- La fecha de nacimiento debe ser mayor o igual al 1 de enero del 2003");
		}
		if(!(lugarNacimiento.length() >= 1 && lugarNacimiento.length() <= 25)) {
			errores.add("*- La longitud del lugar de nacimiento debe ser mayor a 1 y menor a 25");
		}
		if(!(colegio.length() >= 1 && colegio.length() <= 100)) {
			errores.add("*- La longitud del colegio debe ser mayor a 1 y menor a 25");
		}
		if(telFijo.length() != 8) {
			errores.add("*- El telefono fijo debe tener estrictamente 8 digitos");
		}
		if(telMovil.length() != 8) {
			errores.add("*- El telefono movil debe tener estrictamente 8 digitos");
		}
		
		if(errores.isEmpty()){
			mav.setViewName("Acceso");
		}else {
			mav.addObject("req", errores);
			mav.setViewName("Denegado");
			}
			return mav;
		}

			
	public boolean validarFecha(String fechaNacimiento) throws ParseException {
		String fechaMinima = "2003-01-01";
		SimpleDateFormat conversor = new SimpleDateFormat("yyyy-MM-dd"); 
		
		Date fecha1 = conversor.parse(fechaNacimiento);
		Date fecha2 = conversor.parse(fechaMinima);
		
		if(fecha1.before(fecha2)) {
			return true;
		}else {
			return false;
		}  
	}
}