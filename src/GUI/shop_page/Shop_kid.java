package GUI.shop_page;

import Controller.shop.ShopController;
import Entity.CurrentAccount;
import Entity.Kids;
import Entity.Product;
import Entity.ProductList;
import utill.read.ReadAll;

import javax.swing.*;
import java.awt.*;
import java.util.List;
import java.awt.event.ItemEvent;
import javax.swing.JRadioButton;
import javax.swing.border.Border;
import java.util.ArrayList;

public class Shop_kid extends JPanel {
    private JButton buyButton;
    private List<JRadioButton> toggleButtons;
    private ProductList productList;
    private ShopController shopController;
    private JLabel selectedTotalLabel;
    private JLabel currentAccountLabel;
    private JComboBox<String> accountDropdown;
    // Define the custom colors
    private final Color mainBgColor = new Color(191, 221, 239); // #bfddef
    private final Color panelBgColor = new Color(239, 239, 239); // #EFEFEF
    private final Color fontColor = new Color(49, 122, 232); // #317AE8
    private final Color submitButtonColor = new Color(103, 201, 86); // #67C956
    private final Color borderColor = new Color(105, 105, 105); // #696969

    public Shop_kid(ShopController shopController) {
        this.shopController = shopController;
        this.productList = shopController.getKid().getProductList();
        this.selectedTotalLabel = new JLabel("Selected Total: $      0.00");
        this.selectedTotalLabel.setForeground(Color.BLACK);
        this.currentAccountLabel = new JLabel(String.format("Current Account: $%9.2f", shopController.getKid().getBank().getCurrentTotal()));
        this.toggleButtons = new ArrayList<>();

        setLayout(new BorderLayout(10, 10));
        setBackground(new Color(173, 216, 230));
        initUI();
    }

    private void initUI() {
        add(createHeaderPanel(), BorderLayout.NORTH);
        add(createProductsPanel(), BorderLayout.CENTER);
        add(createFooter(), BorderLayout.SOUTH);
    }

    private JPanel createHeaderPanel() {
        JPanel headerPanel = new JPanel(new GridBagLayout());
        headerPanel.setBackground(mainBgColor);
        Border headerBorder = BorderFactory.createMatteBorder(0, 0, 3, 0, borderColor); // Added bottom border
        headerPanel.setBorder(headerBorder);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.anchor = GridBagConstraints.WEST; // Align components to the left
        gbc.weightx = 1.0; // Distribute space evenly

        // Title label
        JLabel titleLabel = new JLabel("FAMILY MALL");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 40));
        titleLabel.setForeground(fontColor);
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
        gbc.gridwidth = GridBagConstraints.REMAINDER; // End row after this component
        headerPanel.add(titleLabel, gbc);

        // Reset gridwidth for next components
        gbc.gridwidth = 1; // Allow next component in the same row
        gbc.weightx = 0; // Do not stretch this component horizontally

        // Payment label
        JLabel label = new JLabel("Choose one to pay:");
        label.setFont(new Font("Arial", Font.PLAIN, 18)); // Adjust font to match your design
        headerPanel.add(label, gbc);

        // Adjust insets to reduce space between label and dropdown
        gbc.insets = new Insets(0, 5, 0, 5); // Reduce space by setting small insets, adjust as needed

        // Account dropdown
        accountDropdown = new JComboBox<>();
        // Assuming BothAccountList is accessible via Kids class
        for (CurrentAccount account : shopController.getKid().getBothAccountList().getCurrentAccounts()) {
            accountDropdown.addItem(account.getName());
        }
        accountDropdown.setSelectedItem("CA1"); // Set the default selection to "CA1"
        gbc.fill = GridBagConstraints.NONE; // Don't expand the dropdown
        gbc.gridwidth = GridBagConstraints.REMAINDER; // End row after this component
        headerPanel.add(accountDropdown, gbc);

        return headerPanel;
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

        buyButton.addActionListener(e -> shopController.buyProducts(selectedTotalLabel, currentAccountLabel,toggleButtons,accountDropdown));


        return footer;
    }

    public static void main(String[] args) {
        Kids kid = ReadAll.readall(String.valueOf(222));
        ShopController ShopController = new ShopController(kid);
        Shop_kid shopKid = new Shop_kid(ShopController);
        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().add(shopKid);
        frame.setSize(800, 600);
        frame.setVisible(true);
    }
}
