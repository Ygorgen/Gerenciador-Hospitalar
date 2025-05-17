package com.GerenciamentoHP.Controller.mappers;


import com.GerenciamentoHP.Controller.DTO.PacientePerfilDto;
import com.GerenciamentoHP.Model.PacientePerfil;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PacienteMapper {

    PacientePerfil toEntity(PacientePerfilDto dto);

    PacientePerfilDto toDTO(PacientePerfil pacientePerfil);
}
