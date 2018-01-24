/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Vista;

import Controlador.EventoVentanaPrincipal;
import java.applet.AudioClip;
import java.util.ArrayList;
import java.util.List;
import javax.swing.ImageIcon;
import javax.swing.JDesktopPane;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

/**
 *
 * @author Eduardo
 */
public class VentanaPrincipal  extends JFrame{
    private JDesktopPane escritorio;
    private JMenuBar barraMenu;
    private List<JMenu> menuList;
    private List<JMenuItem> menuItemList; 
    private JLabel imagen;
    AudioClip snake = java.applet.Applet.newAudioClip(getClass().getResource("snake.wav"));

    public VentanaPrincipal()  {
        super("SNAKE");        
        this.setSize(510, 460);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(3);
        this.iniciaComponentes();
        this.setVisible(true);
    }
    
    public void iniciaComponentes()
    {
        this.escritorio=new JDesktopPane();
        
        this.setContentPane(this.escritorio);
        
        
        this.barraMenu = new JMenuBar();
        this.menuList = new ArrayList<JMenu>();
        this.menuList.add(new JMenu("Archivo"));
        this.menuList.add(new JMenu("Acerca de"));
        this.menuItemList = new ArrayList<JMenuItem>();
        this.menuItemList.add(new JMenuItem("Nuevo Juego"));
        this.menuItemList.add(new JMenuItem("Puntaje Maximo"));
        this.menuItemList.add(new JMenuItem("Desarrolladores"));
        
        imagen = new JLabel();
        imagen.setIcon(new ImageIcon(getClass().getResource("fondo.png")));
        imagen.setBounds(0, 0, 500, 400);
        this.add(imagen);
        snake.loop();
       
        this.setJMenuBar(barraMenu);
        this.barraMenu.add(this.menuList.get(0));
        this.barraMenu.add(this.menuList.get(1));
        this.menuList.get(0).add(this.menuItemList.get(0));
        this.menuList.get(0).add(this.menuItemList.get(1));
        this.menuList.get(1).add(this.menuItemList.get(2));
        this.menuItemList.get(0).addActionListener(new EventoVentanaPrincipal(this));
        this.menuItemList.get(1).addActionListener(new EventoVentanaPrincipal(this));
        this.menuItemList.get(2).addActionListener(new EventoVentanaPrincipal(this));

    }

    public JDesktopPane getEscritorio() {
        return escritorio;
    }

    public void setEscritorio(JDesktopPane escritorio) {
        this.escritorio = escritorio;
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

    public JLabel getImagen() {
        return imagen;
    }

    public void setImagen(JLabel imagen) {
        this.imagen = imagen;
    }

    public AudioClip getSnake() {
        return snake;
    }

    public void setSnake(AudioClip snake) {
        this.snake = snake;
    }
    
    

   
}
