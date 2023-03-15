import java.awt.GridLayout;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Random;
import java.util.Set;
import java.util.concurrent.atomic.AtomicInteger;
import javax.swing.JButton;
import javax.swing.JFrame;
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
    while (set1.size() < arrayList.size()){
      Integer i = random.nextInt(arrayList.size()) + 1;
      set1.add(i);
    }
    array.addAll(set1);
    System.out.println("set1 : "+ set1);
    while (set2.size() < arrayList.size()){
      Integer i = random.nextInt(arrayList.size()) + 1;
      set2.add(i);
    }
    array.addAll(set2);
    System.out.println("set2 : "+ set2);
    System.out.println(arrayList.size());
    setTitle("Pikachu !!!");
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
      button = new JButton(String.valueOf(array.get(i)));
      panel.add(String.valueOf(i), button);
      int finalI = i;
      button.addActionListener((actionEvent) -> {
        answers.put(finalI, button.getText());
        System.out.println(answers);
        if (answers.size() == 2) {
          for (Map.Entry<Integer, String> answer : answers.entrySet()) {
            answersArr.add(answer.getValue());
            compIndex.add(answer.getKey());
          }
          if (answersArr.get(0).equals(answersArr.get(1))) {
            panel.getComponent(compIndex.get(0)).setVisible(false);
            panel.getComponent(compIndex.get(1)).setVisible(false);
            point.getAndIncrement();
            System.out.println("point : "+point);
            if (point.get() == arrayList.size()) {
              JOptionPane.showMessageDialog(panel, "You Win!!!");
            }
            System.out.println(panel.getComponents().length);
            answersArr.clear();
            compIndex.clear();
            answers.clear();
          }
          answersArr.clear();
          compIndex.clear();
          answers.clear();
        }
      });
    }
    add(panel);
    setVisible(true);
  }
}
