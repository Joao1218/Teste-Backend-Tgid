package com.Tgid.TesteTgid.Repositories;

import com.Tgid.TesteTgid.Model.ItemVenda;
import org.springframework.data.jpa.repository.JpaRepository;

//Repositorio do item venda pra conexão com o banco
public interface ItemVendaRepository extends JpaRepository<ItemVenda, Long> {
}
