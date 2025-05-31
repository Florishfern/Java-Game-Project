import java.awt.*;

import javax.swing.ImageIcon;

public class Player {
    private int x, y;
    private final int SIZE = 48;
    private Image playerImage1;
    private Image playerImage2;
    private int playerSkin;

    public Player(int x, int y, int playerSkin) {
        this.playerSkin = playerSkin;
        this.x = x;
        this.y = y;
        this.playerImage1 = new ImageIcon("image/Down-stop.png").getImage();
        this.playerImage2 = new ImageIcon("image/Down-stop2.png").getImage();
    }

    public void draw(Graphics g) {
        if (playerImage1 != null && playerSkin == 1) {
            g.drawImage(playerImage1, x, y, SIZE, SIZE, null);
        }
        else if (playerImage2 != null && playerSkin == 2) {
            g.drawImage(playerImage2, x, y, SIZE, SIZE, null);
        }
        else {
            g.setColor(Color.YELLOW);
            g.fillRect(x, y, SIZE, SIZE);
        }
    }

    public void setImage(String newImage){
        if (playerImage1 != null && playerSkin == 1) {
            this.playerImage1 = new ImageIcon(newImage).getImage();
        }
        else if (playerImage2 != null && playerSkin == 2) {
            this.playerImage2 = new ImageIcon(newImage).getImage();
        }
    }

    public void move(int dx, int dy) {
        x += dx;
        y += dy;

        if(x >= 576 - SIZE) x = 576 - SIZE;
        if(x <= 0) x = 0;
        if(y >= 600 - SIZE) y = 576;
    }

    public int gety(){
        return this.y;
    }

    public void update() {}

    public Rectangle getBounds() {
        return new Rectangle(x, y, SIZE, SIZE);
    }
}
