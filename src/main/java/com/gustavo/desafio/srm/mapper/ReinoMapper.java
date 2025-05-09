package com.gustavo.desafio.srm.mapper;

import com.gustavo.desafio.srm.domain.dto.reino.ReinoCriacaoDTO;
import com.gustavo.desafio.srm.domain.dto.reino.ReinoResponseDTO;
import com.gustavo.desafio.srm.domain.entity.Reino;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.util.List;

@Mapper(componentModel = "spring", uses = MoedaMapper.class)
public interface ReinoMapper {
    @Named("mapReinoIdToReino")
    static Reino mapReinoIdToReino(Integer id) {
        Reino entity = new Reino();
        entity.setId(id);
        return entity;
    }

    @Mapping(source = "moedaId", target = "moeda", qualifiedByName = "mapMoedaIdToMoeda")
    Reino toEntity(ReinoCriacaoDTO dto);

    ReinoResponseDTO toDto(Reino entity);

    List<ReinoResponseDTO> toDto(List<Reino> entity);
}
