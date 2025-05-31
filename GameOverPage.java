import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class GameOverPage extends JPanel {
    private Image HomeBg;
    private Image treeRed2Image;
    private Image treeRed1Image;
    private Image pummai;
    private Image scoreTextImage;
    private Image scoreDisplay;
    private JButton RestartButton;
    private JButton MenuButton;
    private ArrayList<Cloud> clouds;
    private Timer timer;
    private int score;
    
    private Image GameOverTextImage;
    JFrame frame;

    public GameOverPage(JFrame frame, int score, int level, int playerSkin) {
        this.frame = frame;
        this.score = score;

        setPreferredSize(new Dimension(600, 600));
        setLayout(null);
        setFocusable(true);               // ทำให้ JPanel โฟกัสได้
        requestFocusInWindow(); 

        frame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowActivated(WindowEvent e) {
                requestFocusInWindow();   // เมื่อหน้าต่างถูกเปิด ให้โฟกัส
            }
        });

        HomeBg = new ImageIcon("image/HomeBg.png").getImage();
        treeRed2Image = new ImageIcon("image/Tree3.png").getImage();
        treeRed1Image = new ImageIcon("image/Tree3.png").getImage();
        pummai = new ImageIcon("image/Pummai.png").getImage();
        GameOverTextImage = new ImageIcon("image/GameOver.png").getImage();
        scoreTextImage = new ImageIcon("image/Score.png").getImage();
        scoreDisplay = new ImageIcon("image/ScoreDisplay.png").getImage();

        clouds = new ArrayList<>();
        clouds.add(new Cloud("image/Cloud1.png", -120, 44, 410, 128));
        clouds.add(new Cloud("image/Cloud2.png", 268, 2, 174, 82));
        clouds.add(new Cloud("image/Cloud3.png", 400, 66, 200, 100));
        clouds.add(new Cloud("image/Cloud4.png", 480, 240, 200, 118));

        RestartButton = new Button("image/RestartButton.png", "image/RestartButtonpress.png", 73, 342, 208, 93);
        RestartButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.getContentPane().removeAll();
                JurassicRoad jurassicRoad = new JurassicRoad(frame, level, playerSkin); 
                frame.add(jurassicRoad);
                frame.pack();
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.setVisible(true);

                jurassicRoad.setFocusable(true);  
                jurassicRoad.requestFocusInWindow();
            }
        });
        add(RestartButton);
        MenuButton = new Button("image/MenuButton.png", "image/MenuButtonpress.png", 324, 342, 201, 95);
        MenuButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.getContentPane().removeAll(); 
                frame.add(new Home(frame));      
                frame.pack();
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.setVisible(true);
                frame.revalidate();
                frame.repaint();
            }
        });
        add(MenuButton);


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

        if (treeRed2Image != null) {
            g.drawImage(pummai, 0, 0, 600, 600, this);
            g.drawImage(treeRed2Image, 316, 365, 224, 217, this);
            g.drawImage(treeRed1Image, 68, 365, 224, 217, this);
            g.drawImage(GameOverTextImage, 95, 34, 420, 154, this);
            g.drawImage(scoreTextImage, 39, 236, 268, 59, this);
            g.drawImage(scoreDisplay, 312, 211, 263, 109, this);

        } else {
            System.out.println("Image not found.");
        }

        g.setColor(Color.BLACK);
        g.setFont(new Font("Arial", Font.BOLD, 52));
        g.drawString(""+score, 400, 280);

        // repaint();
    }
}

