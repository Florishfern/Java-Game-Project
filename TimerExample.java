import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TimerExample {
    private int timeCounter = 0;
    private Timer timer;

    public TimerExample() {
        // กำหนดเวลา interval (1000 milliseconds = 1 วินาที)
        timer = new Timer(1000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                timeCounter++;
            }
        });
        timer.start();
    }

    public int getTime(){
        return timeCounter;
    }
}
