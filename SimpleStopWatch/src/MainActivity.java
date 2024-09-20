import javax.swing.*;
import java.awt.*;

public class MainActivity {

    private static final JLabel timeLbl = new JLabel();

    private static int seconds = 0;
    private static int minutes = 0;
    private static int hours = 0;

    private static final Timer timer = new Timer(1000, e -> {
        updateTimer();
        timeLbl.setText(formatTimerString(seconds, minutes, hours));
    });

    private static String formatTimerString(int seconds_, int minutes_, int hours_) {
        return String.format("%02d:%02d:%02d", hours_, minutes_, seconds_);
    }

    private static void updateTimer() {
        seconds++;
        if (seconds == 60) {
            seconds = 0;
            minutes++;
            if (minutes == 60) {
                minutes = 0;
                hours++;
            }
        }
    }

    public static void showGUI() {

        JFrame frame = new JFrame("Simple Stop Watch");
        frame.setLayout(new BoxLayout(frame.getContentPane(), BoxLayout.Y_AXIS));

        JLabel title = new JLabel("Simple Stop Watch", SwingConstants.CENTER);
        title.setFont(new Font("Times Roman", Font.BOLD, 24));
        title.setOpaque(true);
        title.setBackground(Color.decode("#f0e9e9"));
        title.setAlignmentX(Component.CENTER_ALIGNMENT);
        frame.add(title);

        timeLbl.setFont(new Font("Times Roman", Font.BOLD, 16));
        timeLbl.setHorizontalAlignment(SwingConstants.CENTER);
        timeLbl.setAlignmentX(Component.CENTER_ALIGNMENT);
        frame.add(timeLbl);

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 10));

        JButton btnStart = new JButton("Start");
        JButton btnStop = new JButton("Stop");
        JButton btnReset = new JButton("Reset");

        btnStart.setBackground(Color.green);
        btnStart.addActionListener(e -> timer.start());

        btnStop.setBackground(Color.red);
        btnStop.addActionListener(e -> timer.stop());

        btnReset.setBackground(Color.yellow);
        btnReset.addActionListener(e -> resetTimer());

        buttonPanel.add(btnStart);
        buttonPanel.add(btnStop);
        buttonPanel.add(btnReset);

        frame.add(buttonPanel);

        frame.pack();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
        frame.setSize(350,200);
    }

    private static void resetTimer() {
        timer.stop();
        seconds = 0;
        minutes = 0;
        hours = 0;

        timeLbl.setText(formatTimerString(seconds, minutes, hours));
    }

    public static void main(String[] args) {
        javax.swing.SwingUtilities.invokeLater(MainActivity::showGUI);
    }
}