package com.brook;

import model.Contato;

import java.util.Calendar;

public class TotaInsere {
    public static void main (String[] args){

        Contato contato = new Contato();
        contato.setNome("Luneki");
        contato.setEmail("luneki@luneki.com");
        contato.setEndereco("Luneki City");
        contato.setDataNascimento(Calendar.getInstance());

        ContatoDAO dao = new ContatoDAO();
        dao.adiciona(contato);
    }
}
