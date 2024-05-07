package GUI.task_page;

import Entity.*;
import GUI.MainFrame;
import utill.read.ReadBank;


import javax.swing.*;
import javax.swing.plaf.FontUIResource;
import javax.swing.text.StyleContext;
import java.awt.*;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

import java.util.Locale;

public class Task_kid extends JPanel {


    private JProgressBar progressBar1;
    private JLabel saving;
    private JLabel money;
    private JLabel progress;
    private JPanel Container;

    private JPanel SavingBlock;
    private JPanel Goals;
    private JPanel Progress;
    private JPanel TaskBlock;
    private JPanel Task1Con;
    private JPanel Task1Info;
    private JPanel Task2Con;
    private JPanel Task3Con;
    private JPanel Task4Con;
    private JPanel Deposit;
    private JPanel Task2Info;
    private JPanel Task3Info;
    private JPanel Task4Info;

    private MainFrame mainFrame;

    private Kids kid;

    public Task_kid() {
        $$$setupUI$$$(); // Ensures all GUI components are initialized first
        Dimension preferredSize = new Dimension(900, 540);
        Container.setPreferredSize(preferredSize);
        add(Container);
        // Debug to ensure components are initialized
    }

    public Task_kid(Kids kid, MainFrame mainFrame) {
        this.mainFrame = mainFrame;
        this.kid = kid;
        $$$setupUI$$$(); // Ensures all GUI components are initialized first
        Dimension preferredSize = new Dimension(900, 540);
        Container.setPreferredSize(preferredSize);
        add(Container);
        // Debug to ensure components are initialized
    }

    // 弹出对话框方法
    private void showDialog(int index) {
        if(kid.getTaskList().getNonConfirmedTask().getTask(index).getCondition(kid.getTaskList().getNonConfirmedTask().getTask(index).getState())=="Submitted"){
            JOptionPane.showMessageDialog(this, "You have submitted this task, please wait for parent's confirmation", "Message", JOptionPane.WARNING_MESSAGE);
        }else {
            // 使用 JOptionPane 来显示消息
            int response = JOptionPane.showConfirmDialog(this, kid.getTaskList().getNonConfirmedTask().getTask(index).getCon1(kid.getTaskList().getNonConfirmedTask().getTask(index).getState()), "Confirmation", JOptionPane.YES_NO_OPTION);


            List<Task> allTasks = kid.getTaskList().getNonConfirmedTask().getAllTasks();
            System.out.println("All tasks before:");
            for (Task task : allTasks) {
                System.out.println(task);
            }
            if (response == JOptionPane.YES_OPTION) {
                JOptionPane.showMessageDialog(this, kid.getTaskList().getNonConfirmedTask().getTask(index).getCon2(kid.getTaskList().getNonConfirmedTask().getTask(index).getState()), "Message", JOptionPane.INFORMATION_MESSAGE);
                kid.getTaskList().updateTask(kid.getTaskList().getNonConfirmedTask().getTask(index).getName(),kid.getTaskList().getNonConfirmedTask().getTask(index).taskOperation(kid.getTaskList().getNonConfirmedTask().getTask(index)));
                mainFrame.refresh();

                List<Task> allTasks1 = kid.getTaskList().getNonConfirmedTask().getAllTasks();
                System.out.println("All tasks after:");
                for (Task task : allTasks1) {
                    System.out.println(task);
                }

            }
        }

    }




