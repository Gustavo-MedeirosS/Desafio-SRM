package com.gustavo.desafio.srm.mapper;

import com.gustavo.desafio.srm.domain.dto.taxa_cambio.TaxaCambioCriacaoDTO;
import com.gustavo.desafio.srm.domain.dto.taxa_cambio.TaxaCambioResponseDto;
import com.gustavo.desafio.srm.domain.entity.TaxaCambio;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = {MoedaMapper.class, ProdutoMapper.class})
public interface TaxaCambioMapper {

    @Mapping(source = "novaTaxa", target = "taxa")
    TaxaCambio toEntity(TaxaCambioCriacaoDTO dto);


    TaxaCambioResponseDto toDto(TaxaCambio entity);
}
