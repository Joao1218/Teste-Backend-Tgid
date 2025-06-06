package com.Tgid.TesteTgid.Repositories;

import com.Tgid.TesteTgid.Model.Produto;
import org.springframework.data.jpa.repository.JpaRepository;

//Repositorio do produto pra conexão com o banco
public interface ProdutoRepository extends JpaRepository<Produto, Long> {
}