    private void $$$setupUI$$$() {

        System.out.println(this.kid.getBank().getSavingTotal());



        Container = new JPanel();
        Container.setLayout(new GridBagLayout());
        Container.setBackground(new Color(-4137489));
        Container.setEnabled(true);



        SavingBlock = new JPanel();
        SavingBlock.setLayout(new BorderLayout(0, 0));
        SavingBlock.setBackground(new Color(-1052689));

        GridBagConstraints gbc;
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        gbc.weightx = 1.0;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.insets = new Insets(20, 10, 10, 10);

        Container.add(SavingBlock, gbc);
        Goals = new JPanel();
        Goals.setLayout(new GridBagLayout());
        Goals.setBackground(new Color(-1052689));
        Goals.setPreferredSize(new Dimension(400, 70));
        SavingBlock.add(Goals, BorderLayout.NORTH);

        saving = new JLabel();
        Font savingFont = this.$$$getFont$$$("Arial Black", Font.BOLD, 36, saving.getFont());
        if (savingFont != null) saving.setFont(savingFont);
        saving.setForeground(new Color(-13534488));
        saving.setText("Saving goals");
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        gbc.anchor = GridBagConstraints.WEST;

        Goals.add(saving, gbc);

        //read file for goal and progress

        money = new JLabel();
        Font moneyFont = this.$$$getFont$$$("Arial Black", Font.BOLD, 28, money.getFont());
        if (moneyFont != null) money.setFont(moneyFont);
        money.setForeground(new Color(-9975466));

        money.setText("$"+kid.getBank().getSavingTotal()+"/$"+kid.getBank().getSavingGoal());

        gbc = new GridBagConstraints();
        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        gbc.anchor = GridBagConstraints.EAST;

        Goals.add(money, gbc);


        Progress = new JPanel();
        Progress.setLayout(new GridBagLayout());
        Progress.setBackground(new Color(-1052689));
        SavingBlock.add(Progress, BorderLayout.SOUTH);

        progressBar1 = new JProgressBar();
        progressBar1.setBackground(new Color(-2565928));
        progressBar1.setForeground(new Color(-1010247));
        progressBar1.setMinimum(0);
        progressBar1.setStringPainted(false);

        //System.out.println(bankKid.getSavingTotal()/bankKid.getSavingGoal());
        progressBar1.setValue((int)((kid.getBank().getSavingTotal()/kid.getBank().getSavingGoal())*100));

        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        gbc.fill = GridBagConstraints.HORIZONTAL;

        Progress.add(progressBar1, gbc);

        progress = new JLabel();
        Font progressFont = this.$$$getFont$$$("Arial Black", -1, 16, progress.getFont());
        if (progressFont != null) progress.setFont(progressFont);
        progress.setForeground(new Color(-12763843));

        progress.setText(kid.getBank().getSavingTotal()/kid.getBank().getSavingGoal()*100+"%");

        gbc = new GridBagConstraints();
        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.weighty = 1.0;
        gbc.anchor = GridBagConstraints.EAST;

        Progress.add(progress, gbc);




        //Tasks set up
        int size = kid.getTaskList().getNonConfirmedTask().getSize();

        //int[] index=kid.getTaskList().getNonConfirmedIndex(size);
        TaskBlock = new JPanel();
        TaskBlock.setLayout(new GridBagLayout());
        TaskBlock.setAlignmentY(0.5f);
        TaskBlock.setBackground(new Color(-4137489));

        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        gbc.anchor = GridBagConstraints.NORTH;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(0, 10, 0, 10);

        Container.add(TaskBlock, gbc);

        //Task 1
        if(size>=1) {
            int index=0;
            Task1Con = new JPanel();
            Task1Con.setLayout(new GridBagLayout());
            gbc = new GridBagConstraints();
            gbc.gridx = 0;
            gbc.gridy = 0;
            gbc.weightx = 1.0;
            gbc.weighty = 1.0;
            gbc.fill = GridBagConstraints.BOTH;
            gbc.insets = new Insets(10, 10, 10, 10);
            TaskBlock.add(Task1Con, gbc);
            Task1Info = new JPanel();
            Task1Info.setLayout(new GridBagLayout());
            Task1Info.setBackground(new Color(-1052689));
            Task1Info.setOpaque(true);
            Task1Info.setRequestFocusEnabled(true);
            Task1Info.setVisible(true);
            gbc = new GridBagConstraints();
            gbc.gridx = 0;
            gbc.gridy = 0;
            gbc.weightx = 1.0;
            gbc.weighty = 1.0;
            gbc.fill = GridBagConstraints.HORIZONTAL;
            Task1Con.add(Task1Info, gbc);
            final JPanel panel1 = new JPanel();
            panel1.setLayout(new GridBagLayout());
            panel1.setBackground(new Color(-1052689));
            gbc = new GridBagConstraints();
            gbc.gridx = 2;
            gbc.gridy = 0;
            gbc.gridheight = 2;
            gbc.weightx = 1.0;
            gbc.weighty = 1.0;
            gbc.anchor = GridBagConstraints.EAST;
            Task1Info.add(panel1, gbc);
            final JLabel label1 = new JLabel();
            label1.setBackground(new Color(-1052689));
            label1.setEnabled(true);
            Font label1Font = this.$$$getFont$$$("Arial Black", Font.BOLD, 22, label1.getFont());
            if (label1Font != null) label1.setFont(label1Font);
            label1.setForeground(new Color(-12763843));
            //button1
            label1.setText(kid.getTaskList().getNonConfirmedTask().getTask(0).getCondition(kid.getTaskList().getNonConfirmedTask().getTask(0).getState()));
            label1.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    showDialog(index);
                }

            });
            System.out.println("after");
            System.out.println(kid.getTaskList().getNonConfirmedTask().getTask(0).getCondition(kid.getTaskList().getNonConfirmedTask().getTask(0).getState()));
            gbc = new GridBagConstraints();
            gbc.gridx = 0;
            gbc.gridy = 0;
            gbc.weightx = 1.0;
            gbc.weighty = 1.0;
            panel1.add(label1, gbc);
            final JLabel label2 = new JLabel();
            label2.setEnabled(true);
            Font label2Font = this.$$$getFont$$$("Arial Black", Font.BOLD, 20, label2.getFont());
            if (label2Font != null) label2.setFont(label2Font);
            label2.setForeground(new Color(-9975466));
            label2.setText("$"+kid.getTaskList().getNonConfirmedTask().getTask(0).getReward());
            gbc = new GridBagConstraints();
            gbc.gridx = 1;
            gbc.gridy = 0;
            gbc.weighty = 1.0;
            gbc.anchor = GridBagConstraints.NORTHEAST;
            Task1Info.add(label2, gbc);
            final JLabel label3 = new JLabel();
            Font label3Font = this.$$$getFont$$$("Arial Black", Font.BOLD, 24, label3.getFont());
            if (label3Font != null) label3.setFont(label3Font);
            label3.setForeground(new Color(-13534488));
            label3.setText(kid.getTaskList().getNonConfirmedTask().getTask(0).getName());
            gbc = new GridBagConstraints();
            gbc.gridx = 0;
            gbc.gridy = 0;
            gbc.weighty = 1.0;
            gbc.anchor = GridBagConstraints.NORTHWEST;
            Task1Info.add(label3, gbc);
            final JLabel label4 = new JLabel();
            label4.setFocusable(false);
            Font label4Font = this.$$$getFont$$$("Arial", Font.ITALIC, 24, label4.getFont());
            if (label4Font != null) label4.setFont(label4Font);
            label4.setForeground(new Color(-12763843));
            label4.setText(kid.getTaskList().getTask(0).getText(kid.getTaskList().getNonConfirmedTask().getTask(0).getState()));
            gbc = new GridBagConstraints();
            gbc.gridx = 0;
            gbc.gridy = 1;
            gbc.weighty = 1.0;
            gbc.anchor = GridBagConstraints.NORTHWEST;
            Task1Info.add(label4, gbc);

        }

        //Task 2
        if(size>=2) {
            int index=1;
            Task2Con = new JPanel();
            Task2Con.setLayout(new GridBagLayout());
            gbc = new GridBagConstraints();
            gbc.gridx = 0;
            gbc.gridy = 1;
            gbc.weightx = 1.0;
            gbc.weighty = 1.0;
            gbc.fill = GridBagConstraints.BOTH;
            gbc.insets = new Insets(10, 10, 10, 10);
            TaskBlock.add(Task2Con, gbc);
            Task2Info = new JPanel();
            Task2Info.setLayout(new GridBagLayout());
            Task2Info.setBackground(new Color(-1052689));
            Task2Info.setOpaque(true);
            Task2Info.setRequestFocusEnabled(true);
            Task2Info.setVisible(true);
            gbc = new GridBagConstraints();
            gbc.gridx = 0;
            gbc.gridy = 0;
            gbc.weightx = 1.0;
            gbc.weighty = 1.0;
            gbc.fill = GridBagConstraints.HORIZONTAL;
            Task2Con.add(Task2Info, gbc);
            final JPanel panel2 = new JPanel();
            panel2.setLayout(new GridBagLayout());
            panel2.setBackground(new Color(-1052689));
            gbc = new GridBagConstraints();
            gbc.gridx = 2;
            gbc.gridy = 0;
            gbc.gridheight = 2;
            gbc.weightx = 1.0;
            gbc.weighty = 1.0;
            gbc.anchor = GridBagConstraints.EAST;
            Task2Info.add(panel2, gbc);
            final JLabel label5 = new JLabel();
            label5.setBackground(new Color(-1052689));
            label5.setEnabled(true);
            Font label5Font = this.$$$getFont$$$("Arial Black", Font.BOLD, 22, label5.getFont());
            if (label5Font != null) label5.setFont(label5Font);
            label5.setForeground(new Color(-12763843));
            label5.setText(kid.getTaskList().getNonConfirmedTask().getTask(1).getCondition(kid.getTaskList().getNonConfirmedTask().getTask(1).getState()));
            gbc = new GridBagConstraints();
            gbc.gridx = 0;
            gbc.gridy = 0;
            gbc.weightx = 1.0;
            gbc.weighty = 1.0;
            panel2.add(label5, gbc);
            final JLabel label6 = new JLabel();
            Font label6Font = this.$$$getFont$$$("Arial Black", Font.BOLD, 24, label6.getFont());
            if (label6Font != null) label6.setFont(label6Font);
            label6.setForeground(new Color(-13534488));
            label6.setText(kid.getTaskList().getNonConfirmedTask().getTask(1).getName());
            gbc = new GridBagConstraints();
            gbc.gridx = 0;
            gbc.gridy = 0;
            gbc.weighty = 1.0;
            gbc.anchor = GridBagConstraints.NORTHWEST;
            Task2Info.add(label6, gbc);
            final JLabel label7 = new JLabel();
            label7.setFocusable(false);
            Font label7Font = this.$$$getFont$$$("Arial", Font.ITALIC, 24, label7.getFont());
            if (label7Font != null) label7.setFont(label7Font);
            label7.setForeground(new Color(-12763843));
            label7.setText(kid.getTaskList().getNonConfirmedTask().getNonConfirmedTask().getTask(1).getText(kid.getTaskList().getNonConfirmedTask().getTask(1).getState()));
            gbc = new GridBagConstraints();
            gbc.gridx = 0;
            gbc.gridy = 1;
            gbc.weighty = 1.0;
            gbc.anchor = GridBagConstraints.NORTHWEST;
            Task2Info.add(label7, gbc);
            final JLabel label8 = new JLabel();
            label8.setEnabled(true);
            Font label8Font = this.$$$getFont$$$("Arial Black", Font.BOLD, 20, label8.getFont());
            if (label8Font != null) label8.setFont(label8Font);
            label8.setForeground(new Color(-9975466));
            label8.setText("$"+kid.getTaskList().getNonConfirmedTask().getTask(1).getReward());
            gbc = new GridBagConstraints();
            gbc.gridx = 1;
            gbc.gridy = 0;
            gbc.weighty = 1.0;
            gbc.anchor = GridBagConstraints.NORTHEAST;
            Task2Info.add(label8, gbc);


        }

        //Task 3
        if(size>=3) {
            int index=2;
            Task3Con = new JPanel();
            Task3Con.setLayout(new GridBagLayout());
            gbc = new GridBagConstraints();
            gbc.gridx = 0;
            gbc.gridy = 3;
            gbc.weightx = 1.0;
            gbc.weighty = 1.0;
            gbc.fill = GridBagConstraints.BOTH;
            gbc.insets = new Insets(10, 10, 10, 10);
            TaskBlock.add(Task3Con, gbc);
            Task3Info = new JPanel();
            Task3Info.setLayout(new GridBagLayout());
            Task3Info.setBackground(new Color(-1052689));
            Task3Info.setOpaque(true);
            Task3Info.setRequestFocusEnabled(true);
            Task3Info.setVisible(true);
            gbc = new GridBagConstraints();
            gbc.gridx = 0;
            gbc.gridy = 0;
            gbc.weightx = 1.0;
            gbc.weighty = 1.0;
            gbc.fill = GridBagConstraints.HORIZONTAL;
            Task3Con.add(Task3Info, gbc);
            final JPanel panel3 = new JPanel();
            panel3.setLayout(new GridBagLayout());
            panel3.setBackground(new Color(-1052689));
            gbc = new GridBagConstraints();
            gbc.gridx = 2;
            gbc.gridy = 0;
            gbc.gridheight = 2;
            gbc.weightx = 1.0;
            gbc.weighty = 1.0;
            gbc.anchor = GridBagConstraints.EAST;
            Task3Info.add(panel3, gbc);
            final JLabel label9 = new JLabel();
            label9.setBackground(new Color(-1052689));
            label9.setEnabled(true);
            Font label9Font = this.$$$getFont$$$("Arial Black", Font.BOLD, 22, label9.getFont());
            if (label9Font != null) label9.setFont(label9Font);
            label9.setForeground(new Color(-12763843));
            label9.setText(kid.getTaskList().getNonConfirmedTask().getTask(2).getCondition(kid.getTaskList().getNonConfirmedTask().getTask(2).getState()));
            gbc = new GridBagConstraints();
            gbc.gridx = 0;
            gbc.gridy = 0;
            gbc.weightx = 1.0;
            gbc.weighty = 1.0;
            panel3.add(label9, gbc);
            final JLabel label10 = new JLabel();
            Font label10Font = this.$$$getFont$$$("Arial Black", Font.BOLD, 24, label10.getFont());
            if (label10Font != null) label10.setFont(label10Font);
            label10.setForeground(new Color(-13534488));
            label10.setText(kid.getTaskList().getNonConfirmedTask().getNonConfirmedTask().getTask(2).getName());
            gbc = new GridBagConstraints();
            gbc.gridx = 0;
            gbc.gridy = 0;
            gbc.weighty = 1.0;
            gbc.anchor = GridBagConstraints.NORTHWEST;
            Task3Info.add(label10, gbc);
            final JLabel label11 = new JLabel();
            label11.setFocusable(false);
            Font label11Font = this.$$$getFont$$$("Arial", Font.ITALIC, 24, label11.getFont());
            if (label11Font != null) label11.setFont(label11Font);
            label11.setForeground(new Color(-12763843));
            label11.setText(kid.getTaskList().getNonConfirmedTask().getTask(2).getText(kid.getTaskList().getNonConfirmedTask().getTask(2).getState()));
            gbc = new GridBagConstraints();
            gbc.gridx = 0;
            gbc.gridy = 1;
            gbc.weighty = 1.0;
            gbc.anchor = GridBagConstraints.NORTHWEST;
            Task3Info.add(label11, gbc);
            final JLabel label12 = new JLabel();
            label12.setEnabled(true);
            Font label12Font = this.$$$getFont$$$("Arial Black", Font.BOLD, 20, label12.getFont());
            if (label12Font != null) label12.setFont(label12Font);
            label12.setForeground(new Color(-9975466));
            label12.setText("$"+kid.getTaskList().getNonConfirmedTask().getTask(2).getReward());
            gbc = new GridBagConstraints();
            gbc.gridx = 1;
            gbc.gridy = 0;
            gbc.weighty = 1.0;
            gbc.anchor = GridBagConstraints.NORTHEAST;
            Task3Info.add(label12, gbc);
        }

        //Task 4
        if(size==4) {
            int index=3;
            Task4Con = new JPanel();
            Task4Con.setLayout(new GridBagLayout());
            gbc = new GridBagConstraints();
            gbc.gridx = 0;
            gbc.gridy = 2;
            gbc.fill = GridBagConstraints.BOTH;
            gbc.insets = new Insets(10, 10, 10, 10);
            TaskBlock.add(Task4Con, gbc);
            Task4Info = new JPanel();
            Task4Info.setLayout(new GridBagLayout());
            Task4Info.setBackground(new Color(-1052689));
            Task4Info.setOpaque(true);
            Task4Info.setRequestFocusEnabled(true);
            Task4Info.setVisible(true);
            gbc = new GridBagConstraints();
            gbc.gridx = 0;
            gbc.gridy = 0;
            gbc.weightx = 1.0;
            gbc.weighty = 1.0;
            gbc.fill = GridBagConstraints.HORIZONTAL;
            Task4Con.add(Task4Info, gbc);
            final JPanel panel4 = new JPanel();
            panel4.setLayout(new GridBagLayout());
            panel4.setBackground(new Color(-1052689));
            gbc = new GridBagConstraints();
            gbc.gridx = 2;
            gbc.gridy = 0;
            gbc.gridheight = 2;
            gbc.weightx = 1.0;
            gbc.weighty = 1.0;
            gbc.anchor = GridBagConstraints.EAST;
            Task4Info.add(panel4, gbc);
            final JLabel label13 = new JLabel();
            label13.setBackground(new Color(-1052689));
            label13.setEnabled(true);
            Font label13Font = this.$$$getFont$$$("Arial Black", Font.BOLD, 22, label13.getFont());
            if (label13Font != null) label13.setFont(label13Font);
            label13.setForeground(new Color(-12763843));
            label13.setText(kid.getTaskList().getNonConfirmedTask().getTask(3).getCondition(kid.getTaskList().getNonConfirmedTask().getTask(3).getState()));
            gbc = new GridBagConstraints();
            gbc.gridx = 0;
            gbc.gridy = 0;
            gbc.weightx = 1.0;
            gbc.weighty = 1.0;
            panel4.add(label13, gbc);
            final JLabel label14 = new JLabel();
            label14.setFocusable(false);
            Font label14Font = this.$$$getFont$$$("Arial", Font.ITALIC, 24, label14.getFont());
            if (label14Font != null) label14.setFont(label14Font);
            label14.setForeground(new Color(-12763843));
            label14.setText(kid.getTaskList().getNonConfirmedTask().getTask(3).getText(kid.getTaskList().getNonConfirmedTask().getTask(3).getState()));
            gbc = new GridBagConstraints();
            gbc.gridx = 0;
            gbc.gridy = 1;
            gbc.weighty = 1.0;
            gbc.anchor = GridBagConstraints.NORTHWEST;
            Task4Info.add(label14, gbc);
            final JLabel label15 = new JLabel();
            label15.setEnabled(true);
            Font label15Font = this.$$$getFont$$$("Arial Black", Font.BOLD, 20, label15.getFont());
            if (label15Font != null) label15.setFont(label15Font);
            label15.setForeground(new Color(-9975466));
            label15.setText("$"+kid.getTaskList().getNonConfirmedTask().getTask(3).getReward());
            gbc = new GridBagConstraints();
            gbc.gridx = 1;
            gbc.gridy = 0;
            gbc.weighty = 1.0;
            gbc.anchor = GridBagConstraints.NORTHEAST;
            Task4Info.add(label15, gbc);
            final JLabel label16 = new JLabel();
            Font label16Font = this.$$$getFont$$$("Arial Black", Font.BOLD, 24, label16.getFont());
            if (label16Font != null) label16.setFont(label16Font);
            label16.setForeground(new Color(-13534488));
            label16.setText(kid.getTaskList().getNonConfirmedTask().getTask(3).getName());
            gbc = new GridBagConstraints();
            gbc.gridx = 0;
            gbc.gridy = 0;
            gbc.weighty = 1.0;
            gbc.anchor = GridBagConstraints.NORTHWEST;
            Task4Info.add(label16, gbc);
        }
        Deposit = new JPanel();
        Deposit.setLayout(new GridBagLayout());

        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.insets = new Insets(0, 0, 10, 0);

        TaskBlock.add(Deposit, gbc);
        final JPanel panel5 = new JPanel();
        panel5.setLayout(new GridBagLayout());
        panel5.setBackground(new Color(-1052689));

        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 1.0;
        gbc.fill = GridBagConstraints.BOTH;

        Deposit.add(panel5, gbc);

        final JLabel label17 = new JLabel();
        Font label17Font = this.$$$getFont$$$("Arial Black", -1, 20, label17.getFont());
        if (label17Font != null) label17.setFont(label17Font);
        label17.setForeground(new Color(-1010247));
        label17.setText("Select an account for task Salary Depository");
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;

        panel5.add(label17, gbc);
    }



    private Font $$$getFont$$$(String fontName, int style, int size, Font currentFont) {
        if (currentFont == null) return null;
        String resultName;
        if (fontName == null) {
            resultName = currentFont.getName();
        } else {
            Font testFont = new Font(fontName, Font.PLAIN, 10);
            if (testFont.canDisplay('a') && testFont.canDisplay('1')) {
                resultName = fontName;
            } else {
                resultName = currentFont.getName();
            }
        }
        Font font = new Font(resultName, style >= 0 ? style : currentFont.getStyle(), size >= 0 ? size : currentFont.getSize());
        boolean isMac = System.getProperty("os.name", "").toLowerCase(Locale.ENGLISH).startsWith("mac");
        Font fontWithFallback = isMac ? new Font(font.getFamily(), font.getStyle(), font.getSize()) : new StyleContext().getFont(font.getFamily(), font.getStyle(), font.getSize());
        return fontWithFallback instanceof FontUIResource ? fontWithFallback : new FontUIResource(fontWithFallback);
    }




    public JComponent $$$getRootComponent$$$() {
        return Container;
    }


    private Font $$$getFont1$$$(String fontName, int style, int size, Font currentFont) {
        if (currentFont == null) return null;
        String resultName;
        if (fontName == null) {
            resultName = currentFont.getName();
        } else {
            Font testFont = new Font(fontName, Font.PLAIN, 10);
            if (testFont.canDisplay('a') && testFont.canDisplay('1')) {
                resultName = fontName;
            } else {
                resultName = currentFont.getName();
            }
        }
        Font font = new Font(resultName, style >= 0 ? style : currentFont.getStyle(), size >= 0 ? size : currentFont.getSize());
        boolean isMac = System.getProperty("os.name", "").toLowerCase(Locale.ENGLISH).startsWith("mac");
        Font fontWithFallback = isMac ? new Font(font.getFamily(), font.getStyle(), font.getSize()) : new StyleContext().getFont(font.getFamily(), font.getStyle(), font.getSize());
        return fontWithFallback instanceof FontUIResource ? fontWithFallback : new FontUIResource(fontWithFallback);
    }

}
