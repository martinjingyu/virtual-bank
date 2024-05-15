package GUI.task_page;

import Controller.MainController;
import Controller.task.Task_kid_control;
import Controller.task.Task_parent_control;
import Entity.*;
import GUI.MainFrame_kid;
import GUI.MainFrame_parent;
//import utill.read.ReadBank;

import javax.swing.*;
import javax.swing.plaf.FontUIResource;
import javax.swing.text.StyleContext;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;
import java.util.Locale;

public class Task_parent extends JPanel {
    private JPanel Container;
    private JPanel taskLiast;
    private JPanel taskTitle;
    private JLabel task_title;
    private JScrollPane TaskList;
    private JList list1;
    private JButton setNewTaskButton;
    private JPanel TaskShown;
    private JPanel taskInfomation;
    private JPanel ShownPanel;
    private JPanel taskDetails;
    private JLabel Salary;
    private JLabel Status;
    private JLabel Description;
    private JPanel Button;
    private JLabel name;
    private JLabel infotitle;
    private JLabel buttonlabel;
    private MainFrame_parent mainFrame;
    private Task_parent_control task_parent_control;
    private int i;
    private MouseAdapter myMouseListener;

    public Task_parent() {
        $$$setupUI$$$(); // Ensures all GUI components are initialized first
        Dimension preferredSize = new Dimension(900, 540);
        Container.setPreferredSize(preferredSize);
        add(Container);


    }

    public Task_parent(Task_parent_control controller, MainFrame_parent mainFrame) {
        this.mainFrame = mainFrame;
        this.task_parent_control = controller;


        $$$setupUI$$$(); // Ensures all GUI components are initialized first
        Dimension preferredSize = new Dimension(900, 540);
        controller.setGUI(this);
        Container.setPreferredSize(preferredSize);
        add(Container);



    }
    private void updateTaskDetails() {
        Salary.setText("$" + task_parent_control.getKid().getTaskList().getNonConfirmedTask().getTask(i).getReward());
        Status.setText(task_parent_control.getKid().getTaskList().getNonConfirmedTask().getTask(i).getState());
        String des1 = task_parent_control.getKid().getTaskList().getNonConfirmedTask().getTask(i).getDescription();
        Description.setText(task_parent_control.getKid().getTaskList().descriptionSet(des1));
        name.setText(task_parent_control.getKid().getTaskList().getNonConfirmedTask().getTask(i).getName());
        buttonlabel.setText(task_parent_control.getKid().getTaskList().getNonConfirmedTask().getTask(i).getCondition1(task_parent_control.getKid().getTaskList().getNonConfirmedTask().getTask(i).getState()));
//        buttonlabel.removeMouseListener(myMouseListener);
//        buttonlabel.addMouseListener(myMouseListener);

        ShownPanel.revalidate();
        ShownPanel.repaint();
//        buttonlabel.removeMouseListener(myMouseListener);
    }
    public void showDialog1(int index){
        String state = task_parent_control.getKid().getTaskList().getNonConfirmedTask().getTask(i).getState();
        switch (state){
            case "ToBeConfirmed":
                int response = JOptionPane.showConfirmDialog(this, "Please check if your child has completed the task "
                                + task_parent_control.getKid().getTaskList().getNonConfirmedTask().getTask(i).getName()
                                + ". If they have, click 'Yes' to confirm. If not, click 'No' to reject the task.",
                        "Confirmation", JOptionPane.YES_NO_OPTION);

                if (response == JOptionPane.YES_OPTION) {
                    // 第一次确认通过后，显示第二次确认对话框
                    int confirmResponse = JOptionPane.showConfirmDialog(this,
                            "Are you sure you want to confirm the task completion?",
                            "Final Confirmation", JOptionPane.YES_NO_OPTION);
                    if (confirmResponse == JOptionPane.YES_OPTION) {

                        // 发信息
                        String name = task_parent_control.getKid().getTaskList().getNonConfirmedTask().getTask(index).getName();
                        double salary = task_parent_control.getKid().getTaskList().getNonConfirmedTask().getTask(index).getReward();
                        String destination = task_parent_control.getKid().getTaskList().getNonConfirmedTask().getTask(index).getDestination();
                        task_parent_control.getKid().getMessagelist().addTaskMessage("Parent_Opt","You have confirmed the task "+ name);
                        task_parent_control.getKid().getMessagelist().addTaskMessage("Child_Opt","Your submission for task "+ name +
                                "has been confirmed. You have received reward $" + salary +
                                " to your current account "+ destination);


                        // 加钱
                        // String destination = task_parent_control.getKid().getTaskList().getNonConfirmedTask().getTask(index).getDestination();
                        // double salary = task_parent_control.getKid().getTaskList().getNonConfirmedTask().getTask(index).getReward();
                        System.out.println("salary： ");
                        System.out.println(salary);
                        task_parent_control.getKid().getAccountManager().getCurrentAccountByName(destination).deposit(salary);

                        // 删除任务
                        task_parent_control.getKid().getTaskList().removeTask(task_parent_control.getKid().getTaskList().getNonConfirmedTask().getTask(i));

                        // 显示状态
                        JOptionPane.showMessageDialog(this,
                                "This task has completed successfully!",
                                "Task Completed!", JOptionPane.INFORMATION_MESSAGE);
                    }
                } else if (response == JOptionPane.NO_OPTION) {
                    // 第一次确认拒绝后，显示第二次确认对话框
                    int rejectResponse = JOptionPane.showConfirmDialog(this,
                            "Are you sure you want to reject the task?",
                            "Final Confirmation", JOptionPane.YES_NO_OPTION);
                    if (rejectResponse == JOptionPane.YES_OPTION) {

                        // 拒绝任务的逻辑
                        // 把ToBeConfirmed打回到Taken
                        task_parent_control.getKid().getTaskList().getNonConfirmedTask().getTask(i).setState("Taken");

                        // 发信息
                        String name = task_parent_control.getKid().getTaskList().getNonConfirmedTask().getTask(index).getName();
                        task_parent_control.getKid().getMessagelist().addTaskMessage("Parent_Opt","You have rejected the task "+ name);
                        task_parent_control.getKid().getMessagelist().addTaskMessage("Child_Opt","Your submission for task "+ name + "has been rejected." +
                                " Please redo and resubmit it.");
                    }
                }
                mainFrame.refresh();
                break;
            case "Taken":
                JOptionPane.showMessageDialog(this,
                        "The task has been accepted by the child. Please wait patiently for the child to complete the task.",
                        "Task Accepted",
                        JOptionPane.INFORMATION_MESSAGE);
                break;
            case "ToBeTaken":
                int response1 = JOptionPane.showConfirmDialog(this,
                        "Are you sure you want to delete this task?",
                        "Delete Task Confirmation",
                        JOptionPane.YES_NO_OPTION,
                        JOptionPane.WARNING_MESSAGE);

                if (response1 == JOptionPane.YES_OPTION) {
                    // 执行删除任务的逻辑
                    // 删除任务
                    task_parent_control.getKid().getTaskList().removeTask(task_parent_control.getKid().getTaskList().getNonConfirmedTask().getTask(i));

                    // 发信息
                    String name = task_parent_control.getKid().getTaskList().getNonConfirmedTask().getTask(index).getName();
                    task_parent_control.getKid().getMessagelist().addTaskMessage("Parent_Opt","You have delete the task "+ name);


                    // 显示状态
                    JOptionPane.showMessageDialog(this,
                            "Task has been deleted.",
                            "Task Deleted",
                            JOptionPane.INFORMATION_MESSAGE);
                    mainFrame.refresh();
                }

        }
    }
    public int ListRefresh(String task_name) {
        return task_parent_control.getKid().getTaskList().getNonConfirmedTask().getIndex(task_name);
    }

//    public static void main(String[] args) {
//        new Task_parent();
//    }




