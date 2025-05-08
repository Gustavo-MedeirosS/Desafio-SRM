package com.gustavo.desafio.srm.mapper;

import com.gustavo.desafio.srm.domain.dto.produto.ProdutoCriacaoDTO;
import com.gustavo.desafio.srm.domain.dto.produto.ProdutoResponseDTO;
import com.gustavo.desafio.srm.domain.entity.Produto;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ProdutoMapper {

    Produto toEntity(ProdutoCriacaoDTO dto);

    List<Produto> toEntity(List<ProdutoCriacaoDTO> dtos);

    ProdutoResponseDTO toDto(Produto entity);

    List<ProdutoResponseDTO> toDto(List<Produto> entities);
}
