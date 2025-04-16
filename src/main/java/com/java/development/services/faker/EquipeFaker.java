package com.java.development.services.faker;

import com.java.development.entities.Equipe;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class EquipeFaker extends Equipe {


    public static Equipe criarEquipe(Equipe equipe){
        equipe.setNomeEquipe(equipe.getNomeEquipe());
        equipe.setFundacao(new Date());
        equipe.setPresidente(FakerUtil.gerarNome());
        return equipe;
    }
}
