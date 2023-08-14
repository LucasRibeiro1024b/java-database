package com.brook;

import java.sql.*;

import connection.ConnectionFactory;
import model.Contato;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class ContatoDAO {
    private Connection connection;

    public ContatoDAO() {
        this.connection = new ConnectionFactory().getConnection();
    }

    public void adiciona(Contato contato) {
        String sql = "INSERT INTO contatos "
                    +"(nome, email, endereco, dataNascimento)"
                    +"VALUES (?,?,?,?)";

        try {
            PreparedStatement stmt = this.connection.prepareStatement(sql);

            stmt.setString(1, contato.getNome());
            stmt.setString(2, contato.getEmail());
            stmt.setString(3, contato.getEndereco());
            stmt.setDate(4, new Date(contato.getDataNascimento().getTimeInMillis()));

            stmt.execute();
            stmt.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void altera(Contato contato) {
        String sql = "UPDATE contatos SET nome = ?, email = ?, endereco = ?, dataNascimento = ? WHERE id = ?";

        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setString(1, contato.getNome());
            stmt.setString(2, contato.getEmail());
            stmt.setString(3, contato.getEndereco());
            stmt.setDate(4, new Date(contato.getDataNascimento().getTimeInMillis()));
            stmt.setLong(5, contato.getId());
            stmt.execute();
            stmt.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<Contato> getLista() {
        try {
            List<Contato> contatos = new ArrayList<Contato>();
            PreparedStatement stmt = this.connection.prepareStatement("SELECT * FROM contatos");
            ResultSet rset = stmt.executeQuery(); // Retorna todas as tuplas da pesquisa

            while (rset.next()) {
                Contato contato = new Contato();
                fillContato(contato, rset);

                contatos.add(contato);
                System.out.println(contato);
            }
            rset.close();
            stmt.close();
            return contatos;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Contato pesquisar(Long id) {
        Contato contato = new Contato();

        try {
            PreparedStatement stmt = this.connection.prepareStatement("SELECT * FROM contatos WHERE id = (?) LIMIT 1");
            stmt.setLong(1, id);
            ResultSet rset = stmt.executeQuery();

            while (rset.next()) {
                fillContato(contato, rset);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return contato;
    }

    private void fillContato(Contato contato, ResultSet rset) throws SQLException {
        contato.setId(rset.getLong("id"));
        contato.setNome(rset.getString("nome"));
        contato.setEmail(rset.getString("email"));
        contato.setEndereco(rset.getString("endereco"));

        Calendar data = Calendar.getInstance();
        data.setTime(rset.getDate("dataNascimento"));
        contato.setDataNascimento(data);
    }

}
