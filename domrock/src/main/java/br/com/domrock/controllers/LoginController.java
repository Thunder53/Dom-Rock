package br.com.domrock.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import br.com.domrock.model.Administrador;
import br.com.domrock.model.DAOs.AdministradorDAO;

public class LoginController {
	
	private AdministradorDAO administradorDAO;
	
	@GetMapping("./AdministradorDAO")
	public String getLogin (AdministradorDAO administrador) {
		administrador.buscaAdministrador ("buscaadministrador");
		return "Login";
		

	}
	
	@PostMapping("/login")
	public String login(@ModelAttribute AdministradorDAO administradorDAO, Administrador administrador) {
		System.out.println("login request:" + administradorDAO);
		Administrador buscaadministrador = administradorDAO.buscaAdministrador(administrador.getNome());
		if(buscaadministrador != null) {
			administrador.addAttribute ("Login", buscaadministrador.getNome());
			return "CRUD";
		}else {
			return "error_page";
		}
	}
	

}
