package com.Tgid.TesteTgid.Repositories;

import com.Tgid.TesteTgid.Model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
}
