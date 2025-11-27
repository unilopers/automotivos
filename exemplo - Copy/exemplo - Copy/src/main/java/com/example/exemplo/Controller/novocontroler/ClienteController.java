package com.example.exemplo.Controller.novocontroler;

import com.example.exemplo.ModelNovo.Cliente;
import com.example.exemplo.ModelNovo.Repository.ClienteRepository;
import com.example.exemplo.view.ClienteView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/clientes")
public class ClienteController {
    @Autowired
    private ClienteRepository clienteRepository;

    @GetMapping
    public ResponseEntity<List<ClienteView>> list() {
        List<ClienteView> clientesEncontrados = clienteRepository.findAll().stream()
                .map(this::toView)
                .toList();
        return ResponseEntity.ok(clientesEncontrados);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ClienteView> read(@PathVariable Integer id) {
        Optional<Cliente> cliente = clienteRepository.findById(id);
        return cliente.map(c -> ResponseEntity.ok(toView(c)))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<ClienteView> create(@RequestBody Cliente cliente) {
        Cliente saved = clienteRepository.save(cliente);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(saved.getId())
                .toUri();
        return ResponseEntity.created(location).body(toView(saved));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ClienteView> update(@PathVariable Integer id, @RequestBody Cliente details) {
        Optional<Cliente> opt = clienteRepository.findById(id);
        if (opt.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        Cliente cliente = opt.get();

        if (details.getNome() != null) {
            cliente.setNome(details.getNome());
        }
        if (details.getTelefone() != null) {
            cliente.setTelefone(details.getTelefone());
        }
        if (details.getEndereco() != null) {
            cliente.setEndereco(details.getEndereco());
        }
        if (details.getAnotacao() != null) {
            cliente.setAnotacao(details.getAnotacao());
        }

        Cliente saved = clienteRepository.save(cliente);
        return ResponseEntity.ok(toView(saved));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        Optional<Cliente> opt = clienteRepository.findById(id);
        if (opt.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        clienteRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    private ClienteView toView(Cliente c) {
        return new ClienteView(c.getId(), c.getNome(), c.getTelefone(), c.getEndereco());
    }
}