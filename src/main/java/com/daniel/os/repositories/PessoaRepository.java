package com.daniel.os.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.daniel.os.domain.Pessoa;

@Repository
public interface PessoaRepository extends JpaRepository<Pessoa, Integer> {

	@Query("SELECT t FROM Pessoa t WHERE t.cpf =:cpf")
	Pessoa findByCPF(@Param("cpf") String cpf);

}
