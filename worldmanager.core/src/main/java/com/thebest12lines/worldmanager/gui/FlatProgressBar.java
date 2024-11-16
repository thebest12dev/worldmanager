package com.thebest12lines.worldmanager.gui;
import javax.swing.*;
import javax.swing.plaf.basic.BasicProgressBarUI;
import java.awt.*;

public class FlatProgressBar {
    public static JProgressBar createFlatProgressBar() {
        // Create a new JProgressBar
        JProgressBar progressBar = new JProgressBar();
        progressBar.setMinimum(0);
        progressBar.setMaximum(100);
        progressBar.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10)); // Set border
        progressBar.setPreferredSize(new Dimension(400, 5));
        // Customize the look of the progress bar
        progressBar.setUI(new BasicProgressBarUI() {
            @Override
            protected Dimension getPreferredInnerHorizontal() {
                return new Dimension(0, 0);
            }

            @Override
            protected void paintDeterminate(Graphics g, JComponent c) {
                Graphics2D g2d = (Graphics2D) g;
                g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                int width = progressBar.getWidth();
                int height = progressBar.getHeight();

                g2d.setColor(Color.LIGHT_GRAY);
                g2d.fillRoundRect(0, 0, width, height, height, height);

                g2d.setColor(new Color(0, 145, 255));
                int progress = progressBar.getValue();
                int min = progressBar.getMinimum();
                int max = progressBar.getMaximum();
                int progressWidth = (int) ((double) (progress - min) / (max - min) * width);
                g2d.fillRoundRect(0, 0, progressWidth, height, height, height);
            }

            @Override
            protected void paintIndeterminate(Graphics g, JComponent c) {
                paintDeterminate(g, c); // For simplicity, use the same painting logic
            }
        });
        return progressBar;
    }
}
