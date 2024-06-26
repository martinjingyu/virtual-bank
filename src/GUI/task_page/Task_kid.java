package GUI.task_page;

import Controller.task.Task_kid_control;
import Entity.*;
import GUI.MainFrame_kid;


import javax.swing.*;
import javax.swing.plaf.FontUIResource;
import javax.swing.text.StyleContext;
import java.awt.*;

import java.awt.event.*;
import java.util.List;

import java.util.Locale;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * The Task_kid class represents the GUI for the kid's task management page.
 * It extends JPanel and provides functionality to view, manage tasks, and set saving goals.
 */
public class Task_kid extends JPanel {


    private JProgressBar progressBar1;
    private JLabel saving;
    private JLabel money;
    private JLabel progress;
    private JPanel Container;
    private JPanel progressbar;
    private JPanel taskList;
    private JPanel taskTitle;
    private JLabel task_title;
    private JScrollPane TaskList;
    private JList list1;
    private JPanel TaskShown;
    private JPanel taskInfomation;
    private JLabel infotitle;
    private JLabel name;
    private JPanel ShownPanel;
    private JPanel taskDetails;
    private JLabel Salary;
    private JLabel Status;
    private JLabel Description;
    private JPanel Button;
    private JLabel buttonlabel;
    private JPanel SavingGoals;
    private JPanel SavingTitle;
    private JPanel Tasks;
    private JLabel MoreInfo;
    private JPanel depository;
    private MainFrame_kid mainFrame;
    private Task_kid_control task_kid_control;
    private JLabel button1, button2, button3, button4, button5;
    private int i;//get List index
    private MouseAdapter myMouseListener;
    private JComboBox Select;
    private JPanel Selector;



    /**
     * Constructs a new Task_kid panel.
     * Initializes the GUI components.
     */
    public Task_kid() {
        $$$setupUI$$$(); // Ensures all GUI components are initialized first
        Dimension preferredSize = new Dimension(900, 540);
        Container.setPreferredSize(preferredSize);
        add(Container);
        // Debug to ensure components are initialized
    }

    /**
     * Constructs a new Task_kid panel with the specified controller and main frame.
     *
     * @param controller the controller for managing tasks
     * @param mainFrame  the main frame of the kid's application
     */
    public Task_kid(Task_kid_control controller, MainFrame_kid mainFrame) {
        this.mainFrame = mainFrame;
        this.task_kid_control = controller;


        $$$setupUI$$$(); // Ensures all GUI components are initialized first
        Dimension preferredSize = new Dimension(900, 540);
        controller.setGUI(this);
        controller.addButtonControl();
        Container.setPreferredSize(preferredSize);
        add(Container);
        // Debug to ensure components are initialized
    }

    /**
     * Updates the task details displayed in the GUI.
     */
    private void updateTaskDetails() {
        Salary.setText("$" + task_kid_control.getKid().getTaskList().getNonConfirmedTask().getTask(i).getReward());
        Status.setText(task_kid_control.getKid().getTaskList().getNonConfirmedTask().getTask(i).getText(task_kid_control.getKid().getTaskList().getNonConfirmedTask().getTask(i).getState()));
        String des1 = task_kid_control.getKid().getTaskList().getNonConfirmedTask().getTask(i).getDescription();
        Description.setText(task_kid_control.getKid().getTaskList().descriptionSet(des1));
        name.setText(task_kid_control.getKid().getTaskList().getNonConfirmedTask().getTask(i).getName());
        buttonlabel.setText(task_kid_control.getKid().getTaskList().getNonConfirmedTask().getTask(i).getCondition(task_kid_control.getKid().getTaskList().getNonConfirmedTask().getTask(i).getState()));

        Tasks.revalidate();
        Tasks.repaint();
    }





