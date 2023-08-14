package com.brook;

import connection.ConnectionFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Calendar;

public class ContatoInsert {
    public static void main(String[] args) throws SQLException {
        Connection conn = null;

        try {
            conn = new ConnectionFactory().getConnection();

            String sql = "INSERT INTO contatos"+
                    " (nome, email, endereco, dataNascimento)"+
                    " VALUES (?, ?, ?, ?)";

            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, "Lucas");
            stmt.setString(2, "lucasribeiro1024b@gmail.com");
            stmt.setString(3, "Tianguá, CE");

            Calendar dataGravar = Calendar.getInstance();
            dataGravar.set(2023, Calendar.NOVEMBER, 27);

            stmt.setDate(4, new java.sql.Date(dataGravar.getTimeInMillis()));


        } catch (SQLException e) {
            System.out.println(e);
        } finally {
            if (conn != null) {
                conn.close();
            }
        }
    }
}
