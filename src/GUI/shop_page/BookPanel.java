import javax.swing.*;
import java.awt.*;

public class BookPanel extends JFrame {

    public BookPanel() {
        super("Book Details");
        setSize(400, 250);  // 修改窗口大小以更好地适应内容
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        setLayout(new GridBagLayout());
        GridBagConstraints constraints = new GridBagConstraints();
        Insets insets = new Insets(10, 10, 10, 10);  // 设置组件间的间距

        // 设置字体
        Font labelFont = new Font("Arial", Font.BOLD, 18);
        Font buttonFont = new Font("Arial", Font.PLAIN, 16);

        // 创建标签和其他控件
        JLabel nameLabel = new JLabel("BOOK");
        nameLabel.setFont(labelFont);
        JLabel priceLabel = new JLabel("$5");
        priceLabel.setFont(labelFont);

        JButton plusButton = new JButton("+");
        plusButton.setFont(buttonFont);
        plusButton.setBackground(Color.LIGHT_GRAY);  // 设置按钮背景色
        plusButton.setOpaque(true);
        plusButton.setBorderPainted(false);

        JLabel quantityLabel = new JLabel("3");
        quantityLabel.setFont(labelFont);

        JButton minusButton = new JButton("-");
        minusButton.setFont(buttonFont);
        minusButton.setBackground(Color.LIGHT_GRAY);
        minusButton.setOpaque(true);
        minusButton.setBorderPainted(false);

        JButton confirmButton = new JButton(new ImageIcon("check_icon.png"));  // 假设您有一个勾选图标

        // 添加组件到面板
        constraints.fill = GridBagConstraints.HORIZONTAL;
        constraints.insets = insets;

        constraints.gridx = 0;
        constraints.gridy = 0;
        constraints.gridwidth = 2;
        add(nameLabel, constraints);

        constraints.gridy = 1;
        add(priceLabel, constraints);

        constraints.gridx = 0;
        constraints.gridy = 2;
        constraints.gridwidth = 1;
        add(plusButton, constraints);

        constraints.gridx = 1;
        add(quantityLabel, constraints);

        constraints.gridx = 2;
        add(minusButton, constraints);

        constraints.gridx = 1;
        constraints.gridy = 3;
        add(confirmButton, constraints);

        setVisible(true);
    }

    public static void main(String[] args) {
        new BookPanel();
    }
}
