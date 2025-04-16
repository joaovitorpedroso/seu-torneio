package com.java.development.services.faker;

import com.github.javafaker.Faker;

import java.util.Date;
import java.util.Random;
import java.util.concurrent.TimeUnit;

public class FakerUtil {

    private static final Faker faker;

    static {
        faker = new Faker();
    }

    public static String gerarNome(){
        return faker.name().fullName();
    }

    public static String gerarNomeSimples(){
        return faker.name().firstName();
    }

    public static Date gerarDataNascimentoJogador(){
        return faker.date().past(14610,5844,TimeUnit.DAYS);
    }

    public static Date gerarDataNascimentoTecnico(){
        return faker.date().past(36525,5844,TimeUnit.DAYS);
    }

    public static String gerarPeDominante(){
        int number = new Random().nextInt(11);
        if (number<6)
            return "Destro";
        if(number>8)
            return "Ambas";
        return "Canhoto";
    }
}
