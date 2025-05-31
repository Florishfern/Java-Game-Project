import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.ImageIcon;
import javax.swing.JButton;

public class Button extends JButton {
    private Image buttonImage;
    private Image buttonImageHover;
    private Music clickMusic;

    public Button(String imagePath, String imagePath2, int x, int y, int WIDTH, int HEIGHT) {
        clickMusic = new Music("ForCode/lclick.wav");

        ImageIcon originalIcon = new ImageIcon(imagePath);
        buttonImage = originalIcon.getImage().getScaledInstance(WIDTH, HEIGHT, Image.SCALE_DEFAULT);

        ImageIcon hoverIcon = new ImageIcon(imagePath2);
        buttonImageHover = hoverIcon.getImage().getScaledInstance(WIDTH, HEIGHT, Image.SCALE_DEFAULT);

        setIcon(new ImageIcon(buttonImage));
        setBounds(x, y, WIDTH, HEIGHT);
        setBorderPainted(false);
        setContentAreaFilled(false);
        setFocusPainted(false);

        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                setIcon(new ImageIcon(buttonImageHover));
                clickMusic.playMusic(false, 0);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                setIcon(new ImageIcon(buttonImage));
            }
        });

    }
}

