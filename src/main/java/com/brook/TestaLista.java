package com.brook;

import model.Contato;
import com.brook.ContatoDAO;

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
            System.out.println("\n");
        }
    }
}
