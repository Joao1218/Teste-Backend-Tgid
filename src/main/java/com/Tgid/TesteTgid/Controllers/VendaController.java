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

@RestController
@RequestMapping("/venda")
public class VendaController {

    @Autowired
    private VendaService vendaService;

    @PostMapping("/{usuarioId}")
    public ResponseEntity<?> criarVenda(@PathVariable Long usuarioId, @RequestBody List<ItemVendaDTO> itens) {
        try {
            Venda novaVenda = vendaService.realizarCompra(usuarioId, itens);
            return new ResponseEntity<>(novaVenda, HttpStatus.CREATED);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Erro ao criar venda: " + e.getMessage());
        }
    }

    @GetMapping
    public ResponseEntity<List<Venda>> listarVenda(){
        List<Venda> venda = vendaService.listarVendas();
        return new ResponseEntity<>(venda, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Venda> buscarUsuarioPorId(@PathVariable Long id) {
        Optional<Venda> venda = vendaService.buscarVendaPorId(id);
        return venda
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/relatorio")
    public ResponseEntity<List<VendaResumoDTO>> relatorio() {
        List<VendaResumoDTO> resumo = vendaService.gerarResumoVendas();
        return ResponseEntity.ok(resumo);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarPorId(@PathVariable Long id) throws Exception {
        vendaService.deletarVenda(id);
        return ResponseEntity.noContent().build();
    }

}
