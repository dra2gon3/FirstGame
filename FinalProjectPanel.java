import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class FinalProjectPanel extends JPanel implements KeyListener {
 private JButton start = new JButton("Start");
 private JButton stop = new JButton("Stop");
 private JButton restart = new JButton("Restart");
 private JButton instructions = new JButton("Options");
 private JButton highscores = new JButton("High Scores");
 private ArrayList<FinalProjectBox> one, two, three, four;
 private javax.swing.Timer timer, timer2;
 private FinalProjectLine fpl;
 private FinalProjectCharacter fpc;
 private int score;

 public FinalProjectPanel() {

  setLayout(new BorderLayout());
  one = new ArrayList<FinalProjectBox>();
  two = new ArrayList<FinalProjectBox>();
  three = new ArrayList<FinalProjectBox>();
  four = new ArrayList<FinalProjectBox>();
  timer = new javax.swing.Timer(750, new TimerListener());
  timer2 = new javax.swing.Timer(16, new TimerListener2());
  setBackground(Color.BLACK);
  add(fpl = new FinalProjectLine());
  add(fpc = new FinalProjectCharacter());
  JPanel bottom = new JPanel();
  bottom.setLayout(new GridLayout());
  add(bottom, BorderLayout.SOUTH);
  start.addActionListener(new HandleStartButton());
  stop.addActionListener(new HandleStopButton());
  restart.addActionListener(new HandleRestartButton());
  instructions.addActionListener(new InstructionPane());
  highscores.addActionListener(new HighScorePane());
  bottom.add(start);
  bottom.add(stop);
  bottom.add(restart);
  bottom.add(instructions);
  bottom.add(highscores);
  setFocusable(true);
  requestFocusInWindow();
  addKeyListener(this);
 }

 public void paintComponent(Graphics g) {
  super.paintComponent(g);
  requestFocusInWindow();
  fpl.draw(g, getWidth(), getHeight());
  fpc.draw(g, getWidth(), getHeight());
  for (FinalProjectBox fpb : one) {
   fpb.draw(g);
  }
  for (FinalProjectBox fpb2 : two) {
   fpb2.draw(g);
  }
  for (FinalProjectBox fpb3 : three) {
   fpb3.draw(g);
  }
  for (FinalProjectBox fpb4 : four) {
   fpb4.draw(g);
  }
  g.setColor(Color.GREEN);
  g.drawString("Score: " + score, 5, 15);
 }

 private class HandleStartButton implements ActionListener {
  public void actionPerformed(ActionEvent e) {
   timer.start();
   timer2.start();
   score = 0;
   FinalProjectBox.speed = FinalProjectBox.initial_speed;
  }
 }

 private class HandleStopButton implements ActionListener {
  public void actionPerformed(ActionEvent e) {
   timer.stop();
   timer2.stop();
   FinalProjectBox.speed = FinalProjectBox.initial_speed;
  }
 }

 private class HandleRestartButton implements ActionListener {
  public void actionPerformed(ActionEvent e) {
   score = 0;
   one.clear();
   two.clear();
   three.clear();
   four.clear();
   timer.start();
   timer2.start();
   FinalProjectBox.speed = FinalProjectBox.initial_speed;
   repaint();
  }
 }
 
 private class InstructionPane implements ActionListener {
     public void actionPerformed(ActionEvent e){
       timer.stop();
       timer2.stop();
       JOptionPane.showMessageDialog(null,"Left arrow key to move left \nRight arrow key to move right", "Instructions", JOptionPane.OK_OPTION);
       timer.start();
       timer2.start();
     }
   }
 
 private class HighScorePane implements ActionListener {
     public void actionPerformed(ActionEvent e) {
       /*try{
         BufferedWriter bw = new BufferedWriter(new FileWriter("Highscore.txt"));
         bw.write(score + " ");
         bw.close();
       }catch(IOException err){
         err.printStackTrace();
       }*/
       try{
         BufferedReader br = new BufferedReader(new FileReader("Highscore.txt"));
         String score = br.readLine();
         System.out.println(score);
       }catch(IOException err){
         err.printStackTrace();
       }
     }
   }

 private class TimerListener implements ActionListener {
  public void actionPerformed(ActionEvent e) {
   score += 1000;
   int[] q = { 0, 1, 2, 3 };
   for (int i = 0; i < 4; ++i) {
    for (int j = 0; j < 4; ++j) {
     if (i == j || Math.random() > 0.5)
      continue;
     q[i] ^= q[j];
     q[j] ^= q[i];
     q[i] ^= q[j];
    }
   }
   int num = (int) (Math.random() * 4);
   for (int i = 0; i < num; ++i) {
    if (q[i] == 0)
     one.add(new FinalProjectBox(getWidth() / 5 - 16));
    else if (q[i] == 1)
     two.add(new FinalProjectBox(getWidth() / 5 * 2 - 15));
    else if (q[i] == 2)
     three.add(new FinalProjectBox(getWidth() / 5 * 3 - 14));
    else if (q[i] == 3)
     four.add(new FinalProjectBox(getWidth() / 5 * 4 - 14));
   }
   timer.setDelay(750 / (FinalProjectBox.speed / 70000));
  }
 }

 public boolean collides(ArrayList<FinalProjectBox> Chung) {
  for (FinalProjectBox number : Chung) {
   if (Math.abs(number.xB() - fpc.xC()) < 30
     && (number.yB() + 30 >= fpc.yC() + 15 && number.yB() < fpc
       .yC() + 30))
    if( number.isblue){
     FinalProjectBox.speed *= 1.1;
     number.locationy = 9999999;
     score *= 1.1;
     return false;
    }
    else
    if (number.isgood) {
     number.locationy = 9999999;
     score += 5000;
     return false;
    } else {
     return true;

    }
  }
  return false;
 }

 class TimerListener2 implements ActionListener {
  public void actionPerformed(ActionEvent e) {
   for (int a = 0; a < one.size(); a++) {
    one.get(a).move();

   }
   for (int b = 0; b < two.size(); b++) {
    two.get(b).move();
   }
   for (int c = 0; c < three.size(); c++) {
    three.get(c).move();
   }
   for (int d = 0; d < four.size(); d++) {
    four.get(d).move();
   }
   boolean dead = false;
   dead |= collides(one);
   dead |= collides(two);
   dead |= collides(three);
   dead |= collides(four);
   if (dead) {
    timer2.stop();
    timer.stop();
    return;
   }
   repaint();

  }
 }

 public void keyReleased(KeyEvent e) {
 }

 public void keyPressed(KeyEvent e) {
  if (e.getKeyCode() == KeyEvent.VK_LEFT) {
   fpc.moveleft();
  } else if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
   fpc.moveright();
  }
 }

 public void keyTyped(KeyEvent e) {
 }

}
