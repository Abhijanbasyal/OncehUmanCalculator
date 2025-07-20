import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class OnceHumanCalculator {

    static String[] weapons = {
            "Bullseye", "Frost", "Blaze", "Power Surge", "Shrapnel", "Fortress", "Unstoppable Bomber"
    };

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> showWeaponSelection());
    }

    private static void showWeaponSelection() {
        JFrame frame = new JFrame("Once Human - Select Weapon");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 350);
        frame.setLocationRelativeTo(null);

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(weapons.length, 1, 10, 10));
        for (String weapon : weapons) {
            JButton btn = new JButton(weapon);
            btn.addActionListener(e -> {
                frame.dispose();
                showCalculatorWindow(weapon);
            });
            panel.add(btn);
        }

        frame.add(panel);
        frame.setVisible(true);
    }

    private static void showCalculatorWindow(String weapon) {
        JFrame frame = new JFrame("Calculate Damage - " + weapon);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(450, 500);
        frame.setLocationRelativeTo(null);

        JPanel panel = new JPanel(new GridLayout(9, 2, 10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));

        JLabel lblWeapon = new JLabel("Weapon: " + weapon);
        lblWeapon.setFont(new Font("Arial", Font.BOLD, 16));

        JLabel psiLabel = new JLabel("Psi Intensity:");
        JTextField psiField = new JTextField();

        JLabel elementalLabel = new JLabel("Elemental DMG (%)");
        JTextField elementalField = new JTextField();

        JLabel statusLabel = new JLabel("Status DMG (%)");
        JTextField statusField = new JTextField();

        JLabel weaponBonusLabel = new JLabel("Weapon Bonus (%)");
        JTextField weaponBonusField = new JTextField();

        JLabel weakspotLabel = new JLabel("Weakspot Bonus (%)");
        JTextField weakspotField = new JTextField();

        JLabel critRateLabel = new JLabel("Crit Rate (%)");
        JTextField critRateField = new JTextField();

        JLabel critDamageLabel = new JLabel("Crit Damage (%)");
        JTextField critDamageField = new JTextField();

        JButton calculateBtn = new JButton("Calculate Damage");
        JLabel resultLabel = new JLabel(" ");

        JButton backBtn = new JButton("Go Back");

        calculateBtn.addActionListener(e -> {
            try {
                // Parse inputs, set defaults if empty
                double psi = parseOrDefault(psiField.getText(), 0);
                double elemental = 1 + parseOrDefault(elementalField.getText(), 0) / 100.0;
                double status = 1 + parseOrDefault(statusField.getText(), 0) / 100.0;
                double weaponBonus = 1 + parseOrDefault(weaponBonusField.getText(), 0) / 100.0;
                double weakspot = 1 + parseOrDefault(weakspotField.getText(), 0) / 100.0;
                double critRate = parseOrDefault(critRateField.getText(), 0) / 100.0;
                double critDamage = parseOrDefault(critDamageField.getText(), 0) / 100.0;

                if (psi == 0) {
                    resultLabel.setText("Psi Intensity cannot be zero.");
                    return;
                }

                double base = psi * 0.12;
                double damage = base * elemental * status * weaponBonus * weakspot * (1 + critRate * critDamage) * 5;

                resultLabel.setText(String.format("Damage per tick: %.2f", damage));
            } catch (NumberFormatException ex) {
                resultLabel.setText("Please enter valid numbers.");
            }
        });

        backBtn.addActionListener(e -> {
            frame.dispose();
            showWeaponSelection();
        });

        frame.setLayout(new BorderLayout());
        frame.add(lblWeapon, BorderLayout.NORTH);

        panel.add(psiLabel);
        panel.add(psiField);

        panel.add(elementalLabel);
        panel.add(elementalField);

        panel.add(statusLabel);
        panel.add(statusField);

        panel.add(weaponBonusLabel);
        panel.add(weaponBonusField);

        panel.add(weakspotLabel);
        panel.add(weakspotField);

        panel.add(critRateLabel);
        panel.add(critRateField);

        panel.add(critDamageLabel);
        panel.add(critDamageField);

        panel.add(calculateBtn);
        panel.add(resultLabel);

        panel.add(backBtn);
        panel.add(new JLabel("")); // filler

        frame.add(panel, BorderLayout.CENTER);
        frame.setVisible(true);
    }

    private static double parseOrDefault(String text, double defaultVal) {
        if (text == null || text.trim().isEmpty())
            return defaultVal;
        return Double.parseDouble(text.trim());
    }
}
