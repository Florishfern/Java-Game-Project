import javax.swing.*;
import java.awt.*;

public class Coin extends Object{
    private Image coinImage;

    public Coin(int x, int y) {
        super(x, y);
        this.coinImage = new ImageIcon("image/EggV2_.gif").getImage();
    }

    public void draw(Graphics g) {
        if (coinImage != null) {
            g.drawImage(coinImage, x, y, WIDTH, HEIGHT, null);
        } else {
            g.setColor(Color.YELLOW);
            g.fillRect(x, y, WIDTH, HEIGHT);
        }
    }
}
