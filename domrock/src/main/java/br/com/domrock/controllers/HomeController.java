package br.com.domrock.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.servlet.ModelAndView;

import br.com.domrock.model.Usuario;
import br.com.domrock.model.Vendedor;
import br.com.domrock.model.DAOs.UsuarioDAO;
import br.com.domrock.model.DAOs.VendedorDAO;

import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class HomeController {
	
	
	private UsuarioDAO usuarioDAO;
	
	@GetMapping("/")
	public ModelAndView index() {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("home/index.html");
		return mv;

	}
	
//	@GetMapping("/")
//	public String getLogin (UsuarioDAO usuario) {
//		usuario.buscaUsuario ("buscarUsuario");
//		return "Login";
//		
//
//	}
//	
//	@PostMapping("/login")
//	public String login(@ModelAttribute UsuarioDAO usuarioDAO, Usuario usuario) {
//		System.out.println("login request:" + usuarioDAO);
//		UsuarioDAO buscaUsuario = usuarioDAO.buscaUsuario(usuario.getEmail(), usuario.getSenha());
//		if(buscaUsuario != null) {
//			usuario.addAttribute ("Login", buscaUsuario.getEmail());
//			return "CRUD";
//		}else {
//			return "error_page";
//		}
//	}
	
	
	
		
	

}
