package com.gustavo.desafio.srm.mapper;

import com.gustavo.desafio.srm.domain.dto.reino.ReinoCriacaoDTO;
import com.gustavo.desafio.srm.domain.dto.reino.ReinoResponseDTO;
import com.gustavo.desafio.srm.domain.entity.Reino;
import org.mapstruct.Mapper;
import org.mapstruct.Named;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ReinoMapper {
    @Named("mapReinoIdToReino")
    static Reino mapReinoIdToReino(Integer id) {
        Reino entity = new Reino();
        entity.setId(id);
        return entity;
    }

//    @Named("mapReinoToReinoResponse")
//    static ReinoResponseDTO mapReinoToReinoResponse(Reino entity) {
//        ReinoResponseDTO dto = new ReinoResponseDTO();
//        dto.setNome(entity.getNome());
//        return dto;
//    }

    Reino toEntity(ReinoCriacaoDTO dto);

    ReinoResponseDTO toDto(Reino entity);

    List<ReinoResponseDTO> toDto(List<Reino> entity);
}
