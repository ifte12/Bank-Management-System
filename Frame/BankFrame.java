package Frame;

import Entity.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;

public class BankFrame extends JFrame implements ActionListener
{
    private JPanel panel;

    private JLabel titleLabel;
    private JLabel nameLabel;
    private JLabel accLabel;
    private JLabel balanceLabel;
    private JLabel imageLabel;

    private JTextField nameTF;
    private JTextField accTF;
    private JTextField balanceTF;

    private JTextArea outputArea;

    private JButton saveBtn;
    private JButton exitBtn;
    private JButton showBtn;

    private Font font1, font2;

    private ImageIcon img;

    public BankFrame()
    {
        super("Bank Management System");

        this.setSize(800,600);
        this.setLocation(300,100);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        panel = new JPanel();
        panel.setLayout(null);
        panel.setBackground(new Color(180,220,250));

        font1 = new Font("Arial", Font.BOLD, 28);
        font2 = new Font("Arial", Font.PLAIN, 18);

        titleLabel = new JLabel("Bank Management System");
        titleLabel.setBounds(220,20,400,40);
        titleLabel.setFont(font1);
        panel.add(titleLabel);

        nameLabel = new JLabel("Name");
        nameLabel.setBounds(50,100,150,30);
        nameLabel.setFont(font2);
        panel.add(nameLabel);

        nameTF = new JTextField();
        nameTF.setBounds(220,100,200,30);
        nameTF.setFont(font2);
        panel.add(nameTF);

        accLabel = new JLabel("Account Number");
        accLabel.setBounds(50,150,150,30);
        accLabel.setFont(font2);
        panel.add(accLabel);

        accTF = new JTextField();
        accTF.setBounds(220,150,200,30);
        accTF.setFont(font2);
        panel.add(accTF);

        balanceLabel = new JLabel("Balance");
        balanceLabel.setBounds(50,200,150,30);
        balanceLabel.setFont(font2);
        panel.add(balanceLabel);

        balanceTF = new JTextField();
        balanceTF.setBounds(220,200,200,30);
        balanceTF.setFont(font2);
        panel.add(balanceTF);

        saveBtn = new JButton("Save");
        saveBtn.setBounds(100,280,120,40);
        saveBtn.setFont(font2);
        saveBtn.addActionListener(this);
        panel.add(saveBtn);

        showBtn = new JButton("Show Data");
        showBtn.setBounds(250,280,150,40);
        showBtn.setFont(font2);
        showBtn.addActionListener(this);
        panel.add(showBtn);

        exitBtn = new JButton("Exit");
        exitBtn.setBounds(430,280,120,40);
        exitBtn.setFont(font2);
        exitBtn.addActionListener(this);
        panel.add(exitBtn);

        outputArea = new JTextArea();
        outputArea.setFont(font2);

        JScrollPane scroll = new JScrollPane(outputArea);
        scroll.setBounds(50,350,680,180);
        panel.add(scroll);

        img = new ImageIcon("Picture/bank.png");

        Image img2 = img.getImage();
        Image newImg = img2.getScaledInstance(200,150,Image.SCALE_SMOOTH);
        img = new ImageIcon(newImg);

        imageLabel = new JLabel(img);
        imageLabel.setBounds(500,80,200,150);

        panel.add(imageLabel);

        this.add(panel);
    }

    public void actionPerformed(ActionEvent ae)
    {
        if(ae.getSource() == saveBtn)
        {
            String name = nameTF.getText();
            String account = accTF.getText();
            String balance = balanceTF.getText();

            if(name.isEmpty() || account.isEmpty() || balance.isEmpty())
            {
                JOptionPane.showMessageDialog(this,"Please Fill All Fields!");
            }

            else
            {
                Account a1 = new Account(name, account, balance);
                a1.saveAccount();

                JOptionPane.showMessageDialog(this,"Account Saved!");

                nameTF.setText("");
                accTF.setText("");
                balanceTF.setText("");
            }
        }

        else if(ae.getSource() == showBtn)
        {
            outputArea.setText("");

            try
            {
                File file = new File("./Data/accountdata.txt");

                if(file.exists())
                {
                    FileReader fr = new FileReader(file);
                    BufferedReader br = new BufferedReader(fr);

                    String line;

                    while((line = br.readLine()) != null)
                    {
                        outputArea.append(line + "\n");
                    }

                    br.close();
                }
            }

            catch(IOException ioe)
            {
                ioe.printStackTrace();
            }
        }

        else if(ae.getSource() == exitBtn)
        {
            System.exit(0);
        }
    }
}
