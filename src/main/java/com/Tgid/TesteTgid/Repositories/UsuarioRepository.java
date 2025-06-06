package com.Tgid.TesteTgid.Repositories;

import com.Tgid.TesteTgid.Model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

//Repositorio do usuario pra conexão com o banco
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
}
