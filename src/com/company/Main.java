package com.company;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class Main extends JFrame {
    public static int coord = 16;
    public static ArrayList<Grid> arrayListGrid = new ArrayList<Grid>();
    public static JPanel panel = new JPanel(new GridLayout(4, 4,0, 0));
    public static int[][] numbers = new int[4][4];
    public static int moves = 0;
    public static long start;
    public static long end;

    public Main() {
        super("Пятнашки");

        setBounds(200, 200, 800, 800);
        setResizable(false);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        createMenu();

        Container container = getContentPane();
        panel.setDoubleBuffered(true);
        container.add(panel);

        panel.addKeyListener(new KeyListener() {

            @Override
            public void keyTyped(KeyEvent e) {}

            @Override
            public void keyReleased(KeyEvent e) {}

            @Override
            public void keyPressed(KeyEvent e) {
                int keyCode = e.getKeyCode();
                switch( keyCode ) {
                    case KeyEvent.VK_UP:
                        checkUp();
                        moves++;
                        break;
                    case KeyEvent.VK_DOWN:
                        checkDown();
                        moves++;
                        break;
                    case KeyEvent.VK_LEFT:
                        checkLeft();
                        moves++;
                        break;
                    case KeyEvent.VK_RIGHT :
                        checkRight();
                        moves++;
                        break;
                }
                if (checkWin()) {
                    end = System.nanoTime();
                    long elapsedTime = end - start;
                    double seconds = (double)elapsedTime / 1000000000.0;
                    JOptionPane.showMessageDialog(null, "Поздравляем, вы победили!!!\n" + "Сделано ходов: " + moves + "\nПотрачено времени: " + (long) seconds + " сек.", "Победа", JOptionPane.INFORMATION_MESSAGE);
                    arrayListGrid.clear();
                    moves = 0;
                    Init init = new Init();
                    init.generate();
                    repaintField();
                }
            }

        });

        panel.setFocusable(true);
        panel.requestFocusInWindow();

        Init init = new Init();
        init.generate();

        repaintField();
    }

    private void checkRight() {
        if(coord!=1 && coord!=5 && coord!=9 && coord!=13) {
            swap(coord-1, coord-2);
            coord = coord-1;
            repaintField();
        }
    }

    private void checkLeft() {
        if(coord!=4 && coord!=8 && coord!=12 && coord!=16) {
            swap(coord-1, coord);
            coord = coord+1;
            repaintField();
        }
    }

    private void checkDown() {
        if(coord > 4) {
            swap(coord-1, coord-5);
            coord = coord-4;
            repaintField();
        }
    }

    private void checkUp() {
        if(coord < 13) {
            swap(coord-1, coord+3);
            coord = coord+4;
            repaintField();
        }
    }

    private void swap(int i, int j) {

        BufferedImage bufferedImage = (arrayListGrid.get(i)).getBufferedImage();
        int number = (arrayListGrid.get(i)).getNumber();
        (arrayListGrid.get(i)).setBufferedImage((arrayListGrid.get(j)).getBufferedImage());
        (arrayListGrid.get(i)).setNumber((arrayListGrid.get(j)).getNumber());
        (arrayListGrid.get(j)).setBufferedImage(bufferedImage);
        (arrayListGrid.get(j)).setNumber(number);
    }

    public void repaintField() {
        panel.removeAll();

        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                JLabel picLabel = new JLabel(new ImageIcon((arrayListGrid.get(i*4+j)).getBufferedImage()));
                panel.add(picLabel);
                picLabel.setVisible(true);
            }
        }
        panel.validate();
        panel.repaint();
    }

    public boolean checkWin() {
        boolean status = true;
        for (int i = 0; i < 16; i++) {
            if((arrayListGrid.get(i)).getNumber()!=i+1)
               status = false;
        }
        return status;
    }

    private void createMenu() {
        JMenuBar menu = new JMenuBar();
        JMenu fileMenu = new JMenu("Меню");

        for (String fileItem : new String [] { "Новая", "Выход" }) {
            JMenuItem item = new JMenuItem(fileItem);
            item.setActionCommand(fileItem.toLowerCase());
            item.addActionListener(new NewMenuListener());
            fileMenu.add(item);
        }
        fileMenu.insertSeparator(1);

        menu.add(fileMenu);
        setJMenuBar(menu);
    }

    private class NewMenuListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            String command = e.getActionCommand();
            if ("выход".equals(command)) {
                System.exit(0);
            }
            if ("новая".equals(command)) {
                arrayListGrid.clear();
                moves = 0;
                Init init = new Init();
                init.generate();
                repaintField();
            }
        }
    }

    public static void main(String[] args) {
        JFrame app = new Main();
        app.setVisible(true);
    }
}