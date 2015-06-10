import javax.swing.*;
import java.awt.*;
public class FinalProjectCharacter extends JComponent{
  private Color color;
  private int locationx;
  private int locationy;
  private int line_number = 1;
  public FinalProjectCharacter(){
    color = new Color(0x00FF00);
  }
  public void draw(Graphics g, int x, int y){
    locationx = x;
    locationy = y;
    g.setColor( Color.GREEN );
   // g.fillOval( locationx/5-15, locationy-75, 30, 30);
g.fillOval( locationx/5 * line_number-15, locationy-75, 30, 30);
  }
  public void moveleft(){
    if( line_number == 1)
      return;
     --line_number;
  }
  public void moveright(){
    if( line_number == 4 )
      return;
    ++line_number;
  }
  public int xC(){
    return locationx/5* line_number-15;
  }
  public int yC(){
    return locationy-75;
  }
}
