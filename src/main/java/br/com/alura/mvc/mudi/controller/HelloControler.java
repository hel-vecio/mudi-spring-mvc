package br.com.alura.mvc.mudi.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller //ANOTAÇÃO NECESSÁRIA PARA O SPRING SABER QUE É UM CONTROLLER.
public class HelloControler {
	
	@GetMapping("/hello") // NECESSÁRIO PARA MAPEAR PARA QUAL VIEW O MÉTODO VAI
	public String hello(Model model) { // MESMO COMPORTAMENTO DO HTTPSERVLETREQUEST
		model.addAttribute("nome", "Mundo"); // PASSAR PARAMETRO NA REQUISIÇÃO
		return "hello"; // RETORNA O NOME DA VIEW
	}
	
	
}
