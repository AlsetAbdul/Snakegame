import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

class GameFrame implements MouseListener {
    JFrame frame = new JFrame();
    JPanel title_panel = new JPanel();
    JPanel button_panel = new JPanel();
    JLabel textField = new JLabel();
    JButton play1 = new JButton("Easy");
    JButton play2 = new JButton("Hard");
    JButton exit1 = new JButton("Exit");


    GameFrame(){

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1000, 700);
        frame.getContentPane().setBackground(new Color(150,50,25));
        frame.setVisible(true);
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);
        frame.setLayout(new BorderLayout());

        textField.setBackground(new Color(4, 95, 130, 255));
        textField.setForeground(Color.RED);
        textField.setFont(new Font("Ink Free",Font.BOLD,100));
        textField.setHorizontalAlignment(JLabel.CENTER);
        textField.setText("Snake Game");
        textField.setOpaque(true);

        title_panel.setLayout(new BorderLayout());
        title_panel.setBounds(0, 0, 1000, 300);

        button_panel.setLayout(null);
        button_panel.setBackground(new Color(4,95,130,255));

        play1.setBackground(Color.darkGray);
        play1.setForeground(Color.BLACK);
        play1.setBorderPainted(false);
        play1.setFocusPainted(false);
        play1.setFont(new Font("MV Boli",Font.BOLD,50));
        play1.setFocusable(false);
        play1.addMouseListener(this);
        play1.setBounds(300, 300, 190, 100);
        button_panel.add(play1);

        play2.setBackground(Color.darkGray);
        play2.setForeground(Color.BLACK);
        play2.setBorderPainted(false);
        play2.setFocusPainted(false);
        play2.setFont(new Font("MV Boli",Font.BOLD,50));
        play2.setFocusable(false);
        play2.addMouseListener(this);
        play2.setBounds(510, 300, 190, 100);
        button_panel.add(play2);

        exit1.setBackground(Color.darkGray);
        exit1.setForeground(Color.BLACK);
        exit1.setBorderPainted(false);
        exit1.setFocusPainted(true);
        exit1.setFont(new Font("MV Boli",Font.BOLD,50));
        exit1.setFocusable(false);
        exit1.addMouseListener(this);
        exit1.setBounds(300, 450, 400, 100);
        button_panel.add(exit1);

        title_panel.add(textField);
        frame.add(title_panel,BorderLayout.CENTER);
        frame.add(button_panel);

    }
    @Override
    public void mouseClicked(MouseEvent e){

        if(e.getSource()== play1){
            frame.dispose();
            GamePanel p = new GamePanel();
            p.setFocusable(true);
            p.frame.setVisible(true);
        }

        if(e.getSource()== play2){
            frame.dispose();
            GamePanel p2 = new GamePanel();
            p2.setFocusable(true);
            p2.frame.setVisible(true);
            p2.DELAY = 55;
            p2.startGame();
        }

        if(e.getSource()== exit1){

            if(JOptionPane.showConfirmDialog(null, "Are you want exit the game ",
                    "Exit", JOptionPane.YES_NO_OPTION)==JOptionPane.YES_NO_OPTION){
                System.exit(0);
            }
        }
    }
    @Override
    public void mousePressed(MouseEvent e) {}
    @Override
    public void mouseReleased(MouseEvent e) {}
    @Override
    public void mouseEntered(MouseEvent e) {

        if(e.getSource()== play1){

            if(play1.getText()=="Easy"){

                play1.setForeground(Color.lightGray);
                play1.setBackground(Color.black
                );
            }
        }

        if(e.getSource()== play2){

            if(play2.getText()=="Hard"){

                play2.setForeground(Color.lightGray);
                play2.setBackground(Color.black);
            }
        }

        if(e.getSource()== exit1){

            if(exit1.getText()== "Exit"){

                exit1.setForeground(Color.black);
                exit1.setBackground(Color.red);
            }
        }
    }
    @Override
    public void mouseExited(MouseEvent e) {

        if(e.getSource()== play1){

            if(play1.getText()=="Easy"){
                play1.setForeground(Color.BLACK);
                play1.setBackground(Color.darkGray);
            }
        }

        if(e.getSource()== play2){

            if(play2.getText()=="Hard"){
                play2.setForeground(Color.BLACK);
                play2.setBackground(Color.darkGray);
            }
        }

        if(e.getSource()== exit1){

            if(exit1.getText()== "Exit"){
                exit1.setForeground(Color.black);
                exit1.setBackground(Color.darkGray);
            }
        }
    }
}