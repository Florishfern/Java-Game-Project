import javax.swing.*;
import java.awt.*;

public class DinoT_rexL extends Dino{
    protected Image dinoImage;

    public DinoT_rexL(int x, int y, int speed, int level) {
        super(x, y, speed);
        if(level == 2){
            this.dinoImage = new ImageIcon("image/DINO1L.gif").getImage();
        }     
        else if(level == 5){
            this.dinoImage = new ImageIcon("image/T-rexL.gif").getImage();
            super.WIDTH = 72;
        }     
        else{
            this.dinoImage = new ImageIcon("image/SauropodL.gif").getImage();
            super.WIDTH = 72;
        }
    }

    public void draw(Graphics g) {
        if (dinoImage != null) {
            g.drawImage(dinoImage, x, y, WIDTH, HEIGHT, null);
        } else {
            g.setColor(Color.RED);
            g.fillRect(x, y, WIDTH, HEIGHT);
        }
    }

    public void update() {
        x += speed;
        if (x > 600) {
            x = -WIDTH;
        }
    }
}
