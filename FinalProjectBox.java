import javax.swing.*;
import java.awt.*;
//import java.awt.event.*;
//import java.util.*;

public class FinalProjectBox extends JComponent {
 private Color color;
 private int locationx;
 public int locationy;
 public static final int initial_speed = 100000;
 public static int speed = initial_speed;
 public boolean isgood = false;
 public boolean isblue = false;

 public FinalProjectBox(int x) {
  locationx = x;
  locationy = 0;
  color = new Color(0x00FF00);
  if (Math.random() < 0.2) {
   isgood = true;
   color = Color.PINK;
  }
  if (Math.random() < 0.2) {
   isgood = false;
   isblue = true;
   color = Color.BLUE;
  }
 }

 public void draw(Graphics g) {
  g.setColor(color);
  g.drawRect(locationx, locationy, 30, 30);
  if (isgood)
   g.fillRect(locationx, locationy, 30, 30);
 }

 public void move() {
  locationy += speed++ / 10000;
 }

 public int xB() {
  return locationx;
 }

 public int yB() {
  return locationy;
 }
}
