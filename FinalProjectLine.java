import javax.swing.*;
import java.awt.*;
//import java.awt.event.*;
//import java.util.*;
public class FinalProjectLine extends JComponent{
  private Color color;
  public FinalProjectLine(){
    color = new Color(0x00FF00);
  }
  public void draw(Graphics g, int x, int y){
    for( int a = 1;a < 5; a++){
      g.setColor(color);
      g.drawLine( a*x/5, 0, a*x/5, y);
    }
  }
}