import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.TextArea;
import java.io.IOException;
import java.io.InputStream;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.Timer;
import java.awt.event.*;
import java.nio.channels.ScatteringByteChannel;


public class CashRegister implements ActionListener {


    JFrame frame;
    JTextArea receipt;
    JButton kaffeButton;
    JButton nalleButton;
    private Produkt valdProdukt;
    private double total = 0;
    private Receipt kvitto = new Receipt();
    JButton muggButton;
    JButton chipsButton;
    JButton vaniljYoghurtButton;
    JButton daimButton;
    JTextArea inputProductName;
    JTextArea inputCount;
    JButton addToReceiptButton;
    JButton payButton;


    public CashRegister() {
        frame = new JFrame("IOT24 POS");


        createReceiptArea();
        createQuickButtonsArea();
        createAddArea();

        frame.getContentPane().setBackground(Color.BLACK);
        frame.setSize(1000, 800);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        frame.setLayout(null);

        frame.setVisible(true);


    }

    private void createAddArea() {
        inputProductName = new JTextArea();
        inputProductName.setBounds(20, 650, 300, 30);
        inputProductName.setFont(new Font("Arial Black", Font.BOLD, 18));
        frame.add(inputProductName);

        JLabel label = new JLabel("Antal");
        label.setBounds(340, 625, 300, 30);
        label.setForeground(Color.WHITE);
        frame.add(label);

        inputCount = new JTextArea();
        inputCount.setBounds(330, 650, 50, 30);
        inputCount.setFont(new Font("Arial Black", Font.BOLD, 18));
        frame.add(inputCount);


        addToReceiptButton = new JButton("Add");
        addToReceiptButton.addActionListener(this);
        addToReceiptButton.setBounds(400, 640, 70, 50);
        addToReceiptButton.setFont(new Font("Arial Black", Font.PLAIN, 14));
        frame.add(addToReceiptButton);


        payButton = new JButton("Pay");
        payButton.addActionListener(this);
        payButton.setBounds(480, 640, 70, 50);
        payButton.setFont(new Font("Arial Black", Font.PLAIN, 14));
        frame.add(payButton);


    }

    private void createQuickButtonsArea() {
        JPanel panel = new JPanel(new FlowLayout(FlowLayout.LEADING, 5, 5));
        panel.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        panel.setBackground(Color.green);
        panel.setPreferredSize(new Dimension(600, 600));

        kaffeButton = new JButton("Kaffe");
        kaffeButton.addActionListener(this);
        panel.add(kaffeButton);

        nalleButton = new JButton("Nalle");
        nalleButton.addActionListener(this);
        panel.add(nalleButton);

        muggButton = new JButton("Mugg");
        muggButton.addActionListener(this);
        panel.add(muggButton);

        chipsButton = new JButton("Chips");
        chipsButton.addActionListener(this);
        panel.add(chipsButton);

        vaniljYoghurtButton = new JButton("Yoghurt");
        vaniljYoghurtButton.addActionListener(this);

        panel.add(vaniljYoghurtButton);


        daimButton = new JButton("Daim");
        daimButton.addActionListener(this);
        panel.add(daimButton);


        panel.setBounds(0, 0, 600, 600);

        frame.add(panel);
    }

    private void createReceiptArea() {
        receipt = new JTextArea();
        receipt.setSize(400, 400);
        receipt.setLineWrap(true);
        receipt.setEditable(false);
        receipt.setVisible(true);
        receipt.setFont(new Font(Font.MONOSPACED, Font.PLAIN, 12));

        JScrollPane scroll = new JScrollPane(receipt);
        scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        scroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        scroll.setBounds(600, 0, 400, 1000);

        frame.add(scroll);
    }

    public void run() {
        receipt.setText("");

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == kaffeButton) {
            valdProdukt = new Produkt("Kaffe", 25.0, 12);
            inputProductName.setText("Kaffe");

        } else if (e.getSource() == nalleButton) {
            valdProdukt = new Produkt("Nalle", 110.0, 25);
            inputProductName.setText("Nalle");


        } else if (e.getSource() == muggButton) {
            valdProdukt = new Produkt("Mugg", 45.0,25);
            inputProductName.setText("Mugg");
        } else if (e.getSource() == chipsButton) {
            valdProdukt = new Produkt("Chips", 75.0, 12);
            inputProductName.setText("Chips");
        } else if (e.getSource() == vaniljYoghurtButton) {
            valdProdukt = new Produkt("Yoghurt", 80.0,12);
            inputProductName.setText("Yoghurt");

        } else if (e.getSource() == daimButton) {
            valdProdukt = new Produkt("Daim", 52, 12);
            inputProductName.setText("Daim");
        } else if (e.getSource() == addToReceiptButton) {
            if (valdProdukt == null) {
                javax.swing.JOptionPane.showMessageDialog(
                        frame, "Välj en produkt Först");
                return;

            }
            try {
                int antal = Integer.parseInt(
                        inputCount.getText().trim());

                if (antal <= 0) {
                    javax.swing.JOptionPane.showMessageDialog(
                            frame, "Ange ett antal större än 0");
                    return;
                }
                kvitto.addRow(valdProdukt, antal);
                double summa = antal * valdProdukt.getPris();
                total = kvitto.getTotal();

                receipt.setText(kvitto.getKvittoText());



                inputCount.setText("");
                inputProductName.setText("");
                valdProdukt = null;
            } catch (NumberFormatException ex) {
                javax.swing.JOptionPane.showMessageDialog(
                        frame, " skriv bara siffror i Antal");

            }

        } else if (e.getSource() == payButton) {
            if (total <= 0) {
                javax.swing.JOptionPane.showMessageDialog(
                        frame, "kvittot är tomt");
                return;
            }

            receipt.append("Tack för dit köp!\n");

            javax.swing.JOptionPane.showMessageDialog(
                    frame, receipt.getText());
            receipt.setText("");
            total = 0;
            kvitto = new Receipt();

        }



        }


    }
