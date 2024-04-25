import javax.swing.*;
import java.awt.*;

public class demo extends JFrame {
    public demo() {
        setTitle("Menu Panel Example");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 600);

        JPanel contentPane = new JPanel();
        contentPane.setLayout(new BorderLayout());

        JPanel menuPanel = new JPanel();
        menuPanel.setPreferredSize(new Dimension(10, getHeight())); // 设置菜单面板的宽度为 20 像素
        menuPanel.setBackground(Color.LIGHT_GRAY);

        JPanel mainPanel = new JPanel();
        mainPanel.setBackground(Color.blue);

        contentPane.add(menuPanel, BorderLayout.WEST);
        contentPane.add(mainPanel, BorderLayout.CENTER);

        setContentPane(contentPane);
        setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(demo::new);
    }
}
