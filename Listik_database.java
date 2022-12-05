package com.test.idea.third;

import com.mysql.cj.jdbc.Driver;
import com.test.idea.EXL;
import com.test.idea.sql;
import java.sql.*;
import java.util.Arrays;
import java.util.Scanner;

public class Listik_database {
    public static void main(String[] args) throws SQLException, ClassNotFoundException {

        Scanner scan = new Scanner(System.in);

        int x = 0;
        String s = "";

        System.out.println("Введите название таблицы: ");
        String tablename = scan.nextLine();

        while (!"6".equals(s)) {
            System.out.println();
            System.out.println("1. Вывести все таблицы из текущей БД.");
            System.out.println("2. Создать таблицу в БД.");
            System.out.println("3. Добавить данные в таблицу.");
            System.out.println("4. Сохранить данные в Excel.");
            System.out.println("5. Найти элемент по ID.");
            System.out.println("6. Выйти из программы.");
            s = scan.next();

            try {
                x = Integer.parseInt(s);
            }
            catch (NumberFormatException e) {
                System.out.println("Неверный формат ввода.");
            }

            switch (x) {

                case 1 -> {
                    sql.TablesOutput();
                }

                case 2 -> {
                    String query = " (string1 TEXT, string2 TEXT)";
                    sql.CreatingSQLTable(tablename, query);
                }

                case 3 -> {

                    DriverManager.registerDriver(new Driver());
                    Class.forName("com.mysql.cj.jdbc.Driver");
                    Connection con2 = DriverManager.getConnection("jdbc:mysql://localhost:3306/mysql", "root", "кщще");
                    System.out.println("Успешно законнектились к БД!");

                    scan.nextLine();

                    String query2 = "INSERT INTO " + tablename +
                            " (string1, string2)" + " VALUES (?, ?);";

                    PreparedStatement stmt3 = con2.prepareStatement(query2);

                    stmt3.setString(1, String.valueOf((Listik.random())));
                    stmt3.setString(2, String.valueOf((Listik.input())));
                    stmt3.executeUpdate();

                    ResultSet rs2 = stmt3.executeQuery("SELECT * from " + tablename + "");
                    System.out.println("Введенные данные: ");
                    while (rs2.next()) {
                        System.out.print(Arrays.toString(rs2.getString((1)).split(",")));
                        System.out.print(Arrays.toString(rs2.getString((2)).split(",")));

                        System.out.println();
                    }

                    System.out.println("Данные в MySQL успешно внесены!");

                    String query = "select count(*) from " + tablename;

                    try (Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/mysql", "root", "кщще");
                         Statement stmt = con.createStatement();
                         ResultSet rs = stmt.executeQuery(query)) {
                        while (rs.next()) {
                            int count = rs.getInt(1);
                            System.out.println("Всего внесено строк в таблицу " + tablename + " : " + count);
                        }
                    } catch (SQLException sqlEx) {
                        sqlEx.printStackTrace();
                    }
                }
                case 4 -> {
                    EXL.ExcelConvector(tablename);
                }

                case 5 -> {

                    try {
                        DriverManager.registerDriver(new Driver());
                        Class.forName("com.mysql.cj.jdbc.Driver");
                        Connection con5 = DriverManager.getConnection("jdbc:mysql://localhost:3306/mysql", "root", "кщще");

                        System.out.println("Введите ID:");
                        int id = scan.nextInt();

                        Statement stmt = con5.createStatement();
                        String query = "SELECT SUBSTRING(string1, " + id + ", " + id + ") FROM " + tablename + "";
                        ResultSet rs = stmt.executeQuery(query);
                        ResultSetMetaData rsmd = rs.getMetaData();
                        int columnsNumber = rsmd.getColumnCount();

                        while (rs.next()) {
                            for(int i = 1 ; i <= columnsNumber; i++){
                                System.out.print(rs.getString(i) + "\t");
                            }
                            System.out.println();
                        }
                    } catch (Exception e){
                        System.out.println(e);
                    }
                }

                case 6 -> {

                    try {
                        DriverManager.registerDriver(new Driver());
                        Class.forName("com.mysql.cj.jdbc.Driver");
                        Connection con6 = DriverManager.getConnection("jdbc:mysql://localhost:3306/mysql", "root", "кщще");

                        System.out.println("Введите ID:");
                        int id = scan.nextInt();

                        Statement stmt = con6.createStatement();
                        String query = "DELETE SUBSTRING(string1, " + id + ", " + id + ") FROM " + tablename + "";
                        stmt.executeUpdate(query);
                        System.out.println("Элемент с указанным ID удален из таблицы.");
                    } catch (Exception e){
                        System.out.println(e);
                    }
                }

                case 7 -> {
                    System.out.println("Вышли из нашей программы.");
                }
            }
        }
    }
}
