package com.gustavo.desafio.srm.mapper;

import com.gustavo.desafio.srm.domain.dto.moeda.MoedaCriacaoDTO;
import com.gustavo.desafio.srm.domain.dto.moeda.MoedaResponseDTO;
import com.gustavo.desafio.srm.domain.entity.Moeda;
import org.mapstruct.Mapper;
import org.mapstruct.Named;

import java.util.List;

@Mapper(componentModel = "spring")
public interface MoedaMapper {
    @Named("mapMoedaDestinoToMoeda")
    static Moeda mapMoedaDestinoToMoeda(Integer id) {
        Moeda entity = new Moeda();
        entity.setId(id);
        return entity;
    }

    @Named("mapMoedaOrigemToMoeda")
    static Moeda mapMoedaOrigemToMoeda(Integer id) {
        Moeda entity = new Moeda();
        entity.setId(id);
        return entity;
    }

    @Named("mapMoedaIdToMoeda")
    static Moeda mapMoedaIdToMoeda(Integer id) {
        Moeda entity = new Moeda();
        entity.setId(id);
        return entity;
    }

    Moeda toEntity(MoedaCriacaoDTO dto);

    MoedaResponseDTO toDto(Moeda dto);

    List<MoedaResponseDTO> toDto(List<Moeda> dto);
}
