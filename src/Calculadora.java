import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Calculadora extends JFrame {
    private JTextField tela;
    private double numero1 = 0;
    private String operador = "";
    private boolean novoNumero = true;

    public Calculadora() {
        setTitle("Calculadora");
        setSize(320, 450);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        tela = new JTextField("0");
        tela.setFont(new Font("Monospaced", Font.BOLD, 30));
        tela.setHorizontalAlignment(JTextField.RIGHT);
        tela.setEditable(false);
        tela.setBackground(new Color(240, 248, 255));
        add(tela, BorderLayout.NORTH);

        ActionListener ouvinte = new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String botaoClicado = ((JButton)e.getSource()).getText();
                if (botaoClicado.matches("[0-9.]") || botaoClicado.equals("00")) {
                    if (novoNumero) {
                        tela.setText("");
                        novoNumero = false;
                    }
                    tela.setText(tela.getText() + botaoClicado);
                } else if (botaoClicado.equals("C")) {
                    tela.setText("0");
                    numero1 = 0;
                    operador = "";
                    novoNumero = true;
                } else if (botaoClicado.equals("=")) {
                    double numero2;
                    try {
                        numero2 = Double.parseDouble(tela.getText());
                    } catch (NumberFormatException ex) {
                        tela.setText("Erro");
                        return;
                    }
                    double resultado = 0;
                    if (operador.equals("+")) resultado = numero1 + numero2;
                    else if (operador.equals("-")) resultado = numero1 - numero2;
                    else if (operador.equals("*")) resultado = numero1 * numero2;
                    else if (operador.equals("/")) {
                        if (numero2 == 0) {
                            tela.setText("Erro");
                            return;
                        }
                        resultado = numero1 / numero2;
                    }
                    tela.setText(String.format("%.2f", resultado));
                    novoNumero = true;
                } else if ("+-×÷".contains(botaoClicado)) {
                    try {
                        numero1 = Double.parseDouble(tela.getText());
                    } catch (NumberFormatException ex) {
                        numero1 = 0;
                    }
                    operador = botaoClicado.equals("×") ? "*" : botaoClicado.equals("÷") ? "/" : botaoClicado;
                    novoNumero = true;
                } else if (botaoClicado.equals("±")) {
                    try {
                        double valor = Double.parseDouble(tela.getText());
                        tela.setText(String.format("%.2f", -valor));
                    } catch (NumberFormatException ex) {
                        tela.setText("0");
                    }
                } else if (botaoClicado.equals("%")) {
                    try {
                        double valor = Double.parseDouble(tela.getText());
                        tela.setText(String.format("%.2f", valor / 100));
                    } catch (NumberFormatException ex) {
                        tela.setText("0");
                    }
                }
            }
        };

        JPanel botoes = new JPanel(new GridLayout(5, 4, 8, 8));
        botoes.setBackground(new Color(25, 25, 112));
        add(botoes, BorderLayout.CENTER);

        String[] nomes = {"C", "±", "%", "÷", "7", "8", "9", "×",
                "4", "5", "6", "-", "1", "2", "3", "+",
                "0", "00", ".", "="};
        for (String nome : nomes) {
            JButton btn = new JButton(nome);
            if (nome.matches("[0-9.]") || nome.equals("00")) {
                btn.setBackground(new Color(100, 149, 237));
            } else if (nome.equals("C")) {
                btn.setBackground(new Color(0, 191, 255));
            } else {
                btn.setBackground(new Color(25, 25, 112));
            }
            btn.setForeground(Color.WHITE);
            btn.setFont(new Font("Arial", Font.BOLD, 18));
            btn.setFocusPainted(false);
            btn.addActionListener(ouvinte);
            botoes.add(btn);
        }

        setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new Calculadora());
    }
}



