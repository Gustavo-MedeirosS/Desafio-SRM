package com.gustavo.desafio.srm.mapper;

import com.gustavo.desafio.srm.domain.entity.Moeda;
import org.mapstruct.Mapper;
import org.mapstruct.Named;

@Mapper(componentModel = "spring")
public interface MoedaMapper {
    @Named("mapMoedaDestinoToMoeda")
    static Moeda mapMoedaDestinoToMoeda(Integer id) {
        Moeda entity = new Moeda();
        entity.setId(id);
        return entity;
    }

    @Named("mapMoedaDestinoToString")
    static String mapMoedaDestinoToString(Moeda entity) {
        return entity.getNome();
    }

    @Named("mapMoedaOrigemToString")
    static String mapMoedaOrigemToString(Moeda entity) {
        return entity.getNome();
    }
}
