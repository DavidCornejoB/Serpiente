/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Vista;

import Modelo.Lista;
import Modelo.Nodo;
import java.applet.AudioClip;
import java.awt.Color;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 *
 * @author Eduardo
 */
public class VentanaJuego extends JFrame implements KeyListener {

    int n = 40, m = 40;
    JPanel[][] panel = new JPanel[n][m];
    JTextField t = new JTextField();
    JLabel label = new JLabel("Puntaje = 0");

    int X = 0;
    int Y = 0;
    int comidaX;
    int comidaY;
    Mover mov = new Mover();
    Lista lista = new Lista();
    int puntos = 0;  //
    String marcha = "derecha";
    

    AudioClip comer = java.applet.Applet.newAudioClip(getClass().getResource("comer.wav"));
    AudioClip perder = java.applet.Applet.newAudioClip(getClass().getResource("perder.wav"));

    public VentanaJuego() {

        mov.start();
        this.setLayout(null);
        t.requestFocus();
        t.addKeyListener(this);
        this.add(t);
        for (int x = 0; x < n; x++) {
            for (int y = 0; y < m; y++) {
                panel[x][y] = new JPanel();
                panel[x][y].setBounds(x * 12, y * 12, 12, 12);
                this.add(panel[x][y]);
                panel[x][y].setBackground(Color.BLACK);
                panel[x][y].setBorder(null);
            }
        }

        X = n / 2;
        Y = m / 2;
        Nodo cabeza;
        for (int i = 0; i < 3; i++) {
            cabeza = new Nodo();
            cabeza.setX(X - i);
            cabeza.setY(Y);
            lista.agregarFinal(cabeza);
            panel[cabeza.getX()][cabeza.getY()].setBackground(Color.YELLOW);
        }

        generarManzana();
    }

    void generarManzana() {
        comidaX = aleatorioX();
        comidaY = aleatorioY();
        dibujarManzana();   //COMIDA 
    }

    void dibujarManzana() {
        if (panel[comidaX][comidaY].getBackground() == Color.DARK_GRAY || panel[comidaX][comidaY].getBackground() == Color.BLUE) {
            generarManzana();
        } else {
            panel[comidaX][comidaY].setBackground(Color.CYAN);
        }
    }

    public void keyTyped(KeyEvent e) {
    }

    public void keyPressed(KeyEvent e) {
        switch (e.getKeyCode()) {
            case 37:

                if (!marcha.equals("derecha")) {
                    mov.Opcion(37);
                }
                break;
            case 38:
                if (!marcha.equals("abajo")) {
                    mov.Opcion(38);
                }
                break;
            case 39:
                if (!marcha.equals("izquierda")) {
                    mov.Opcion(39);
                }
                break;
            case 40:
                if (!marcha.equals("arriba")) {
                    mov.Opcion(40);
                }
                break;
        }
    }

    public void keyReleased(KeyEvent e) {
    }

    void crecer() {
        puntos++;
        label.setText("Puntaje = " + puntos);
        if (puntos == 20) {  //Siguiente nivel

            JOptionPane.showMessageDialog(null, "Juego Termino con Exito");
            System.out.println("valor de puntos" + puntos);
            mov.detenElHilo();
        }
        Nodo n = new Nodo();
        n.setCodigo(lista.obtenerCodigo());
        lista.agregarFinal(n);
        generarManzana();
    }

    void obtenerLogitudCuerpo() {
        Nodo aux = lista.getLista();
    }

    void validar() {
        try{
        if (panel[X][Y].getBackground() == Color.BLACK) {
        } else if (panel[X][Y].getBackground() == Color.YELLOW) {
            perder.play();
            JOptionPane.showMessageDialog(null, "Perdiste", "Game Over", JOptionPane.ERROR_MESSAGE);
            mov.detenElHilo();
        } else if (panel[X][Y].getBackground() == Color.CYAN) {
            crecer();
            comer.play();
        } 
        limpiar();
        } catch (ArrayIndexOutOfBoundsException err) {
            perder.play();
            JOptionPane.showMessageDialog(null, "Perdiste", "Game Over", JOptionPane.ERROR_MESSAGE);
            mov.detenElHilo();
            
        }
    }

    void derecha() {
        X++;
        validar();
    }

    void izquierda() {
        X--;
        validar();
    }

    void abajo() {
        Y++;
        validar();
    }

    void arriba() {
        Y--;
        validar();
    }

    public void limpiar() {
        for (int x = 0; x < n; x++) {
            for (int y = 0; y < m; y++) {
                panel[x][y].setBackground(Color.BLACK);
            }
        }
        dibujarManzana();
        if (lista.getLista() != null) {
            Nodo aux = lista.getLista();
            while (aux.sig != null) {
                aux.setX(aux.sig.getX());
                aux.setY(aux.sig.getY());
                panel[aux.getX()][aux.getY()].setBackground(Color.YELLOW);
                if (aux.getX() == 0 && aux.getY() == 0) {
                    panel[aux.getX()][aux.getY()].setBackground(Color.BLACK);
                }
                aux = aux.sig;
            }
            aux.setX(X);
            aux.setY(Y);
            panel[X][Y].setBackground(Color.YELLOW);
        }
    }

    public int aleatorioX() {
        return (int) (Math.random() * n - 1);
    }

    public int aleatorioY() {
        return (int) (Math.random() * m - 1);
    }

    public class Mover extends Thread {

        private boolean continuar = true;
        private int tecla = 0;

        public void detenElHilo() {
            continuar = false;
        }

        public void Opcion(int x) {
            tecla = x;
        }

        public void run() {
            while (continuar) {
                try {
                    Thread.currentThread().sleep(200);
                    switch (tecla) {
                        case 37:
                            if (X >= 1) {
                                izquierda();
                                marcha = "izquierda";
                            } else {
                                perder.play();
                                JOptionPane.showMessageDialog(null, "Perdiste", "Game Over", JOptionPane.ERROR_MESSAGE);
                                this.stop();
                               
                            }
                            break;
                        case 38:
                            if (Y >= 1) {
                                arriba();
                                marcha = "arriba";
                            } else {
                                perder.play();
                                JOptionPane.showMessageDialog(null, "Perdiste", "Game Over", JOptionPane.ERROR_MESSAGE);
                                this.stop();
                            }
                            break;
                        case 39:
                            if (X <= 77) {
                                derecha();
                                marcha = "derecha";
                            } else {
                                perder.play();
                                JOptionPane.showMessageDialog(null, "Perdiste", "Game Over", JOptionPane.ERROR_MESSAGE);
                                this.stop();
                            }
                            break;
                        case 40:
                            if (Y <= 39) {
                                abajo();
                                marcha = "abajo";
                            } else {
                                JOptionPane.showMessageDialog(null, "OOOO a perdido jaja");
                                this.stop();
                            }
                            break;
                    }
                } catch (InterruptedException ex) {
                    Logger.getLogger(VentanaJuego.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }
}
