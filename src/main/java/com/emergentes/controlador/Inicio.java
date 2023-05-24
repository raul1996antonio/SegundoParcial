package com.emergentes.controlador;

import com.emergentes.dao.ProductoDAOimpl;
import com.emergentes.modelo.Producto;
import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.emergentes.dao.ProductoDAO;

@WebServlet(name = "Inicio", urlPatterns = {"/Inicio"})
public class Inicio extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        try {

            int id;
            Producto producto = new Producto();
            ProductoDAO dao = new ProductoDAOimpl();

            String action = (request.getParameter("action") != null) ? request.getParameter("action") : "view";

            switch (action) {
                case "add":
                    request.setAttribute("prod", producto);
                    request.getRequestDispatcher("frm_producto.jsp").forward(request, response);
                    break;

                case "edit":
                    id = Integer.parseInt(request.getParameter("id"));
                    producto = dao.getById(id);
                    request.setAttribute("prod", producto);
                    request.getRequestDispatcher("frm_producto.jsp").forward(request, response);
                    break;

                case "delete":
                    id = Integer.parseInt(request.getParameter("id"));
                    dao.delete(id);
                    response.sendRedirect("Inicio");
                    break;

                    /*caso terminado*/
                case "view":
                    List<Producto> lista = dao.getAll();
                    request.setAttribute("prod", lista);
                    request.getRequestDispatcher("index.jsp").forward(request, response);
                    break;

                default:
                    break;

            }

        } catch (Exception ex) {
            System.out.println("ERROR: " + ex.getMessage());
        }

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        try {

            ProductoDAO dao = new ProductoDAOimpl();

            int id = Integer.parseInt(request.getParameter("id"));
            String descripcion = request.getParameter("descripcion");
            int cantidad = Integer.parseInt(request.getParameter("cantidad"));
            float precio = Float.parseFloat(request.getParameter("precio"));
            String categoria = request.getParameter("categoria");
            

            Producto producto = new Producto();
            producto.setId(id);
            producto.setDescripcion(descripcion);
            producto.setCantidad(cantidad);
            producto.setPrecio(precio);
            producto.setCategoria(categoria);
            
            
            System.out.println("Objeto seminario" + producto.toString());

            if (id == 0) {
                dao.insert(producto);
                
            } else {
                dao.update(producto);
            }

        } catch (Exception e) {
            System.out.println("ERROR AL GUARDAR datos... doPost()" + e.getMessage());
        }

        response.sendRedirect("Inicio");

    }

}
