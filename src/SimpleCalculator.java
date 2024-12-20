import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SimpleCalculator extends JFrame implements ActionListener {

    // Declare components
    private JTextField numberField1, numberField2;
    private JLabel answerLabel;
    private JButton[] numberButtons;
    private JButton addButton, subtractButton, multiplyButton, divideButton, percentageButton, equalsButton;

    private boolean isFirstField = true; // To track where to put the number
    private String operator = "";

    public SimpleCalculator() {
        // Set up the frame
        setTitle("Simple Calculator");
        setSize(300, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridLayout(6, 4, 10, 10)); // 6 rows, 3 columns grid for a better layout

        // Create number text fields (non-editable)
        numberField1 = new JTextField();
        numberField1.setEditable(false);
        numberField2 = new JTextField();
        numberField2.setEditable(false);

        // Create label to display the answer
        answerLabel = new JLabel("Answers: ");

        // Initialize number buttons
        numberButtons = new JButton[10];
        for (int i = 0; i < 10; i++) {
            numberButtons[i] = new JButton(String.valueOf(i));
            numberButtons[i].addActionListener(this); // Attach action listener to each button
        }

        // Initialize operator buttons
        addButton = new JButton("+");
        subtractButton = new JButton("-");
        multiplyButton = new JButton("*");
        divideButton = new JButton("/");
        percentageButton = new JButton("%"); // Percentage button
        equalsButton = new JButton("="); // Equals button

        // Add action listeners to operator buttons
        addButton.addActionListener(this);
        subtractButton.addActionListener(this);
        multiplyButton.addActionListener(this);
        divideButton.addActionListener(this);
        percentageButton.addActionListener(this);
        equalsButton.addActionListener(this);

        // Add components to the frame in order
        add(new JLabel("Number 1:"));
        add(numberField1);
        add(new JLabel("Number 2:"));
        add(numberField2);

        add(answerLabel);

        // Add number buttons (0-9)
        for (int i = 1; i <= 9; i++) {
            add(numberButtons[i]);
        }

        // Add operation buttons and layout
        add(addButton);
        add(subtractButton);
        add(multiplyButton);
        add(divideButton);
        add(percentageButton);
        add(numberButtons[0]); // Button for '0'
        add(equalsButton); // Button for '='
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // Get the source of the event (the button that was clicked)
        Object source = e.getSource();

        // If a number button is clicked
        for (int i = 0; i < 10; i++) {
            if (source == numberButtons[i]) {
                if (isFirstField) {
                    numberField1.setText(String.valueOf(i)); // Set first number
                    isFirstField = false; // Move to second number field
                } else {
                    numberField2.setText(String.valueOf(i)); // Set second number
                }
            }
        }

        // If an operator button is clicked, store the operator and move to second field
        if (source == addButton) {
            operator = "+";
        } else if (source == subtractButton) {
            operator = "-";
        } else if (source == multiplyButton) {
            operator = "*";
        } else if (source == divideButton) {
            operator = "/";
        } else if (source == percentageButton) {
            operator = "%";
        }

        // If the equals button is clicked, perform the calculation
        if (source == equalsButton) {
            if (!numberField1.getText().isEmpty() && !numberField2.getText().isEmpty()) {
                double num1 = Double.parseDouble(numberField1.getText());
                double num2 = Double.parseDouble(numberField2.getText());
                double result = 0;

                switch (operator) {
                    case "+":
                        result = num1 + num2;
                        break;
                    case "-":
                        result = num1 - num2;
                        break;
                    case "*":
                        result = num1 * num2;
                        break;
                    case "/":
                        if (num2 == 0) {
                            answerLabel.setText("Error: Cannot divide by zero");
                            return;
                        }
                        result = num1 / num2;
                        break;
                    case "%":
                        result = (num1 * num2) / 100; // Percentage operation
                        break;
                }

                // Display the result in the answer label
                answerLabel.setText("Answer: " + result);

                // Clear the text fields for new input
                numberField1.setText("");
                numberField2.setText("");
                isFirstField = true; // Reset to entering the first number
            }
        }
    }

    public static void main(String[] args) {
        // Create and display the calculator
        SimpleCalculator calculator = new SimpleCalculator();
        calculator.setVisible(true);
    }
}
