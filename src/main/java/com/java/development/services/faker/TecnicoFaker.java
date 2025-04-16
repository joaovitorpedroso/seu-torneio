package com.java.development.services.faker;

import com.java.development.entities.Jogador;
import com.java.development.entities.Posicao;
import com.java.development.entities.Tecnico;

public class TecnicoFaker {

    public static Tecnico criarTecnico(){
        Tecnico tecnico = new Tecnico();
        tecnico.setDtNascTecnico(FakerUtil.gerarDataNascimentoTecnico());
        tecnico.setNomeTecnico(FakerUtil.gerarNome());
        return tecnico;
    }
}
