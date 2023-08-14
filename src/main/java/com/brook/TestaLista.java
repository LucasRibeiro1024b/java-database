package com.brook;

import model.Contato;
import dao.ContatoDAO;

import java.text.SimpleDateFormat;
import java.util.List;

public class TestaLista {
    public static void main(String[] args) {
        ContatoDAO dao = new ContatoDAO();
        List<Contato> contatos = dao.getLista();

        for (Contato contato : contatos) {
            System.out.println("Nome: " + contato.getNome());
            System.out.println("Email: " + contato.getEmail());
            System.out.println("Endereco: " + contato.getEndereco());
            System.out.println("Data de Nascimento: " + contato.getDataNascimento().getTime());
            System.out.println(new SimpleDateFormat("yyyy/MM/dd hh:mm.ss").format(contato.getDataNascimento().getTime()));
            System.out.println("\n");
        }
    }
}
