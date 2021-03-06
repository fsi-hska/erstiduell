/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

 /*
 * ConfigWindow.java
 *
 * Created on 12.03.2011, 21:55:44
 */
package info.hska.erstiduell.view;

import info.hska.erstiduell.Config;
import info.hska.erstiduell.Controller;
import info.hska.erstiduell.buzzer.Key;
import info.hska.erstiduell.questions.QuestionLibrary;
import java.awt.Color;
import java.awt.GraphicsDevice;
import java.awt.event.FocusEvent;
import java.awt.event.KeyEvent;
import java.io.File;
import java.util.HashSet;
import java.util.Set;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JToggleButton;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.filechooser.FileFilter;

/**
 *
 * @author timroes
 */
public final class ConfigWindow extends javax.swing.JFrame {

    private Controller controller;
    private Key[] hotkeys = new Key[4];

    /**
     * Creates new form ConfigWindow2
     */
    public ConfigWindow(Controller controller) {
        this.controller = controller;
        hotkeys[0] = new Key(48, 1, "0");
        hotkeys[1] = new Key(49, 1, "1");
        hotkeys[2] = new Key(50, 1, "2");
        hotkeys[3] = new Key(51, 1, "3");
        initComponents();
        updateErrors();

        this.setLocationByPlatform(true);
        this.setVisible(true);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     */
    @SuppressWarnings("unchecked")
    private void initComponents() {
        java.awt.GridBagConstraints gridBagConstraints;

        jToggleButton1 = new javax.swing.JToggleButton();
        error = new javax.swing.JLabel();
        questionsPanel = new javax.swing.JPanel();
        loadQuestions = new javax.swing.JButton();
        questionFile = new javax.swing.JTextField();
        questionLabel = new javax.swing.JLabel();
        gamePanel = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        amountPlayers = new javax.swing.JSpinner();
        colorPanel = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        fR = new javax.swing.JSpinner();
        fG = new javax.swing.JSpinner();
        fB = new javax.swing.JSpinner();
        jLabel3 = new javax.swing.JLabel();
        bR = new javax.swing.JSpinner();
        bG = new javax.swing.JSpinner();
        bB = new javax.swing.JSpinner();
        outputPanel = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        outputMonitor = new javax.swing.JComboBox();
        inputSettings = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        input1 = new javax.swing.JToggleButton();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        input2 = new javax.swing.JToggleButton();
        input3 = new javax.swing.JToggleButton();
        input4 = new javax.swing.JToggleButton();
        ok = new javax.swing.JButton();

        jToggleButton1.setText("jToggleButton1");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new java.awt.GridBagLayout());

        error.setFont(new java.awt.Font("Liberation Sans", 1, 15));
        error.setForeground(new java.awt.Color(255, 0, 0));
        error.setText("jLabel5");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(10, 10, 5, 10);
        getContentPane().add(error, gridBagConstraints);

        questionsPanel.setBorder(javax.swing.BorderFactory.createTitledBorder("Question Library"));
        questionsPanel.setLayout(new java.awt.GridBagLayout());

        loadQuestions.setText("Load Questions");
        loadQuestions.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                loadQuestionsActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.insets = new java.awt.Insets(2, 5, 2, 5);
        questionsPanel.add(loadQuestions, gridBagConstraints);

        questionFile.setText("[No file loaded]");
        questionFile.setEnabled(false);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(2, 5, 2, 5);
        questionsPanel.add(questionFile, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.insets = new java.awt.Insets(2, 5, 2, 5);
        questionsPanel.add(questionLabel, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        getContentPane().add(questionsPanel, gridBagConstraints);

        gamePanel.setBorder(javax.swing.BorderFactory.createTitledBorder("Game Settings"));
        gamePanel.setLayout(new java.awt.GridBagLayout());

        jLabel1.setText("Number of Teams:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(2, 5, 2, 5);
        gamePanel.add(jLabel1, gridBagConstraints);

        amountPlayers.setModel(new javax.swing.SpinnerNumberModel(4, 2, 4, 1));
        amountPlayers.addChangeListener(new ChangeListener() {

            public void stateChanged(ChangeEvent e) {
                if ((Integer) amountPlayers.getValue() < 4) {
                    input4.setEnabled(false);
                } else {
                    input4.setEnabled(true);
                }

                if ((Integer) amountPlayers.getValue() < 3) {
                    input3.setEnabled(false);
                } else {
                    input3.setEnabled(true);
                }

                updateErrors();
            }

        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.insets = new java.awt.Insets(2, 5, 2, 5);
        gamePanel.add(amountPlayers, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        getContentPane().add(gamePanel, gridBagConstraints);

        colorPanel.setBorder(javax.swing.BorderFactory.createTitledBorder("Color Settings"));
        colorPanel.setLayout(new java.awt.GridBagLayout());

        jLabel2.setText("Text color:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(2, 5, 2, 5);
        colorPanel.add(jLabel2, gridBagConstraints);

        fR.setModel(new javax.swing.SpinnerNumberModel(255, 0, 255, 1));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(2, 5, 2, 5);
        colorPanel.add(fR, gridBagConstraints);

        fG.setModel(new javax.swing.SpinnerNumberModel(255, 0, 255, 1));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(2, 5, 2, 5);
        colorPanel.add(fG, gridBagConstraints);

        fB.setModel(new javax.swing.SpinnerNumberModel(255, 0, 255, 1));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.insets = new java.awt.Insets(2, 5, 2, 5);
        colorPanel.add(fB, gridBagConstraints);

        jLabel3.setText("Backgroundcolor:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(2, 5, 2, 5);
        colorPanel.add(jLabel3, gridBagConstraints);

        bR.setModel(new javax.swing.SpinnerNumberModel(0, 0, 255, 1));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(2, 5, 2, 5);
        colorPanel.add(bR, gridBagConstraints);

        bG.setModel(new javax.swing.SpinnerNumberModel(0, 0, 255, 1));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(2, 5, 2, 5);
        colorPanel.add(bG, gridBagConstraints);

        bB.setModel(new javax.swing.SpinnerNumberModel(0, 0, 255, 1));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(2, 5, 2, 5);
        colorPanel.add(bB, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        getContentPane().add(colorPanel, gridBagConstraints);

        outputPanel.setBorder(javax.swing.BorderFactory.createTitledBorder("Output Settings"));
        outputPanel.setLayout(new java.awt.GridBagLayout());

        jLabel4.setText("Output Monitor:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(2, 5, 2, 5);
        outputPanel.add(jLabel4, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(2, 5, 2, 5);
        outputPanel.add(outputMonitor, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        getContentPane().add(outputPanel, gridBagConstraints);

        inputSettings.setBorder(javax.swing.BorderFactory.createTitledBorder("Input Settings"));
        inputSettings.setLayout(new java.awt.GridBagLayout());

        teamInitialize(jLabel5, input1, 1);
        teamInitialize(jLabel6, input2, 2);
        teamInitialize(jLabel7, input3, 3);
        teamInitialize(jLabel8, input4, 4);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 5;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        getContentPane().add(inputSettings, gridBagConstraints);

        ok.setText("Start Game");
        ok.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                okActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 6;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.EAST;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        getContentPane().add(ok, gridBagConstraints);
        // automatically load demo questions for easy testing
        if (true) {
            loadQuestions(new File("./demo_questions.qes"));
        }
        pack();
    }

    void teamInitialize(javax.swing.JLabel label, JToggleButton input, final int teamNo) {
        label.setText("Team " + teamNo);
        java.awt.GridBagConstraints gridBagConstraints = new java.awt.GridBagConstraints();
        if (hotkeys[teamNo - 1] != null) {
            input.setText(hotkeys[teamNo - 1].toString());
        } else {
            input.setText("[Assign key]");
        }
        if (((Integer) amountPlayers.getValue()) < teamNo) {
            input.setEnabled(false);
        }
        input.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                inputActionPerformed(evt);
            }
        });
        input.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                inputFocusLost(evt, teamNo);
            }
        });
        input.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                inputKeyPressed(evt, teamNo);
            }
        });
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = teamNo - 1;
        gridBagConstraints.insets = new java.awt.Insets(2, 5, 2, 5);
        inputSettings.add(input, gridBagConstraints);
    }

    private void loadQuestionsActionPerformed(java.awt.event.ActionEvent evt) {
        JFileChooser fc = new JFileChooser();
        File workingDirectory = new File(System.getProperty("user.dir"));
        fc.setCurrentDirectory(workingDirectory);
        fc.setFileFilter(new FileFilter() {

            @Override
            public boolean accept(File f) {
                return f.getName().endsWith(".qes") || f.isDirectory();
            }

            @Override
            public String getDescription() {
                return "Question Library (.qes)";
            }
        });

        fc.showDialog(this, "Load");
        loadQuestions(fc.getSelectedFile());

    }

    private void loadQuestions(File input) {
        try {
            QuestionLibrary.loadQuestions(input);
        } catch (Exception ex) {

            for (StackTraceElement el : ex.getStackTrace()) {
                System.out.println(el.getFileName() + " " + el.getMethodName() + ":" + el.getLineNumber());
            }
            JOptionPane.showMessageDialog(this,
                    "Could not read question library correctly:\n\n"
                    + "[" + ex.getClass().getSimpleName() + "]\n"
                    + ((ex.getMessage() == null) ? "" : ex.getMessage()),
                    "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        questionLabel.setText(String.valueOf(QuestionLibrary.getInstance()
                .getQuestionAmount()) + " questions");
        questionFile.setText(input.getAbsolutePath());

        updateErrors();

    }

    private void okActionPerformed(java.awt.event.ActionEvent evt) {
        if (!error.isVisible()) {

            Key[] hotkeys = new Key[(Integer) amountPlayers.getValue()];

            System.arraycopy(this.hotkeys, 0, hotkeys, 0, hotkeys.length);

            controller.configured(new Config(
                    (Integer) amountPlayers.getValue(),
                    (GraphicsDevice) outputMonitor.getSelectedItem(),
                    new Color((Integer) fR.getValue(), (Integer) fG.getValue(), (Integer) fB.getValue()),
                    new Color((Integer) bR.getValue(), (Integer) bG.getValue(), (Integer) bB.getValue()),
                    hotkeys
            ));
            setVisible(false);
            dispose();
        }
    }

    private void inputActionPerformed(java.awt.event.ActionEvent evt) {
        JToggleButton input = (JToggleButton) evt.getSource();
        input.setSelected(true);
        input.setText("[Press key now]");
    }

    private void inputKeyPressed(KeyEvent evt, int player) {
        JToggleButton input = (JToggleButton) evt.getSource();
        if (input.isSelected()) {
            hotkeys[player - 1] = new Key(evt);
            input.setText(hotkeys[player - 1].toString());
            input.setSelected(false);
            updateErrors();
        }
    }

    private void inputFocusLost(FocusEvent evt, int player) {
        JToggleButton bt = ((JToggleButton) evt.getSource());
        bt.setSelected(false);
        if (hotkeys[player - 1] == null) {
            bt.setText("[Assign key]");
        } else {
            bt.setText(hotkeys[player - 1].toString());
        }
    }

    private void updateErrors() {
        ok.setEnabled(true);
        StringBuilder str = new StringBuilder("<html><body>Errors:");

        if (QuestionLibrary.getInstance() == null) {
            ok.setEnabled(false);
            str.append("<br>You must load a question library.");
        }

        int players = (Integer) amountPlayers.getValue();

        if (hotkeys[0] == null || hotkeys[1] == null
                || (players >= 3 && hotkeys[2] == null)
                || (players >= 4 && hotkeys[3] == null)) {
            str.append("<br>You must assign a key for every player.");
            ok.setEnabled(false);
        }

        Set set = new HashSet();
        for (Key k : hotkeys) {
            if (k != null) {
                if (set.contains(k)) {
                    ok.setEnabled(false);
                    str.append("<br>Each player must have a different key.");
                    break;
                } else {
                    set.add(k);
                }
            }
        }

        if (!ok.isEnabled()) {
            error.setText(str.toString());
            error.setVisible(true);
        } else {
            error.setVisible(false);
        }

    }

    public void setMonitors(GraphicsDevice[] devices) {
        outputMonitor.removeAllItems();
        for (GraphicsDevice d : devices) {
            outputMonitor.addItem(d);
        }
        if (devices.length > 1) {
            outputMonitor.setSelectedIndex(1);
        }
    }

    private javax.swing.JSpinner amountPlayers;
    private javax.swing.JSpinner bB;
    private javax.swing.JSpinner bG;
    private javax.swing.JSpinner bR;
    private javax.swing.JPanel colorPanel;
    private javax.swing.JLabel error;
    private javax.swing.JSpinner fB;
    private javax.swing.JSpinner fG;
    private javax.swing.JSpinner fR;
    private javax.swing.JPanel gamePanel;
    private javax.swing.JToggleButton input1;
    private javax.swing.JToggleButton input2;
    private javax.swing.JToggleButton input3;
    private javax.swing.JToggleButton input4;
    private javax.swing.JPanel inputSettings;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JToggleButton jToggleButton1;
    private javax.swing.JButton loadQuestions;
    private javax.swing.JButton ok;
    private javax.swing.JComboBox outputMonitor;
    private javax.swing.JPanel outputPanel;
    private javax.swing.JTextField questionFile;
    private javax.swing.JLabel questionLabel;
    private javax.swing.JPanel questionsPanel;
}
