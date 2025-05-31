import java.awt.*;

public class Object {
    protected int x, y;
    protected final int WIDTH = 48, HEIGHT = 48;
    protected Image coinImage;

    public Object(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getY(){
        return this.y;
    }

    public void draw(Graphics g) {
        if (coinImage != null) {
            g.drawImage(coinImage, x, y, WIDTH, HEIGHT, null);
        } else {
            g.setColor(Color.YELLOW);
            g.fillRect(x, y, WIDTH, HEIGHT);
        }
    }

    public void move(int dx, int dy) {
        x += dx;
        y += dy;
    
    }

    public void update() {}

    public boolean intersects(Player player) {
        return getBounds().intersects(player.getBounds());
    }

    public boolean intersects(Object object) {
        return getBounds().intersects(object.getBounds());
    }

    public Rectangle getBounds() {
        return new Rectangle(x, y, WIDTH, HEIGHT);
    }

    
}