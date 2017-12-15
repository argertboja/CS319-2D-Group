package window;

import userinterface.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Window extends JFrame{

    private ImageIcon iconHolderIcon, helpIcon, helpIconHover, settingsIcon, settingsIconHover, pauseIcon, pauseIconHover ;
    private JLabel iconHolder, help, settings, pause, pauseOpacity;
    private MyMouseHandler mouseHandler;
    private GameEngine gameEngine;

    public Window (int width, int height, String title, GameEngine gameEngine) {
        mouseHandler = new MyMouseHandler();
        gameEngine.setPreferredSize(new Dimension(width, height));
        gameEngine.setMaximumSize(new Dimension(width, height));
        gameEngine.setMinimumSize(new Dimension(width, height));
        this.gameEngine = gameEngine;
        setTitle(title);

        pauseOpacity = new JLabel();
        pauseOpacity.setBounds(0,0,1100,550);
        pauseOpacity.setBackground(new Color(0,0,0,64));

        iconHolder = new JLabel();
        iconHolderIcon = new ImageIcon(getClass().getResource("/images/iconHolder.png"));
        iconHolder.setIcon(iconHolderIcon);
        iconHolder.setBounds(width-250, height-50, 250, 50);


        helpIcon = new ImageIcon(getClass().getResource("/images/helpIcon.png"));
        helpIconHover = new ImageIcon(getClass().getResource("/images/helpIconHover.png"));
        help = new JLabel ();
        help.setIcon(helpIcon);
        help.setBounds(30, 10, 32, 38);
        help.addMouseListener(mouseHandler);
        iconHolder.add(help);

        settingsIcon = new ImageIcon(getClass().getResource("/images/settingsIcon.png"));
        settingsIconHover = new ImageIcon(getClass().getResource("/images/settingsIconHover.png"));
        settings = new JLabel ();
        settings.setIcon(settingsIcon);
        settings.setBounds(100, 10, 40, 38);
        settings.addMouseListener(mouseHandler);
        iconHolder.add(settings);

        pauseIcon = new ImageIcon(getClass().getResource("/images/pauseIcon.png"));
        pauseIconHover = new ImageIcon(getClass().getResource("/images/pauseIconHover.png"));
        pause = new JLabel ();
        pause.setIcon(pauseIcon);
        pause.setBounds(180, 10, 32, 38);
        pause.addMouseListener(mouseHandler);
        iconHolder.add(pause);

        add(iconHolder);
        add(gameEngine);
        pack();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        setLocationRelativeTo(null);

        // Set Frame Icon
        String path = "/images/icon.png";
        ImageIcon imageIcon = new ImageIcon(getClass().getResource(path));

        setIconImage(imageIcon.getImage());



        gameEngine.start();
        setVisible(true);
    }

    public void quitGame() {
        dispose();
    }

    // Private class for Mouse Handler
    private class MyMouseHandler extends MouseAdapter {
        @Override
        public void mouseEntered(MouseEvent e) {
            JLabel source = (JLabel) e.getSource();
            if (source == help) {
                help.setIcon(helpIconHover);
            }
            else if (source == pause) {
                pause.setIcon(pauseIconHover);
            }
            else if (source == settings) {
                settings.setIcon(settingsIconHover);
            }
        }
        @Override
        public void mouseClicked(MouseEvent e) {
            JLabel source = (JLabel) e.getSource();
            if (source == help) {
                new Help("game");
                gameEngine.setPlaying(false);
            }
            else if (source == settings) {
                new SettingsMenu("game");
            }
            else if (source == pause) {
                new PauseMenu();
            }
        }

        @Override
        public void mouseExited(java.awt.event.MouseEvent e) {
            JLabel source = (JLabel) e.getSource();
            if (source == help) {
                help.setIcon(helpIcon);
            }
            else if (source == pause) {
                pause.setIcon(pauseIcon);
            }
            else if (source == settings) {
                settings.setIcon(settingsIcon);
            }
        }
    }
}