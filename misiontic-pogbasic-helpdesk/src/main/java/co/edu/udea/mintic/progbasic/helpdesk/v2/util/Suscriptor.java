/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.udea.mintic.progbasic.helpdesk.v2.util;

/**
 *
 * @author alexandervivas
 */
public interface Suscriptor<T> {
    
    public void recibirEvento(T evento);
    
}
