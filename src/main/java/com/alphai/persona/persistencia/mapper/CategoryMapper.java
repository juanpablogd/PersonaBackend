package com.alphai.persona.persistencia.mapper;

import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import com.alphai.persona.dominio.Category;
import com.alphai.persona.persistencia.entidad.Categoria;

@Mapper(componentModel = "spring")
public interface CategoryMapper {
	@Mappings({
			@Mapping(source = "idCategoria", target = "categoryId"),
			@Mapping(source = "descripcion", target = "category"),
			@Mapping(source = "estado", target = "active"),
	})
	Category toCategory(Categoria categoria);

	@InheritInverseConfiguration
	@Mapping(target = "productos", ignore = true)
	Categoria toCategoria(Category category);
}