    /**
     * Shows a dialog for task actions based on the task state.
     *
     * @param index the index of the task to be acted upon
     */
    public void showDialog(int index) {
        String state = task_kid_control.getKid().getTaskList().getNonConfirmedTask().getTask(index).getCondition(task_kid_control.getKid().getTaskList().getNonConfirmedTask().getTask(index).getState());
        switch (state) {
            case "Submitted":
                JOptionPane.showMessageDialog(this, "You have submitted this task, please wait for parent's confirmation", "Message", JOptionPane.WARNING_MESSAGE);
                break;
            case "Action":
                int initialResponse = JOptionPane.showConfirmDialog(this,
                        "Submit the task or cancel the taken status? Yes for Submit, No for Cancel",
                        "Submit or Cancel",
                        JOptionPane.YES_NO_OPTION,
                        JOptionPane.QUESTION_MESSAGE);

                if (initialResponse == JOptionPane.YES_OPTION) {
                    // 第一次确认提交任务
                    int confirmSubmit = JOptionPane.showConfirmDialog(this,
                            "Are you sure you want to submit the task?",
                            "Confirm Submit",
                            JOptionPane.YES_NO_OPTION,
                            JOptionPane.QUESTION_MESSAGE);

                    if (confirmSubmit == JOptionPane.YES_OPTION) {
                        task_kid_control.getKid().getTaskList().getNonConfirmedTask().getTask(i).setState("ToBeConfirmed");
                        // 确认提交任务的逻辑
                        // 发信息
                        String name = task_kid_control.getKid().getTaskList().getNonConfirmedTask().getTask(index).getName();
                        task_kid_control.getKid().getMessagelist().addTaskMessage("Child_Opt","You have submitted the task "+ name);
                        JOptionPane.showMessageDialog(this,
                                "Task has been submitted.",
                                "Task Submitted",
                                JOptionPane.INFORMATION_MESSAGE);
                    }
                } else if (initialResponse == JOptionPane.NO_OPTION) {
                    // 第一次确认取消任务
                    int confirmCancel = JOptionPane.showConfirmDialog(this,
                            "Are you sure you want to cancel the mission?",
                            "Confirm Cancel",
                            JOptionPane.YES_NO_OPTION,
                            JOptionPane.WARNING_MESSAGE);

                    if (confirmCancel == JOptionPane.YES_OPTION) {
                        task_kid_control.getKid().getTaskList().getNonConfirmedTask().getTask(i).setState("ToBeTaken");
                        // 发信息
                        String name = task_kid_control.getKid().getTaskList().getNonConfirmedTask().getTask(index).getName();
                        task_kid_control.getKid().getMessagelist().addTaskMessage("Child_Opt","You have cancel taken the task "+ name);
                        // 确认取消任务的逻辑
                        JOptionPane.showMessageDialog(this,
                                "Task has been canceled.",
                                "Task Canceled",
                                JOptionPane.INFORMATION_MESSAGE);
                    }
                }
                updateTaskDetails();
                break;
            case "Pick it":
                int response1 = JOptionPane.showConfirmDialog(this, task_kid_control.getKid().getTaskList().getNonConfirmedTask().getTask(index).getCon1(task_kid_control.getKid().getTaskList().getNonConfirmedTask().getTask(index).getState()), "Confirmation", JOptionPane.YES_NO_OPTION);
                if (response1 == JOptionPane.YES_OPTION) {
                    JOptionPane.showMessageDialog(this, task_kid_control.getKid().getTaskList().getNonConfirmedTask().getTask(index).getCon2(task_kid_control.getKid().getTaskList().getNonConfirmedTask().getTask(index).getState()), "Message", JOptionPane.INFORMATION_MESSAGE);
                    task_kid_control.getKid().getTaskList().updateTask(task_kid_control.getKid().getTaskList().getNonConfirmedTask().getTask(index).getName(), task_kid_control.getKid().getTaskList().getNonConfirmedTask().getTask(index).taskOperation(task_kid_control.getKid().getTaskList().getNonConfirmedTask().getTask(index)));
                    // 发信息
                    String name = task_kid_control.getKid().getTaskList().getNonConfirmedTask().getTask(index).getName();
                    task_kid_control.getKid().getMessagelist().addTaskMessage("Child_Opt","You have taken the task "+ name);
                    updateTaskDetails();
                }
        }
    }

    /**
     * Shows a warning dialog for account selection.
     */
    public void showWarning() {
        JOptionPane.showMessageDialog(this, "Please select your account first.", "Message", JOptionPane.WARNING_MESSAGE);
    }


    /**
     * Refreshes the task list and returns the index of the specified task.
     *
     * @param task_name the name of the task to find the index of
     * @return the index of the task in the list
     */
    public int ListRefresh(String task_name) {
        return task_kid_control.getKid().getTaskList().getNonConfirmedTask().getIndex(task_name);
    }


