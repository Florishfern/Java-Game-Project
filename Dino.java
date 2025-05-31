import java.awt.*;

public class Dino {
    protected int x, y, speed;
    protected int WIDTH = 48, HEIGHT = 48;

    public Dino(int x, int y, int speed) {
        this.x = x;
        this.y = y;
        this.speed = speed;
    }

    public int getY(){
        return this.y;
    }
    
    public void move(int dx, int dy) {
        x += dx;
        y += dy;
    }
        
    public void update() {
        x += speed;
        if (x > 600) {
            x = -WIDTH;
        }
    }

    public boolean intersects(Player player) {
        return getBounds().intersects(player.getBounds());
    }

    public Rectangle getBounds() {
        return new Rectangle(x, y, WIDTH, HEIGHT);
    } 
}
