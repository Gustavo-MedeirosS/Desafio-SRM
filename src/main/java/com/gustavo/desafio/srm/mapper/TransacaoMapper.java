package com.gustavo.desafio.srm.mapper;

import com.gustavo.desafio.srm.domain.dto.transacao.TransacaoCriacaoDTO;
import com.gustavo.desafio.srm.domain.dto.transacao.TransacaoResponseDTO;
import com.gustavo.desafio.srm.domain.dto.transacao.TransacaoSubResponseDTO;
import com.gustavo.desafio.srm.domain.entity.Transacao;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.util.List;

@Mapper(componentModel = "spring", uses = {MoedaMapper.class, ReinoMapper.class, ItemTransacaoMapper.class})
public interface TransacaoMapper {

    @Mapping(source = "reinoId", target = "reino", qualifiedByName = "mapReinoIdToReino")
    @Mapping(source = "moedaDestinoId", target = "moedaDestino", qualifiedByName = "mapMoedaDestinoToMoeda")
    @Mapping(source = "moedaOrigemId", target = "moedaOrigem", qualifiedByName = "mapMoedaOrigemToMoeda")
    Transacao toEntity(TransacaoCriacaoDTO dto);

    @Named("toSubResponse")
    TransacaoSubResponseDTO toSubResponseDto(Transacao entity);

    @Named("toResponse")
    TransacaoResponseDTO toDto(Transacao entity);

    List<TransacaoResponseDTO> toDto(List<Transacao> entity);
}
