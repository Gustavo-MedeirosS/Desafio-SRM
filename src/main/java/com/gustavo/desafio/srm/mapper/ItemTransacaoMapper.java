package com.gustavo.desafio.srm.mapper;

import com.gustavo.desafio.srm.domain.dto.item_transacao.ItemTransacaoResponseDTO;
import com.gustavo.desafio.srm.domain.entity.ItemTransacao;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring", uses = {TransacaoMapper.class})
public interface ItemTransacaoMapper {

    @Mapping(source = "transacao", target = "transacao", qualifiedByName = "toSubResponse")
    ItemTransacaoResponseDTO toDto(ItemTransacao entity);

    List<ItemTransacaoResponseDTO> toDto(List<ItemTransacao> entity);
}
