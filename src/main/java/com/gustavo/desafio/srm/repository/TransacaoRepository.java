package com.gustavo.desafio.srm.repository;

import com.gustavo.desafio.srm.domain.entity.Transacao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface TransacaoRepository extends JpaRepository<Transacao, Integer>, JpaSpecificationExecutor<Transacao> {

}
