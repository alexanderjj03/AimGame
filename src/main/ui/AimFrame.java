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
import java.util.Scanner;

import model.TargetCollection;
import model.Target;
import model.AimGame;

/*
 * Initializes the game. The following code is inspired by lab 3.
 */
public class AimFrame extends JFrame {
    private static final int INTERVAL = 20;
    private AimGame game;
    private GamePanel gp;

    // Constructs main window
    // EFFECTS: sets up window in which the aim game will be played
    public AimFrame(int startingTargs) {
        super("Paddle Ball");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setUndecorated(true);
        game = new AimGame(startingTargs);
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
        public void mousePressed(MouseEvent m) {
            game.mousePressed(m);
        }
    }

    /*
     * Play the game (incomplete, will add a system to ensure that numtarg >= 1)
     */
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("How many targets would you like to start with?");
        int numtargs = sc.nextInt();
        new AimFrame(numtargs);
    }
}
