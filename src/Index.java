import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

class Calculator extends JFrame implements ActionListener {
    JButton[] btnArr = new JButton[16];
    JTextField dspField;
    SpringLayout layout = new SpringLayout();
    boolean clickEq = false;
    boolean clickOp = false;
    double n1, n2;
    char op;

    Calculator() {
        setSize(300, 410);
        setTitle("Calculator");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);

        setLayout(layout);

        addPanel();

        setVisible(true);
    }

    public void addPanel() {
        displayPanel();//display panel
        buttonPanel();//btn panel
    }

    public void displayPanel() {
        JPanel disPanel = new JPanel();
        dspField = new JTextField(9);
        dspField.setFont(new Font("", Font.PLAIN, 36));
        dspField.setEditable(false);
        dspField.setText("0");
        dspField.setHorizontalAlignment(JTextField.RIGHT);

        disPanel.add(dspField);

        this.getContentPane().add(disPanel);

        layout.putConstraint(SpringLayout.NORTH, disPanel, 11, SpringLayout.NORTH, this);
        layout.putConstraint(SpringLayout.WEST, disPanel, 1, SpringLayout.WEST, this);
    }

    public void buttonPanel() {
        JPanel btnPanel = new JPanel();
        btnPanel.setLayout(new GridLayout(4, 4, 10, 10));

        String[] btnName = {"7", "8", "9", "/", "4", "5", "6", "x", "1", "2", "3", "-", "0", ".", "=", "+"};
        //set btn names
        for (int i = 0; i < 16; i++) {
            btnArr[i] = new JButton(btnName[i]);
            btnArr[i].setFont(new Font("", Font.PLAIN, 36));
            btnArr[i].addActionListener(this);
            btnPanel.add(btnArr[i]);
        }
        //set btn names

        this.getContentPane().add(btnPanel);

        layout.putConstraint(SpringLayout.NORTH, btnPanel, 90, SpringLayout.NORTH, this);
        layout.putConstraint(SpringLayout.WEST, btnPanel, 17, SpringLayout.WEST, this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        String btnClick = e.getActionCommand();
        if (btnClick.matches("[0-9]")) {
            if (clickEq || clickOp || dspField.getText().equals("0")) {
                dspField.setText(btnClick);
            }else {
                dspField.setText(dspField.getText() + btnClick);
            }
        } else if (btnClick.equals("=")) {
            n2 = Double.parseDouble(dspField.getText());
            double result = switch (op) {
                case '+' -> n1 + n2;
                case '-' -> n1 - n2;
                case '/' -> n1 / n2;
                case 'x' -> n1 * n2;
                default -> 0;
            };

            dspField.setText((Double.toString(result)));

            clickOp = false;
            clickEq = true;
        } else if (btnClick.equals(".")) {
            if (!dspField.getText().contains(("."))) {
                dspField.setText((dspField.getText() + btnClick));
            }
        } else {
            if (!clickOp) {
                n1 = Double.parseDouble(dspField.getText());
            }
            op = btnClick.charAt(0);

            clickOp = true;
            clickEq = true;
        }
    }
}

class Index {
    public static void main(String[] args) {
        new Calculator();
    }
}
