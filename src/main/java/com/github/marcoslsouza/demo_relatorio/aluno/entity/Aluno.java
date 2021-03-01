package com.github.marcoslsouza.demo_relatorio.aluno.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class Aluno {

	@ToString.Exclude
	private Long id;
	private String nome;
	private String matricula;
	private Double nota;
	private Integer faltas;
}
