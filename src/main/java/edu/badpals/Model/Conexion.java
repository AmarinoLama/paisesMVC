package edu.badpals.Model;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Conexion {

    private static Connection connectDatabase() {

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            String url = "jdbc:mysql://localhost:3306/mundo";
            return DriverManager.getConnection(url, "root", "root");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return null;
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public void getPaises() {
        try {
            Connection conexion = connectDatabase();
            Statement s = conexion.createStatement();
            ResultSet rs = s.executeQuery("SELECT * FROM paises");
            List<Pais> paises = new ArrayList<>();
            while (rs.next()) {
                Pais pais = new Pais(rs.getString(1),rs.getInt(2),rs.getString(3),rs.getString(4));
                paises.add(pais);
                System.out.println(pais);
            }
            rs.close();
            s.close();
            conexion.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public boolean insertPais(Pais pais){
        try{
            Connection conexion = connectDatabase();
            PreparedStatement s = conexion.prepareStatement("INSERT INTO Paises (nombre_pais, habitantes, capital, moneda)  " +
                    " VALUES (?,?,?,?)");
            s.setString(1,pais.getNombre_pais());
            s.setInt(2,pais.getNumero_habitantes());
            s.setString(3,pais.getNombre_capital());
            s.setString(4,pais.getNombre_moneda());
            s.executeUpdate();
            conexion.close();
            return true;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return false;
        }

    }

    public boolean modificarPais(String nombre, Pais pais) {
        String sql = "UPDATE paises SET nombre_pais = ?, habitantes = ?, capital = ?, moneda = ? WHERE nombre_pais = ?";
        try {
            Connection conexion = connectDatabase();
            PreparedStatement statement = conexion.prepareStatement(sql);
            statement.setString(1, pais.getNombre_pais());
            statement.setInt(2, pais.getNumero_habitantes());
            statement.setString(3, pais.getNombre_capital());
            statement.setString(4, pais.getNombre_moneda());
            statement.setString(5, nombre);
            int rowsUpdated = statement.executeUpdate();
            conexion.close();
            return rowsUpdated > 0;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return false;
        }
    }

    public void borrarPais(String nombre) {
        try{
            Connection conexion = connectDatabase();
            PreparedStatement s = conexion.prepareStatement("DELETE FROM Paises WHERE nombre_pais = ?");
            s.setString(1,nombre);
            s.executeUpdate();
            conexion.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
