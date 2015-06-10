import javax.swing.*;
import java.awt.*;
public class RunningFinalProject{
  public static void main( String [] args ) {
    JFrame f = new JFrame();
    f.setTitle( "FinalProject" );
    f.setSize(340,940);
    f.setDefaultCloseOperation( JFrame. EXIT_ON_CLOSE );
    Container pane = f.getContentPane();
    pane.setLayout( new BorderLayout() );
    FinalProjectPanel p = new FinalProjectPanel();
    pane.add( p, BorderLayout.CENTER );
    pane.setPreferredSize( new Dimension( 340, 940));
    f.pack();
    f.setVisible( true );
  }
}
