package com.Tgid.TesteTgid.Controllers;

import com.Tgid.TesteTgid.Model.Usuario;
import com.Tgid.TesteTgid.Service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

//Deixando claro que é um controller
@RestController
//Definindo qual o caminho pra acessarmos ele
@RequestMapping("/usuario")
public class UsuarioController {

    //Carrega o repositorio se precisarmos ficar gerando o construtor novamente
    @Autowired
    private UsuarioService usuarioService;

    //Mostrando que o metodo que vamos usar é o POST
    //Usamos o request body porque precisamos que a informação venha no body da aplicação
    @PostMapping
    public ResponseEntity<Usuario> cadastrarUsuario(@RequestBody Usuario usuario) throws Exception {
        Usuario novoUsuario = usuarioService.cadastrarUsuario(usuario);
        return new ResponseEntity<>(novoUsuario, HttpStatus.CREATED);
    }

    //Mostrando que o metodo que vamos usar é o GET
    @GetMapping
    public ResponseEntity<List<Usuario>> listarUsuarios(){
        List<Usuario> usuarios = usuarioService.listarUsuarios();
        return new ResponseEntity<>(usuarios, HttpStatus.OK);
    }

    /*Mostrando que o metodo que vamos usar é o GET e qua a pessoa precisa escolher o ID
       por isso o PATHVARIABLE
        */
    @GetMapping("/{id}")
    public ResponseEntity<Usuario> buscarUsuarioPorId(@PathVariable Long id) {
        Optional<Usuario> usuario = usuarioService.buscarUsuarioPorId(id);
        return usuario
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    /*Mostrando que o metodo que vamos usar é o DELETE e qua a pessoa precisa escolher o ID
       por isso o PATHVARIABLE
        */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarPorId(@PathVariable Long id) throws Exception {
        usuarioService.deletarUsuario(id);
        return ResponseEntity.noContent().build();
    }


}
