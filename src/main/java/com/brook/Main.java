package com.brook;

import connection.ConnectionFactory;

import java.sql.Connection;

public class Main {
    public static void main(String[] args) {
        Connection conn = new ConnectionFactory().getConnection();
        System.out.println("Connection done!");
    }
}