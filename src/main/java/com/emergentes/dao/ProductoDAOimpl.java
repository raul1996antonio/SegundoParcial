package com.emergentes.dao;

import com.emergentes.modelo.Producto;
import com.emergentes.utiles.ConexionBD;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProductoDAOimpl extends ConexionBD implements ProductoDAO {

    @Override
    public void insert(Producto producto) throws Exception {
        try {
            this.conectar();
            String sql = "INSERT INTO productos(descripcion, cantidad, precio, categoria) VALUES(?, ?, ?, ?)";
            PreparedStatement ps = this.connection.prepareStatement(sql);
            ps.setString(1, producto.getDescripcion());
            ps.setInt(2, producto.getCantidad());
            ps.setFloat(3, producto.getPrecio());
            ps.setString(4, producto.getCategoria());

            ps.executeUpdate();

        } catch (SQLException e) {
            throw new Exception("Error INSERT: " + e.getMessage());
        } finally {
            this.desconectar();
        }

    }

    @Override
    public void update(Producto producto) throws Exception {
        try {
            this.conectar();
            String sql = "UPDATE productos SET descripcion=?, cantidad=?, precio=?, categoria=? WHERE id=?";
            PreparedStatement ps = this.connection.prepareStatement(sql);
            ps.setString(1, producto.getDescripcion());
            ps.setInt(2, producto.getCantidad());
            ps.setFloat(3, producto.getPrecio());
            ps.setString(4, producto.getCategoria());
            
            ps.setInt(5, producto.getId());
            
            ps.executeUpdate();

           

            
            
        } catch (SQLException e) {
            throw new Exception("Error al actualizar el producto: " + e.getMessage());
        } finally {
            this.desconectar();
        }
        
    }
   

    @Override
    public void delete(int id) throws Exception {
        try {
            this.conectar();
            String sql = "DELETE FROM productos WHERE id = ?";
            PreparedStatement ps = this.connection.prepareStatement(sql);
            ps.setInt(1, id);
            
            ps.executeUpdate();
            
            
        } catch (SQLException e) {
            System.out.println("ERROR EN SQL: " + e.getMessage());
            e.printStackTrace();
        } finally {
            this.desconectar();
        }

    }

    
    @Override
    public Producto getById(int id) throws Exception {
        Producto producto = new Producto();
        try {
            this.conectar();
            String sql = "SELECT * FROM productos WHERE id = ?";
            PreparedStatement ps = this.connection.prepareStatement(sql);
            ps.setInt(1, id);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                producto.setId(rs.getInt("id"));
                producto.setDescripcion(rs.getString("descripcion"));
                producto.setCantidad(rs.getInt("cantidad"));
                producto.setPrecio(rs.getFloat("precio"));
                producto.setCategoria(rs.getString("categoria"));
            }

        } catch (SQLException e) {
            System.out.println("ERROR EN SQL: " + e.getMessage());
            e.printStackTrace();
            
        } finally {
            this.desconectar();
            
        }

        return producto;
    }

    @Override
    public List<Producto> getAll() throws Exception {
        ArrayList<Producto> lista = new ArrayList<>();
        try {
            this.conectar();
            String sql = "SELECT * FROM productos";
            PreparedStatement ps = this.connection.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Producto producto = new Producto();
                producto.setId(rs.getInt("id"));
                producto.setDescripcion(rs.getString("descripcion"));
                producto.setCantidad(rs.getInt("cantidad"));
                producto.setPrecio(rs.getFloat("precio"));
                producto.setCategoria(rs.getString("categoria"));
                
                lista.add(producto);
            }

            rs.close();
            ps.close();

        } catch (Exception e) {
            System.out.println("ERROR EN SQL: " + e.getMessage());
            e.printStackTrace();
            
        } finally {
            this.desconectar();
        }

        return lista;

    }

}
