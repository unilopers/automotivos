package com.example.exemplo.Controller.novocontroler;

import com.example.exemplo.ModelNovo.Fornecedor;
import com.example.exemplo.ModelNovo.Repository.FornecedorRepository;
import com.example.exemplo.view.FornecedorView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/fornecedores")
public class FornecedorController {
    @Autowired
    private FornecedorRepository fornecedorRepository;

    @GetMapping
    public ResponseEntity<List<FornecedorView>> list() {
        List<FornecedorView> lista = fornecedorRepository.findAll().stream()
                .map(this::toView)
                .toList();
        return ResponseEntity.ok(lista);
    }

    @GetMapping("/{id}")
    public ResponseEntity<FornecedorView> read(@PathVariable Integer id) {
        Optional<Fornecedor> fornecedor = fornecedorRepository.findById(id);
        return fornecedor.map(f -> ResponseEntity.ok(toView(f)))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<FornecedorView> create(@RequestBody Fornecedor fornecedor) {
        Fornecedor saved = fornecedorRepository.save(fornecedor);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(saved.getId())
                .toUri();
        return ResponseEntity.created(location).body(toView(saved));
    }

    @PutMapping("/{id}")
    public ResponseEntity<FornecedorView> update(@PathVariable Integer id, @RequestBody Fornecedor details) {
        Optional<Fornecedor> opt = fornecedorRepository.findById(id);
        if (opt.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        Fornecedor fornecedor = opt.get();

        if (details.getNomeFornecedor() != null) {
            fornecedor.setNomeFornecedor(details.getNomeFornecedor());
        }

        Fornecedor saved = fornecedorRepository.save(fornecedor);
        return ResponseEntity.ok(toView(saved));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        Optional<Fornecedor> opt = fornecedorRepository.findById(id);
        if (opt.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        fornecedorRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    private FornecedorView toView(Fornecedor f) {
        return new FornecedorView(f.getId(), f.getNomeFornecedor());
    }
}