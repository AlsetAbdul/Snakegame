import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Random;

class GamePanel extends JPanel implements ActionListener {
    static final int SCREEN_WIDTH = 1000;
    static final int SCREEN_HEIGHT = 700;
    static final int UNIT_SIZE = 16;
    static final int GAME_UNITS = (SCREEN_WIDTH * SCREEN_HEIGHT) / UNIT_SIZE;
    final int x[] = new int[GAME_UNITS];
    final int y[] = new int[GAME_UNITS];
    int DELAY = 75;
    int bodyParts = 5;
    int applesEaten;
    int appleX;
    int appleY;
    char direction = 'R';
    boolean running = false;
    Timer timer;
    Random random;
    JFrame frame = new JFrame("Snake");
    JButton replay = new JButton("Home");
    JButton exit2 = new JButton("Exit");


    GamePanel() {

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
        frame.setResizable(false);
        this.setLayout(null);

        replay.setBackground(Color.WHITE);
        replay.setForeground(Color.BLACK);
        replay.setContentAreaFilled(false);
        replay.setFocusPainted(false);
        replay.setBorderPainted(false);
        replay.setFont(new Font("MV Boli", Font.PLAIN, 25));
        replay.setBounds(995, 540, 102, 50);

        exit2.setBackground(Color.WHITE);
        exit2.setForeground(Color.BLACK);
        exit2.setContentAreaFilled(false);
        exit2.setFocusPainted(false);
        exit2.setBorderPainted(false);
        exit2.setFont(new Font("MV Boli", Font.PLAIN, 25));
        exit2.setBounds(995, 610, 102, 50);

        random = new Random();

        this.setPreferredSize(new Dimension(1100, 688));
        this.setBackground(Color.WHITE);
        this.setFocusable(true);
        this.addKeyListener(new MyKeyAdapter());
        this.add(replay);
        this.add(exit2);

        frame.add(this);
        frame.pack();
        frame.setLocationRelativeTo(null);
        replay.addMouseListener(new MouseListener() {

            public void mouseClicked(MouseEvent e) {

                if (e.getSource() == replay) {
                    frame.dispose();
                    GameFrame d = new GameFrame();
                    d.frame.setVisible(true);
                }
            }

            @Override
            public void mousePressed(MouseEvent e) {
            }

            @Override
            public void mouseReleased(MouseEvent e) {
            }

            @Override
            public void mouseEntered(MouseEvent e) {

                if (e.getSource() == replay) {

                    replay.setForeground(Color.green);
                }
            }

            @Override
            public void mouseExited(MouseEvent e) {

                if (e.getSource() == replay) {

                    replay.setForeground(Color.BLACK);
                }

            }

        });

        exit2.addMouseListener(new MouseListener() {

            public void mouseClicked(MouseEvent e) {

                if (JOptionPane.showConfirmDialog(null, "Are you want exit the game ",
                        "Exit", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_NO_OPTION) {

                    System.exit(0);
                }
            }

            @Override
            public void mousePressed(MouseEvent e) {
            }

            @Override
            public void mouseReleased(MouseEvent e) {
            }

            @Override
            public void mouseEntered(MouseEvent e) {

                if (e.getSource() == exit2) {

                    exit2.setForeground(Color.red);
                }
            }

            @Override
            public void mouseExited(MouseEvent e) {

                if (e.getSource() == exit2) {

                    exit2.setForeground(Color.BLACK);
                }
            }
        });

        startGame();
    }

    public void startGame() {

        newApple();
        running = true;
        timer = new Timer(DELAY, this);
        timer.start();
    }

    public void paintComponent(Graphics g) {

        super.paintComponent(g);
        draw(g);
    }

    public void draw(Graphics g) {

        if (running) {

            g.setColor(Color.BLACK);
            g.fillRect(0, 0, 992, 688);
            g.setColor(Color.red);
            g.fillOval(appleX, appleY, UNIT_SIZE, UNIT_SIZE);
            if (bodyParts % 5 == 0 && bodyParts != 5) {

                g.fillOval(appleX - 2, appleY - 2, 2 * UNIT_SIZE, 2 * UNIT_SIZE);
            }

            if (applesEaten < 15) {
                for (int i = 0; i < bodyParts; i++) {

                    if (i == 0) {

                        g.setColor(Color.GREEN);
                        g.fillOval(x[i], y[i], UNIT_SIZE, UNIT_SIZE);
                    } else {

                        g.setColor(new Color(45, 180, 0));


                        g.fillOval(x[i], y[i], UNIT_SIZE, UNIT_SIZE);
                    }
                }
            }
            if (applesEaten >= 15 && applesEaten < 30) {
                for (int i = 0; i < bodyParts; i++) {

                    if (i == 0) {

                        g.setColor(Color.yellow);
                        g.fillOval(x[i], y[i], UNIT_SIZE, UNIT_SIZE);
                    } else {

                        g.setColor(new Color(197, 224, 19));


                        g.fillOval(x[i], y[i], UNIT_SIZE, UNIT_SIZE);
                    }
                }
            }
            if (applesEaten >= 30 && applesEaten < 45) {
                for (int i = 0; i < bodyParts; i++) {

                    if (i == 0) {

                        g.setColor(Color.blue);
                        g.fillOval(x[i], y[i], UNIT_SIZE, UNIT_SIZE);
                    } else {

                        g.setColor(new Color(30, 90, 212));


                        g.fillOval(x[i], y[i], UNIT_SIZE, UNIT_SIZE);
                    }
                }
            }
            if (applesEaten >= 45) {

                for (int i = 0; i < bodyParts; i++) {

                    if (i == 0) {

                        g.setColor(Color.green);
                        g.fillOval(x[i], y[i], UNIT_SIZE, UNIT_SIZE);
                    } else {


                        g.setColor(new Color(random.nextInt(255), random.nextInt(255), random.nextInt(255)));
                        g.fillOval(x[i], y[i], UNIT_SIZE, UNIT_SIZE);
                    }
                }
            }
            g.setColor(Color.BLACK);
            g.setFont(new Font("InkFree", Font.BOLD, 20));
            g.drawString("Score : " + applesEaten, 1003, 30);

        } else {

            gameOver(g);
        }
    }

    public void newApple() {

        appleX = random.nextInt((int) (992 / UNIT_SIZE)) * UNIT_SIZE;
        appleY = random.nextInt((int) (688 / UNIT_SIZE)) * UNIT_SIZE;


    }

    public void move() {

        for (int i = bodyParts; i > 0; i--) {

            x[i] = x[i - 1];
            y[i] = y[i - 1];
        }

        switch (direction) {

            case 'U':
                y[0] = y[0] - UNIT_SIZE;
                break;
            case 'D':
                y[0] = y[0] + UNIT_SIZE;
                break;
            case 'L':
                x[0] = x[0] - UNIT_SIZE;
                break;
            case 'R':
                x[0] = x[0] + UNIT_SIZE;
                break;

        }

    }

    public void checkApple() {

        if (bodyParts % 5 == 0 && bodyParts != 5) {
            for (int i = appleX; i < appleX + 2 * UNIT_SIZE; i++) {

                if (x[0] == i && y[0] == appleY) {

                    bodyParts++;
                    applesEaten += 5;
                    newApple();
                }
            }
            for (int i = appleY; i < appleY + 2 * UNIT_SIZE; i++) {

                if (x[0] == appleX && y[0] == i) {

                    bodyParts++;
                    applesEaten += 5;
                    newApple();
                }
            }
        } else if (x[0] == appleX && y[0] == appleY) {

            bodyParts++;
            applesEaten++;
            newApple();
        }
    }

    public void checkCollision() {

        for (int i = bodyParts; i > 0; i--) {

            if ((x[0] == x[i]) && (y[0] == y[i])) {

                running = false;
            }
        }
        if (x[0] < 0) {

            x[0] = 976;
        }
        if (x[0] > 976) {

            x[0] = 0;
        }
        if (y[0] < 0) {

            y[0] = 672;
        }
        if (y[0] > 672) {

            y[0] = 0;
        }

        if (!running) {

            timer.stop();
        }


    }

    public void gameOver(Graphics g) {

        g.setColor(Color.GRAY);
        g.fillRect(0, 0, 992, 688);
        g.setColor(Color.red);
        g.setFont(new Font("InkFree", Font.BOLD, 75));
        FontMetrics metrics2 = getFontMetrics(g.getFont());
        g.drawString("GAME OVER", (SCREEN_WIDTH - metrics2.stringWidth("GAME OVER")) / 2, SCREEN_HEIGHT / 2);

        g.setColor(Color.WHITE);
        g.setFont(new Font("InkFree", Font.BOLD, 40));
        FontMetrics metrics1 = getFontMetrics(g.getFont());
        g.drawString("SCORE :" + applesEaten, (SCREEN_WIDTH - metrics1.stringWidth("SCORE :" + applesEaten)) / 2, g.getFont().getSize());

        g.setColor(Color.BLACK);
        g.setFont(new Font("InkFree", Font.BOLD, 20));
        g.drawString("Score :" + applesEaten, 1003, 30);
    }


    @Override
    public void actionPerformed(ActionEvent e) {

        if (running) {

            move();
            checkApple();
            checkCollision();
        }
        repaint();
    }

    public class MyKeyAdapter extends KeyAdapter {
        public void keyPressed(KeyEvent e) {
            switch (e.getKeyCode()) {

                case KeyEvent.VK_LEFT:
                    if (direction != 'R') {

                        direction = 'L';
                    }
                    break;
            }
            switch (e.getKeyCode()) {

                case KeyEvent.VK_RIGHT:
                    if (direction != 'L') {

                        direction = 'R';
                    }
                    break;
            }
            switch (e.getKeyCode()) {

                case KeyEvent.VK_UP:
                    if (direction != 'D') {

                        direction = 'U';
                    }
                    break;
            }
            switch (e.getKeyCode()) {

                case KeyEvent.VK_DOWN:
                    if (direction != 'U') {

                        direction = 'D';
                    }
                    break;
            }
        }
    }

}