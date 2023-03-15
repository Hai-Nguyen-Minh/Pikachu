import java.awt.FlowLayout;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Random;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class GameLol extends JFrame {
  public GameLol() {
    setTitle("Game lol doan so :)");
    setSize(500, 400);
    addWindowListener(new WindowAdapter() {
      @Override
      public void windowClosing(WindowEvent e) {
        System.exit(0);
      }
    });
    JPanel panel = new JPanel();
    panel.setLayout(new FlowLayout(FlowLayout.LEADING));
    add(panel);
    JLabel label = new JLabel("Your answer: ");
    JTextField field = new JTextField();
    field.setSize(80, 30);
    JButton button1 = new JButton("click");
    Random random = new Random();
    Integer result = random.nextInt(100);
    System.out.println(result);
    button1.addActionListener((actionEvent) -> {
      try {
        Integer answer = Integer.valueOf(field.getText());
        if (answer > 100 || answer < 0) {
          JOptionPane.showMessageDialog(panel, "Out of memory!!!");
        }
        else if (answer.equals(result)) {
          JOptionPane.showMessageDialog(panel, "You Win!!!");
        }
        else {
          JOptionPane.showMessageDialog(panel, "Wrong!!!");
        }
      }
      catch (NumberFormatException exception) {
        JOptionPane.showMessageDialog(panel, "Error!!! your answer is not numeric");
      }
    });
    panel.add(label);
    panel.add(field);
    panel.add(button1);
    setVisible(true);
  }
}
