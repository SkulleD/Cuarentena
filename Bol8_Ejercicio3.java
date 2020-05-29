//Bol8_Ejercicio3 - Álvaro Rodríguez Vila

import java.awt.*;
import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.io.*;
import java.awt.event.*;

public class Bol8_Ejercicio3 extends JFrame implements ActionListener {
    JFileChooser fc = new JFileChooser();
    fc.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
    JButton boton;
    JLabel tag;
    int opciones = JFileChooser.APPROVE_OPTION;
    JTextArea textito;
    BufferedReader leeTexto;

    public Bol8_Ejercicio3() {
        super("Ejercicio 3");
        setLayout(new FlowLayout());

        boton = new JButton("Abrir archivo");
        boton.addActionListener(this);
        boton.setSize(boton.getPreferredSize());
        this.add(boton);

        tag = new JLabel("<html></html>");
        this.add(tag);

        textito = new JTextArea();
        textito.setSize(textito.getPreferredSize());
        this.add(textito);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        FileNameExtensionFilter texto = new FileNameExtensionFilter("Texto", "txt", "text");
        FileNameExtensionFilter images = new FileNameExtensionFilter("Imágenes", "png", "jpg", "jpeg", "gif");
        String path = "";
        File file;
        String nombre;
        double tamano;
        double kb;
        String permisoLeer;
        String permisoEscribir;
        String permisoEjecucion;

        fc.addChoosableFileFilter(texto);
        fc.addChoosableFileFilter(images);
        if (opciones == JFileChooser.APPROVE_OPTION) {
            fc.showOpenDialog(this);
            path = fc.getSelectedFile().getPath();
            file = new File(path);
            try {
                leeTexto = new BufferedReader(new FileReader(new File(path)));
            } catch (FileNotFoundException e1) {
                e1.printStackTrace();
            }

            if (path.endsWith(".txt") || path.endsWith(".text")) {
                try {
                    textito.read(leeTexto, "File");
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }

            if (path.endsWith(".png") || path.endsWith(".jpg") || path.endsWith(".jpeg") || path.endsWith(".gif")) {
                tag.setIcon(new ImageIcon(fc.getSelectedFile().getPath()));
                tag.setSize(tag.getPreferredSize());
            } else {
                if (file.isDirectory()) {
                    try {
                        leeTexto = new BufferedReader(new FileReader(new File(path)));
                    } catch (FileNotFoundException e1) {
                        e1.printStackTrace();
                    }

                    try {
                        textito.read(leeTexto, "File");
                    } catch (IOException e1) {
                        e1.printStackTrace();
                    } catch (NullPointerException e1) {

                    }
                } else {
                    tamano = file.length();
                    kb = tamano;

                    if (file.canRead()) {
                        permisoLeer = "Lectura: Sí";
                    } else {
                        permisoLeer = "Lectura: No";
                    }

                    if (file.canWrite()) {
                        permisoEscribir = "Escritura: Sí";
                    } else {
                        permisoEscribir = "Escritura: No";
                    }

                    if (file.canExecute()) {
                        permisoEjecucion = "Ejecución: Sí";
                    } else {
                        permisoEjecucion = "Ejecucion: No";
                    }

                    nombre = "Nombre --> " + file.getName();
                    tag.setText(
                            "<html>" + nombre + "<br>Ruta --> " + path + "<br>Tamaño --> " + kb + " KB<br>Permisos --> "
                                    + permisoLeer + " " + permisoEscribir + " " + permisoEjecucion + "</html>");
                }
            }
        }
    }
}