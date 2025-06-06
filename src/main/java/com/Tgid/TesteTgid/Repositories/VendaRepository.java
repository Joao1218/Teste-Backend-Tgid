package com.Tgid.TesteTgid.Repositories;

import com.Tgid.TesteTgid.Model.Venda;
import org.springframework.data.jpa.repository.JpaRepository;

//Repositorio de venda pra conexão com o banco
public interface VendaRepository extends JpaRepository<Venda, Long> {
}
