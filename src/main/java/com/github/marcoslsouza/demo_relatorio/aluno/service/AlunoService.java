package com.github.marcoslsouza.demo_relatorio.aluno.service;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;

import com.github.marcoslsouza.demo_relatorio.aluno.entity.Aluno;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

@Service
public class AlunoService {
	
	public byte[] gerarRelatorio(int tipo, HttpServletResponse response) throws FileNotFoundException, JRException {
		
		HashMap parametros = new HashMap();
		
		if(tipo == 1)
			parametros.put("param_rel", "RELATÓRIO 1");
		else
			if(tipo == 2)
				parametros.put("param_rel", "RELATÓRIO 2");
		
		Aluno aluno1 = new Aluno();
		Aluno aluno2 = new Aluno();
		
		List<Aluno> listAluno = new ArrayList<>();
		
		if(tipo == 1) {
			
			aluno1.setNome("Maria");
			aluno1.setMatricula("RA20213232");
			aluno1.setFaltas(0);
			aluno1.setNota(8.0);
			
			aluno2.setNome("João");
			aluno2.setMatricula("RA20214444");
			aluno2.setFaltas(0);
			aluno2.setNota(8.0);
			
		} else
			if(tipo == 2){
				aluno1.setNome("Paulo");
				aluno1.setMatricula("RA20213838");
				aluno1.setFaltas(2);
				aluno1.setNota(9.0);
			}
		
		if(aluno1 != null || !aluno1.getNome().isEmpty())
			listAluno.add(aluno1);
		
		if(aluno2 != null || !aluno2.getNome().isEmpty())
			listAluno.add(aluno2);
		
		// Gera o relatorio
		JRBeanCollectionDataSource ds = new JRBeanCollectionDataSource(listAluno);
		
		File file = ResourceUtils.getFile("classpath:relatorios/relatorio.jasper");
		JasperPrint print = JasperFillManager.fillReport(file.getAbsolutePath(), parametros, ds);
		
		return JasperExportManager.exportReportToPdf(print);
	}
}
