package GUI.task_page;

import Controller.task.Task_parent_control;
import Entity.Task;
import GUI.MainFrame_kid;

import javax.swing.*;
import javax.swing.plaf.FontUIResource;
import javax.swing.text.StyleContext;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Locale;


public class TaskSet extends JPanel {
    private JPanel Container;
    private JPanel taskSetting;
    private JPanel settingPanel;
    private JPanel settingTitle;
    private JLabel set_title;
    private JPanel textArea;
    private JTextField task_name;
    private JTextField task_salary;
    private JTextField task_description;
    private JPanel button;
    private JLabel submit_button;
    private JPanel TaskSet;
    private JLabel salary;
    private JLabel name;
    private JLabel description;
    private JFrame frame;
    private Task_parent_control task_parent_control;
    private MainFrame_kid mainFrame;//这里等parent登录写好了要改成parent，下面也是

    public TaskSet(Task_parent_control task_parent_control,MainFrame_kid mainFrame) {
        $$$setupUI$$$();
        this.task_parent_control = task_parent_control;
        this.mainFrame = mainFrame;
        Dimension preferredSize = new Dimension(540, 540);
        Container.setPreferredSize(preferredSize);
        add(Container);
        frame = new JFrame("New Task Setting");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setContentPane(this);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

//    public static void main(String[] args) {
//        new TaskSet(task_parent_control);
//    }



    private void $$$setupUI$$$() {
        Container = new JPanel();
        Container.setLayout(new GridBagLayout());
        Container.setBackground(new Color(-4137489));
        TaskSet = new JPanel();
        TaskSet.setLayout(new GridBagLayout());
        TaskSet.setBackground(new Color(-4137489));
        GridBagConstraints gbc;
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.insets = new Insets(20, 10, 20, 10);
        Container.add(TaskSet, gbc);
        taskSetting = new JPanel();
        taskSetting.setLayout(new GridBagLayout());
        taskSetting.setBackground(new Color(-4137489));
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        gbc.fill = GridBagConstraints.BOTH;
        TaskSet.add(taskSetting, gbc);
        settingPanel = new JPanel();
        settingPanel.setLayout(new GridBagLayout());
        settingPanel.setBackground(new Color(-1));
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        gbc.fill = GridBagConstraints.BOTH;
        taskSetting.add(settingPanel, gbc);
        settingTitle = new JPanel();
        settingTitle.setLayout(new GridBagLayout());
        settingTitle.setBackground(new Color(-1));
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        gbc.fill = GridBagConstraints.BOTH;
        settingPanel.add(settingTitle, gbc);
        set_title = new JLabel();
        Font set_titleFont = this.$$$getFont$$$("Arial Black", Font.BOLD, 36, set_title.getFont());
        if (set_titleFont != null) set_title.setFont(set_titleFont);
        set_title.setForeground(new Color(-13534488));
        set_title.setText("Task Setting");
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        gbc.anchor = GridBagConstraints.WEST;
        settingTitle.add(set_title, gbc);
        textArea = new JPanel();
        textArea.setLayout(new GridBagLayout());
        textArea.setBackground(new Color(-1));
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.insets = new Insets(0, 2, 0, 5);
        settingPanel.add(textArea, gbc);
        task_name = new JTextField();
        task_name.setBackground(new Color(-986896));
        task_name.setForeground(new Color(-12763843));
        task_name.setText("");
        gbc = new GridBagConstraints();
        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        gbc.anchor = GridBagConstraints.WEST;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.insets = new Insets(2, 0, 2, 0);
        textArea.add(task_name, gbc);
        task_salary = new JTextField();
        task_salary.setBackground(new Color(-986896));
        task_salary.setForeground(new Color(-12763843));
        task_salary.setText("");
        gbc = new GridBagConstraints();
        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        gbc.anchor = GridBagConstraints.WEST;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.insets = new Insets(2, 0, 2, 0);
        textArea.add(task_salary, gbc);
        task_description = new JTextField();
        task_description.setBackground(new Color(-986896));
        task_description.setForeground(new Color(-12763843));
        task_description.setText("");
        gbc = new GridBagConstraints();
        gbc.gridx = 1;
        gbc.gridy = 2;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        gbc.anchor = GridBagConstraints.WEST;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.insets = new Insets(2, 0, 2, 0);
        textArea.add(task_description, gbc);
        name = new JLabel();
        Font nameFont = this.$$$getFont$$$("Arial Black", -1, -1, name.getFont());
        if (nameFont != null) name.setFont(nameFont);
        name.setForeground(new Color(-1010247));
        name.setText("Task Name:");
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.WEST;
        textArea.add(name, gbc);
        salary = new JLabel();
        Font salaryFont = this.$$$getFont$$$("Arial Black", -1, -1, salary.getFont());
        if (salaryFont != null) salary.setFont(salaryFont);
        salary.setForeground(new Color(-1010247));
        salary.setText("Salary:");
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.anchor = GridBagConstraints.WEST;
        textArea.add(salary, gbc);
        description = new JLabel();
        Font descriptionFont = this.$$$getFont$$$("Arial Black", -1, -1, description.getFont());
        if (descriptionFont != null) description.setFont(descriptionFont);
        description.setForeground(new Color(-1010247));
        description.setText("Description:");
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.anchor = GridBagConstraints.WEST;
        textArea.add(description, gbc);
        button = new JPanel();
        button.setLayout(new GridBagLayout());
        button.setBackground(new Color(-1052689));
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(20, 0, 20, 0);
        taskSetting.add(button, gbc);
        submit_button = new JLabel();
        submit_button.setCursor(new Cursor(Cursor.HAND_CURSOR));
        submit_button.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                String TaskName,TaskSalary,TaskDescription;
                // 获取文本框输入的内容
                TaskName = task_name.getText();
                TaskSalary = task_salary.getText();
                TaskDescription = task_description.getText();
                if(task_parent_control.validateName(TaskName) && task_parent_control.validateDescription(TaskDescription) && task_parent_control.validateSalary(TaskSalary) && task_parent_control.getKid().getTaskList().checkDuplicateName(TaskName)){
                    // 显示确认对话框
                    int response = JOptionPane.showConfirmDialog(null,
                            "Are you sure you want to set this task as new task?",
                            "Confirm Save",
                            JOptionPane.YES_NO_OPTION);

                    // 检查用户的响应
                    if (response == JOptionPane.YES_OPTION) {
                        task_parent_control.getKid().getTaskList().addTask(new Task(TaskName,Double.parseDouble(TaskSalary),"ToBeTaken",TaskDescription,"x"));

                        // 用户确认保存
                        JOptionPane.showMessageDialog(null,"New task has set.","Save",
                                JOptionPane.INFORMATION_MESSAGE);
                        // 关闭当前窗口
                        mainFrame.refresh();
                        frame.setVisible(false);
                        // mainFrame.refresh();

                    }
                    // 如果用户选择 "No"，不执行保存操作
                } else if (!(task_parent_control.validateName(TaskName))) {
                    JOptionPane.showMessageDialog(null,
                            "Task name should not contain special characters or be empty, and it should contain letters.",
                            "Invalid Task Name",
                            JOptionPane.WARNING_MESSAGE);
                } else if (!(task_parent_control.validateDescription(TaskDescription))) {
                    JOptionPane.showMessageDialog(null,
                            "Task description should only contain letters, numbers, and spaces, and must not exceed 150 characters.",
                            "Invalid Task Description",
                            JOptionPane.WARNING_MESSAGE);
                } else if(!(task_parent_control.validateSalary(TaskSalary))){
                    JOptionPane.showMessageDialog(null,
                            "Task salary should only contain numbers and at most one decimal point.",
                            "Invalid Task Salary",
                            JOptionPane.WARNING_MESSAGE);
                } else if(!(task_parent_control.getKid().getTaskList().checkDuplicateName(TaskName))){
                    JOptionPane.showMessageDialog(null,
                            "The task name '" + TaskName + "' already exists in the task list.",
                            "Duplicate Task Name",
                            JOptionPane.WARNING_MESSAGE);
                }

            }
        });
        Font submit_buttonFont = this.$$$getFont$$$("Arial Black", Font.BOLD, 28, submit_button.getFont());
        if (submit_buttonFont != null) submit_button.setFont(submit_buttonFont);
        submit_button.setForeground(new Color(-12763843));
        submit_button.setText("Submit");
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        gbc.insets = new Insets(20, 30, 20, 30);
        button.add(submit_button, gbc);
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
