package Entity;

import java.io.*;
import java.time.format.DateTimeFormatter;

import javax.swing.*;

public class Account
{
    private String name;
    private String accountNumber;
    private String balance;

    private File file;
    private FileWriter writer;

    public Account() {}

    public Account(String name, String accountNumber, String balance)
    {
        this.name = name;
        this.accountNumber = accountNumber;
        this.balance = balance;
    }

    public void saveAccount()
    {
        try
        {
            file = new File("./Data/accountdata.txt");

            if(!file.exists())
            {
                file.getParentFile().mkdirs();
                file.createNewFile();
            }

            writer = new FileWriter(file, true);
            

            writer.write("====================================\n");
            writer.write("Account Holder Name : " + name + "\n");
            writer.write("Account Number      : " + accountNumber + "\n");
            writer.write("Balance             : " + balance + "\n");
            writer.write("====================================\n\n");

            writer.flush();
            writer.close();
        }

        catch(IOException ioe)
        {
            ioe.printStackTrace();
            JOptionPane.showMessageDialog(null,"File Writing Error!");
        }
    }
}