package br.com.llduran.easytask.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.llduran.easytask.api.model.Tarefa;
import br.com.llduran.easytask.api.service.TarefasService;

@RestController
@RequestMapping("/")
public class TarefasController
{
	@Autowired
	private TarefasService tarefasService;

	@GetMapping
	public String hello()
	{
		return "Bem vindo ao microsserviço easyTask!";
	}

	@GetMapping("/tarefas")
	public List<Tarefa> listarTodas()
	{
		return tarefasService.listarTodas();
	}

	@GetMapping("/tarefas/{id}")
	public Tarefa buscarPorId(@PathVariable String id)
	{
		return tarefasService.buscarPorId(id);
	}

	@GetMapping("/tarefas/descricao/{descricao}")
	public List<Tarefa> buscarPorDescricao(@PathVariable String descricao)
	{
		return tarefasService.buscarPorDescricao(descricao);
	}

	@PostMapping("/tarefas")
	public Tarefa criar(@RequestBody Tarefa tarefa)
	{
		return tarefasService.save(tarefa);
	}

	@PutMapping("/tarefas/{id}")
	public Tarefa atualizar(@PathVariable String id, @RequestBody Tarefa tarefa)
	{
		return tarefasService.update(id, tarefa);
	}

	@DeleteMapping("/tarefas/{id}")
	public void excluir(@PathVariable String id)
	{
		tarefasService.deleteById(id);
	}
}
