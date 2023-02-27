package com.alphai.persona.persistencia.crud;

import com.alphai.persona.persistencia.entidad.Compra;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface CompraCrudRepositorio extends JpaRepository<Compra,Integer> {
    Optional<List<Compra>> findByIdCliente(String idCliente);
}