    private void $$$setupUI$$$() {
        myMouseListener=new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                task_parent_control.mouseClicked(e,i);
            }
        };
        Container = new JPanel();
        Container.setLayout(new GridBagLayout());
        Container.setBackground(new Color(-4137489));
        Container.setForeground(new Color(-4137489));
        taskLiast = new JPanel();
        taskLiast.setLayout(new GridBagLayout());
        taskLiast.setBackground(new Color(-4137489));
        GridBagConstraints gbc;
        gbc = new GridBagConstraints();
        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        gbc.anchor = GridBagConstraints.NORTH;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(80, 10, 10, 0);
        Container.add(taskLiast, gbc);
        taskTitle = new JPanel();
        taskTitle.setLayout(new GridBagLayout());
        taskTitle.setBackground(new Color(-4137489));
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        gbc.insets = new Insets(0, 0, 20, 0);
        taskLiast.add(taskTitle, gbc);
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
        TaskList = new JScrollPane();
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.fill = GridBagConstraints.BOTH;
        taskLiast.add(TaskList, gbc);
        list1 = new JList();
        final DefaultListModel defaultListModel1 = new DefaultListModel();
        List<Task> allTasks = task_parent_control.getKid().getTaskList().getNonConfirmedTask().getAllTasks();
        int index = 0;
        for (Task task : allTasks) {
            defaultListModel1.addElement(task_parent_control.getKid().getTaskList().getNonConfirmedTask().getTask(index).getName());
            index++;
        }
