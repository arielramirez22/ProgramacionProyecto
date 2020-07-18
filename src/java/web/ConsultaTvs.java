package web;

import entidades.DB;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "ConsultaTvs", urlPatterns = {"/ConsultaTvs"})
public class ConsultaTvs extends HttpServlet {

protected void processRequest(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {
        PrintWriter out = response.getWriter();
        response.setContentType("text/html;charset=UTF-8");
        out.println("<!DOCTYPE html>\n" +
            "<html>\n" +
            "<head>\n" +
            "<meta charset='utf-8'>\n" +
            "<meta http-equiv='X-UA-Compatible' content='IE=edge'>\n" +
            "<title>Listado de productos</title>\n" +
            "<meta name='viewport' content='width=device-width, initial-scale=1'>\n" +
            "<link rel='stylesheet' type='text/css' media='screen' href='css/productos.css.css'>\n" +
            "<script src='main.css'></script>\n" +
            "</head>\n" +
            "<body>\n" +
            "<header>\n"+
	    "<div class=\"navWrapper\" id=\"home\">\n"+
	    "<div class=\" clearfix\">\n"+
	    "<h2 class=\"companyName\">E-COMMERCE</h2>\n"+
	    "<nav class=\"mainNav clearfix\">\n"+
	    "<ul>\n"+
	    "<li><a href=\"#home\">Inicio</a></li>\n"+
	    "<li><a href=\"#work\" class=\"smoothScroll\">Categoria</a></li>\n"+
	    "<li><a href=\"#about\" class=\"smoothScroll\">Contacto</a></li>\n"+
	    "<li><a href=\"#contact\" class=\"smoothScroll\">Salir</a></li>\n"+
	    "</ul>\n"+
	    "</nav>\n"+
	    "</div>\n"+
	    "</div>\n"+
            "</header>\n" +
            "<main>"
            +"<div class=\"wrap category-page\" ng-controller=\"Application.Controllers.Category.ProductList\">");
                    
        String consultaSql = "SELECT * FROM productos ORDER BY id DESC LIMIT 25";
        Connection miConexion = null;
        try {
        miConexion = DB.getInstance().getConnection();
        PreparedStatement miPreparativo = miConexion.prepareStatement(consultaSql);
        ResultSet miResultado =  miPreparativo.executeQuery();
        
        while (miResultado.next()) {
        Producto miProducto = new Producto();
        miProducto.nombre = miResultado.getString("nombre");
        miProducto.precio = miResultado.getString("precio");
        miProducto.cantidad = miResultado.getString("cantidad");
        miProducto.descripcion = miResultado.getString("descripcion");
        miProducto.foto = miResultado.getString("foto");
        System.out.println(miProducto);
               
                
           out.println("<div class=\"list\">\n" +
        "<div class=\"product\">\n" +
        "<img alt=\"shoes1\" src="+miProducto.foto+">\n" +
        "<div><h2>"+miProducto.nombre+"</h2>\n" +
        "<p class=\"price\">Precio:$" +miProducto.precio+"<sup>"+"  Und: "+miProducto.cantidad+"</sup></p>\n" +
        "<p class=\"descr\">Descripcion: <b> " +miProducto.descripcion+"</b></p>\n" +
        "<br>\n" +
        "<a href=\"#\"><p>Agregar al carrito</p></a></div>\n" +
        "</div>\n" +
        "</div>\n" +
        "</section>");
            }
            out.println("</div>");
            
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
            out.println("</main>\n" +
            "<footer></footer>\n" +
            "<body>\n" +
            "</html>");

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

