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

    public void create(T object);

    public void update(T object);

    public void delete(T object);

    public List<T> all();

    public T findBy(T object);
    
    public T find(Integer id);

    public List<T> retrieve(Integer first, Integer size);

}