//        defaultListModel1.addElement("Sweep floor;");
//        defaultListModel1.addElement("Independent study;");
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
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridheight = 3;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.insets = new Insets(0, 10, 20, 10);
        Container.add(TaskShown, gbc);
        taskInfomation = new JPanel();
        taskInfomation.setLayout(new GridBagLayout());
        taskInfomation.setBackground(new Color(-4137489));
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 1.0;
        gbc.weighty = 0.18;
        gbc.anchor = GridBagConstraints.SOUTH;
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
        ShownPanel.setMinimumSize(new Dimension(510, 250));
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.weightx = 1.0;
        gbc.weighty = 0.5;
        gbc.fill = GridBagConstraints.BOTH;
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
        Font SalaryFont = this.$$$getFont$$$("Arial Black", -1, 22, Salary.getFont());
        if (SalaryFont != null) Salary.setFont(SalaryFont);
        Salary.setForeground(new Color(-9975466));
        Salary.setHorizontalAlignment(2);
        Salary.setText("$12");
        Salary.setVerticalAlignment(1);
        taskDetails.add(Salary, BorderLayout.EAST);
        Status = new JLabel();
        Font StatusFont = this.$$$getFont$$$("Arial Black", -1, 18, Status.getFont());
        if (StatusFont != null) Status.setFont(StatusFont);
        Status.setForeground(new Color(-10262674));
        Status.setText(task_parent_control.getKid().getTaskList().getNonConfirmedTask().getTask(0).getState());
        taskDetails.add(Status, BorderLayout.SOUTH);
        Description = new JLabel();
        Font DescriptionFont = this.$$$getFont$$$("Arial Black", -1, 16, Description.getFont());
        if (DescriptionFont != null) Description.setFont(DescriptionFont);
        Description.setForeground(new Color(-13947600));
        Description.setHorizontalAlignment(4);
        Description.setHorizontalTextPosition(2);
        String des1 = task_parent_control.getKid().getTaskList().getNonConfirmedTask().getTask(i).getDescription();
        Description.setText(task_parent_control.getKid().getTaskList().descriptionSet(des1));
        Description.setVerticalAlignment(0);
        Description.setVerticalTextPosition(1);
        taskDetails.add(Description, BorderLayout.WEST);
        name = new JLabel();
        Font nameFont = this.$$$getFont$$$("Arial Black", -1, 26, name.getFont());
        if (nameFont != null) name.setFont(nameFont);
        name.setForeground(new Color(-13534488));
        name.setText(task_parent_control.getKid().getTaskList().getNonConfirmedTask().getTask(0).getName());
        taskDetails.add(name, BorderLayout.NORTH);
        Button = new JPanel();
        Button.setLayout(new GridBagLayout());
        Button.setBackground(new Color(-992809));
        Button.setPreferredSize(new Dimension(90, 29));
        gbc = new GridBagConstraints();
        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.weightx = 6.0;
        gbc.weighty = 1.0;
        ShownPanel.add(Button, gbc);
        buttonlabel = new JLabel();
        buttonlabel.setCursor(new Cursor(Cursor.HAND_CURSOR));
        buttonlabel.addMouseListener(myMouseListener);
        Font buttonlabelFont = this.$$$getFont$$$("Arial Black", Font.BOLD, 18, buttonlabel.getFont());
        if (buttonlabelFont != null) buttonlabel.setFont(buttonlabelFont);
        buttonlabel.setHorizontalAlignment(SwingConstants.LEFT);
        buttonlabel.setHorizontalTextPosition(SwingConstants.LEFT);
        buttonlabel.setPreferredSize(new Dimension(160, 23));
        buttonlabel.setForeground(new Color(-1010247));
        buttonlabel.setText(task_parent_control.getKid().getTaskList().getNonConfirmedTask().getTask(i).getCondition1(task_parent_control.getKid().getTaskList().getNonConfirmedTask().getTask(i).getState()));
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        gbc.anchor = GridBagConstraints.WEST;
        Button.add(buttonlabel, gbc);
        setNewTaskButton = new JButton();
        setNewTaskButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new TaskSet(task_parent_control,mainFrame);
            }

        });
        setNewTaskButton.setBackground(new Color(-992809));
        Font setNewTaskButtonFont = this.$$$getFont$$$("Arial Black", -1, 16, setNewTaskButton.getFont());
        if (setNewTaskButtonFont != null) setNewTaskButton.setFont(setNewTaskButtonFont);
        setNewTaskButton.setForeground(new Color(-1010247));
        setNewTaskButton.setText("Set new task");
        gbc = new GridBagConstraints();
        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.insets = new Insets(0, 0, 70, 0);
        Container.add(setNewTaskButton, gbc);
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
