/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import Vista.VentanaJuego;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFrame;

import Vista.VentanaPrincipal;
import java.applet.AudioClip;

/**
 *
 * @author Eduardo
 */
public class EventoVentanaPrincipal implements ActionListener {

    private VentanaPrincipal vVentana;
    AudioClip monedas = java.applet.Applet.newAudioClip(getClass().getResource("monedas.wav"));

    public EventoVentanaPrincipal(VentanaPrincipal vVentana) {

        this.vVentana = vVentana;
    }

    public void actionPerformed(ActionEvent ae) {

        if (ae.getSource().equals(this.vVentana.getMenuItemList().get(0))) {
            VentanaJuego v = new VentanaJuego();
            this.vVentana.getSnake().stop();
            v.setTitle("Snake");
            v.setSize(486, 508);
            v.setLocationRelativeTo(null);
            v.setResizable(false);
            v.setVisible(true);

        }
        if (ae.getSource().equals(this.vVentana.getMenuItemList().get(1))) {
            monedas.play();
            System.out.println("chao");
        }

        if (ae.getSource().equals(this.vVentana.getMenuItemList().get(2))) {
            System.out.println("realizado");
        }

    }
}
