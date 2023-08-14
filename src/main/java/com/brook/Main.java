package com.brook;

import connection.ConnectionFactory;

import java.sql.Connection;
import java.sql.SQLException;

public class Main {
    public static void main(String[] args) throws SQLException {
        Connection conn = new ConnectionFactory().getConnection();
        System.out.println("Connection done!");
        conn.close();
    }
}