package com.gustavo.desafio.srm.mapper;

import com.gustavo.desafio.srm.domain.dto.produto.ProdutoCriacaoDTO;
import com.gustavo.desafio.srm.domain.dto.produto.ProdutoResponseDTO;
import com.gustavo.desafio.srm.domain.entity.Produto;
import com.gustavo.desafio.srm.domain.entity.Reino;
import jdk.jfr.Name;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.util.List;

@Mapper(componentModel = "spring", uses = {ReinoMapper.class})
public interface ProdutoMapper {

    @Mapping(source = "reinoId", target = "reino", qualifiedByName = "mapReinoIdToReino")
    Produto toEntity(ProdutoCriacaoDTO dto);

    List<Produto> toEntity(List<ProdutoCriacaoDTO> dtos);

    ProdutoResponseDTO toDto(Produto entity);

    List<ProdutoResponseDTO> toDto(List<Produto> entities);
}
