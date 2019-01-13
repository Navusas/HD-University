package model;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.TimerTask;

/**
 * @author Domantas Giedraitis (student id: u1757704 )
 * @version 1
 * @since 2019-01-09
 */
public class Chronomter implements ActionListener {
    private int seconds;
    private int minutes;
    private int hours;
    private static Timer chronomter;
    private JLabel label;

    public Chronomter(JLabel label) {
        this.label = label;
        seconds = 0;
        minutes = 0;
        hours = 0;
        chronomter = new Timer(1000, this);
    }

    public void stop() {
        chronomter.stop();
        seconds = 0;
        minutes = 0;
        hours = 0;
    }

    public void start() {
        if (chronomter != null) {
            chronomter.start();
        }
    }

    public boolean isRunning() {
        return chronomter != null;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        seconds++;
        if (seconds == 60) {
            minutes++;
            seconds = 0;
            if (minutes == 60) {
                hours++;
                minutes = 0;
                if (hours == 24) {
                    hours = 0;
                }
            }
        }
        label.setText(String.format("%02d:%02d:%02d", hours, minutes, seconds));
    }

    public int getSeconds() {
        return seconds;
    }

    public void setSeconds(int seconds) {
        this.seconds = seconds;
    }

    public int getMinutes() {
        return minutes;
    }

    public void setMinutes(int minutes) {
        this.minutes = minutes;
    }

    public int getHours() {
        return hours;
    }

    public void setHours(int hours) {
        this.hours = hours;
    }
    //    public static void restart(int second) {
//        seconds = second;
//    }
//    public static void concurrencyTimer() {
//        int oldSecond = Date.getCurrentSecond();
//        new java.util.Timer().scheduleAtFixedRate(new TimerTask() {
//            @Override
//            public void run() {
//                if(Date.getCurrentSecond() != oldSecond) {
//                    int newSecond = oldSecond;
//                    Chronomter.restart();
//                }
//            }
//        }, 0, 1);
//    }
}
