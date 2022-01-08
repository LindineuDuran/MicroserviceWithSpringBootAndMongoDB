package br.com.llduran.easytask.api.service;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.ResponseStatus;

import br.com.llduran.easytask.api.model.StatusTarefa;
import br.com.llduran.easytask.api.model.Tarefa;
import br.com.llduran.easytask.api.repository.TarefasRepository;
import br.com.llduran.easytask.api.validate.TarefasValidate;

@Service
public class TarefasService extends TarefasValidate
{

	@Autowired
	private TarefasRepository tarefasRepository;

	@ResponseStatus(HttpStatus.OK)
	public List<Tarefa> listarTodas()
	{
		List<Tarefa> tarefas = tarefasRepository.findAll();
		trataListaTarefasNotFound(tarefas);
		return tarefas;
	}

	@ResponseStatus(HttpStatus.OK)
	public Tarefa buscarPorId(String id)
	{
		Tarefa tarefa = tarefasRepository.findById(id).orElse(null);
		trataTarefaNotFound(tarefa, MENSAGEM_NOT_FOUND_CONSULTA);
		return tarefa;
	}

	@ResponseStatus(HttpStatus.OK)
	public List<Tarefa> buscarPorDescricao(String descricao)
	{
		List<Tarefa> tarefas = tarefasRepository.findByDescricaoLikeIgnoreCase(descricao);
		trataListaTarefasNotFound(tarefas);
		return tarefas;
	}

	@ResponseStatus(HttpStatus.CREATED)
	public Tarefa save(Tarefa tarefa)
	{
		tarefa.setDataCriacao(LocalDate.now());

		if (StringUtils.isEmpty(tarefa.getStatusTarefa()))
		{
			tarefa.setStatusTarefa(StatusTarefa.A_FAZER);
		}
		return tarefasRepository.save(tarefa);
	}

	@ResponseStatus(HttpStatus.NO_CONTENT)
	public Tarefa update(String id, Tarefa tarefa)
	{
		Tarefa tarefaToUpdate = tarefasRepository.findById(id).orElse(null);
		trataTarefaNotFound(tarefaToUpdate, MENSAGEM_NOT_FOUND_ALTERACAO);

		return tarefasRepository.save(trataCamposAlteracao(tarefa, tarefaToUpdate));
	}

	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void deleteById(String id)
	{
		Tarefa tarefaToUpdate = tarefasRepository.findById(id).orElse(null);
		trataTarefaNotFound(tarefaToUpdate, MENSAGEM_NOT_FOUND_EXCLUSAO);
		tarefasRepository.deleteById(id);
	}
}
