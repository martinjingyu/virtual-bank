package GUI.task_page;

import Entity.Kids;
import Entity.Task;

import javax.swing.*;
import javax.swing.plaf.FontUIResource;
import javax.swing.text.StyleContext;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;
import java.util.Locale;
import java.util.Objects;

public class Depository extends JPanel {
    private JPanel Container;
    private JPanel textArea;
    private JPanel saving;
    private JPanel current;
    private Kids kid;

    public Depository(Kids kid) {
        this.kid=kid;
        $$$setupUI$$$(); // Ensures all GUI components are initialized first
        Dimension preferredSize = new Dimension(900, 540);
        Container.setPreferredSize(preferredSize);
        add(Container);
        // Debug to ensure components are initialized
        // 创建并设置 JFrame
        JFrame frame = new JFrame("Depository Window");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);  // 点击关闭按钮时释放窗口资源
        frame.setContentPane(this);  // 将当前 Depository JPanel 设置为内容面板
        frame.pack();  // 根据内容调整大小
        frame.setLocationRelativeTo(null);  // 居中显示窗口
        frame.setVisible(true);  // 显示窗口
    }


    private void showDialog(String destination) {

        if(Objects.equals(kid.getTaskList().getTask(1).getDestination(),destination)){
            JOptionPane.showMessageDialog(this, "The account you are currently using is the one you have selected.", "Message", JOptionPane.WARNING_MESSAGE);
        }else {
            // 使用 JOptionPane 来显示消息
            int response = JOptionPane.showConfirmDialog(this, "Are you sure to switch your account to "+destination+" ?", "Confirmation", JOptionPane.YES_NO_OPTION);

            if (response == JOptionPane.YES_OPTION) {
                JOptionPane.showMessageDialog(this,"You have switch your account to "+destination+" successfully.", "Message", JOptionPane.INFORMATION_MESSAGE);
                kid.getTaskList().changeDepository(destination);
            }
        }

    }



    private void $$$setupUI$$$() {
        Container = new JPanel();
        Container.setLayout(new GridBagLayout());
        Container.setBackground(new Color(-4137489));
        textArea = new JPanel();
        textArea.setLayout(new GridBagLayout());
        textArea.setBackground(new Color(-921103));
        GridBagConstraints gbc;
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        gbc.weightx = 1.0;
        gbc.weighty = 0.4;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.insets = new Insets(30, 10, 30, 10);
        Container.add(textArea, gbc);
        final JLabel label1 = new JLabel();
        Font label1Font = this.$$$getFont$$$("Arial Black", -1, 20, label1.getFont());
        if (label1Font != null) label1.setFont(label1Font);
        label1.setForeground(new Color(-12763843));
        label1.setText("When a parent pays you for an assignment,");
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        textArea.add(label1, gbc);
        final JLabel label2 = new JLabel();
        Font label2Font = this.$$$getFont$$$("Arial Black", -1, 20, label2.getFont());
        if (label2Font != null) label2.setFont(label2Font);
        label2.setForeground(new Color(-12763843));
        label2.setText("which account do you expect to be credited?");
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        textArea.add(label2, gbc);
        saving = new JPanel();
        saving.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {

                showDialog("saving");
            }


        });

        List<Task> allTasks = kid.getTaskList().getAllTasks();
        System.out.println("应该是saving");
        for (Task task : allTasks) {
            System.out.println(task);
        }


        saving.setLayout(new GridBagLayout());
        saving.setBackground(new Color(-921103));
        gbc = new GridBagConstraints();
        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.insets = new Insets(20, 10, 30, 20);
        Container.add(saving, gbc);
        final JLabel label3 = new JLabel();
        Font label3Font = this.$$$getFont$$$("Arial Black", -1, 20, label3.getFont());
        if (label3Font != null) label3.setFont(label3Font);
        label3.setForeground(new Color(-1010247));
        label3.setText("Saving");
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        gbc.anchor = GridBagConstraints.SOUTH;
        saving.add(label3, gbc);
        final JLabel label4 = new JLabel();
        Font label4Font = this.$$$getFont$$$("Arial Black", -1, 20, label4.getFont());
        if (label4Font != null) label4.setFont(label4Font);
        label4.setForeground(new Color(-1010247));
        label4.setText("Account");
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        gbc.anchor = GridBagConstraints.NORTH;
        saving.add(label4, gbc);
        current = new JPanel();
        current.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {

                showDialog("current");
            }


        });

        List<Task> allTasks1 = kid.getTaskList().getAllTasks();
        System.out.println("应该是current");
        for (Task task : allTasks1) {
            System.out.println(task);
        }

        current.setLayout(new GridBagLayout());
        current.setBackground(new Color(-921103));
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.insets = new Insets(20, 10, 30, 20);
        Container.add(current, gbc);
        final JLabel label5 = new JLabel();
        Font label5Font = this.$$$getFont$$$("Arial Black", -1, 20, label5.getFont());
        if (label5Font != null) label5.setFont(label5Font);
        label5.setForeground(new Color(-13534488));
        label5.setText("Current");
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        gbc.anchor = GridBagConstraints.SOUTH;
        current.add(label5, gbc);
        final JLabel label6 = new JLabel();
        Font label6Font = this.$$$getFont$$$("Arial Black", -1, 20, label6.getFont());
        if (label6Font != null) label6.setFont(label6Font);
        label6.setForeground(new Color(-13534488));
        label6.setText("Account");
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        gbc.anchor = GridBagConstraints.NORTH;
        current.add(label6, gbc);
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
