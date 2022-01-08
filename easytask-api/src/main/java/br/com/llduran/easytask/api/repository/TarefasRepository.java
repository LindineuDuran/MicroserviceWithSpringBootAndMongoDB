package br.com.llduran.easytask.api.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import br.com.llduran.easytask.api.model.Tarefa;

@Repository
public interface TarefasRepository extends MongoRepository<Tarefa, String>
{
	List<Tarefa> findByDescricaoLikeIgnoreCase(String descricao);
}
