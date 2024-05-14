package GUI.shop_page;

import Controller.shop.ShopParentController;
import Entity.CurrentAccount;
import Entity.Kids;
import Entity.Product;
import GUI.RefreshListener;
import utill.read.ReadAll;

import java.awt.*;
import java.util.ArrayList;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import java.util.List;
import java.awt.event.ItemEvent;

public class Shop_parent extends JPanel implements RefreshListener {

    private JCheckBox bookCheckBox, toy1CheckBox, toy2CheckBox, toy3CheckBox;
    private JTextField nameTextField, priceTextField;
    private JButton submitButton, confirmButton;
    private JLabel currentAccountLabel;
    private ShopParentController shopController;
    private List<JButton> ButtonList;
    private JPanel todoListPanel;
    private JComboBox<String> accountDropdown;

    // Define the custom colors
    private final Color mainBgColor = new Color(191, 221, 239); // #bfddef
    private final Color panelBgColor = new Color(239, 239, 239); // #EFEFEF
    private final Color fontColor = new Color(49, 122, 232); // #317AE8
    private final Color submitButtonColor = new Color(103, 201, 86); // #67C956
    private final Color borderColor = new Color(105, 105, 105); // #696969

    public Shop_parent(ShopParentController controller) {
        this.shopController = controller;
        this.shopController.setRefreshListener(this);

        setBackground(mainBgColor); // Set overall background
        initUI();
    }

    private void initUI() {
        // Set the layout with padding around the entire layout
        setPreferredSize(new Dimension(900, 540));
        setLayout(new BorderLayout(20, 20)); // Added horizontal and vertical gaps
        setBorder(new EmptyBorder(20, 40, 20, 40)); // Added horizontal and vertical margins

        add(createHeaderPanel(), BorderLayout.NORTH);
        add(createToDoListPanel(), BorderLayout.WEST);
        add(createUploadProductsPanel(), BorderLayout.CENTER);
        add(createAccountInfoPanel(), BorderLayout.SOUTH);
    }

    @Override
    public void refreshUI() {
        remove(todoListPanel);
        todoListPanel = createToDoListPanel();
        add(createToDoListPanel(), BorderLayout.WEST);
        validate();
        repaint();
    }

