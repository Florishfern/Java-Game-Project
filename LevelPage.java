import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;


public class LevelPage extends JPanel {
    private Image HomeBg;
    private Image treeRedImage;
    private Image pummai;
    private Image treeYellowImage;
    private Image birdImage;
    private ArrayList<Cloud> clouds;
    private Timer timer;
    private JButton EasyButton;
    private JButton NormalButton;
    private JButton AdvanceButton;
    private Image levelsTextImage;
    private int dx = 33;
    private int dy = 207;
    JFrame frame;
    public Music bgMusic;
    public Music birdflyMusic;
    private boolean check = true;
    

    public LevelPage(JFrame frame, int playerSkin) {
        
        this.frame = frame;
        // this.playerSkin = playerSkin;
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
        birdflyMusic = new Music("ForCode/Wing_Flap.wav");

        HomeBg = new ImageIcon("image/HomeBg.png").getImage();
        treeRedImage = new ImageIcon("image/Tree6.png").getImage();
        pummai = new ImageIcon("image/Pummai.png").getImage();
        treeYellowImage = new ImageIcon("image/Tree2.png").getImage();
        levelsTextImage = new ImageIcon("image/Levels.png").getImage();
        birdImage = new ImageIcon("image/Pteranodon.gif").getImage();

        clouds = new ArrayList<>();
        clouds.add(new Cloud("image/Cloud1.png", -120, 44, 410, 128));
        clouds.add(new Cloud("image/Cloud2.png", 268, 2, 174, 82));
        clouds.add(new Cloud("image/Cloud3.png", 400, 66, 200, 100));
        clouds.add(new Cloud("image/Cloud4.png", 480, 240, 200, 118));

        EasyButton = new Button("image/EasyButton.png", "image/EasyButton.png", 215, 187, 173, 83);
        // EasyButton = new Button("image/E1.png", "image/E2.png", 215, 187, 173, 83);
        EasyButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(check){
                    check = false;
                    bgMusic.stopMusic();
                    birdflyMusic.playMusic(true, 0);
                    birdMove(() -> {
                        frame.getContentPane().removeAll();
                        JurassicRoad jurassicRoad = new JurassicRoad(frame, 2, playerSkin); 
                        frame.add(jurassicRoad);
                        frame.pack();
                        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                        frame.setVisible(true);

                        jurassicRoad.setFocusable(true); 
                        jurassicRoad.requestFocusInWindow();
                    });
                }
            }
        });
        add(EasyButton);
        NormalButton = new Button("image/NormalButton.png", "image/NormalButton.png", 185, 285, 230, 92);
        // NormalButton = new Button("image/N1.png", "image/N2.png", 185, 285, 230, 92);
        NormalButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(check){
                    check = false;
                    bgMusic.stopMusic();
                    birdflyMusic.playMusic(true, 0);
                    birdMove(() -> {
                        frame.getContentPane().removeAll();
                        JurassicRoad jurassicRoad = new JurassicRoad(frame, 5, playerSkin); 
                        frame.add(jurassicRoad);
                        frame.pack();
                        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                        frame.setVisible(true);

                        jurassicRoad.setFocusable(true); 
                        jurassicRoad.requestFocusInWindow();
                    });
                }
            }
        });
        add(NormalButton);

        AdvanceButton = new Button("image/AdvanceButton.png", "image/AdvanceButton.png", 185, 385, 236, 93);
        // AdvanceButton = new Button("image/A1.png", "image/A2.png", 185, 385, 236, 93);
        AdvanceButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(check){
                    check = false;
                    bgMusic.stopMusic();
                    birdflyMusic.playMusic(true, 0);
                    birdMove(() -> {
                        frame.getContentPane().removeAll();
                        JurassicRoad jurassicRoad = new JurassicRoad(frame, 12, playerSkin); 
                        frame.add(jurassicRoad);
                        frame.pack();
                        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                        frame.setVisible(true);

                        jurassicRoad.setFocusable(true); 
                        jurassicRoad.requestFocusInWindow();
                    });
                }
            }
        });
        add(AdvanceButton);

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

        if (treeRedImage != null) {
            g.drawImage(pummai, 0, 0, 600, 600, this);
            g.drawImage(treeYellowImage, 417, 255, 200, 328, null);
            g.drawImage(treeRedImage, -16, 362, 193, 231, this);
            g.drawImage(levelsTextImage, 80, 45, 460, 145, this);
            g.drawImage(birdImage, dx, dy, 167, 125, this);
        } else {
            System.out.println("Image not found.");
        }
        
    }

    public void birdMove(Runnable onComplete) {
        Timer birdTimer = new Timer(20, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                
                if (dx >= 600) {
                    ((Timer) e.getSource()).stop(); 
                    birdflyMusic.stopMusic();
                    onComplete.run(); 
                    
                } else {
                    dx += 10; 
                    repaint();
                }
            }
        });
        birdTimer.start();
    }
    
    
    
}
