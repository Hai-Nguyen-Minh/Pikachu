import java.awt.GridLayout;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Objects;
import java.util.Random;
import java.util.Set;
import java.util.concurrent.atomic.AtomicInteger;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class Pikachu extends JFrame {
  public Pikachu() {
    Map<Integer, String> answers = new HashMap<>();
    ArrayList<Integer> arrayList = new PikachuArray().getPikachuArray();
    Set<Integer> set1 = new LinkedHashSet<>();
    Set<Integer> set2 = new LinkedHashSet<>();
    ArrayList<Integer> array = new ArrayList<>();
    ArrayList<String> answersArr = new ArrayList<>();
    ArrayList<Integer> compIndex = new ArrayList<>();
    AtomicInteger point = new AtomicInteger();
    Random random = new Random();
    JLabel label = new JLabel();
    int second = 0;
    int minute = 0;
    int hour = 0;
    while (set1.size() < arrayList.size()){
      Integer i = random.nextInt(arrayList.size()) + 1;
      set1.add(i);
    }
    array.addAll(set1);
    while (set2.size() < arrayList.size()){
      Integer i = random.nextInt(arrayList.size()) + 1;
      set2.add(i);
    }
    array.addAll(set2);
    getContentPane().setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));
    setTitle("Pikaychi !!!");
    setSize(800, 800);
    addWindowListener(new WindowAdapter() {
      @Override
      public void windowClosing(WindowEvent e) {
        System.exit(0);
      }
    });
    JPanel panel = new JPanel();
    panel.setLayout(new GridLayout(10, 10));
    //init 100 button to click
    for (int i = 0; i < array.size(); i++) {
      JButton button;
      button = new JButton();
      panel.add(String.valueOf(i), button);
      int finalI = i;
      button.addActionListener((actionEvent) -> {
        button.setText(String.valueOf(array.get(finalI)));
        answers.put(finalI, button.getText());
        if (answers.size() == 2) {
          for (Map.Entry<Integer, String> answer : answers.entrySet()) {
            answersArr.add(answer.getValue());
            compIndex.add(answer.getKey());
          }
          if (answersArr.get(0).equals(answersArr.get(1))) {
            panel.getComponent(compIndex.get(0)).setVisible(false);
            panel.getComponent(compIndex.get(1)).setVisible(false);
            point.getAndIncrement();
            win(panel, point.intValue(), arrayList.size());
            answersArr.clear();
            compIndex.clear();
            answers.clear();
          }
          else {
            JButton button1 = (JButton) panel.getComponent(compIndex.get(0));
            JButton button2 = (JButton) panel.getComponent(compIndex.get(1));
            button1.setText("");
            button2.setText("");
            answersArr.clear();
            compIndex.clear();
            answers.clear();
          }
        }
      });
    }
    add(label);
    add(panel);
    setVisible(true);
    try {
      while(win(panel, point.intValue(), arrayList.size())) {
        second++;
        if (second > 59) {
          minute++;
          second = 0;
          if (minute > 59) {
            hour++;
            minute = 0;
          }
        }
        Thread.sleep(1000);
        label.setText("Time [ " + hour + " : " + minute + " : " + second + " ]");
      }
    }
    catch (InterruptedException exception) {
      throw new RuntimeException();
    }
  }
  private boolean win(JPanel panel, Integer point, Integer size) {
    if (Objects.equals(point, size)) {
      JOptionPane.showMessageDialog(panel, "You Win!!!");
      return false;
    }
    return true;
  }
}
