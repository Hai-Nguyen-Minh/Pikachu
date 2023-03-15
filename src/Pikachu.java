import java.awt.GridLayout;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class Pikachu extends JFrame {
  public Pikachu() {
    Map<Integer, String> answers = new HashMap<>();
    ArrayList<Integer> arrayList = new ArrayList<>();
    ArrayList<Integer> arrayA = new PikachuArray().getPikachuArray();
    ArrayList<Integer> arrayB = new PikachuArray().getPikachuArray();
    ArrayList<String> answersArr = new ArrayList<>();
    ArrayList<Integer> compIndex = new ArrayList<>();
    Random random = new Random();
    arrayList.addAll(arrayA);
    arrayList.addAll(arrayB);
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
    for (int i = 0; i < arrayList.size(); i++) {
      JButton button;
      button = new JButton(String.valueOf(random.nextInt(arrayList.size())));
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
