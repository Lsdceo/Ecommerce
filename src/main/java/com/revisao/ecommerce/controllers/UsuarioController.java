package com.revisao.ecommerce.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.revisao.ecommerce.dto.UsuarioDTO;
import com.revisao.ecommerce.services.UsuarioService;

@RestController
@RequestMapping("usuario")
public class UsuarioController {

	@Autowired
	UsuarioService usuarioService;
	
	@PostMapping(value = "/salvar")
	public ResponseEntity <UsuarioDTO> cadastrarUsuario(@RequestBody UsuarioDTO dto){
		dto = usuarioService.salvarUsuario(dto);
		return ResponseEntity.ok(dto);
	}
	
	@PostMapping(value = "/login")
	public ResponseEntity <?> logar(@RequestBody UsuarioDTO dto){
		boolean teste = usuarioService.login(dto);
		
		if (teste) {
			return ResponseEntity.ok("Sucesso!");			
		}
		return ResponseEntity.status(401).body("Senha ou login incorretos");
	}
}