package com.example.exemplo.Controller.novocontroler;

import com.example.exemplo.ModelNovo.Produto;
import com.example.exemplo.ModelNovo.Fornecedor;
import com.example.exemplo.ModelNovo.Repository.ProdutoRepository;
import com.example.exemplo.ModelNovo.Repository.FornecedorRepository;
import com.example.exemplo.view.ProdutoView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping({"/produtos", "/produto"})
public class ProdutoController {
    @Autowired
    private ProdutoRepository produtoRepository;

    @Autowired
    private FornecedorRepository fornecedorRepository;

    @GetMapping
    public List<ProdutoView> list() {
        return produtoRepository.findAll().stream()
                .map(this::toView)
                .toList();
    }

    @GetMapping("/{sku}")
    public ResponseEntity<ProdutoView> read(@PathVariable String sku) {
        Optional<Produto> produto = produtoRepository.findById(sku);
        return produto.map(p -> ResponseEntity.ok(toView(p)))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<ProdutoView> create(@RequestBody Produto produto) {
        if (produto.getSku() == null || produto.getSku().isEmpty()) {
            return ResponseEntity.badRequest().build();
        }
        if (produtoRepository.existsById(produto.getSku())) {
            return ResponseEntity.status(409).build();
        }

        if (produto.getFornecedor() != null && produto.getFornecedor().getId() != null) {
            Optional<Fornecedor> opt = fornecedorRepository.findById(produto.getFornecedor().getId());
            if (opt.isEmpty()) {
                return ResponseEntity.badRequest().build();
            }
            produto.setFornecedor(opt.get());
        }

        Produto saved = produtoRepository.save(produto);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{sku}")
                .buildAndExpand(saved.getSku())
                .toUri();
        return ResponseEntity.created(location).body(toView(saved));
    }

    @PutMapping("/{sku}")
    public ResponseEntity<ProdutoView> update(@PathVariable String sku, @RequestBody Produto details) {
        Optional<Produto> opt = produtoRepository.findById(sku);
        if (opt.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        Produto produto = opt.get();

        if (details.getNomeProduto() != null) {
            produto.setNomeProduto(details.getNomeProduto());
        }
        if (details.getCodigoBarra() != null) {
            produto.setCodigoBarra(details.getCodigoBarra());
        }
        if (details.getPrecoProduto() != null) {
            produto.setPrecoProduto(details.getPrecoProduto());
        }
        if (details.getFornecedor() != null && details.getFornecedor().getId() != null) {
            Optional<Fornecedor> optFornecedor = fornecedorRepository.findById(details.getFornecedor().getId());
            if (optFornecedor.isEmpty()) {
                return ResponseEntity.badRequest().build();
            }
            produto.setFornecedor(optFornecedor.get());
        }

        Produto saved = produtoRepository.save(produto);
        return ResponseEntity.ok(toView(saved));
    }

    @DeleteMapping("/{sku}")
    public ResponseEntity<Void> delete(@PathVariable String sku) {
        Optional<Produto> opt = produtoRepository.findById(sku);
        if (opt.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        produtoRepository.deleteById(sku);
        return ResponseEntity.noContent().build();
    }

    private ProdutoView toView(Produto p) {
        String fornecedorNome = p.getFornecedor() != null ? p.getFornecedor().getNomeFornecedor() : null;
        return new ProdutoView(p.getSku(), p.getNomeProduto(), p.getPrecoProduto(), fornecedorNome);
    }
}