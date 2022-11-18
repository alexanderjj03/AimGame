package ui;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.*;
import java.io.IOException;
import persistence.JsonReader;

/*
 * Initializes the game. The following code is inspired by lab 3.
 */
public class AimFrame extends JFrame {
    private static final int INTERVAL = 20;
    private static final String JSON_STORE = "./data/aimgame.json";
    private AimGame game;
    private GamePanel gp;
    private JsonReader jsonReader;
    private int startingTargs;
    private int targSize;

    // Constructs main window
    // EFFECTS: sets up window in which the aim game will be played
    public AimFrame() {
        super("Aim Game");
        jsonReader = new JsonReader(JSON_STORE);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        playIntro();
        setUndecorated(true);
        boolean loaded = loadSavedGame();
        if (!loaded) {
            startingTargs = targetSelection();
            targSize = sizeSelection();
            game = new AimGame(startingTargs, targSize, false);
        }
        gp = new GamePanel(game);
        add(gp);
        addKeyListener(new KeyHandler());
        addMouseListener(new MouseHandler());
        pack();
        centreOnScreen();
        setVisible(true);
        setResizable(false);
        addTimer();
    }

    // EFFECTS: Shows the game's introduction image.
    private void playIntro() {
        JFrame frame = new JFrame();
        ImageIcon intro = new ImageIcon("Aim Game v2.png");
        JOptionPane.showMessageDialog(frame, "", "Click ok to continue",
                JOptionPane.INFORMATION_MESSAGE, intro);
    }

    // EFFECTS: Sets up a pop up window for the user to decide whether to load a saved game. Only returns true if
    // the user says yes, AND there is a saved game file.
    private boolean loadSavedGame() {
        JFrame frame = new JFrame();
        int n = JOptionPane.showConfirmDialog(frame, "Would you like to load your saved game?",
                "Load saved?", JOptionPane.YES_NO_OPTION);
        if (n == JOptionPane.YES_OPTION) {
            boolean successfullyLoaded = loadAimGame();
            if (successfullyLoaded) {
                JOptionPane.showMessageDialog(frame, "Loaded AimGame from " + JSON_STORE);
                return true;
            } else {
                JOptionPane.showMessageDialog(frame, "Unable to read from file: " + JSON_STORE);
                return false;
            }
        } else {
            return false;
        }
    }

    // EFFECTS: Sets up pop-up window for the user to decide on the number of starting targets. The input number
    // is returned. If the input isn't an integer (or is < 0), 1 is returned. If the input is over 20, 20 is returned
    // (to minimize lag and prevent people from potentially breaking the program).
    private int targetSelection() {
        JFrame frame = new JFrame();
        int num;
        String s = JOptionPane.showInputDialog(
                frame, "Alright, how many random targets would you like to start with? "
                        + "Number between 1 and 20, please", null);
        try {
            num = Integer.parseInt(s);
            if (num > 20) {
                num = 20;
            } else if (num < 1) {
                num = 1;
            }
        } catch (Exception e) {
            num = 1;
        }
        return num;
    }

    // EFFECTS: Sets up pop-up window for the user to decide on the size of the starting targets. The input number
    // is returned. The number must be even, and be between 10 and 100 (inclusive). 20 is the default value.
    private int sizeSelection() {
        JFrame frame = new JFrame();
        int num;
        String s = JOptionPane.showInputDialog(
                frame, "What size targets do you want? Enter an even integer between 10 and 100, this will "
                        + "be the target's diameter in pixels.", null);
        try {
            num = Integer.parseInt(s);
            if (num > 100) {
                num = 100;
            } else if (num < 10) {
                num = 10;
            } else if (num % 2 == 1) {
                num = num - 1;
            }
        } catch (Exception e) {
            num = 20;
        }
        return num;
    }

    // Set up timer
    // MODIFIES: none
    // EFFECTS: initializes a timer that updates game each
    // INTERVAL milliseconds
    private void addTimer() {
        Timer t = new Timer(INTERVAL, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                gp.repaint();
            }
        });
        t.start();
    }

    // Centres frame on desktop
    // MODIFIES: this
    // EFFECTS: location of frame is set so frame is centred on desktop
    private void centreOnScreen() {
        Dimension scrn = Toolkit.getDefaultToolkit().getScreenSize();
        setLocation((scrn.width - getWidth()) / 2, (scrn.height - getHeight()) / 2);
    }

    /*
     * A key handler to respond to key events
     */
    private class KeyHandler extends KeyAdapter {
        @Override
        public void keyPressed(KeyEvent e) {
            game.keyPressed(e.getKeyCode());
        }
    }

    /*
     * A mouse handler to respond to mouse events.
     */
    private class MouseHandler extends MouseAdapter {
        @Override
        public void mousePressed(MouseEvent m) {
            game.mousePressed(m);
        }
    }

    // MODIFIES: this
    // EFFECTS: loads aimgame from file
    private boolean loadAimGame() {
        try {
            game = jsonReader.read();
            return true;
        } catch (IOException e) {
            return false;
        }
    }
}
