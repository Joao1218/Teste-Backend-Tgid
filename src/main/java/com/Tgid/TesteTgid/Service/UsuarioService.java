package com.Tgid.TesteTgid.Service;

import com.Tgid.TesteTgid.Model.Usuario;
import com.Tgid.TesteTgid.Repositories.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository repository;

    public Usuario cadastrarUsuario(Usuario usuario) throws Exception {
        if(usuario.getId() != null){
            throw new Exception("Usuario já cadastrado");
        }
        return repository.save(usuario);
    }

    public List<Usuario> listarUsuarios(){
        return repository.findAll();
    }

    public Optional<Usuario> buscarUsuarioPorId(Long id){
        return repository.findById(id);
    }

    public void deletarUsuario(Long id) throws Exception {
        if (!repository.existsById(id)){
            throw new Exception("Usuario não encontrado");
        }
        repository.deleteById(id);
    }
}
