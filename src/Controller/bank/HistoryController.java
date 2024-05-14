package Controller.bank;

import Entity.HistoryTransactionList;
import Entity.Kids;
import Entity.HistoryTransaction;

import GUI.bank_page.history_page;
import GUI.bank_page.Bank_kid;
import utill.read.ReadAll;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;

public class HistoryController {
    private Kids kid;
    private history_page GUI;
    private HistoryTransactionList historyTransactionList;
    private String SelectedDate;
    public HistoryController(Kids kid){
        this.kid = kid;
        historyTransactionList = kid.getTransactionList();
    }
    public void setGUI(history_page GUI){
        this.GUI = GUI;
    }
    public Kids getKid(){
        return kid;
    }
    public String getSelectedDate(){return SelectedDate;}

    public void addButtonListener(){
        for(JLabel label: GUI.getDateList()){
            label.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    super.mouseClicked(e);
                    for(JLabel c: GUI.getDateList()){
                        c.setBackground(Color.white);
                    }
                    label.setBackground(Color.cyan);
                    SelectedDate = label.getText();
                    GUI.refreshUI();


                }
            });
        }

    }

    public static void main(String[] args){
        Kids kid = ReadAll.readall("222");
    }

}
