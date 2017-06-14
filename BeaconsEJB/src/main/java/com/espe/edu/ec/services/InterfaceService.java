/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.espe.edu.ec.services;

import java.util.List;

/**
 *
 * @author Juan
 */
public interface InterfaceService<T> {

    public void crear(T object);

    public void actualizar(T object);

    public void eliminar(T object);

    public List<T> buscarTodos();
    
    public T buscar(Integer id);

    public List<T> traerLazzy(Integer first, Integer size);

}
