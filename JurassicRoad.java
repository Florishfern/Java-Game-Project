import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Random;

public class JurassicRoad extends JPanel implements ActionListener, KeyListener {
    JFrame frame;
    private Timer timer;
    private Player player;
    private ArrayList<Dino> Dinos;
    private ArrayList<Object> objects;
    private Random rand;
    private int score = 0;
    private int scorebg = 0;
    private Image backgroundImage1;
    private Image backgroundImage2;
    private int bgY = 0;
    private boolean upPressed, downPressed, leftPressed, rightPressed;
    private int level;
    private boolean changeBg = false;
    private Image houseimg;
    private int Yhouse = 288;
    private int playerSkin;
    Music bgMusic;
    Music eggMusic;
    Music eggGMusic;
    Music DeadMusic;

    public JurassicRoad(JFrame frame, int level, int playerSkin) {
        this.level = level;
        this.frame = frame;
        this.playerSkin = playerSkin;
        setPreferredSize(new Dimension(600, 600));

        eggMusic = new Music("ForCode/NORMAL_EGG_PICKUP.wav");
        eggGMusic = new Music("ForCode/GOLD_EGG_PICKUP.wav");
        bgMusic = new Music("ForCode/MENU_SOUND.wav");
        DeadMusic = new Music("ForCode/EFFECT_03.wav");
        backgroundImage1 = new ImageIcon("image/Map-2.png").getImage();
        backgroundImage2 = new ImageIcon("image/Map-2.png").getImage();
        houseimg = new ImageIcon("image/New-house.png").getImage();
        player = new Player(288, 288, playerSkin);
        Dinos = new ArrayList<>();
        objects = new ArrayList<>();
        rand = new Random();
        timer = new Timer(30, this);
        timer.start();

        frame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowActivated(WindowEvent e) {
                requestFocusInWindow();  
            }
        });

        bgMusic.playMusic(true, -20.0f);

        setFocusable(true);             
        requestFocusInWindow();           
        addKeyListener(this);  
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        if (backgroundImage1 != null && backgroundImage2 != null) {
            g.drawImage(backgroundImage1, 0, bgY, getWidth(), getHeight(), this);
            g.drawImage(backgroundImage2, 0, bgY - getHeight(), getWidth(), getHeight(), this);
        }

        player.draw(g);
        
        g.drawImage(houseimg, 288-10, Yhouse-10, 65, 65, this);

        for (Dino DinoA : Dinos) {
            if (DinoA instanceof DinoT_rexL) {
                ((DinoT_rexL) DinoA).draw(g);
            }
            if (DinoA instanceof DinoT_rexR) {
                ((DinoT_rexR) DinoA).draw(g);
            }
            // DinoA.update();
        }

        for (int i = 0; i < objects.size(); i++) {
            for (int j = 0; j < objects.size(); j++) {
                if (objects.get(i).intersects(objects.get(j)) && i != j) {
                    objects.remove(j);
                }
            }
            objects.get(i).draw(g);
        }

        g.setColor(Color.BLACK);
        g.drawString("Score: " + score, 10, 20);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        int dinopop = rand.nextInt(100);
        int objectpop = rand.nextInt(100);
        if (dinopop < 3 + this.level) {
            if (rand.nextBoolean()) {
                Dinos.add(new DinoT_rexL(-72, rand.nextInt(12) * 48, rand.nextInt(5) + this.level, this.level));
            } else {
                Dinos.add(new DinoT_rexR(600, rand.nextInt(12) * 48, rand.nextInt(5) + this.level, this.level));
            }
        }
        if (objectpop <= 3) {
            if(objectpop <= 2)
                objects.add(new Coin((rand.nextInt(9) + 2) * 48, 0));
            else
                objects.add(new CoinG((rand.nextInt(9) + 2) * 48, 0));
        }
        if (objectpop < 10 && objectpop > 3) {
            objects.add(new Tree((rand.nextInt(9) + 2) * 48, (rand.nextBoolean() ? 0 : -1 ) * rand.nextInt(5) * 48));
        }
        player.update();
        for (Dino DinoA : Dinos) {
            DinoA.update();
            if (DinoA.intersects(player)) {
                DeadMusic.playMusic(false, 0);
                bgMusic.stopMusic();
                timer.stop();
                frame.getContentPane().removeAll();
                frame.add(new GameOverPage(frame, this.score, this.level, this.playerSkin));
                frame.pack();
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.setVisible(true);
                frame.revalidate();
                frame.repaint();
            }
        }

        for (int i = 0; i < objects.size(); i++) {
            objects.get(i).update();
            if (objects.get(i).intersects(player)) {
                if (objects.get(i) instanceof Coin) {
                    eggMusic.playMusic(false, 0);
                    objects.remove(i);
                    score++;
                    scorebg++;
                }
                else if (objects.get(i) instanceof CoinG) {
                    eggGMusic.playMusic(false, 0);
                    objects.remove(i);
                    score += 3;
                    scorebg += 3;
                }
            } 
            else if (objects.get(i).getY() > 600) {
                objects.remove(i);
            }
        }

        repaint();
    }

    @Override
    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();
        int moveAmount = 48;
        Rectangle playerBounds = player.getBounds();

        if ((key == KeyEvent.VK_W || key == KeyEvent.VK_UP) && !upPressed && player.gety() == 288) {
            boolean canMoveUp = true;
            upPressed = true;
            if(playerSkin == 1)
                player.setImage("image/Player_1Up.gif");
            else if(playerSkin == 2)
                player.setImage("image/Player_2Up.gif");
            for (Object objectA : objects) {
                if (objectA instanceof Tree && objectA.getBounds().intersects(playerBounds.x, playerBounds.y - moveAmount, playerBounds.width, playerBounds.height)) {
                    canMoveUp = false;
                    break;
                }
            }
            if (canMoveUp) {
                for (Dino DinoA : Dinos) {
                    DinoA.move(0, moveAmount);
                }
                for (Object objectA : objects) {
                    objectA.move(0, moveAmount);
                }
                bgY += moveAmount;
                Yhouse += moveAmount;
            }
        }
        else if ((key == KeyEvent.VK_W || key == KeyEvent.VK_UP) && !upPressed ) {
            boolean canMoveUp = true;
            upPressed = true;
            if(playerSkin == 1)
                player.setImage("image/Player_1Up.gif");
            else if(playerSkin == 2)
                player.setImage("image/Player_2Up.gif");
            for (Object objectA : objects) {
                if (objectA instanceof Tree && objectA.getBounds().intersects(playerBounds.x, playerBounds.y - moveAmount, playerBounds.width, playerBounds.height)) {
                    canMoveUp = false;
                    break;
                }
            }
            if (canMoveUp) {
                player.move(0, -moveAmount);
            }
        }

        if ((key == KeyEvent.VK_S || key == KeyEvent.VK_DOWN) && !downPressed) {
            boolean canMoveDown = true;
            downPressed = true;
            if(playerSkin == 1)
                player.setImage("image/Player_1Down.gif");
            else if(playerSkin == 2)
                player.setImage("image/Player_2Down.gif");
            for (Object objectA : objects) {
                if (objectA instanceof Tree && objectA.getBounds().intersects(playerBounds.x, playerBounds.y + moveAmount, playerBounds.width, playerBounds.height)) {
                    canMoveDown = false;
                    break;
                }
            }
            if (canMoveDown) {
                player.move(0, moveAmount);
            }
        }

        if ((key == KeyEvent.VK_A || key == KeyEvent.VK_LEFT) && !leftPressed) {
            boolean canMoveLeft = true;
            leftPressed = true;
            if(playerSkin == 1)
                player.setImage("image/Player_1Left.gif");
            else if(playerSkin == 2)
                player.setImage("image/Player_2Left.gif");
            for (Object objectA : objects) {
                if (objectA instanceof Tree && objectA.getBounds().intersects(playerBounds.x - moveAmount, playerBounds.y, playerBounds.width, playerBounds.height)) {
                    canMoveLeft = false;
                    break;
                }
            }
            if (canMoveLeft) {
                player.move(-moveAmount, 0);
            }
        }

        if ((key == KeyEvent.VK_D || key == KeyEvent.VK_RIGHT) && !rightPressed) {
            boolean canMoveRight = true;
            rightPressed = true;
            if(playerSkin == 1)
                player.setImage("image/Player_1Right.gif");
            else if(playerSkin == 2)
                player.setImage("image/Player_2Right.gif");
            for (Object objectA : objects) {
                if (objectA instanceof Tree && objectA.getBounds().intersects(playerBounds.x + moveAmount, playerBounds.y, playerBounds.width, playerBounds.height)) {
                    canMoveRight = false;
                    break;
                }
            }
            if (canMoveRight) {
                player.move(moveAmount, 0);
            }
        }

        if (bgY >= getHeight()) {
            if(changeBg){
                backgroundImage1 = backgroundImage2;
                changeBg =false;
            }
            if(this.scorebg >= 40){
                backgroundImage2 = new ImageIcon("image/Map-2.png").getImage();
                this.scorebg = 0;
                changeBg = true;
            }
            else if(this.scorebg >= 30){
                backgroundImage2 = new ImageIcon("image/Map-4.png").getImage();
                changeBg = true;
            }
            else if(this.scorebg >= 20){
                backgroundImage2 = new ImageIcon("image/Map-3.png").getImage();
                changeBg = true;
            }
            else if(this.scorebg >= 10){
                backgroundImage2 = new ImageIcon("image/Map-1.png").getImage();
                changeBg = true;
            }
            bgY = 0;
        }
        
        repaint();
    }

    @Override
    public void keyReleased(KeyEvent e) {
        int key = e.getKeyCode();
        if (key == KeyEvent.VK_W || key == KeyEvent.VK_UP){
            upPressed = false;
        }   
        if (key == KeyEvent.VK_S || key == KeyEvent.VK_DOWN){
            downPressed = false;
        }  
        if (key == KeyEvent.VK_A || key == KeyEvent.VK_LEFT){
            leftPressed = false;
        } 
        if (key == KeyEvent.VK_D || key == KeyEvent.VK_RIGHT){
            rightPressed = false;
        }  
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

}
