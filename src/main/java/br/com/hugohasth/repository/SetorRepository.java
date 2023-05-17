package br.com.hugohasth.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.hugohasth.model.Setor;

@Repository
public interface SetorRepository extends JpaRepository<Setor, Long> {

}
