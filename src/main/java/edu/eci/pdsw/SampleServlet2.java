/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.eci.pdsw;

import edu.eci.pdsw.stubs.datasourcestub.DataSourceStub;
import java.io.IOException;
import java.io.Writer;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author sebas
 */

@WebServlet(urlPatterns = "/helloServlet2")

public class SampleServlet2 extends HttpServlet {
    
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Writer responseWriter=resp.getWriter();        
        if(req.getParameter("numId") != null) {
            try {
                Integer.parseInt(req.getParameter("numId"));
                DataSourceStub dss = DataSourceStub.getInstance();                
                try {
                    dss.getClientById(Integer.parseInt(req.getParameter("numId")));
                    resp.setStatus(200);
                    resp.setHeader("prueba", "201");
                } catch (Exception e) {                    
                    responseWriter.write("La persona con identificacion numero: " + req.getParameter("numId") + " no existe");
                    resp.setStatus(400);
                    resp.setHeader("prueba", "401");
                }                
            } catch(Exception e) {                
                responseWriter.write("El parametro que entro no es un entero");
            }           
        } else {
            responseWriter.write("No ha ingresado ningún numero de identificacion");
        }
        //responseWriter.write("Hola! "+(req.getParameter("numId")!=null?req.getParameter("numId"):""));
        responseWriter.flush();
    }
}
