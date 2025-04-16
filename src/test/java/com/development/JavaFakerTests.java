package com.development;

import com.github.javafaker.Faker;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.TimeUnit;

@RunWith(SpringRunner.class)
class JavaFakerTests {

    @Test
    void exibirNomes() throws ParseException {
        Faker faker = new Faker();

        String nome = faker.name().firstName();
        System.out.println("nome " + nome);
        Date past = faker.date().past(14610, TimeUnit.DAYS);
        Date future = faker.date().past(5844, TimeUnit.DAYS);
        System.out.println("past " + past);
        System.out.println("future " + future);
        Date dateNascimento = faker.date().between(past,future);
        System.out.println("dateNascimento " + dateNascimento);
    }

    void criarDataNascimento() throws ParseException {
        Calendar c = Calendar.getInstance();
        Faker faker = new Faker();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date date1 = sdf.parse(""+String.valueOf(c.get(Calendar.YEAR))+"-"+String.valueOf(c.get(Calendar.DAY_OF_MONTH))+"-"+String.valueOf(c.get(Calendar.DATE))+"");
        Date date2 = sdf.parse(""+String.valueOf(c.get(Calendar.YEAR))+"-"+createRandomIntBetween(1, 12)+"-"+createRandomIntBetween(1, 28)+"");

        System.out.println("date1 "+ date1);
        System.out.println("date2 "+ date2);
        System.out.println("date2 "+ date2);
        

    }

    public static int createRandomIntBetween(int start, int end) {
        return start + (int) Math.round(Math.random() * (end - start));
    }
}
