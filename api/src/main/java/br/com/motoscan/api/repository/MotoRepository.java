package br.com.motoscan.api.repository;

import br.com.motoscan.api.model.Moto;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface MotoRepository extends JpaRepository<Moto, Long> {
    // O Spring Data JPA cria a implementação desses métodos para nós!
    Optional<Moto> findByPlaca(String placa);
    void deleteByPlaca(String placa);
}