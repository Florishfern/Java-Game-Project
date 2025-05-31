import javax.swing.*;
import java.awt.*;
import java.util.Random;

public class Tree extends Object{
    private Random rand;
    private Image TreeImage;
    private int WIDTH = 48, HEIGHT = 48;
    private String[] pic = {"image/Tree1.png", "image/Tree2.png", "image/Tree3.png", "image/Tree4.png", "image/Tree5.png", "image/Tree6.png"};
    public Tree(int x, int y) {
        super(x, y);
        this.rand = new Random();
        this.TreeImage = new ImageIcon(pic[rand.nextInt(pic.length)]).getImage();
    }

    public void draw(Graphics g) {
        if (TreeImage != null) {
            g.drawImage(TreeImage, x, y, WIDTH, HEIGHT, null);
        } else {
            g.setColor(Color.YELLOW);
            g.fillRect(x, y, WIDTH, HEIGHT);
        }
    }
}