    /**
     * Initializes the UI components.
     */
    private void $$$setupUI$$$() {

        myMouseListener=new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                task_kid_control.mouseClicked(e,i);
            }
        };
        Container = new JPanel();
        Container.setLayout(new GridBagLayout());
        Container.setBackground(new Color(-4137489));
        Container.setEnabled(true);
        SavingGoals = new JPanel();
        SavingGoals.setLayout(new BorderLayout(0, 0));
        SavingGoals.setBackground(new Color(-1052689));
        GridBagConstraints gbc;
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        gbc.weightx = 1.0;
        gbc.weighty = 0.8;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(0, 15, 0, 15);
        Container.add(SavingGoals, gbc);
        SavingTitle = new JPanel();
        SavingTitle.setLayout(new GridBagLayout());
        SavingTitle.setBackground(new Color(-1052689));
        SavingTitle.setPreferredSize(new Dimension(400, 70));
        SavingGoals.add(SavingTitle, BorderLayout.NORTH);
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
        SavingTitle.add(saving, gbc);
        money = new JLabel();
        Font moneyFont = this.$$$getFont$$$("Arial Black", Font.BOLD, 28, money.getFont());
        if (moneyFont != null) money.setFont(moneyFont);
        money.setForeground(new Color(-9975466));

        money.setText("$"+task_kid_control.getKid().getAccountManager().getTotalCurrentBalance()+"/$"+task_kid_control.getKid().getAccountManager().getSavingGoal());

        gbc = new GridBagConstraints();
        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        gbc.anchor = GridBagConstraints.EAST;
        SavingTitle.add(money, gbc);
        progressbar = new JPanel();
        progressbar.setLayout(new GridBagLayout());
        progressbar.setBackground(new Color(-1052689));
        SavingGoals.add(progressbar, BorderLayout.SOUTH);
        progressBar1 = new JProgressBar();
        progressBar1.setBackground(new Color(-2565928));
        progressBar1.setForeground(new Color(-1010247));
        progressBar1.setMinimum(0);
        progressBar1.setStringPainted(false);

        if(task_kid_control.getKid().getAccountManager().getSavingGoal()==0){
            progressBar1.setValue(100);
        }
        else{
            progressBar1.setValue((int)((task_kid_control.getKid().getAccountManager().getTotalCurrentBalance()/task_kid_control.getKid().getAccountManager().getSavingGoal())*100));
        }


        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        progressbar.add(progressBar1, gbc);
        progress = new JLabel();
        Font progressFont = this.$$$getFont$$$("Arial Black", -1, 16, progress.getFont());
        if (progressFont != null) progress.setFont(progressFont);
        progress.setForeground(new Color(-12763843));

        if(task_kid_control.getKid().getAccountManager().getSavingGoal()==0) {
            progress.setText("100%");
        }
        else {
            double totalCurrentBalance = task_kid_control.getKid().getAccountManager().getTotalCurrentBalance();
            double savingGoal = task_kid_control.getKid().getAccountManager().getSavingGoal();
            double percentage = (totalCurrentBalance / savingGoal) * 100;
            String formattedPercentage = String.format("%.2f%%", percentage);
            progress.setText(formattedPercentage);
        }
        //progress.setText(task_kid_control.getKid().getAccountManager().getTotalCurrentBalance()/task_kid_control.getKid().getAccountManager().getSavingGoal()*100+"%");


        gbc = new GridBagConstraints();
        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.weighty = 1.0;
        gbc.anchor = GridBagConstraints.EAST;
        progressbar.add(progress, gbc);
        Tasks = new JPanel();
        Tasks.setLayout(new GridBagLayout());
        Tasks.setBackground(new Color(-4137489));
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        gbc.anchor = GridBagConstraints.NORTH;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(0, 0, 10, 5);
        Container.add(Tasks, gbc);
        taskList = new JPanel();
        taskList.setLayout(new GridBagLayout());
        taskList.setBackground(new Color(-4137489));
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        gbc.anchor = GridBagConstraints.NORTH;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(23, 0, 0, 0);
        Tasks.add(taskList, gbc);
        taskTitle = new JPanel();
        taskTitle.setLayout(new GridBagLayout());
        taskTitle.setBackground(new Color(-4137489));
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 1.0;
        gbc.weighty = 0.2;
        gbc.anchor = GridBagConstraints.NORTH;
        gbc.insets = new Insets(0, 0, 20, 0);
        taskList.add(taskTitle, gbc);
        task_title = new JLabel();
        Font task_titleFont = this.$$$getFont$$$("Arial Black", Font.BOLD, 26, task_title.getFont());
        if (task_titleFont != null) task_title.setFont(task_titleFont);
        task_title.setForeground(new Color(-12763843));
        task_title.setText("Task List");
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        gbc.anchor = GridBagConstraints.WEST;
        taskTitle.add(task_title, gbc);
        MoreInfo = new JLabel();
        MoreInfo.setForeground(new Color(-10262674));
        MoreInfo.setText("Click the task to see more");
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        gbc.anchor = GridBagConstraints.WEST;
        taskTitle.add(MoreInfo, gbc);
        TaskList = new JScrollPane();
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.anchor = GridBagConstraints.NORTH;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(0, 5, 0, 0);
        taskList.add(TaskList, gbc);
        list1 = new JList();
        final DefaultListModel defaultListModel1 = new DefaultListModel();
        List<Task> allTasks = task_kid_control.getKid().getTaskList().getNonConfirmedTask().getAllTasks();
        int index = 0;
        for (Task task : allTasks) {
            defaultListModel1.addElement(task_kid_control.getKid().getTaskList().getNonConfirmedTask().getTask(index).getName());
            index++;
        }
