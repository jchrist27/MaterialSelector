import java.io.*;
import java.util.*;


public class MaterialSimulator{
   public static void main(String[] args) throws FileNotFoundException{
      Panel testPanel = new Panel();
      LayerManager layers = new LayerManager();
      //testPanel.addLayer(layers.getLayer(107));
      System.out.println("Deflection: "+ testPanel.calculateDeflection());
      PrintStream yeetThatShit = new PrintStream(new File("layerdump.txt"));
      layers.layerDump(yeetThatShit);
      PanelManager newPanels = new PanelManager();
      
      
   }
}