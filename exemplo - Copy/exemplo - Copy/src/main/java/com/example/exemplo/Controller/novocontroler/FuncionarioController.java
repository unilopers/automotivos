package com.example.exemplo.Controller.novocontroler;

import com.example.exemplo.ModelNovo.Funcionario;
import com.example.exemplo.ModelNovo.Repository.FuncionarioRepository;
import com.example.exemplo.view.FuncionarioView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/funcionarios")
public class FuncionarioController {
    @Autowired
    private FuncionarioRepository funcionarioRepository;

    @GetMapping
    public ResponseEntity<List<FuncionarioView>> list() {
        List<FuncionarioView> lista = funcionarioRepository.findAll().stream()
                .map(this::toView)
                .toList();
        return ResponseEntity.ok(lista);
    }

    @GetMapping("/{id}")
    public ResponseEntity<FuncionarioView> read(@PathVariable Integer id) {
        Optional<Funcionario> funcionario = funcionarioRepository.findById(id);
        return funcionario.map(f -> ResponseEntity.ok(toView(f)))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<FuncionarioView> create(@RequestBody Funcionario funcionario) {
        Funcionario saved = funcionarioRepository.save(funcionario);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(saved.getId())
                .toUri();
        return ResponseEntity.created(location).body(toView(saved));
    }

    @PutMapping("/{id}")
    public ResponseEntity<FuncionarioView> update(@PathVariable Integer id, @RequestBody Funcionario details) {
        Optional<Funcionario> opt = funcionarioRepository.findById(id);
        if (opt.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        Funcionario funcionario = opt.get();

        if (details.getNomeFuncionario() != null) {
            funcionario.setNomeFuncionario(details.getNomeFuncionario());
        }
        if (details.getCargoFuncionario() != null) {
            funcionario.setCargoFuncionario(details.getCargoFuncionario());
        }
        if (details.getSalarioFuncionario() != null) {
            funcionario.setSalarioFuncionario(details.getSalarioFuncionario());
        }

        Funcionario saved = funcionarioRepository.save(funcionario);
        return ResponseEntity.ok(toView(saved));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        Optional<Funcionario> opt = funcionarioRepository.findById(id);
        if (opt.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        funcionarioRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    private FuncionarioView toView(Funcionario f) {
        return new FuncionarioView(f.getId(), f.getNomeFuncionario(), f.getCargoFuncionario());
    }
}