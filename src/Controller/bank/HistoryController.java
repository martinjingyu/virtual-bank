package Controller.bank;

import Entity.HistoryTransactionList;
import Entity.Kids;
import Entity.HistoryTransaction;

import GUI.bank_page.history_page;
import GUI.bank_page.Bank_kid;
import utill.read.ReadAll;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.*;

public class HistoryController {
    private Kids kid;
    private history_page GUI;
    private HistoryTransactionList historyTransactionList;
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

    public void addButtonListener(){
    }

    public static void main(String[] args){
        Kids kid = ReadAll.readall("222");
    }

}
