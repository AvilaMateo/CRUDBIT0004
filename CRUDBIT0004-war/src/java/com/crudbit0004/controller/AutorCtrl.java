/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.crudbit0004.controller;

import com.crudbit004.entities.Autor;
import com.crudbit004.model.AutorFacade;
import java.io.Serializable;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

/**
 *
 * @author MATEO AVILA
 */
@Named(value = "autorCtrl")
@SessionScoped
public class AutorCtrl implements Serializable {

    @EJB
    private AutorFacade autorFacade;
    private Autor autor;
    String mensaje = "";

    public AutorCtrl() {
        this.autor = new Autor();
    }

    public Autor getAutor() {
        return autor;
    }

    public void setAutor(Autor autor) {
        this.autor = autor;
    }

    //metodo para listar todo los autores 
    public List<Autor> ListAllAutor() {
        return autorFacade.findAll();
    }

    //metodo para agregar unautores 
    public void AddAutor() {
        try {
            this.autorFacade.create(autor);
            this.autor = new Autor();
            this.mensaje = "Informacion Registrada";
        } catch (Exception e) {
            e.printStackTrace();
            this.mensaje = "Error: " + e.getMessage();
        }
        FacesMessage mens = new FacesMessage(this.mensaje);
        FacesContext.getCurrentInstance().addMessage(null, mens);

    }

//preparamos los datos del autor a editar
    public void SelectEditAutor(Autor aut) {
        this.autor = aut;
    }

    //metodo para editar un autores 
    public void EditAutor() {
        try {
            this.autorFacade.edit(autor);
            this.autor = new Autor();
            this.mensaje = "Información Actualizada con Exito!";
        } catch (Exception e) {
            e.printStackTrace();
            this.mensaje = "Error: " + e.getMessage();
        }
        FacesMessage mens = new FacesMessage(this.mensaje);
        FacesContext.getCurrentInstance().addMessage(null, mens);

    }

    //eliminar un alumno
    public void DeleteAutor(Autor aut) {
        try {
            this.autorFacade.remove(aut);
            this.autor = new Autor();
            this.mensaje = "Informacion Eliminada con Exito!";
        } catch (Exception e) {
            e.printStackTrace();
            this.mensaje = "Error: " + e.getMessage();
        }
        FacesMessage mens = new FacesMessage(this.mensaje);
        FacesContext.getCurrentInstance().addMessage(null, mens);

    }
    public void limpiar(){
          this.autor = new Autor();
    }

}
