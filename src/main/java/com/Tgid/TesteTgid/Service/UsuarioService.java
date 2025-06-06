package com.Tgid.TesteTgid.Service;

import com.Tgid.TesteTgid.Model.Usuario;
import com.Tgid.TesteTgid.Repositories.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

//Anotação pra dizer que é um service
@Service
public class UsuarioService {

    //Carrega o repositorio se precisarmos ficar gerando o construtor novamente
    @Autowired
    private UsuarioRepository repository;


    public Usuario cadastrarUsuario(Usuario usuario) throws Exception {
        //Tratamento de excessão
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
        //Tratamento de excessão
        if (!repository.existsById(id)){
            throw new Exception("Usuario não encontrado");
        }
        repository.deleteById(id);
    }
}
