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
    private shopKidController shopController;
    private JLabel selectedTotalLabel;
    private JLabel currentAccountLabel;
    private JPanel productsPanel;
    private JComboBox<String> accountDropdown;

    // Define the custom colors
    private final Color mainBgColor = new Color(191, 221, 239); // #bfddef
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
        this.selectedTotalLabel = new JLabel("Selected Total: $     0.00");
        this.currentAccountLabel = new JLabel();
        this.selectedTotalLabel.setForeground(Color.BLACK);
        this.toggleButtons = new ArrayList<>();

        setLayout(new BorderLayout(10, 10));
        setBackground(mainBgColor);
        initUI();
        this.shopController.initializeGUI(this);
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
        productsPanel = new JPanel(new GridLayout(0, 4, 10, 10)); // 动态行数，固定4列
        productsPanel.setBackground(mainBgColor);
        JScrollPane scrollPane = new JScrollPane(productsPanel);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane.setPreferredSize(new Dimension(800, 480));

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
        buyButton.setBackground(submitButtonColor); // 深绿色
        buyButton.setForeground(Color.WHITE);
        JPanel buyPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        buyPanel.setBackground(mainBgColor);
        buyPanel.add(buyButton);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridwidth = 2;
        gbc.gridy = 0;
        gbc.weightx = 1.0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        footer.add(buyPanel, gbc);

        selectedTotalLabel.setFont(new Font("Arial", Font.PLAIN, 14));
        selectedTotalLabel.setHorizontalAlignment(SwingConstants.RIGHT);
        gbc.gridwidth = 1;
        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.anchor = GridBagConstraints.LINE_END;
        footer.add(selectedTotalLabel, gbc);

        currentAccountLabel.setFont(new Font("Arial", Font.PLAIN, 14));
        currentAccountLabel.setHorizontalAlignment(SwingConstants.RIGHT);
        gbc.gridx = 1;
        gbc.gridy = 2;
        footer.add(currentAccountLabel, gbc);

        buyButton.addActionListener(e -> shopController.buyProducts(selectedTotalLabel, currentAccountLabel, toggleButtons));

        return footer;
    }

    /**
     * Retrieves the label that displays the current balance of the selected account.
     * This is crucial for users to see how much money is available in their account before making a purchase.
     *
     * @return the JLabel containing the current account balance
     */
    public JLabel getCurrentAccountLabel() {
        return currentAccountLabel;
    }

    /**
     * Retrieves the dropdown menu used for selecting an account.
     * This dropdown allows the user to switch between different accounts for payment purposes.
     *
     * @return the JComboBox that allows users to select from available accounts
     */
    public JComboBox<String> getAccountDropdown() {
        return accountDropdown;
    }

    /**
     * Retrieves the label that displays the current balance of the selected account.
     * This is crucial for users to see how much money is available in their account before making a purchase.
     *
     * @return the JLabel containing the current account balance
     */
    public JPanel getProductsPanel() {
        return productsPanel;
    }


    /**
     * Creates and adds product panels to the products panel.
     *
     * @param productsPanel The panel where product components are added.
     * @param productList   The list of products to display.
     */
    public void createProductPanels(JPanel productsPanel, ProductList productList) {
        for (Product product : productList.getAllProducts()) {
            JPanel productPanel = new JPanel();
            productPanel.setLayout(new BoxLayout(productPanel, BoxLayout.Y_AXIS));
            productPanel.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
            productPanel.setBackground(Color.WHITE);

            JLabel nameLabel = new JLabel(product.getName(), SwingConstants.CENTER);
            nameLabel.setFont(new Font("Arial", Font.BOLD, 16));
            nameLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

            JLabel priceLabel = new JLabel(String.format("$%.2f", product.getPrice()), SwingConstants.CENTER);
            priceLabel.setFont(new Font("Arial", Font.PLAIN, 14));
            priceLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

            JRadioButton radioButton = new JRadioButton();
            radioButton.setAlignmentX(Component.CENTER_ALIGNMENT);
            radioButton.setBackground(Color.WHITE);

            radioButton.addItemListener(e -> {
                boolean isSelected = e.getStateChange() == ItemEvent.SELECTED;
                shopController.updateSelectedProductList(product, isSelected, selectedTotalLabel);
            });

            productPanel.add(Box.createVerticalGlue());
            productPanel.add(nameLabel);
            productPanel.add(Box.createVerticalGlue());
            productPanel.add(priceLabel);
            productPanel.add(Box.createVerticalStrut(10));
            productPanel.add(radioButton);
            productPanel.add(Box.createVerticalGlue());

            toggleButtons.add(radioButton);
            productsPanel.add(productPanel);
        }
    }


    public static void main(String[] args) {
        try {
            Kids kid = ReadAll.readall(String.valueOf(222));
            shopKidController ShopController = new shopKidController(kid);
            Shop_kid shopKid = new Shop_kid(ShopController);
            JFrame frame = new JFrame();
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.getContentPane().add(shopKid);
            frame.setSize(800, 600);
            frame.setVisible(true);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}