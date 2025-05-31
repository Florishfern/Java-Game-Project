import javax.swing.*;
import java.awt.*;

public class DinoT_rexR extends Dino{
    protected Image dinoImage;

    public DinoT_rexR(int x, int y, int speed, int level) {
        super(x, y, speed);
        if(level == 2){
            this.dinoImage = new ImageIcon("image/DINO1R.gif").getImage();
        }
        else if(level == 5){
            this.dinoImage = new ImageIcon("image/T-rexR.gif").getImage();
            super.WIDTH = 72;
        }    
        else{
            this.dinoImage = new ImageIcon("image/SauropodR.gif").getImage();
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
        x -= speed;
        if (x < 0) {
            x = -WIDTH;
        }
    }
}
