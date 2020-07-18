/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package web;

import entidades.DB;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "ProductoNuevo", urlPatterns = {"/ProductoNuevo"})
public class ProductoNuevo extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        System.out.println("Estamos probando el Servlet");
        System.out.println("Producto: " + request.getParameter("nombre"));
        System.out.println("Descripcion: " + request.getParameter("descripcion"));
        System.out.println("Precio: " + request.getParameter("precio"));
        System.out.println("Foto: " + request.getParameter("foto"));
        System.out.println("Cantidad: " + request.getParameter("cantidad"));
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        Producto productoInsertar = new Producto();
        productoInsertar.nombre = request.getParameter("nombre");
        productoInsertar.precio = request.getParameter("precio");
        productoInsertar.cantidad = request.getParameter("cantidad");
        productoInsertar.descripcion = request.getParameter("descripcion");
        productoInsertar.foto = request.getParameter("foto");

        String consultaSql = "INSERT INTO `productos` (`nombre`, `precio`, `foto`, `cantidad`, `descripcion`) VALUES "
                + "(" + "'" + productoInsertar.nombre + "'" + ", "
                + "" + "'" + productoInsertar.precio + "'" + ", "
                + "" + "'" + productoInsertar.foto + "'" + ", "
                + "" + "'" + productoInsertar.cantidad + "'" + ", "
                + "" + "'" + productoInsertar.descripcion + "'" + ");";
        System.out.println(consultaSql);
        out.println("subir el producto nuevo!");
        
        Connection miConexion = null;
        try {
            miConexion = DB.getInstance().getConnection();
            PreparedStatement miPreparativo = miConexion.prepareStatement(consultaSql);
            miPreparativo.execute();
            out.println("se agrego");
            response.sendRedirect("http://localhost:8080/Prueba_1/ConsultaTvs");
        } catch (ClassNotFoundException miExepcion) {
            System.out.println(miExepcion);
        } catch (SQLException miExepcion) {
            System.out.println(miExepcion);
        } finally {
            try {
                if (miConexion != null) {
                    miConexion.close();
                }

            } catch (SQLException miExepcion) {
                System.out.println(miExepcion);
            }
        }

    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }

}
