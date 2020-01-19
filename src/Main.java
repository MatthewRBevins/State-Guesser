
import java.awt.*;
import java.awt.Color;
import javax.swing.*;
import java.awt.event.*;

public class Main {
    public static void main(String[] args) {
        new Frame();
    }
}
class Frame extends JFrame{
    int questionNum = 1;
    ButtonListener b = new ButtonListener();
    JLabel title = new JLabel("States Ten Questions");
    JLabel desc = new JLabel("You think of a US state, and the computer will guess what state it is in 10 or less yes or no questions!");
    JLabel questionTitle = new JLabel("QUESTION: " + questionNum);
    JLabel question = new JLabel("Is your state west of the Mississippi River?");
    JComboBox<String> choice = new JComboBox<>();
    JButton submit = new JButton("Submit Answer");
    JButton back = new JButton("Back");
    Guesser guess = new Guesser();
    boolean newGame = false;
    public boolean startOver = false;
    JPanel main = new JPanel();

    public Frame() {
        setLayout(new FlowLayout(FlowLayout.CENTER, 1000, 50));
        setSize(1000, 1000);
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        choice.addItem("yes");
        choice.addItem("no");
        submit.setActionCommand("1");
        back.setActionCommand("2");
        submit.addActionListener(b);
        back.addActionListener(b);
        submit.setBackground(Color.green);
        add(title);
        add(desc);
        add(questionTitle);
        //work on this:
        //add(new JLabel(new ImageIcon("usmap.png")));
        add(question);
        add(choice);
        add(submit);
        add(main);
        setTitle("US State Guesser");
        pack();
        setVisible(true);
    }
    class ButtonListener implements ActionListener{
        public void actionPerformed(ActionEvent e) {
            System.out.println(e.getActionCommand());
            if (e.getActionCommand().equals("1")) {
                remove(submit);
                remove(main);
                remove(questionTitle);
                remove(question);
                remove(choice);
                add(questionTitle);
                add(question);
                add(choice);
                add(submit);
                add(main);
                main.add(back);
                if (! newGame) {
                    startOver = false;
                    question.setText(guess.calculate(choice.getSelectedIndex()));
                    if (! guess.liar) {
                        questionNum++;
                        questionTitle.setText("QUESTION: " + questionNum);
                    }
                    if (guess.done) {
                        main.remove(back);
                        submit.setText("New Game");
                        remove(choice);
                        newGame = true;
                        questionNum = 1;
                        questionTitle.setText("QUESTION: " + questionNum);
                        remove(questionTitle);
                        main.remove(back);
                    }
                } else {
                    question.setText("Is your state west of the Mississippi River?");
                    submit.setText("Submit Answer");
                    remove(submit);
                    remove(question);
                    remove(main);
                    main.remove(back);
                    add(questionTitle);
                    add(question);
                    add(choice);
                    add(submit);
                    startOver = true;
                    newGame = false;
                    guess.startOver();
                }
            }
            else if (e.getActionCommand().equals("2")){
                question.setText(guess.back());
                questionNum--;
                if (questionNum == 1) {
                    main.remove(back);
                }
                questionTitle.setText("QUESTION: " + questionNum);
            }
        }
    }
}
