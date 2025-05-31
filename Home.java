import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
// import javax.sound.sampled.*;
// import java.io.File;
// import java.io.IOException;

public class Home extends JPanel {
    private Image treeImage;
    private Image HomeBg;
    private Image pummai;
    private Image dinoImage;
    private ArrayList<Cloud> clouds;
    private Timer timer;
    private JButton StartButton;
    private JButton ExitButton;
    private Image gameName;
    public JFrame frame;
    public Music bgMusic;

    public Home(JFrame frame) {
        this.frame = frame;

        setPreferredSize(new Dimension(600, 600));
        setLayout(null);

        bgMusic = new Music("ForCode/MUSIC_05.wav");

        HomeBg = new ImageIcon("image/HomeBg.png").getImage();
        treeImage = new ImageIcon("image/TreeInHome.png").getImage();
        pummai = new ImageIcon("image/Pummai.png").getImage();
        dinoImage = new ImageIcon("image/DINO1R.gif").getImage();
        gameName = new ImageIcon("image/GameName.png").getImage();

        clouds = new ArrayList<>();
        clouds.add(new Cloud("image/Cloud1.png", -120, 44, 410, 128));
        clouds.add(new Cloud("image/Cloud2.png", 268, 2, 174, 82));
        clouds.add(new Cloud("image/Cloud3.png", 400, 66, 200, 100));
        clouds.add(new Cloud("image/Cloud4.png", 480, 240, 200, 118));

        StartButton = new Button("image/playButton.png", "image/playbuttonpress.png", 197, 256, 206, 88);
        StartButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.getContentPane().removeAll(); 
                frame.add(new CharacterPage(frame));      
                frame.pack();
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.setVisible(true);
                frame.revalidate();
                frame.repaint();
                bgMusic.stopMusic();
            }
        });
        add(StartButton);

        ExitButton = new Button("image/exitButton.png", "image/exitbuttonpress.png", 197, 365, 206, 88);
        ExitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                bgMusic.stopMusic();
                System.exit(0);
            }
        });
        add(ExitButton);

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

        if (treeImage != null) {
            g.drawImage(treeImage, -120, 600 - 390, 328, 390, this);
            g.drawImage(dinoImage, 403, 344, 225, 225, null);
            g.drawImage(pummai, 0, 0 , 600, 600, this);
            g.drawImage(gameName, 71, 30, 477, 200, this);
        } else {
            System.out.println("Image not found.");
        }

    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("RUN REX RUN!!!");
        Home home = new Home(frame);
        frame.add(home);
        frame.pack();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}
