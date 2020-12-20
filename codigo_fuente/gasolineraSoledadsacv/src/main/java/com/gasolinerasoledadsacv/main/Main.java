package com.gasolinerasoledadsacv.main;

import com.gasolinerasoledadsacv.view.main.MainView;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

public class Main {

    public static void main(String[] args) {
                System.setProperty("sun.java2d.ddscale", "true");
        System.setProperty("sun.java2d.uiScale", "0.5");
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (ClassNotFoundException | IllegalAccessException | InstantiationException | UnsupportedLookAndFeelException ex) {

        }
        MainView lev = new MainView();
        lev.setVisible(true);
    }
}
