import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class OnceHumanCalculator {

    static String[] weapons = {
        "Bullseye", "Frost", "Blaze", "Power Surge", "Shrapnel", "Fortress"
    };

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> showWeaponSelection());
    }

    private static void showWeaponSelection() {
        JFrame frame = new JFrame("Once Human - Select Weapon");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 300);
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

    // private static void showCalculatorWindow(String weapon) {
    //     JFrame frame = new JFrame("Calculate Damage - " + weapon);
    //     frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    //     frame.setSize(400, 400);
    //     frame.setLocationRelativeTo(null);

    //     JPanel panel = new JPanel(new GridLayout(8, 2, 10, 10));
    //     panel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));

    //     JLabel lblWeapon = new JLabel("Weapon: " + weapon);
    //     lblWeapon.setFont(new Font("Arial", Font.BOLD, 16));

    //     JLabel psiLabel = new JLabel("Psi Intensity:");
    //     JTextField psiField = new JTextField();

    //     JLabel elementalLabel = new JLabel("Elemental DMG (%)");
    //     JTextField elementalField = new JTextField();

    //     JLabel statusLabel = new JLabel("Status DMG (%)");
    //     JTextField statusField = new JTextField();

    //     JLabel weaponBonusLabel = new JLabel("Weapon Bonus (%)");
    //     JTextField weaponBonusField = new JTextField();

    //     JLabel weakspotLabel = new JLabel("Weakspot Bonus (%)");
    //     JTextField weakspotField = new JTextField();

    //     JButton calculateBtn = new JButton("Calculate Damage");
    //     JLabel resultLabel = new JLabel(" ");

    //     calculateBtn.addActionListener(e -> {
    //         try {
    //             double psi = Double.parseDouble(psiField.getText());
    //             double elemental = 1 + Double.parseDouble(elementalField.getText()) / 100.0;
    //             double status = 1 + Double.parseDouble(statusField.getText()) / 100.0;
    //             double weaponBonus = 1 + Double.parseDouble(weaponBonusField.getText()) / 100.0;
    //             double weakspot = 1 + Double.parseDouble(weakspotField.getText()) / 100.0;

    //             double base = psi * 0.12;
    //             double damage = base * elemental * status * weaponBonus * weakspot * 5;

    //             resultLabel.setText(String.format("Damage per tick: %.2f", damage));
    //         } catch (NumberFormatException ex) {
    //             resultLabel.setText("Please enter valid numbers.");
    //         }
    //     });

    //     // Layout setup
    //     frame.setLayout(new BorderLayout());
    //     frame.add(lblWeapon, BorderLayout.NORTH);

    //     panel.add(psiLabel);
    //     panel.add(psiField);

    //     panel.add(elementalLabel);
    //     panel.add(elementalField);

    //     panel.add(statusLabel);
    //     panel.add(statusField);

    //     panel.add(weaponBonusLabel);
    //     panel.add(weaponBonusField);

    //     panel.add(weakspotLabel);
    //     panel.add(weakspotField);

    //     panel.add(calculateBtn);
    //     panel.add(resultLabel);

    //     frame.add(panel, BorderLayout.CENTER);
    //     frame.setVisible(true);
    // }

    private static void showCalculatorWindow(String weapon) {
    JFrame frame = new JFrame("Calculate Damage - " + weapon);
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.setSize(400, 450);
    frame.setLocationRelativeTo(null);

    JPanel panel = new JPanel(new GridLayout(9, 2, 10, 10)); // Increased rows for Go Back button
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

    JButton calculateBtn = new JButton("Calculate Damage");
    JLabel resultLabel = new JLabel(" ");

    JButton backBtn = new JButton("Go Back");

    calculateBtn.addActionListener(e -> {
        try {
            double psi = Double.parseDouble(psiField.getText());
            double elemental = 1 + Double.parseDouble(elementalField.getText()) / 100.0;
            double status = 1 + Double.parseDouble(statusField.getText()) / 100.0;
            double weaponBonus = 1 + Double.parseDouble(weaponBonusField.getText()) / 100.0;
            double weakspot = 1 + Double.parseDouble(weakspotField.getText()) / 100.0;

            double base = psi * 0.12;
            double damage = base * elemental * status * weaponBonus * weakspot * 5;

            resultLabel.setText(String.format("Damage per tick: %.2f", damage));
        } catch (NumberFormatException ex) {
            resultLabel.setText("Please enter valid numbers.");
        }
    });

    backBtn.addActionListener(e -> {
        frame.dispose(); // Close current calculator window
        showWeaponSelection(); // Go back to weapon selection window
    });

    // Layout setup
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

    panel.add(calculateBtn);
    panel.add(resultLabel);

    panel.add(backBtn); // Add Go Back button to panel
    panel.add(new JLabel("")); // empty label to fill grid cell

    frame.add(panel, BorderLayout.CENTER);
    frame.setVisible(true);
}

}
