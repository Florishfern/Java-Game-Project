import javax.swing.*;
import java.awt.*;

public class CoinG extends Object{
    private Image coinImage;

    public CoinG(int x, int y) {
        super(x, y);
        this.coinImage = new ImageIcon("image/DinoEggG.gif").getImage();
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
