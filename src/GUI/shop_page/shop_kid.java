package GUI.shop_page;

import Controller.shop.shopController;
import Entity.Kids;
import Entity.Product;
import Entity.ProductList;
import utill.read.ReadAll;

import javax.swing.*;
import java.awt.*;
import java.util.List;
import java.awt.event.ItemEvent;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;
import javax.swing.JRadioButton;
import java.util.ArrayList;

public class shop_kid extends JPanel {
    private JButton buyButton;
    private List<JRadioButton> toggleButtons;
    private ProductList productList;
    private shopController shopController;
    private JLabel selectedTotalLabel;
    private JLabel currentAccountLabel;

    public shop_kid(Kids kid) {
        this.productList = kid.getProductList();
        this.shopController = new shopController(kid);
        this.selectedTotalLabel = new JLabel("Selected Total: $      0.00");
        this.selectedTotalLabel.setForeground(Color.BLACK);
        this.currentAccountLabel = new JLabel(String.format("Current Account: $%9.2f", kid.getBank().getCurrentTotal()));
        this.toggleButtons = new ArrayList<>();

        setLayout(new BorderLayout(10, 10));
        setBackground(new Color(173, 216, 230));
        initUI();
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

    private JScrollPane createProductsPanel() {
        JPanel productsPanel = new JPanel(new GridLayout(0, 4, 10, 10)); // 动态行数，固定4列
        productsPanel.setBackground(new Color(173, 216, 230));

        for (Product product : this.productList.getAllProducts()) {
            JPanel productPanel = new JPanel();
            productPanel.setLayout(new BoxLayout(productPanel, BoxLayout.Y_AXIS));
            productPanel.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
            productPanel.setBackground(Color.WHITE); // 设置白色背景
            productPanel.setPreferredSize(new Dimension(200, 85)); // 每个面板固定大小

            JLabel nameLabel = new JLabel(product.getName(), SwingConstants.CENTER);
            nameLabel.setFont(new Font("Arial", Font.BOLD, 16)); // 商品名称字体更大
            nameLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

            JLabel priceLabel = new JLabel(String.format("$%.2f", product.getPrice()), SwingConstants.CENTER);
            priceLabel.setFont(new Font("Arial", Font.PLAIN, 14)); // 价格字体
            priceLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

            JRadioButton radioButton = new JRadioButton();
            radioButton.setAlignmentX(Component.CENTER_ALIGNMENT);

            productPanel.add(nameLabel);
            productPanel.add(Box.createVerticalStrut(10)); // 添加间隔
            productPanel.add(priceLabel);
            productPanel.add(Box.createVerticalStrut(10)); // 添加间隔
            productPanel.add(radioButton);
            productPanel.add(Box.createVerticalStrut(5)); // 与底部边界的间隔

            radioButton.addItemListener(e -> {
                shopController.updateSelectedProductList(product, e.getStateChange() == ItemEvent.SELECTED,selectedTotalLabel);
            });

            toggleButtons.add(radioButton);
            productsPanel.add(productPanel);
        }

        JScrollPane scrollPane = new JScrollPane(productsPanel);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane.setPreferredSize(new Dimension(800, 480)); // 设定JScrollPane的大小，可调整以适合您的界面需求

        return scrollPane;
    }

    private JPanel createFooter() {
        JPanel footer = new JPanel(new GridBagLayout());
        footer.setBackground(new Color(173, 216, 230));

        buyButton = new JButton("BUY!");
        buyButton.setFont(new Font("Arial", Font.BOLD, 18));
        buyButton.setBackground(new Color(0, 128, 0)); // 深绿色
        buyButton.setForeground(Color.WHITE);
        JPanel buyPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        buyPanel.setBackground(new Color(173, 216, 230));
        buyPanel.add(buyButton);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridwidth = 2; // 设置跨两列
        gbc.gridy = 0;
        gbc.weightx = 1.0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        footer.add(buyPanel, gbc);

        // Selected Total label
        selectedTotalLabel.setFont(new Font("Arial", Font.PLAIN, 14));
        selectedTotalLabel.setHorizontalAlignment(SwingConstants.RIGHT);
        gbc.gridwidth = 1; // 只占一列
        gbc.gridx = 1; // 放在第二列
        gbc.gridy = 1; // 第二行
        gbc.anchor = GridBagConstraints.LINE_END; // 设置右对齐
        footer.add(selectedTotalLabel, gbc);

        // Current Account label
        currentAccountLabel.setFont(new Font("Arial", Font.PLAIN, 14));
        currentAccountLabel.setHorizontalAlignment(SwingConstants.RIGHT);
        gbc.gridx = 1; // 第二列
        gbc.gridy = 2; // 第三行
        footer.add(currentAccountLabel, gbc);

        buyButton.addActionListener(e -> shopController.buyProducts(selectedTotalLabel, currentAccountLabel,toggleButtons));


        return footer;
    }

    public static void main(String[] args) {
        Kids kid = ReadAll.readall(String.valueOf(222));
        shop_kid shopKid = new shop_kid(kid);
        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().add(shopKid);
        frame.setSize(800, 600);
        frame.setVisible(true);
    }
}
