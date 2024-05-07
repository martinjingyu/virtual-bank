package GUI.shop_page;

import Entity.*;
import utill.read.ReadAll;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ItemEvent;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import exceptions.InsufficientFundsException;

public class shop_kid extends JPanel {
    private JButton buyButton;
    private List<JToggleButton> toggleButtons;
    private JLabel currentAccountLabel;
    private double currentAccount; // 初始化账户余额
    private ProductList productList;
    private Bank bank;
    private List<Product> selectedProductList;
    private Kids kid;
    private JLabel selectedTotalLabel; // 使其成为类的成员变量以便于访问和修改
    private MessageList messagelist;

    public shop_kid(Kids kid) {
        this.kid = kid;
        this.productList = kid.getProductList();
        bank = kid.getBank();
        currentAccount = bank.getCurrentTotal();
        selectedProductList = new ArrayList<>();
        selectedTotalLabel = new JLabel("Selected Total: $0.00");
        selectedTotalLabel.setForeground(Color.BLACK);
        this.messagelist = kid.getMessagelist();

        setLayout(new BorderLayout(10, 10));
        setBackground(new Color(173, 216, 230)); // 浅蓝色背景
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

        toggleButtons = new ArrayList<>();

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
                if (e.getStateChange() == ItemEvent.SELECTED) {
                    selectedProductList.add(product);
                    updateSelectedTotalDisplay();
                } else if (e.getStateChange() == ItemEvent.DESELECTED) {
                    selectedProductList.remove(product);
                    updateSelectedTotalDisplay();
                }
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

        // BUY button
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

        buyButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                buyProducts(); // Call the buyProducts method when button is clicked
            }
        });


        // Selected Total label
        selectedTotalLabel = new JLabel(String.format("Selected Total: $%9.2f", calculateSelectedTotal()), SwingConstants.RIGHT);
        selectedTotalLabel.setFont(new Font("Arial", Font.PLAIN, 14));
        gbc.gridwidth = 1; // 只占一列
        gbc.gridx = 1; // 放在第二列
        gbc.gridy = 1; // 第二行
        gbc.anchor = GridBagConstraints.LINE_END; // 设置右对齐
        footer.add(selectedTotalLabel, gbc);

        // Current Account label
        currentAccountLabel = new JLabel(String.format("Current Account: $%9.2f", currentAccount), SwingConstants.RIGHT);
        currentAccountLabel.setFont(new Font("Arial", Font.PLAIN, 14));

        gbc.gridx = 1; // 第二列
        gbc.gridy = 2; // 第三行
        footer.add(currentAccountLabel, gbc);

        return footer;
    }





    private double calculateSelectedTotal() {
        double total = 0;
        for (Product product : selectedProductList) {
            total += product.getPrice();
        }
        return total;
    }

    private void updateCurrentAccountDisplay() {
        currentAccountLabel.setText("Current Account: $" + String.format("%9.2f", currentAccount));
        System.out.println(currentAccount);
    }

    private void updateSelectedTotalDisplay() {
        double selectedTotal = calculateSelectedTotal();
        String labelText = String.format("Selected Total: $%9.2f", selectedTotal);
        selectedTotalLabel.setFont(new Font("Arial", Font.PLAIN, 14));
        selectedTotalLabel.setText(labelText);

        // 检查选定商品的总价是否大于当前账户余额，并根据结果更新标签的颜色
        if (selectedTotal > currentAccount) {
            selectedTotalLabel.setForeground(Color.RED); // 将字体颜色设置为红色
        } else {
            selectedTotalLabel.setForeground(Color.BLACK); // 保持原来的颜色
        }
    }

        private void buyProducts() {

            if (selectedProductList.isEmpty()) {
                JOptionPane.showMessageDialog(this, "You haven't selected any products.", "No Products Selected!", JOptionPane.WARNING_MESSAGE);
                return; // Exit the method
            }

            double totalCost = calculateSelectedTotal();
            try {
                System.out.println(totalCost);
                double newTotal = bank.changeCurrent(-totalCost);
                if (totalCost > 0.8 * currentAccount) { // Check if totalCost exceeds 80% of currentAccount
                    messagelist.addShopMessage(totalCost);
                }
                currentAccount = newTotal;
                JOptionPane.showMessageDialog(this, "Purchase Successful!");
                currentAccountLabel.setText(String.format("Current Account: $%.2f", currentAccount)); // Update the current account display
                toggleButtons.forEach(button -> button.setSelected(false)); // Reset all toggle buttons
                selectedProductList.clear(); // Clear the list of selected products
                updateSelectedTotalDisplay(); // Update the display of the selected total price
                updateCurrentAccountDisplay();
            } catch (InsufficientFundsException e) {
                JOptionPane.showMessageDialog(this, e.getMessage(), "Insufficient Balance!", JOptionPane.ERROR_MESSAGE);
            }
        }

        public static void main(String[] args) {
            Kids kid = ReadAll.readall(String.valueOf(222));
            shop_kid page1 = new shop_kid(kid);
            shop_parent page2 = new shop_parent();
            new test_gui(page1);
        }


}


