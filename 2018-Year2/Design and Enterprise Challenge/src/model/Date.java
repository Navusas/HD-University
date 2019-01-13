package model;

import javax.swing.*;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.TimerTask;
import java.util.Timer;

/**
 * @author Domantas Giedraitis (student id: u1757704 )
 * @version 1
 * @since 2019-01-09
 */
public class Date {
    private JLabel timeLabel, dateLabel;
    private final SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
    private final SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm:ss");
    private int currentSecond;
    private Calendar calendar;

    public Date(JLabel timelabel, JLabel dateLabel) {
        this.timeLabel = timelabel;
        this.dateLabel = dateLabel;
        start();
    }

    private void reset() {
        currentSecond = Calendar.getInstance().get(Calendar.SECOND);
        calendar = Calendar.getInstance();
    }

    private void start() {
        reset();
        new Timer().scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                if (currentSecond == 60) {
                    reset();
                }
                timeLabel.setText(String.format("%02d:%02d:%02d", calendar.get(Calendar.HOUR),
                        calendar.get(Calendar.MINUTE),
                        currentSecond));
                dateLabel.setText(dateFormat.format(Calendar.getInstance().getTime()));
                currentSecond++;
            }
        }, 0, 1000);
    }

    public void startChronomter(Chronomter chronomter, JLabel label) {
        if (chronomter.isRunning()) {
            chronomter.stop();
        }
        label.setText("00:00:00");
        int newSecond = currentSecond;
        new Timer().scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                if (currentSecond != newSecond) {
                    chronomter.start();
                    cancel();
                }
            }
        }, 0, 1);
    }
}
