/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.crudbit0004.controller;

import com.crudbit004.entities.Autor;
import com.crudbit004.entities.Libro;
import com.crudbit004.model.AutorFacade;
import com.crudbit004.model.LibroFacade;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;

/**
 *
 * @author MATEO AVILA
 */
@Named(value = "libroCtrl")
@SessionScoped
public class LibroCtrl implements Serializable {

    @EJB
    private AutorFacade autorFacade;
    @EJB
    private LibroFacade libroFacade;

    private Libro libro;
    String mensaje = "";
    private List<SelectItem> SelectItemsOneAutor;

    public LibroCtrl() {
        this.libro = new Libro();
    }

    public Libro getLibro() {
        return libro;
    }

    public void setLibro(Libro libro) {
        this.libro = libro;
    }

    //metodo para listar todo los autores 
    public List<Libro> ListAllLibro() {
        return libroFacade.findAll();
    }

//seleccion los autores
    public List<SelectItem> SelectItemsOneAutor() {
        try {
            this.SelectItemsOneAutor = new ArrayList<SelectItem>();
            List<Autor> autoresList = autorFacade.findAll();
            SelectItemsOneAutor.clear();
            for (Autor aut : autoresList) {
                SelectItem selectItem = new SelectItem(aut.getIdautor(), aut.getNombreautor());
                this.SelectItemsOneAutor.add(selectItem);
            }
            this.mensaje = "Informacion Cargada con Exito!";
        } catch (Exception e) {
            e.printStackTrace();
            this.mensaje = "Error: " + e.getMessage();
        }
        FacesMessage mens = new FacesMessage(this.mensaje);
        FacesContext.getCurrentInstance().addMessage(null, mens);
        return SelectItemsOneAutor;
    }

    //metodo para agregar unautores 
    public void AddLibro() {
        try {
            libroFacade.create(libro);
            this.libro = new Libro();
            this.mensaje = "Libro registado Exitosamente";
        } catch (Exception e) {
            e.printStackTrace();
            this.mensaje = "Erro: " + e.getMessage();

        }
        FacesMessage mens = new FacesMessage(this.mensaje);
        FacesContext.getCurrentInstance().addMessage(null, mens);
    }

    //preparamos los datos del autor a editar
    public String SelectEditLibro(Libro libro) {
        this.libro = libro;
        return "updateAutor";
    }

    //metodo para editar un autores 
    public String EditLibro() {
        this.libroFacade.edit(libro);
        this.libro = new Libro();
        return "index";
    }

    //eliminar un alumno
    public void DeleteLibro(Libro libro) {
        this.libroFacade.remove(libro);
    }

}
