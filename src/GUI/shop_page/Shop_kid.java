package GUI.shop_page;

import Controller.shop.shopKidController;
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

/**
 * The Shop_kid class represents the UI panel for the shop, allowing kids to browse and buy products.
 */
public class Shop_kid extends JPanel {
    private JButton buyButton;
    private List<JRadioButton> toggleButtons;
    private ProductList productList;
    private shopKidController shopController;
    private JLabel selectedTotalLabel;
    private JLabel currentAccountLabel;
    private JComboBox<String> accountDropdown;

    // Define the custom colors
    private final Color mainBgColor = new Color(191, 221, 239); // #bfddef
    private final Color panelBgColor = new Color(239, 239, 239); // #EFEFEF
    private final Color fontColor = new Color(49, 122, 232); // #317AE8
    private final Color submitButtonColor = new Color(103, 201, 86); // #67C956
    private final Color borderColor = new Color(105, 105, 105); // #696969

    /**
     * Constructs a Shop_kid panel with the specified controller.
     *
     * @param shopController the controller for this panel
     */
    public Shop_kid(shopKidController shopController) {
        this.shopController = shopController;
        this.productList = shopController.getKid().getProductList();
        this.selectedTotalLabel = new JLabel("Selected Total: $      0.00");
        this.selectedTotalLabel.setForeground(Color.BLACK);
        this.currentAccountLabel = new JLabel(String.format("Current Account: $%9.2f", shopController.getKid().getAccountManager().getCurrentAccountBalance("CA1")));
        this.toggleButtons = new ArrayList<>();

        setLayout(new BorderLayout(10, 10));
        setBackground(mainBgColor);
        initUI();
    }

    /**
     * Initializes the UI components of the panel.
     */
    private void initUI() {
        add(createHeaderPanel(), BorderLayout.NORTH);
        add(createProductsPanel(), BorderLayout.CENTER);
        add(createFooter(), BorderLayout.SOUTH);
    }

    /**
     * Creates the header panel.
     *
     * @return the header panel
     */
    private JPanel createHeaderPanel() {
        JPanel headerPanel = new JPanel(new GridBagLayout());
        headerPanel.setBackground(mainBgColor);
        Border headerBorder = BorderFactory.createMatteBorder(0, 0, 3, 0, borderColor); // Added bottom border
        headerPanel.setBorder(headerBorder);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.anchor = GridBagConstraints.WEST; // Align components to the left
        gbc.weightx = 1.0; // Distribute space evenly

        JLabel titleLabel = new JLabel("FAMILY MALL");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 40));
        titleLabel.setForeground(fontColor);
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
        gbc.gridwidth = GridBagConstraints.REMAINDER; // End row after this component
        headerPanel.add(titleLabel, gbc);

        gbc.gridwidth = 1; // Allow next component in the same row
        gbc.weightx = 0; // Do not stretch this component horizontally

        JLabel label = new JLabel("Choose one to pay:");
        label.setFont(new Font("Arial", Font.PLAIN, 18)); // Adjust font to match your design
        headerPanel.add(label, gbc);

        gbc.insets = new Insets(0, 5, 0, 5); // Reduce space by setting small insets, adjust as needed

        accountDropdown = new JComboBox<>();
        List<CurrentAccount> accounts = shopController.getKid().getAccountManager().getCurrentAccounts();
        for (CurrentAccount account : accounts) {
            accountDropdown.addItem(account.getName());
        }
        if (!accounts.isEmpty()) {
            accountDropdown.setSelectedItem(accounts.get(0).getName()); // Set the default selection to the first account name
            shopController.setSelectedAccountName(accounts.get(0).getName(), currentAccountLabel); // Update the controller's selected account
        }
        accountDropdown.addItemListener(e -> {
            if (e.getStateChange() == ItemEvent.SELECTED) {
                shopController.setSelectedAccountName((String) e.getItem(), currentAccountLabel);
            }
        });

        gbc.fill = GridBagConstraints.NONE; // Don't expand the dropdown
        gbc.gridwidth = GridBagConstraints.REMAINDER; // End row after this component
        headerPanel.add(accountDropdown, gbc);

        return headerPanel;
    }

    /**
     * Creates the products panel.
     *
     * @return the products panel
     */
    private JScrollPane createProductsPanel() {
        JPanel productsPanel = new JPanel(new GridLayout(0, 4, 10, 10)); // 动态行数，固定4列
        productsPanel.setBackground(mainBgColor);

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
                shopController.updateSelectedProductList(product, e.getStateChange() == ItemEvent.SELECTED, selectedTotalLabel);
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

    /**
     * Creates the footer panel.
     *
     * @return the footer panel
     */
    private JPanel createFooter() {
        JPanel footer = new JPanel(new GridBagLayout());
        footer.setBackground(mainBgColor);

        buyButton = new JButton("BUY!");
        buyButton.setFont(new Font("Arial", Font.BOLD, 18));
        buyButton.setBackground(new Color(0, 128, 0)); // 深绿色
        buyButton.setForeground(Color.WHITE);
        JPanel buyPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        buyPanel.setBackground(mainBgColor);
        buyPanel.add(buyButton);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridwidth = 2; // 设置跨两列
        gbc.gridy = 0;
        gbc.weightx = 1.0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        footer.add(buyPanel, gbc);

        selectedTotalLabel.setFont(new Font("Arial", Font.PLAIN, 14));
        selectedTotalLabel.setHorizontalAlignment(SwingConstants.RIGHT);
        gbc.gridwidth = 1; // 只占一列
        gbc.gridx = 1; // 放在第二列
        gbc.gridy = 1; // 第二行
        gbc.anchor = GridBagConstraints.LINE_END; // 设置右对齐
        footer.add(selectedTotalLabel, gbc);

        currentAccountLabel.setFont(new Font("Arial", Font.PLAIN, 14));
        currentAccountLabel.setHorizontalAlignment(SwingConstants.RIGHT);
        gbc.gridx = 1; // 第二列
        gbc.gridy = 2; // 第三行
        footer.add(currentAccountLabel, gbc);

        buyButton.addActionListener(e -> shopController.buyProducts(selectedTotalLabel, currentAccountLabel, toggleButtons));

        return footer;
    }

    public static void main(String[] args) {
        Kids kid = ReadAll.readall(String.valueOf(222));
        shopKidController ShopController = new shopKidController(kid);
        Shop_kid shopKid = new Shop_kid(ShopController);
        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().add(shopKid);
        frame.setSize(800, 600);
        frame.setVisible(true);
    }
}