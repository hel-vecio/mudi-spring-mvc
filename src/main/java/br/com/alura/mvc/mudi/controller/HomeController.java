package br.com.alura.mvc.mudi.controller;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import br.com.alura.mvc.mudi.model.Pedido;
import br.com.alura.mvc.mudi.model.StatusPedido;
import br.com.alura.mvc.mudi.repository.PedidoRepository;

@Controller
@RequestMapping("/home")
public class HomeController {
	
	@Autowired // UTILIZADO PARA INJEÇÃO DE DEPENDÊNCIA - O SPRING INSTANCIA ESSA CLASSE AUTOMATICAMENTE
	private PedidoRepository pedidoRepository;

	@GetMapping
	public String home(Model model, Principal principal) {	// PRINCIPAL PEGA OS ATRIBUTOS DO USUARIO LOGADO
		List<Pedido> pedidos = pedidoRepository.findAllByUsuario(principal.getName());
		model.addAttribute("pedidos", pedidos); //PASSANDO A LISTA PEDIDOS NA REQUISIÇÃO POR PARÂMETRO
		return "home";
	}
	
	@GetMapping("/{status}")
	public String porStatus(@PathVariable("status")String status, Model model) {	
		List<Pedido> pedidos = pedidoRepository.findByStatus(StatusPedido.valueOf(status.toUpperCase()));
		model.addAttribute("pedidos", pedidos); //PASSANDO A LISTA PEDIDOS NA REQUISIÇÃO POR PARÂMETRO
		model.addAttribute("status", status);
		return "home";
	}
	
	@ExceptionHandler(IllegalArgumentException.class)
	public String onError() {
		return "redirect:/home";
	}
	
}
