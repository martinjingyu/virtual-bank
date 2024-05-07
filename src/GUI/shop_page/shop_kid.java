package GUI.shop_page;

import Entity.Bank;
import Entity.Kids;
import Entity.Product;
import Entity.ProductList;
import utill.read.ReadAll;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ItemEvent;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

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

    public shop_kid(Kids kid) {
        this.kid = kid;
        this.productList = kid.getProductList();
        bank = kid.getBank();
        currentAccount = this.bank.getCurrentTotal();
        selectedProductList = new ArrayList<>();
        selectedTotalLabel = new JLabel("Selected Total: $0.00");
        selectedTotalLabel.setForeground(Color.BLACK);

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

    private JPanel createProductsPanel() {
        JPanel productsPanel = new JPanel(new GridLayout(2, 2, 10, 10));
        productsPanel.setBackground(new Color(173, 216, 230));
        toggleButtons = new ArrayList<>();

        for (Product product : this.productList.getAllProducts()) {
            String buttonHtml = String.format("<html>%s<br>$%.2f</html>", product.getName(), product.getPrice());
            JToggleButton button = new JToggleButton(buttonHtml);
            button.setFocusPainted(false);
            button.setFont(new Font("Arial", Font.PLAIN, 16));

            button.addItemListener(e -> {
                if (e.getStateChange() == ItemEvent.SELECTED) {
                    System.out.println("---------------");
                    selectedProductList.add(product);  // 将产品添加到选中列表
                    for (Product p : selectedProductList) {
                        System.out.println(p);  // 打印每个产品
                    }
                    System.out.println(calculateSelectedTotal());
                } else if (e.getStateChange() == ItemEvent.DESELECTED) {
                    System.out.println(product);
                    selectedProductList.remove(product);  // 从选中列表中移除产品
                    System.out.println("---------------");
                    for (Product p : selectedProductList) {
                        System.out.println(p);  // 打印每个产品
                    }
                    System.out.println(calculateSelectedTotal());
                }
                updateSelectedTotalDisplay();
            });
            toggleButtons.add(button);
            productsPanel.add(button);
        }
        return productsPanel;
    }

    private JPanel createFooter() {
        JPanel footer = new JPanel(new GridBagLayout());
        footer.setBackground(new Color(173, 216, 230));

        // Selected Products Total Price label
        selectedTotalLabel = new JLabel(String.format("Selected Total: $%7s", String.format("%7.2f", calculateSelectedTotal())), SwingConstants.RIGHT);
        selectedTotalLabel.setFont(new Font("Arial", Font.PLAIN, 14));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 1.0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.anchor = GridBagConstraints.LINE_END; // 设置右对齐
        footer.add(selectedTotalLabel, gbc);

        // Current Account label
        currentAccountLabel = new JLabel(String.format("Current Account: $%7s", String.format("%7.2f", currentAccount)), SwingConstants.RIGHT);
        currentAccountLabel.setFont(new Font("Arial", Font.PLAIN, 14));
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 1; // 第二行
        gbc.weightx = 1.0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.anchor = GridBagConstraints.LINE_END; // 设置右对齐
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

//    private void updateCurrentAccountDisplay() {
//        currentAccountLabel.setText("Current Account: $" + String.format("%.2f", currentAccount));
//    }

    private void updateSelectedTotalDisplay() {
        double selectedTotal = calculateSelectedTotal();
        String labelText = "Selected Total: $" + String.format("%.2f", selectedTotal);
        selectedTotalLabel.setText(labelText);

        // 检查选定商品的总价是否大于当前账户余额，并根据结果更新标签的颜色
        if (selectedTotal > currentAccount) {
            selectedTotalLabel.setForeground(Color.RED); // 将字体颜色设置为红色
        } else {
            selectedTotalLabel.setForeground(Color.BLACK); // 保持原来的颜色
        }
    }

    private void buyProducts() {
        double totalCost = calculateSelectedTotal();

        // Check if the current account has enough money
        if (currentAccount >= totalCost) {
            currentAccount -= totalCost; // Deduct the total cost from the current account
            JOptionPane.showMessageDialog(this, "Purchase Successful!");
            currentAccountLabel.setText(String.format("Current Account: $%.2f", currentAccount)); // Update the current account display
            toggleButtons.forEach(button -> button.setSelected(false)); // Reset all toggle buttons
            selectedProductList.clear(); // Clear the list of selected products
            updateSelectedTotalDisplay(); // Update the display of the selected total price
        } else {
            JOptionPane.showMessageDialog(this, "Insufficient Balance!"); // Show an error message if not enough balance
        }
    }


    public static void main(String[] args) {
        JFrame frame = new JFrame("Shop Kid");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Kids kid = ReadAll.readall(String.valueOf(222));
        frame.getContentPane().add(new shop_kid(kid));
        frame.setSize(900, 450);
        frame.setLocationRelativeTo(null); // Center the window
        frame.setVisible(true);
    }

}