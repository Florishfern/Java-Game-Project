import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;

import javax.swing.ImageIcon;

public class Cloud {
    private Image cloudImage;
    private int x, y, WIDTH, HEIGHT;
    private int speed = 2;

    public Cloud(String cloud, int x, int y, int WIDTH, int HEIGHT){
        this.x = x;
        this.y = y;
        this.WIDTH = WIDTH;
        this.HEIGHT = HEIGHT;
        this.cloudImage = new ImageIcon(cloud).getImage();
    }

    public void draw(Graphics g) {
        if (cloudImage != null) {
            g.drawImage(cloudImage, x, y, WIDTH, HEIGHT, null);
        } else {
            g.setColor(Color.RED);
            g.fillRect(x, y, WIDTH, HEIGHT);
        }
    }
    
    public void update() {
        x += speed;
        if (x > 600) {
            x = 0 - 410;
        }
    }
} 