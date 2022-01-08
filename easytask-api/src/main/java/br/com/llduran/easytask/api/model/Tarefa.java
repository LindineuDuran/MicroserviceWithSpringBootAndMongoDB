package br.com.llduran.easytask.api.model;

import java.time.LocalDate;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;

@Data
@Document
public class Tarefa
{
	@Id
	private String id;

	private String descricao;
	private LocalDate dataCriacao;
	private LocalDate dataConclusao;
	private StatusTarefa statusTarefa;
}
