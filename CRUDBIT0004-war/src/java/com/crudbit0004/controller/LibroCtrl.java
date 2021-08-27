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
    private Autor autor;
    String mensaje = "";
    
    private List<SelectItem> SelectItemsOneAutor;

    public LibroCtrl() {
        this.libro = new Libro();
        this.autor = new Autor();
    }

    public Libro getLibro() {
        return libro;
    }

    public void setLibro(Libro libro) {
        this.libro = libro;
    }

    public AutorFacade getAutorFacade() {
        return autorFacade;
    }

    public void setAutorFacade(AutorFacade autorFacade) {
        this.autorFacade = autorFacade;
    }

    public Autor getAutor() {
        return autor;
    }

    public void setAutor(Autor autor) {
        this.autor = autor;
    }

    //metodo para listar todo los autores 
    public List<Libro> ListAllLibro() {
        return libroFacade.findAll();
    }
    
//      public List<Libro> ListlibrosAutor() {
//        return libroFacade.listLibrosAutor();
//    }

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
            this.libro.setAutor(autor);
            this.libroFacade.create(libro);
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
    public void SelectEditLibro(Libro libro) {
        this.autor.setIdautor(libro.getAutor().getIdautor());
        this.libro = libro;

    }

    //metodo para editar un autores 
    public void EditLibro() {
        try {
            this.libro.setAutor(autor);
            this.libroFacade.edit(libro);
            this.libro = new Libro();
            this.mensaje = "Libro registado Exitosamente";
        } catch (Exception e) {
            this.mensaje = "Erro: " + e.getMessage();

        }
        FacesMessage mens = new FacesMessage(this.mensaje);
        FacesContext.getCurrentInstance().addMessage(null, mens);

    }

    //eliminar un alumno
    public void DeleteLibro(Libro libro) {
        this.libroFacade.remove(libro);
    }

    //cargar autor para el libro a editar


    public void limpiar() {
        this.libro = new Libro();
        this.autor = new Autor();
    }

}
