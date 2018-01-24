/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import Vista.VentanaJuego;
import Vista.VentanaPrincipal;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 *
 * @author Estudiante
 */
public class EventoVentanaJuego implements ActionListener {
    
    private VentanaJuego vJuego;
    private VentanaPrincipal vPrincipal;

    public EventoVentanaJuego(VentanaJuego vJuego) {
        this.vJuego = vJuego;
        this.vPrincipal = vPrincipal;
    }    

    public VentanaJuego getvJuego() {
        return vJuego;
    }

    public void setvJuego(VentanaJuego vJuego) {
        this.vJuego = vJuego;
    }

    public VentanaPrincipal getvPrincipal() {
        return vPrincipal;
    }

    public void setvPrincipal(VentanaPrincipal vPrincipal) {
        this.vPrincipal = vPrincipal;
    }
   

    @Override
    public void actionPerformed(ActionEvent ae) {
        
        if(ae.getSource().equals(this.vJuego.getMenuItemList().get(0))){
            System.out.println("hola");
            this.vJuego = new VentanaJuego();
            
            
        }
        
        if(ae.getSource().equals(this.vJuego.getMenuItemList().get(1))){
            this.vJuego.setVisible(false);
            this.vJuego.getJuego().stop();
            this.vPrincipal = new VentanaPrincipal();
            this.vPrincipal.setVisible(true);
            
            
        }
    }
    
    
}
