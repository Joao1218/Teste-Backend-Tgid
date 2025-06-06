package com.Tgid.TesteTgid.Controllers;

import com.Tgid.TesteTgid.DTO.ItemVendaDTO;
import com.Tgid.TesteTgid.DTO.VendaResumoDTO;
import com.Tgid.TesteTgid.Model.Venda;
import com.Tgid.TesteTgid.Service.VendaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

//Deixando claro que é um controller
@RestController
//Definindo qual o caminho pra acessarmos ele
@RequestMapping("/venda")
public class VendaController {

    //Carrega o repositorio se precisarmos ficar gerando o construtor novamente
    @Autowired
    private VendaService vendaService;

    /*Mostrando que o metodo que vamos usar é o POST Usamos o request body porque
    precisamos que a informação venha no body da aplicação e tambem que o usuario escolha o id
    por isso o PATHVARIABLE*/
    @PostMapping("/{usuarioId}")
    public ResponseEntity<?> criarVenda(@PathVariable Long usuarioId, @RequestBody List<ItemVendaDTO> itens) {
        //try e catch pra tratamento de excessões
        try {
            Venda novaVenda = vendaService.realizarCompra(usuarioId, itens);
            return new ResponseEntity<>(novaVenda, HttpStatus.CREATED);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Erro ao criar venda: " + e.getMessage());
        }
    }

    //Mostrando que o metodo que vamos usar é o GET
    @GetMapping
    public ResponseEntity<List<Venda>> listarVenda(){
        List<Venda> venda = vendaService.listarVendas();
        return new ResponseEntity<>(venda, HttpStatus.OK);
    }

    /*Mostrando que o metodo que vamos usar é o GET e qua a pessoa precisa escolher o ID
        por isso o PATHVARIABLE
         */
    @GetMapping("/{id}")
    public ResponseEntity<Venda> buscarUsuarioPorId(@PathVariable Long id) {
        Optional<Venda> venda = vendaService.buscarVendaPorId(id);
        return venda
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    /*Mostrando que o metodo que vamos usar é o GET e qua a pessoa precisa escolher o ID
        por isso o PATHVARIABLE
         */
    @GetMapping("/relatorio")
    public ResponseEntity<List<VendaResumoDTO>> relatorio() {
        List<VendaResumoDTO> resumo = vendaService.gerarResumoVendas();
        return ResponseEntity.ok(resumo);
    }

    /*Mostrando que o metodo que vamos usar é o DELETE e qua a pessoa precisa escolher o ID
       por isso o PATHVARIABLE
        */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarPorId(@PathVariable Long id) throws Exception {
        vendaService.deletarVenda(id);
        return ResponseEntity.noContent().build();
    }

}
