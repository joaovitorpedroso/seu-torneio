package com.java.development.services;

import com.java.development.entities.*;
import com.java.development.entities.dto.ContratosEquipeDtoResponse;
import com.java.development.entities.dto.EquipeDtoRequest;
import com.java.development.repositories.*;
import com.java.development.services.faker.EquipeFaker;
import com.java.development.services.faker.JogadorFaker;
import com.java.development.services.faker.TecnicoFaker;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class AdminService {

    @Autowired
    EquipeRepository equipeRepository;

    @Autowired
    JogadorRepository jogadorRepository;

    @Autowired
    PosicaoRepository posicaoRepository;

    @Autowired
    ContratoJogadorRepository contratoJogadorRepository;

    @Autowired
    ContratoTecnicoRepository contratoTecnicoRepository;

    @Autowired
    TecnicoRepository tecnicoRepository;

    @Transactional
    public ContratosEquipeDtoResponse criarEquipeCompleta(EquipeDtoRequest equipe){
        Equipe equipeFake = EquipeFaker.criarEquipe(equipe.toDtoResponse(equipe));
        Equipe equipeCriada = equipeRepository.save(equipeFake);
        List<Jogador> jogadoresCriados = criarJogadoresFakes();
        criarContratosFakes(equipeCriada,jogadoresCriados);
        Tecnico tecnicoCriado = criarTecnicoFake();
        criarContratoTecnico(equipeCriada,tecnicoCriado);
        return ContratosEquipeDtoResponse.toDtoResponseList(equipeCriada,contratoJogadorRepository.findByIdEquipe(equipeCriada.getIdEquipe()).stream().toList(),
                contratoTecnicoRepository.findByIdEquipe(equipeCriada.getIdEquipe()).stream().toList());
    }

    public List<Jogador> criarJogadoresFakes(){
        List<Jogador> jogadoresCriados = new ArrayList<>();
        //Goleiro
        jogadoresCriados.add(criarJogadorPorPosicao(1L));
        //Zagueiros
        jogadoresCriados.add(criarJogadorPorPosicao(2L));
        jogadoresCriados.add(criarJogadorPorPosicao(2L));
        //Lateral esquerdo
        jogadoresCriados.add(criarJogadorPorPosicao(3L));

        //Lateral direito
        jogadoresCriados.add(criarJogadorPorPosicao(4L));

        //Volante
        jogadoresCriados.add(criarJogadorPorPosicao(5L));
        jogadoresCriados.add(criarJogadorPorPosicao(5L));
        //Meia armador
        jogadoresCriados.add(criarJogadorPorPosicao(6L));

        //Meia esquerda
        jogadoresCriados.add(criarJogadorPorPosicao(7L));

        //Meia direita
        jogadoresCriados.add(criarJogadorPorPosicao(8L));


        //Centro avante
        jogadoresCriados.add(criarJogadorPorPosicao(12L));

        //Goleiro reserva
        jogadoresCriados.add(criarJogadorPorPosicao(1L));

        //Zagueiros reservas
        jogadoresCriados.add(criarJogadorPorPosicao(2L));
        jogadoresCriados.add(criarJogadorPorPosicao(2L));
        //Lateral esquerdo reserva
        jogadoresCriados.add(criarJogadorPorPosicao(3L));

        //Lateral direito reserva
        jogadoresCriados.add(criarJogadorPorPosicao(4L));

        //Volante reserva
        jogadoresCriados.add(criarJogadorPorPosicao(5L));

        //Meia armador reserva
        jogadoresCriados.add(criarJogadorPorPosicao(6L));

        //Ponta direita
        jogadoresCriados.add(criarJogadorPorPosicao(9L));

        //Ponta esquerda
        jogadoresCriados.add(criarJogadorPorPosicao(10L));

        //Segundo atacante
        jogadoresCriados.add(criarJogadorPorPosicao(11L));

        //Centro avante
        jogadoresCriados.add(criarJogadorPorPosicao(12L));


        return  jogadoresCriados;
    }

    public Jogador criarJogadorPorPosicao(Long idPosicao) {
        return jogadorRepository.save(JogadorFaker.criarJogadorPorPosicao(posicaoRepository.findById(idPosicao).stream().toList().get(0)));
    }

    public Tecnico criarTecnicoFake() {
        return tecnicoRepository.save(TecnicoFaker.criarTecnico());
    }

    public List<ContratoJogador> criarJogadoresEContratos(){
        List<Jogador> jogadores = criarJogadoresFakes();
        List<ContratoJogador> contratos = new ArrayList<>();
        Equipe equipe = obterEquipePorId(2L);
        for(Jogador jogador : jogadores){
            Calendar dtFimContrato = Calendar.getInstance();
            dtFimContrato.add(Calendar.YEAR, 2);
            ContratoJogador contratoJogador = new ContratoJogador();
            contratoJogador.setJogador(jogador);
            contratoJogador.setNumeroJogador(new Random().nextInt(100));
            contratoJogador.setStatus("Vigente");
            contratoJogador.setDtInicio(new Date());
            contratoJogador.setDtFim(dtFimContrato.getTime());
            contratoJogador.setEquipe(equipe);
            contratoJogadorRepository.save(contratoJogador);
            contratos.add(contratoJogador);
        }
        return contratos;
    }

    public List<ContratoJogador> criarContratosFakes(Equipe equipe, List<Jogador> jogadores){
        List<ContratoJogador> contratos = new ArrayList<>();
        //TODO melhorar lógica para criação de números, para que não ocorram repetições
        for(Jogador jogador : jogadores){
            Calendar dtFimContrato = Calendar.getInstance();
            dtFimContrato.add(Calendar.YEAR, 2);
            ContratoJogador contratoJogador = new ContratoJogador();
            contratoJogador.setJogador(jogador);
            contratoJogador.setNumeroJogador(new Random().nextInt(100));
            contratoJogador.setStatus("Vigente");
            contratoJogador.setDtInicio(new Date());
            contratoJogador.setDtFim(dtFimContrato.getTime());
            contratoJogador.setEquipe(equipe);
            contratoJogadorRepository.save(contratoJogador);
            contratos.add(contratoJogador);
        }
        return contratos;
    }

    public void criarContratoTecnico(Equipe equipe, Tecnico tecnico){
        Calendar dtFimContrato = Calendar.getInstance();
        dtFimContrato.add(Calendar.YEAR, 2);
        ContratoTecnico contratoTecnico = new ContratoTecnico();
        contratoTecnico.setTecnico(tecnico);
        contratoTecnico.setStatus("Vigente");
        contratoTecnico.setDtInicio(new Date());
        contratoTecnico.setDtFim(dtFimContrato.getTime());
        contratoTecnico.setEquipe(equipe);
        contratoTecnicoRepository.save(contratoTecnico);
    }

    public Equipe obterEquipePorId(Long idEquipe) {
        return equipeRepository.findById(idEquipe).stream().toList().get(0);
    }

}
