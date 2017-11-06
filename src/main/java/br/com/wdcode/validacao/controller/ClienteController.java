package br.com.wdcode.validacao.controller;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.com.wdcode.validacao.file.type.TypeFile;
import br.com.wdcode.validacao.model.Cliente;
import br.com.wdcode.validacao.model.Sexo;
import br.com.wdcode.validacao.repository.Clientes;

@Controller
@RequestMapping("/clientes")
public class ClienteController {
	
	@Autowired
	private Clientes clientes;
	
	@GetMapping("/novo")
	public ModelAndView novo(Cliente cliente) {
		ModelAndView mv = new ModelAndView("cadastroCliente");
		mv.addObject("sexos", Arrays.asList(Sexo.values()));
		return mv;
	}
	
	@PostMapping("/novo")
	public ModelAndView salvar(@Valid Cliente cliente,BindingResult result, RedirectAttributes attributes) {
		if(result.hasErrors()) {
			return novo(cliente);
		}
		this.clientes.save(cliente);
		ModelAndView mv = new ModelAndView("redirect:/clientes/novo");
		attributes.addFlashAttribute("mensagem", "Cliente salvo com Sucesso");
		return mv;
	}
	
	@RequestMapping(value="/arquivo/{value}", method = RequestMethod.GET)    
	public HttpEntity<byte[]> download(@PathVariable("value") String value) {
		
		System.out.println(value);
		
		TypeFile typeFile = TypeFile.valueOf(value.toUpperCase());
		typeFile.getInstanceFile(this.clientes.findAll()).criarArquivo();
	 try {
		 byte[] arquivo = Files.readAllBytes(Paths.get("clientes."+value));
		 HttpHeaders httpHeaders = new HttpHeaders();

		 httpHeaders.add("Content-Disposition", "attachment;filename=\""+ "clientes."+value +"\"");

		 HttpEntity<byte[]> entity = new HttpEntity<byte[]>( arquivo, httpHeaders);
		 return entity;
	 }catch (IOException e) {
		// TODO: handle exception
	}

	 return null;
	}
} 
