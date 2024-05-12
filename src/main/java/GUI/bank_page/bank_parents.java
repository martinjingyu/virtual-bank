package GUI.bank_page;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class bank_parents extends JPanel implements ActionListener{
    private JPanel bank;
    private JButton changeRate_c;
    private JButton changeRate_s;
    private JButton button_history;
    private JButton button_transfer;
    private JButton button_yes;
    private JButton button_no;

    public bank_parents() {
        setLayout(null); // 使用绝对布局

        changeRate_c = new JButton("change");
        changeRate_c.setBounds(350,200,100,30);
        add(changeRate_c);

        changeRate_s = new JButton("change");
        changeRate_s.setBounds(350,320,100,30);
        add(changeRate_s);

        button_transfer = new JButton("Transfer to Child");
        button_transfer.setBounds(620,290,110,60);
        add(button_transfer);

        button_history = new JButton("Review");
        button_history.setBounds(590, 420, 140, 30);
        button_history.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                history_page review = new history_page();
                JFrame Review_win = new JFrame();
                Review_win.setTitle("History");
                Review_win.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                Review_win.setLocationRelativeTo(null);
                Review_win.add(review);
                Review_win.setSize(800, 400);
                Review_win.setVisible(true);
            }
        });
        add(button_history);
    }

    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(Color.WHITE);
        g.fillRect(70, 40, 350, 80); // 绘制顶部白色矩形框
        g.setColor(Color.BLACK);
        g.drawString("Income", 150, 60); // 在框内写字
        g.drawString("Expenses", 280, 60);
        g.drawString("+50.00", 150, 100); // 在下方显示 +50
        g.drawString("-20.00", 280, 100);

        g.setColor(Color.WHITE);
        g.fillRect(500, 40, 270, 80);
        g.setColor(Color.black);
        g.drawString("Saving Goals", 610, 60);
        g.drawString("300.00", 610, 100);

        g.setColor(Color.WHITE);
        g.fillRect(70, 150, 430, 90);
        g.setColor(Color.black);
        g.drawString("Current", 90, 220);
        g.drawString("Interest Rate", 150, 170);
        g.drawString("Total", 280, 170);
        g.drawString("0.0%", 150, 220);
        g.drawString("60.00", 280, 220);

        g.setColor(Color.WHITE);
        g.fillRect(70, 270, 430, 90);
        g.setColor(Color.black);
        g.drawString("Saving", 90, 340);
        g.drawString("Interest Rate", 150, 290);
        g.drawString("Total", 280, 290);
        g.drawString("0.0%", 150, 340);
        g.drawString("60.00", 280, 340);

        g.setColor(Color.WHITE);
        g.fillRect(580,150,190,210);
        g.setColor(Color.black);
        g.drawString("Transfer Money",640,170);
        JTextField textField = new JTextField();
        textField.setBounds(620,220,110,40);
        add(textField);

        g.setColor(Color.WHITE);
        g.fillRect(70, 390, 700, 80);
        g.setColor(Color.black);
        g.drawString("Transaction History", 90, 440);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }
}
