package com.github.marcoslsouza.demo_relatorio.aluno.controller;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.github.marcoslsouza.demo_relatorio.aluno.service.AlunoService;

@RestController
@RequestMapping("/api/alunos")
public class AlunoController {
	
	@Autowired
	private AlunoService alunoService;
	
	@GetMapping
	public void relatorioAluno(@RequestParam("tipo") int tipo, HttpServletResponse response) {
		
		try {
			byte[] bytes = alunoService.gerarRelatorio(tipo, response);
			response.setHeader("Content-disposition", "inline; filename=relatorio-alunos.pdf");
			response.getOutputStream().write(bytes);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
