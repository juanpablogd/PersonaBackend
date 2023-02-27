package com.alphai.persona.persistencia.crud;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import com.alphai.persona.persistencia.entidad.Producto;

public interface ProductoCrudRepositorio extends JpaRepository<Producto, Integer> {
	
	public List<Producto> findByIdCategoriaOrderByNombreAsc(int idCategoria);
	
	public Optional<List<Producto>> findByCantidadStockLessThanAndEstado(int cantidadStock, boolean estado);
	
	public Optional<List<Producto>> findByNombreAndPrecioVentaGreaterThanAndEstado(String nombre,double precioVenta,boolean estado);
	
}