//        defaultListModel1.addElement("Sweep floor;");
//        defaultListModel1.addElement("Independent study;");
//        final AtomicInteger i1 = new AtomicInteger();
        list1.addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting()) {
                JList source = (JList) e.getSource();
                String selected = (String) source.getSelectedValue();
                i = ListRefresh(selected);
                updateTaskDetails();
            }
        });

        list1.setModel(defaultListModel1);
        TaskList.setViewportView(list1);
        TaskShown = new JPanel();
        TaskShown.setLayout(new GridBagLayout());
        TaskShown.setBackground(new Color(-4137489));
        gbc = new GridBagConstraints();
        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.weightx = 1.0;
        gbc.weighty = 0.7;
        gbc.anchor = GridBagConstraints.NORTH;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(30, 10, 20, 10);
        Tasks.add(TaskShown, gbc);
        taskInfomation = new JPanel();
        taskInfomation.setLayout(new GridBagLayout());
        taskInfomation.setBackground(new Color(-4137489));
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 1.0;
        gbc.weighty = 0.33;
        gbc.anchor = GridBagConstraints.NORTH;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(0, 0, 10, 0);
        TaskShown.add(taskInfomation, gbc);
        infotitle = new JLabel();
        Font infotitleFont = this.$$$getFont$$$("Arial Black", Font.BOLD, 26, infotitle.getFont());
        if (infotitleFont != null) infotitle.setFont(infotitleFont);
        infotitle.setForeground(new Color(-12763843));
        infotitle.setText("Task Information");
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        gbc.insets = new Insets(0, 0, 10, 0);
        taskInfomation.add(infotitle, gbc);
        ShownPanel = new JPanel();
        ShownPanel.setLayout(new GridBagLayout());
        ShownPanel.setBackground(new Color(-992809));
        ShownPanel.setMinimumSize(new Dimension(510, 450));
        ShownPanel.setPreferredSize(new Dimension(510, 210));
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.weightx = 1.0;
        gbc.weighty = 1.2;
        gbc.anchor = GridBagConstraints.NORTH;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(0, 0, 50, 0);
        TaskShown.add(ShownPanel, gbc);
        taskDetails = new JPanel();
        taskDetails.setLayout(new BorderLayout(0, 0));
        taskDetails.setBackground(new Color(-921103));
        taskDetails.setPreferredSize(new Dimension(440, 101));
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 4.0;
        gbc.weighty = 1.0;
        gbc.fill = GridBagConstraints.BOTH;
        ShownPanel.add(taskDetails, gbc);
        Salary = new JLabel();
        Font SalaryFont = this.$$$getFont$$$("Arial Black", -1, 20, Salary.getFont());
        if (SalaryFont != null) Salary.setFont(SalaryFont);
        Salary.setForeground(new Color(-9975466));
        Salary.setHorizontalAlignment(2);
        Salary.setText("$"+task_kid_control.getKid().getTaskList().getNonConfirmedTask().getTask(i).getReward());
        Salary.setVerticalAlignment(1);
        taskDetails.add(Salary, BorderLayout.EAST);
        Status = new JLabel();
        Font StatusFont = this.$$$getFont$$$("Arial Black", -1, 20, Status.getFont());
        if (StatusFont != null) Status.setFont(StatusFont);
        Status.setForeground(new Color(-10262674));
        Status.setText(task_kid_control.getKid().getTaskList().getTask(i).getText(task_kid_control.getKid().getTaskList().getNonConfirmedTask().getTask(i).getState()));
        taskDetails.add(Status, BorderLayout.SOUTH);

        Description = new JLabel();
        Font DescriptionFont = this.$$$getFont$$$("Arial Black", -1, 16, Description.getFont());
        if (DescriptionFont != null) Description.setFont(DescriptionFont);
        Description.setForeground(new Color(-13947600));
        Description.setHorizontalAlignment(SwingConstants.LEFT);
        Description.setHorizontalTextPosition(SwingConstants.LEFT);
        String des1 = task_kid_control.getKid().getTaskList().getNonConfirmedTask().getTask(0).getDescription();
        Description.setText(task_kid_control.getKid().getTaskList().descriptionSet(des1));
        Description.setVerticalAlignment(0);
        taskDetails.add(Description, BorderLayout.WEST);
        name = new JLabel();
        Font nameFont = this.$$$getFont$$$("Arial Black", -1, 28, name.getFont());
        if (nameFont != null) name.setFont(nameFont);
        name.setForeground(new Color(-13534488));
        name.setHorizontalAlignment(0);
        name.setHorizontalTextPosition(0);
        name.setText(task_kid_control.getKid().getTaskList().getNonConfirmedTask().getTask(i).getName());
        name.setVerticalAlignment(3);
        taskDetails.add(name, BorderLayout.NORTH);
        Button = new JPanel();
        Button.setLayout(new GridBagLayout());
        Button.setBackground(new Color(-992809));
        Button.setPreferredSize(new Dimension(90, 29));
        gbc = new GridBagConstraints();
        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.weightx = 4.0;
        gbc.weighty = 1.0;
        gbc.anchor = GridBagConstraints.WEST;  // 对齐到东侧（右侧）
        ShownPanel.add(Button, gbc);
        buttonlabel = new JLabel();
        buttonlabel.setCursor(new Cursor(Cursor.HAND_CURSOR));
        buttonlabel.addMouseListener(myMouseListener);
        Font buttonlabelFont = this.$$$getFont$$$("Arial Black", Font.BOLD, 15, buttonlabel.getFont());
        if (buttonlabelFont != null) buttonlabel.setFont(buttonlabelFont);
        buttonlabel.setForeground(new Color(-1010247));
        buttonlabel.setHorizontalAlignment(SwingConstants.LEFT);
        buttonlabel.setHorizontalTextPosition(SwingConstants.LEFT);
        buttonlabel.setPreferredSize(new Dimension(140, 23));
        buttonlabel.setText(task_kid_control.getKid().getTaskList().getNonConfirmedTask().getTask(0).getCondition(task_kid_control.getKid().getTaskList().getNonConfirmedTask().getTask(0).getState()));
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        gbc.anchor = GridBagConstraints.EAST;
        Button.add(buttonlabel, gbc);
        Selector = new JPanel();
        Selector.setLayout(new GridBagLayout());
        Selector.setBackground(new Color(-1052689));
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.weightx = 1.0;
        gbc.weighty = 0.8;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.insets = new Insets(0, 15, 30, 15);
        TaskShown.add(Selector, gbc);
        final JLabel label1 = new JLabel();
        Font label1Font = this.$$$getFont$$$("Arial Black", -1, 20, label1.getFont());
        if (label1Font != null) label1.setFont(label1Font);
        label1.setForeground(new Color(-1010247));
        label1.setText("Select an account for task Salary Depository");
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        gbc.anchor = GridBagConstraints.NORTH;
        Selector.add(label1, gbc);
        Select = new JComboBox();
//        Select.setSelectedItem("CA1"); // Set the default selection to "CA1"
//        Select.addItemListener(e -> {
//            if (e.getStateChange() == ItemEvent.SELECTED) {
//                task_kid_control.setSelectedAccountName((String) e.getItem(),currentAccountLabel);
//            }
//        });
        final DefaultComboBoxModel defaultComboBoxModel1 = new DefaultComboBoxModel();
        for (CurrentAccount account : task_kid_control.getKid().getAccountManager().getCurrentAccounts()) {
            defaultComboBoxModel1.addElement(account.getName());
        }
        Select.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String selectedAccount = (String) Select.getSelectedItem();
                task_kid_control.getKid().getTaskList().changeDepository(selectedAccount);
                JOptionPane.showMessageDialog(null,
                        "You have selected current account: " + selectedAccount,
                        "Account Selected",
                        JOptionPane.INFORMATION_MESSAGE);
            }
        });
//        defaultComboBoxModel1.addElement("CA1");
//        defaultComboBoxModel1.addElement("CA2");
//        defaultComboBoxModel1.addElement("CA3");
        Select.setModel(defaultComboBoxModel1);
        gbc = new GridBagConstraints();
        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.WEST;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        Selector.add(Select, gbc);
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

}
