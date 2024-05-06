package GUI.shop_page;

import Entity.Product;
import Entity.ProductList;
import javax.swing.*;
import java.awt.*;
import java.util.List;
import java.util.ArrayList;

public class shop_kid extends JPanel {
    private JButton buyButton;
    private List<JToggleButton> toggleButtons;
    private JLabel currentAccountLabel;

    public shop_kid() {
        setLayout(new BorderLayout(10, 10));
        setBackground(new Color(173, 216, 230)); // 浅蓝色背景
        initProducts();
        initUI();
    }

    private void initProducts() {
        ProductList productList = new ProductList();
        productList.addProduct(new Product("BOOK", 5));
        productList.addProduct(new Product("T-SHIRT", 5));
        productList.addProduct(new Product("TOY", 5));
        productList.addProduct(new Product("S", 5));

        toggleButtons = new ArrayList<>();
        for (Product product : productList.getAllProducts()) {
            String buttonHtml = String.format("<html>%s<br>$%.2f</html>", product.getName(), product.getPrice());
            JToggleButton button = new JToggleButton(buttonHtml);
            button.setFocusPainted(false);
            button.setFont(new Font("Arial", Font.PLAIN, 16));
            toggleButtons.add(button);
        }
    }

    private void initUI() {
        add(createHeader(), BorderLayout.NORTH);
        add(createProductsPanel(), BorderLayout.CENTER);
        add(createFooter(), BorderLayout.SOUTH);
    }

    private JPanel createHeader() {
        JPanel header = new JPanel();
        header.setBackground(new Color(173, 216, 230));
        JLabel titleLabel = new JLabel("FAMILY MALL", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        header.add(titleLabel);
        return header;
    }

    private JPanel createProductsPanel() {
        JPanel productsPanel = new JPanel(new GridLayout(2, 2, 10, 10));
        productsPanel.setBackground(new Color(173, 216, 230));
        for (JToggleButton button : toggleButtons) {
            productsPanel.add(button);
        }
        return productsPanel;
    }

    private JPanel createFooter() {
        JPanel footer = new JPanel(new BorderLayout());
        footer.setBackground(new Color(173, 216, 230));

        buyButton = new JButton("BUY!");
        buyButton.setFont(new Font("Arial", Font.BOLD, 18));
        buyButton.setBackground(new Color(0, 128, 0)); // 深绿色
        buyButton.setForeground(Color.WHITE);

        // 中间面板用于放置 BUY 按钮
        JPanel buyPanel = new JPanel(new GridBagLayout()); // 使用 GridBagLayout 来确保按钮居中
        buyPanel.setBackground(new Color(173, 216, 230));
        buyPanel.add(buyButton, new GridBagConstraints()); // 默认约束居中
        footer.add(buyPanel, BorderLayout.CENTER);

        // 创建一个单独的面板用于放置 Current Account 标签
        JPanel accountPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        accountPanel.setBackground(new Color(173, 216, 230));

        currentAccountLabel = new JLabel("Current Account: $60.00");
        currentAccountLabel.setFont(new Font("Arial", Font.PLAIN, 14));
        accountPanel.add(currentAccountLabel);

        footer.add(accountPanel, BorderLayout.SOUTH); // 将账户信息面板添加到底部

        return footer;
    }


    public static void main(String[] args) {
        JFrame frame = new JFrame("Shop Kid");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().add(new shop_kid());
        frame.setSize(900, 450);
        frame.setLocationRelativeTo(null); // Center the window
        frame.setVisible(true);
    }
}