    // Create the header "Family Mall" with margin
    private JPanel createHeaderPanel() {
        JPanel headerPanel = new JPanel();
        headerPanel.setBackground(mainBgColor);
        Border headerBorder = BorderFactory.createMatteBorder(0, 0, 3, 0, borderColor); // Added bottom border
        headerPanel.setBorder(headerBorder);

        JLabel titleLabel = new JLabel("Transaction History");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 40));
        titleLabel.setForeground(fontColor);

        headerPanel.add(titleLabel);
        return headerPanel;
    }




    private JPanel createToDoListPanel() {
        todoListPanel = new JPanel();
        todoListPanel.setLayout(new BorderLayout());
        todoListPanel.setBorder(new LineBorder(borderColor, 1));
        todoListPanel.setPreferredSize(new Dimension(270, 400));
        todoListPanel.setBackground(panelBgColor);
        todoListPanel.setBorder(new EmptyBorder(30, 0, 0, 0));

        // Top Label
        JLabel todoLabel = new JLabel("TO DO LIST");
        todoLabel.setFont(new Font("Arial", Font.BOLD, 26));
        todoLabel.setForeground(fontColor);
        todoLabel.setHorizontalAlignment(SwingConstants.CENTER);
        todoListPanel.add(todoLabel, BorderLayout.NORTH);

        // Scrollable check box panel
        JPanel checkBoxPanel = new JPanel();
        List<JCheckBox> checkBoxes = new ArrayList<>();
        checkBoxPanel.setLayout(new BoxLayout(checkBoxPanel, BoxLayout.Y_AXIS));
        checkBoxPanel.setBackground(panelBgColor);

        JScrollPane scrollPane = new JScrollPane(checkBoxPanel);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane.setBorder(null);

        for (Product product : shopController.getKid().getSelectedList().getAllProducts()) {
            JPanel singleCheckBoxPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
            singleCheckBoxPanel.setBackground(panelBgColor);

            JCheckBox checkBox = new JCheckBox(product.getName());
            checkBox.setFont(new Font("Arial", Font.BOLD, 18));
            checkBox.addActionListener(e -> shopController.updateBoughtProductList(product, checkBox.isSelected()));
            checkBoxes.add(checkBox);
            singleCheckBoxPanel.add(checkBox);
            checkBoxPanel.add(singleCheckBoxPanel);
            checkBoxPanel.add(Box.createVerticalStrut(10));
        }

        todoListPanel.add(scrollPane, BorderLayout.CENTER);

        // Confirm Button
        JButton confirmButton = new JButton("Confirm");
        confirmButton.setFont(new Font("Arial", Font.BOLD, 25));
        confirmButton.setBackground(new Color(192, 192, 192));
        confirmButton.setForeground(Color.BLACK);
        confirmButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        confirmButton.addActionListener(e -> {
            shopController.updateSelectedProduct(checkBoxes);
        });

        todoListPanel.add(confirmButton, BorderLayout.SOUTH);

        return todoListPanel;
    }


    // Create the Upload Products Panel
    private JPanel createUploadProductsPanel() {
        JPanel uploadPanel = new JPanel(new GridBagLayout());
        uploadPanel.setPreferredSize(new Dimension(540, 0)); // Width: 60% of overall window width
        uploadPanel.setBackground(panelBgColor);
        uploadPanel.setBorder(new LineBorder(borderColor, 1)); // Added border
        uploadPanel.setAlignmentX(Component.CENTER_ALIGNMENT); // Center alignment

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridwidth = GridBagConstraints.REMAINDER;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(5, 50, 10, 50); // Increased left and right padding

        JLabel uploadLabel = new JLabel("UPLOAD PRODUCTS");
        uploadLabel.setFont(new Font("Arial", Font.BOLD, 26));
        uploadLabel.setForeground(fontColor);
        uploadLabel.setHorizontalAlignment(SwingConstants.CENTER); // Center alignment
        gbc.gridy = 0;
        uploadPanel.add(uploadLabel, gbc);

        nameTextField = new JTextField(15);
        nameTextField.setBorder(BorderFactory.createTitledBorder(new LineBorder(borderColor), "Name")); // Added border
        gbc.gridy = 1;
        uploadPanel.add(nameTextField, gbc);

        priceTextField = new JTextField(15);
        priceTextField.setBorder(BorderFactory.createTitledBorder(new LineBorder(borderColor), "Price")); // Added border
        gbc.gridy = 2;
        uploadPanel.add(priceTextField, gbc);

        submitButton = new JButton("SUBMIT");
        submitButton.setFont(new Font("Arial", Font.BOLD, 14));
        submitButton.setBackground(submitButtonColor);
        submitButton.setForeground(Color.WHITE);
        gbc.gridy = 3;

        submitButton.addActionListener(e -> {
            shopController.updateProducts(nameTextField.getText(),priceTextField.getText());
        });

        uploadPanel.add(submitButton, gbc);

        return uploadPanel;
    }

    // Create the Account Information Panel
    private JPanel createAccountInfoPanel() {
        JPanel accountInfoPanel = new JPanel();
        accountInfoPanel.setBackground(panelBgColor);
        accountInfoPanel.setBorder(new LineBorder(borderColor, 1)); // Added border

        JLabel accountLabel = new JLabel("CURRENT ACCOUNT: ");
        accountLabel.setFont(new Font("Arial", Font.BOLD, 26));
        accountLabel.setForeground(fontColor);

        currentAccountLabel = new JLabel(String.format("$%.2f", shopController.getKid().getBank().getCurrentTotal()));
        currentAccountLabel.setFont(new Font("Arial", Font.PLAIN, 16));

        accountInfoPanel.add(accountLabel);
        accountInfoPanel.add(currentAccountLabel);

        return accountInfoPanel;
    }

    public List<JButton> getButtonList()
    {
        return ButtonList;
    }

    public static void main(String[] args) {
        Kids kid = ReadAll.readall(String.valueOf(222));
        ShopParentController ShopController = new ShopParentController(kid);
        JFrame frame = new JFrame("Shop Application");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Shop_parent shopParent = new Shop_parent(ShopController);
        frame.setContentPane(shopParent);
        ShopController.addListener(shopParent);
        frame.pack();
        frame.setVisible(true);
    }
}

