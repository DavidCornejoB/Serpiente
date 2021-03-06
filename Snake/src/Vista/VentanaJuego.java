/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Vista;

import Controlador.EventoVentanaJuego;
import Controlador.EventoVentanaPrincipal;
import Modelo.Lista;
import Modelo.Nodo;
import java.applet.AudioClip;
import java.awt.Color;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JDesktopPane;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 *
 * @author Eduardo
 */
public class VentanaJuego extends JFrame implements KeyListener {

    int n = 40, m = 40, a = 250;
    JPanel[][] panel = new JPanel[n][m];
    JTextField t = new JTextField();
    JLabel label = new JLabel("Puntaje = 0");

    private JDesktopPane escritorio;
    private JMenuBar barraMenu;
    private List<JMenu> menuList;
    private List<JMenuItem> menuItemList;

    int X = 0;
    int Y = 0;
    int comidaX;
    int comidaY;
    Mover mov = new Mover();
    Lista lista = new Lista();
    int puntos = 0;  //
    String marcha = "derecha";

    AudioClip comer = java.applet.Applet.newAudioClip(getClass().getResource("comer.wav"));
    AudioClip perder = java.applet.Applet.newAudioClip(getClass().getResource("sad.wav"));
    AudioClip juego = java.applet.Applet.newAudioClip(getClass().getResource("juego.wav"));

    public VentanaJuego() {

        mov.start();
        juego.loop();

        this.barraMenu = new JMenuBar();
        this.menuList = new ArrayList<JMenu>();
        this.menuList.add(new JMenu("Juego"));

        this.menuItemList = new ArrayList<JMenuItem>();
        this.menuItemList.add(new JMenuItem("Nuevo Juego"));
        this.menuItemList.add(new JMenuItem("Salir"));

        this.setJMenuBar(barraMenu);
        this.barraMenu.add(this.menuList.get(0));
        this.menuList.get(0).add(this.menuItemList.get(0));
        this.menuList.get(0).add(this.menuItemList.get(1));

        this.menuItemList.get(0).addActionListener(new EventoVentanaJuego(this));
        this.menuItemList.get(1).addActionListener(new EventoVentanaJuego(this));
        
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

    public JMenuBar getBarraMenu() {
        return barraMenu;
    }

    public void setBarraMenu(JMenuBar barraMenu) {
        this.barraMenu = barraMenu;
    }

    public List<JMenu> getMenuList() {
        return menuList;
    }

    public void setMenuList(List<JMenu> menuList) {
        this.menuList = menuList;
    }

    public List<JMenuItem> getMenuItemList() {
        return menuItemList;
    }

    public void setMenuItemList(List<JMenuItem> menuItemList) {
        this.menuItemList = menuItemList;
    }

    public int getN() {
        return n;
    }

    public void setN(int n) {
        this.n = n;
    }

    public int getM() {
        return m;
    }

    public void setM(int m) {
        this.m = m;
    }

    public JPanel[][] getPanel() {
        return panel;
    }

    public void setPanel(JPanel[][] panel) {
        this.panel = panel;
    }

    public JTextField getT() {
        return t;
    }

    public void setT(JTextField t) {
        this.t = t;
    }

    public JLabel getLabel() {
        return label;
    }

    public void setLabel(JLabel label) {
        this.label = label;
    }

    public JDesktopPane getEscritorio() {
        return escritorio;
    }

    public void setEscritorio(JDesktopPane escritorio) {
        this.escritorio = escritorio;
    }

    public int getX() {
        return X;
    }

    public void setX(int X) {
        this.X = X;
    }

    public int getY() {
        return Y;
    }

    public void setY(int Y) {
        this.Y = Y;
    }

    public int getComidaX() {
        return comidaX;
    }

    public void setComidaX(int comidaX) {
        this.comidaX = comidaX;
    }

    public int getComidaY() {
        return comidaY;
    }

    public void setComidaY(int comidaY) {
        this.comidaY = comidaY;
    }

    public Mover getMov() {
        return mov;
    }

    public void setMov(Mover mov) {
        this.mov = mov;
    }

    public Lista getLista() {
        return lista;
    }

    public void setLista(Lista lista) {
        this.lista = lista;
    }

    public int getPuntos() {
        return puntos;
    }

    public void setPuntos(int puntos) {
        this.puntos = puntos;
    }

    public String getMarcha() {
        return marcha;
    }

    public void setMarcha(String marcha) {
        this.marcha = marcha;
    }

    public AudioClip getComer() {
        return comer;
    }

    public void setComer(AudioClip comer) {
        this.comer = comer;
    }

    public AudioClip getPerder() {
        return perder;
    }

    public void setPerder(AudioClip perder) {
        this.perder = perder;
    }

    public AudioClip getJuego() {
        return juego;
    }

    public void setJuego(AudioClip juego) {
        this.juego = juego;
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

    public int getA() {
        return a;
    }

    public void setA(int a) {
        this.a = a;
    }

    void crecer() {
        if (puntos % 5 == 0 && getA() > 50) {
            int e = a - 50;
            setA(e);
        }
        puntos++;
        label.setText("Puntaje = " + puntos);
        /*if (puntos == 20) {  //Siguiente nivel

            JOptionPane.showMessageDialog(null, "Juego Termino con Exito");
            System.out.println("valor de puntos" + puntos);
            mov.detenElHilo();
        }*/
        Nodo n = new Nodo();
        n.setCodigo(lista.obtenerCodigo());
        lista.agregarFinal(n);
        generarManzana();
    }

    void obtenerLogitudCuerpo() {
        Nodo aux = lista.getLista();
    }

    void validar() {
        try {
            if (panel[X][Y].getBackground() == Color.BLACK) {
            } else if (panel[X][Y].getBackground() == Color.YELLOW) {
                juego.stop();
                perder.play();
                JOptionPane.showMessageDialog(null, "Perdiste", "Game Over", JOptionPane.ERROR_MESSAGE);
                perder.stop();
                mov.detenElHilo();
            } else if (panel[X][Y].getBackground() == Color.CYAN) {
                crecer();
                comer.play();
            }
            limpiar();
        } catch (ArrayIndexOutOfBoundsException err) {
            juego.stop();
            perder.play();
            JOptionPane.showMessageDialog(null, "Perdiste", "Game Over", JOptionPane.ERROR_MESSAGE);
            perder.stop();
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
                    Thread.currentThread().sleep(getA());
                    switch (tecla) {
                        case 37:
                            if (X >= 1) {
                                izquierda();
                                marcha = "izquierda";
                            } else {
                                juego.stop();
                                perder.play();
                                JOptionPane.showMessageDialog(null, "Perdiste", "Game Over", JOptionPane.ERROR_MESSAGE);
                                perder.stop();
                                this.stop();

                            }
                            break;
                        case 38:
                            if (Y >= 1) {
                                arriba();
                                marcha = "arriba";
                            } else {
                                juego.stop();
                                perder.play();
                                JOptionPane.showMessageDialog(null, "Perdiste", "Game Over", JOptionPane.ERROR_MESSAGE);
                                perder.stop();
                                this.stop();
                            }
                            break;
                        case 39:
                            if (X <= 77) {
                                derecha();
                                marcha = "derecha";
                            } else {
                                juego.stop();
                                perder.play();
                                JOptionPane.showMessageDialog(null, "Perdiste", "Game Over", JOptionPane.ERROR_MESSAGE);
                                perder.stop();
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
