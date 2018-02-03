package com.cse5450.group5;

import java.sql.*;

public class Main {

    public static void main(String[] args) {
        try {
            Class.forName("org.h2.Driver");
            try {
                Connection dbConn = DriverManager.getConnection("jdbc:h2:~/cs5450", "sa", "");
                Statement db_statement = dbConn.createStatement();
                System.out.println("Creating CS5450 H2 Table...\n");
                String h2Table = "CREATE TABLE CS5450 " + "(entry_id INTEGER not NULL, " + " group_member VARCHAR(255))";
                db_statement.executeUpdate(h2Table);

                int id = 0;
                String[] memberArray = {"Zack", "Eric", "Arun", "Pankaj"};

                while (id < 1000) {
                    id++;
                    String randomMember = memberArray[(int)(Math.random() * memberArray.length)];
                    System.out.println("Inserting entry id: " + id + " into H2 Database for: " + randomMember );
                    String insertRecord = "INSERT INTO CS5450 " + "VALUES (" + id + ", '" + randomMember + "')";
                    db_statement.executeUpdate(insertRecord);
                }

                System.out.println("");
                System.out.println("Insert Random Group Member Results:");
                System.out.println("-----------------------------------");

                for(int i = 0; i <= memberArray.length - 1; i++) {
                    String member = memberArray[i];
                    String memberQuery = "SELECT * FROM CS5450 WHERE GROUP_MEMBER='" + member + "'";
                    ResultSet memberResult = db_statement.executeQuery(memberQuery);
                    int count = 0;
                    while (memberResult.next()) {
                        count++;
                    }
                    System.out.println("Number of entries for: " + member + " - " + count);
                }

                String dropTable = "DROP TABLE CS5450 ";
                System.out.println("\nCS5450 Table Dropped.");
                db_statement.executeUpdate(dropTable);
                dbConn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
