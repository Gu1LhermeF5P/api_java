package br.com.motoscan.api.controller;

import br.com.motoscan.api.model.Moto;
import br.com.motoscan.api.repository.MotoRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/motos")
public class MotoController {

    @Autowired
    private MotoRepository motoRepository;

    @GetMapping
    public List<Moto> listarMotos() {
        return motoRepository.findAll();
    }

    @PostMapping
    public Moto criarMoto(@RequestBody Moto moto) {
        return motoRepository.save(moto);
    }

    @PutMapping("/{placa}")
    @Transactional
    public ResponseEntity<Moto> atualizarMoto(@PathVariable String placa, @RequestBody Moto dadosAtualizados) {
        Optional<Moto> motoOptional = motoRepository.findByPlaca(placa);

        if (motoOptional.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        Moto motoExistente = motoOptional.get();
        motoExistente.setModelo(dadosAtualizados.getModelo());
        motoExistente.setZona(dadosAtualizados.getZona());
        motoExistente.setFalhaMecanica(dadosAtualizados.isFalhaMecanica());
        motoExistente.setMulta(dadosAtualizados.isMulta());
        motoExistente.setRoubada(dadosAtualizados.isRoubada());

        Moto motoSalva = motoRepository.save(motoExistente);

        return ResponseEntity.ok(motoSalva);
    }

    
    @DeleteMapping("/{placa}")
    @Transactional
    public ResponseEntity<Void> deletarMoto(@PathVariable String placa) {
        
        if (motoRepository.findByPlaca(placa).isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        motoRepository.deleteByPlaca(placa);
      
        return ResponseEntity.noContent().build();
    }
}