import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;


public class CharacterPage extends JPanel {
    private Image HomeBg;
    private Image pummai;
    private Image name_1;
    private Image name_2;
    private JButton player1;
    private JButton player2;
    private Image light_yellow;
    private Image light_blue;
    private Image thunderbolt;
    private ArrayList<Cloud> clouds;
    private Timer timer;
    private Image characterTextImage;
    JFrame frame;
    public Music bgMusic;

    public CharacterPage(JFrame frame) {
        
        this.frame = frame;

        setPreferredSize(new Dimension(600, 600));
        setLayout(null);
        setFocusable(true);   
        requestFocusInWindow(); 

        frame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowActivated(WindowEvent e) {
                requestFocusInWindow(); 
            }
        });

        bgMusic = new Music("ForCode/MUSIC_05.wav");

        HomeBg = new ImageIcon("image/HomeBg.png").getImage();
        name_2= new ImageIcon("image/name2.png").getImage();
        name_1 = new ImageIcon("image/name1.png").getImage();
        pummai = new ImageIcon("image/Pummai.png").getImage();
        characterTextImage = new ImageIcon("image/Character.png").getImage();
        light_blue = new ImageIcon("image/light_blue.png").getImage();
        thunderbolt = new ImageIcon("image/thunderbolt.png").getImage();
        light_yellow = new ImageIcon("image/light_yellow.png").getImage();



        clouds = new ArrayList<>();
        clouds.add(new Cloud("image/Cloud1.png", -203, 125, 405, 125));
        clouds.add(new Cloud("image/Cloud2.png", 108, 0, 144, 68));
        clouds.add(new Cloud("image/Cloud3.png", 404, 0, 204, 85));
        clouds.add(new Cloud("image/Cloud4.png", 608, 126, 162, 95));

        player1 = new Button("image/Down-stop.png", "image/Player_1Down.gif", 315, 208, 215, 215);
        player1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.getContentPane().removeAll(); 
                frame.add(new LevelPage(frame, 1));      
                frame.pack();
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.setVisible(true);
                frame.revalidate();
                frame.repaint();
                bgMusic.stopMusic();
            }
        });
        add(player1);
        player2 = new Button("image/Down-stop2.png", "image/Player_2Down.gif", 82, 251, 170, 170);
        player2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.getContentPane().removeAll(); 
                frame.add(new LevelPage(frame, 2));      
                frame.pack();
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.setVisible(true);
                frame.revalidate();
                frame.repaint();
                bgMusic.stopMusic();
            }
        });
        add(player2);

        bgMusic.playMusic(true, -20.0f);

        timer = new Timer(5, e -> repaint());
        timer.start();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        g.drawImage(HomeBg, 0, 0, 600, 600, this);

        for (Cloud cloud : clouds) {
            cloud.draw(g);
            cloud.update();
        }

        if (pummai != null) {
            g.drawImage(pummai, 0, 0, 600, 600, this);
            g.drawImage(light_blue, 243, 168, 343, 343, this);
            g.drawImage(thunderbolt, 263, 171, 332, 332, this);
            g.drawImage(light_yellow, -35, 120, 400, 400, this);
            g.drawImage(name_2, 85, 451, 175, 43, null);
            g.drawImage(name_1, 353, 448, 150, 46, null);
            g.drawImage(characterTextImage, 105, 60, 400, 150, this);

        } else {
            System.out.println("Image not found.");
        }
    }
    
}
