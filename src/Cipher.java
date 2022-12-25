import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.*;
import java.awt.event.ActionListener;
import java.util.Arrays;
import javax.swing.*;
import static java.lang.String.valueOf;

public class Cipher extends JFrame{
    public JTextField encryptTextField;
    public JTextField decryptTextField;

    public Cipher(){
        setLocation (100,100);
        setSize(500,500);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        JPanel northPanel = new JPanel();
        northPanel.setLayout(new GridLayout(3,3));
        northPanel.add(new JLabel("Secret Letters"));
        add(northPanel,BorderLayout.NORTH);

        JPanel centerPanel = new JPanel();
        centerPanel.setLayout(new GridLayout(3,3,6,6));
        centerPanel.add(new JLabel("Encrypt"));
        JTextField encryptTextField = new JTextField(12);
        centerPanel.add(encryptTextField);
        JTextField result = new JTextField();
        JLabel label = new JLabel("Result");
        centerPanel.add(label);centerPanel.add(result);
        centerPanel.add(new JLabel("Decrypt"));
        JTextField decryptTextField = new JTextField(12);
        centerPanel.add(decryptTextField);
        add(centerPanel, BorderLayout.CENTER);

        JPanel southPanel = new JPanel();
        JButton convertButton = new JButton("Convert");
        southPanel.add(convertButton);
        JButton clearButton = new JButton("Clear");
        southPanel.add(clearButton);
        add(southPanel,BorderLayout.SOUTH);
        //Convert Button
        convertButton.addActionListener(new ConvertButtonListener(){
            public void actionPerformed(ActionEvent e){
                String encrypt;
                String decrypt;
                String fieldOne=encryptTextField.getText();
                String fieldTwo=decryptTextField.getText();

                try{
                    if(fieldTwo.equals("") || fieldTwo==null){
                        encrypt = (fieldOne);
                        char[] inputArray = encrypt.toCharArray();
                        boolean a = true;
                        for (int i = 0; i < inputArray.length; i++) {
                            if (Character.isAlphabetic(inputArray[i]) || inputArray[i] == 32) {
                                if (inputArray[i] > 64 && inputArray[i] <= 77 || inputArray[i] > 96 && inputArray[i] <= 109) {
                                    inputArray[i] = (char) (inputArray[i] + 13);
                                } else if (inputArray[i] > 77 && inputArray[i] < 91 || inputArray[i] > 109 && inputArray[i] < 123) {
                                    inputArray[i] = (char) (inputArray[i] - 13);
                                }
                            } else {
                                a = false;
                                JOptionPane.showMessageDialog(null," Enter alphabets and spaces only! ");
                                break;
                            }
                        }
                        if(a==true)
                        {
                            result.setText(valueOf(inputArray));
                        }
                    }else{
                        decrypt = (fieldTwo);
                        char[] inputArray = decrypt.toCharArray();
                        boolean a = true;
                        for (int i = 0; i < inputArray.length; i++) {
                            if (Character.isAlphabetic(inputArray[i]) || inputArray[i] == 32) {
                                if (inputArray[i] > 77 && inputArray[i] < 91 || inputArray[i] > 109 && inputArray[i] < 123) {
                                    inputArray[i] = (char) (inputArray[i] - 13);
                                } else if(inputArray[i] > 64 && inputArray[i] <= 77 || inputArray[i] > 96 && inputArray[i] <= 109) {
                                    inputArray[i] = (char) (inputArray[i] + 13);
                                }
                            } else {
                                JOptionPane.showMessageDialog(null," Enter alphabets and spaces only! ");
                                break;
                            }
                        }
                        if(a==true)
                        {
                            result.setText(valueOf(inputArray));
                        }
                    }
                }catch(Exception r){
                    JFrame f = new JFrame("Exception Handling");
                    JOptionPane.showMessageDialog(f,"Please enter letters");
                }
            }
        });

        //Clear Button
        clearButton.addActionListener(new ClearButtonListener(){
            public void actionPerformed(ActionEvent e){
                encryptTextField.setText("");
                decryptTextField.setText("");
                result.setText("");
            }
        });
    }

    private class ConvertButtonListener implements ActionListener{
        public void actionPerformed(ActionEvent e) {
            String encrypt;
            String decrypt;
            String fieldOne = encryptTextField.getText();
            String fieldTwo = decryptTextField.getText();

            encrypt = (fieldOne);
            char[] inputArray = encrypt.toCharArray();
            if(fieldTwo.equals("") || fieldTwo==null){
                boolean a = true;
                for (int i = 0; i < inputArray.length; i++) {
                    if (Character.isAlphabetic(inputArray[i]) || inputArray[i] == 32) {
                        if (inputArray[i] > 64 && inputArray[i] <= 77 || inputArray[i] > 96 && inputArray[i] <= 109) {
                            inputArray[i] = (char) (inputArray[i] + 13);
                        } else if (inputArray[i] > 77 && inputArray[i] < 91 || inputArray[i] > 109 && inputArray[i] < 123) {
                            inputArray[i] = (char) (inputArray[i] - 13);
                        }
                    } else {
                        JOptionPane.showMessageDialog(null," Enter alphabets and spaces only! ");
                        break;
                    }
                }
                if(a==true)
                {
                    JOptionPane.showMessageDialog(null,"Encrypted " + Arrays.toString(inputArray));
                }
            }else{
                decrypt= (fieldTwo);
                char[] inputArray1 = decrypt.toCharArray();
                boolean a = true;
                for (int i = 0; i < inputArray.length; i++) {
                    if (Character.isAlphabetic(inputArray[i]) || inputArray[i] == 32) {
                        if (inputArray[i] > 77 && inputArray[i] < 91 || inputArray[i] > 109 && inputArray[i] < 123) {
                            inputArray[i] = (char) (inputArray[i] - 13);
                        } else if(inputArray[i] > 64 && inputArray[i] <= 77 || inputArray[i] > 96 && inputArray[i] <= 109) {
                            inputArray[i] = (char) (inputArray[i] + 13);
                        }
                    } else {
                        JOptionPane.showMessageDialog(null," Enter alphabets and spaces only! ");
                        break;
                    }
                }
                if(a==true)
                {
                    JOptionPane.showMessageDialog(null,"Decrypted " + Arrays.toString(inputArray));
                }
            }
        }
    }

    //Clear button interface
    private class ClearButtonListener implements ActionListener{
        public void actionPerformed(ActionEvent e){
            encryptTextField.setText("");
            decryptTextField.setText("");
        }
    }
    public static void main(String[]args){
        Cipher A = new Cipher();
        A.setVisible(true);
    }
}