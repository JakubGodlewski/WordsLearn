package com.godlewski.gui;

import com.godlewski.dao.DatabaseInterfaceImpl;
import com.godlewski.domain.User;
import com.godlewski.domain.UserWordCategoryLanguage;
import com.godlewski.domain.models.CustomTableModel;
import com.godlewski.gui.learning.HurryupmodePanel;
import com.godlewski.gui.learning.LearningmodePanel;
import com.godlewski.gui.learning.RepeatsmodePanel;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

/**
 * Created by jakub on 21.05.2017.
 */
public class MainPanel extends JPanel{

    private TablePanel tablePanel;
    private HurryupmodePanel hurryupmodePanel;
    private LearningmodePanel learningmodePanel;
    private RepeatsmodePanel repeatsmodePanel;
    User user;

    public MainPanel(User user)
    {
        super(new CardLayout());
        this.user = user;
        tablePanel = new TablePanel(user);
        hurryupmodePanel = new HurryupmodePanel();
        learningmodePanel = new LearningmodePanel();
        repeatsmodePanel = new RepeatsmodePanel();

        add(tablePanel, "TABLE");
        add(hurryupmodePanel, "HURRY UP");
        add(learningmodePanel, "LEARNING");
        add(repeatsmodePanel, "REPEATS");

    }

    public JMenuBar createMenuBar()
    {
        JMenuBar menuBar = new JMenuBar();

        JMenu menuLearn = new JMenu("Learn");
        JMenu menuHelp = new JMenu("Help");

        JMenuItem menuItemLearningMode = new JMenuItem("Learning Mode");
        JMenuItem menuItemRepeatsMode = new JMenuItem("Repeats Mode");
        JMenuItem menuItemHurryUpMode = new JMenuItem("Hurry Up Mode");

        JMenuItem menuItemAbout = new JMenuItem("About");
        menuItemAbout.addActionListener(e ->
        {
            JFrame frame = new JFrame("About");
            frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            AboutPanel panel = new AboutPanel();
            panel.setVisible(true);

            frame.setContentPane(panel);
            frame.setVisible(true);
            frame.setResizable(false);
            frame.pack();
        });

        JMenuItem menuItemLogOut = new JMenuItem("Log out");
        menuItemLogOut.addActionListener(e ->logOut());

        JMenuItem menuItemExit = new JMenuItem("Exit");
        menuItemExit.addActionListener(e ->{
            JFrame frame = (JFrame)this.getRootPane().getParent();
            frame.dispose();
        });

        menuLearn.add(menuItemLearningMode);
        menuItemLearningMode.addActionListener(x ->{
            CardLayout cl = (CardLayout)this.getLayout();
            cl.show(this, "LEARING");
        });
        menuLearn.add(menuItemRepeatsMode);
        menuItemRepeatsMode.addActionListener(x ->{
            CardLayout cl = (CardLayout)this.getLayout();
            cl.show(this, "REPEATS");
        });
        menuLearn.add(menuItemHurryUpMode);
        menuItemHurryUpMode.addActionListener(x ->{
            CardLayout cl = (CardLayout)this.getLayout();
            cl.show(this, "HURRY UP");
        });

        menuHelp.add(menuItemAbout);
        menuHelp.add(menuItemLogOut);
        menuHelp.add(menuItemExit);

        menuBar.add(menuLearn);
        menuBar.add(menuHelp);

        return menuBar;
    }

    private void logOut()
    {
        JFrame frame = new JFrame("Login");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        LoginPanel panel = new LoginPanel();
        panel.setVisible(true);

        frame.setContentPane(panel);
        frame.setVisible(true);
        frame.setResizable(false);
        frame.pack();

        JFrame thisFrame = (JFrame)this.getRootPane().getParent();
        thisFrame.dispose();
    }

}
