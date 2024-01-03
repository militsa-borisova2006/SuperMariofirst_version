import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MarioGame extends JFrame{
    //Frame sizes, Mario and obstacles, Mario speed
    private static final int FRAME_WIDTH = 1500;
    private static final int FRAME_HEIGHT = 800;
    private static final int MARIO_WIDTH = 100;
    private static final int MARIO_HEIGHT = 100;
    private static final int OBSTACLE_WIDTH = 100;
    private static final int OBSTACLE_HEIGHT = 100;
    private static final int MARIO_SPEED = 5;
//image variables
    private ImageIcon background;
    private ImageIcon marioImage;
    private ImageIcon obstacleImage;

    private int marioX;
    private int marioY;
    private int[] obstacleX = {400, 600, 1000}; // Initial x-coordinates of three obstacles
    private int obstacleY;

    public MarioGame() {
        setTitle("Super Mario Game");
        setSize(FRAME_WIDTH, FRAME_HEIGHT);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//Close button
        setResizable(true);//Big screen

        // Load images
        background = new ImageIcon("src/SuperMario_Images/SuperMario_background.png"); // Replace with background image
        marioImage = new ImageIcon("src/SuperMario_Images/SuperMario_Character.png"); // Replace with Mario image
        obstacleImage = new ImageIcon("src/SuperMario_Images/SuperMario_Tube.png"); // Replace with obstacle image

        // Set initial positions
        marioX = 150;
        marioY = FRAME_HEIGHT - MARIO_HEIGHT - 200;
        obstacleY = FRAME_HEIGHT - OBSTACLE_HEIGHT - 200;

        // Timer for animation, the timer fires an action event every 50 milliseconds.
        Timer timer = new Timer(100, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                moveMario();// update the position of Mario.
                repaint();// turn invokes the paint method.
            }
        });
        timer.start();//This starts the timer, initiating the animation loop.
    }

    private void moveMario() {
        marioX += MARIO_SPEED;//method for moving

        // If Mario goes beyond the right edge, reset his position
        if (marioX > FRAME_WIDTH) {
            marioX = 0;
        }
    }

    @Override//paint method - drawing the background, Mario, and obstacles on the game window.
    public void paint(Graphics g) {
        super.paint(g);
        drawBackground(g);
        drawMario(g);
        drawObstacles(g);
    }

    private void drawBackground(Graphics g) {
        g.drawImage(background.getImage(), 0, 0, FRAME_WIDTH, FRAME_HEIGHT, null);
    }

    private void drawMario(Graphics g) {
        g.drawImage(marioImage.getImage(), marioX, marioY, MARIO_WIDTH, MARIO_HEIGHT, null);
    }

    private void drawObstacles(Graphics g) {
        for (int i = 0; i < obstacleX.length; i++) {
            g.drawImage(obstacleImage.getImage(), obstacleX[i], obstacleY, OBSTACLE_WIDTH, OBSTACLE_HEIGHT, null);
        }
    }

    public static void main(String[] args) {
                MarioGame game = new MarioGame();
                game.setVisible(true);
    }
}
