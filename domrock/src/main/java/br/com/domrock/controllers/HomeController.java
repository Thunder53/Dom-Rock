package br.com.domrock.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.servlet.ModelAndView;

import br.com.domrock.model.Administrador;
import br.com.domrock.model.Usuario;
import br.com.domrock.model.Vendedor;
import br.com.domrock.model.DAOs.AdministradorDAO;
import br.com.domrock.model.DAOs.UsuarioDAO;
import br.com.domrock.model.DAOs.VendedorDAO;

import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class HomeController {
	
	
	
	
	@GetMapping("/")
	public ModelAndView index() {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("/home/index.html");
		return mv;

	}
	
	@GetMapping("./AdministradorDAO")
	public String getLogin (AdministradorDAO administrador) {
		administrador.buscaAdministrador ("buscaadministrador");
		return "Login";
		

	}
	
	
		
	

}
