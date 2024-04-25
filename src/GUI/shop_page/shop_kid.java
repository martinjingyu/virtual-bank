package GUI.shop_page;

import javax.swing.*;
import java.awt.*;

public class shop_kid extends JPanel{
    private JButton BUYButton;
    private JRadioButton radioButton1;
    private JRadioButton radioButton2;
    private JRadioButton radioButton3;
    private JRadioButton radioButton4;
    private JRadioButton radioButton5;
    private JRadioButton radioButton6;
    private JPanel shop_kid;
    private JPanel MALL;
    private JScrollPane Product;

    public shop_kid(JPanel shop_kid) {
        Dimension preferredSize = new Dimension(650, 540);
        shop_kid.setPreferredSize(preferredSize);
        add(shop_kid);
    }
}
