package com.GerenciamentoHP.Controller.mappers;

import com.GerenciamentoHP.Controller.DTO.PacientePerfilDto;
import com.GerenciamentoHP.Controller.DTO.ResultadoPesquisaPacienteDTO;
import com.GerenciamentoHP.Model.PacientePerfil;
import java.time.LocalDate;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-07-31T21:11:41-0300",
    comments = "version: 1.6.3, compiler: javac, environment: Java 21.0.6 (Oracle Corporation)"
)
@Component
public class PacienteMapperImpl implements PacienteMapper {

    @Override
    public PacientePerfil toEntity(PacientePerfilDto dto) {
        if ( dto == null ) {
            return null;
        }

        PacientePerfil pacientePerfil = new PacientePerfil();

        pacientePerfil.setNome( dto.nome() );
        pacientePerfil.setRg( dto.rg() );
        pacientePerfil.setDataNascimento( dto.dataNascimento() );

        return pacientePerfil;
    }

    @Override
    public PacientePerfilDto toDTO(PacientePerfil pacientePerfil) {
        if ( pacientePerfil == null ) {
            return null;
        }

        String nome = null;
        String rg = null;
        LocalDate dataNascimento = null;

        nome = pacientePerfil.getNome();
        rg = pacientePerfil.getRg();
        dataNascimento = pacientePerfil.getDataNascimento();

        PacientePerfilDto pacientePerfilDto = new PacientePerfilDto( nome, rg, dataNascimento );

        return pacientePerfilDto;
    }

    @Override
    public ResultadoPesquisaPacienteDTO toEntity(PacientePerfil pacientePerfil) {
        if ( pacientePerfil == null ) {
            return null;
        }

        String nome = null;
        Long atendimento = null;
        String rg = null;
        LocalDate dataNascimento = null;
        LocalDate dataCadastro = null;
        LocalDate ultimaConsulta = null;

        nome = pacientePerfil.getNome();
        atendimento = pacientePerfil.getAtendimento();
        rg = pacientePerfil.getRg();
        dataNascimento = pacientePerfil.getDataNascimento();
        dataCadastro = pacientePerfil.getDataCadastro();
        ultimaConsulta = pacientePerfil.getUltimaConsulta();

        ResultadoPesquisaPacienteDTO resultadoPesquisaPacienteDTO = new ResultadoPesquisaPacienteDTO( nome, atendimento, rg, dataNascimento, dataCadastro, ultimaConsulta );

        return resultadoPesquisaPacienteDTO;
    }
}
