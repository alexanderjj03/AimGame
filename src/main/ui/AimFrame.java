package ui;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JFrame;
import javax.swing.Timer;
import java.io.IOException;
import java.util.Scanner;
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

    // Constructs main window
    // EFFECTS: sets up window in which the aim game will be played
    public AimFrame() {
        super("Aim Game");
        Scanner sc = new Scanner(System.in);
        jsonReader = new JsonReader(JSON_STORE);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setUndecorated(true);
        System.out.println("Would you like to load your saved game? Enter 'y' to load, or any other input to not.");
        String input = sc.nextLine();
        if (input.equalsIgnoreCase("y")) {
            loadAimGame();
        } else {
            System.out.println("Alright, how many random targets would you like to start with?");
            startingTargs = sc.nextInt();
            game = new AimGame(startingTargs, false);
        }
        gp = new GamePanel(game);
        add(gp);
        addKeyListener(new KeyHandler());
        addMouseListener(new MouseHandler());
        pack();
        centreOnScreen();
        setVisible(true);
        addTimer();
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
     * A mouse handler to respond to mouse events
     */
    private class MouseHandler extends MouseAdapter {
        @Override
        public void mousePressed(MouseEvent m) {
            game.mousePressed(m);
        }
    }

    // MODIFIES: this
    // EFFECTS: loads aimgame from file
    private void loadAimGame() {
        try {
            game = jsonReader.read();
            System.out.println("Loaded AimGame from " + JSON_STORE);
        } catch (IOException e) {
            System.out.println("Unable to read from file: " + JSON_STORE);
            System.exit(0);
        }
    }


}
