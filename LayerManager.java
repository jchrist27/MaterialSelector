import java.util.*;
import java.io.*;
public class LayerManager{
   public static ArrayList<Layer> layerList; // collection of all eligible layer types
   private int layerID;
   public LayerManager(){
      System.out.println("LayerManager made!");
      layerList = new ArrayList<Layer>(50);
      layerList.add(new EmptyLayer());
      layerID = 1;
      // Fibers: Fiber, Resin, Grade, Percent.
      for(int resin = 0; resin < 7; resin++){
         for(int fiber = 7; fiber < 14; fiber++){
            for(int grade = 1; grade < 4; grade++){
               for(int percent = 40; percent < 61; percent+=10){
                  FiberLayer layerTarget = new FiberLayer(fiber, resin, grade, percent);
                  if(layerTarget.getThickness() < 1.25 && (layerTarget.material.maxUseTemp > layerTarget.resinMaterial.cureTemp)){
                     layerTarget.setID(layerID);
                     layerList.add(layerTarget);
                     layerID++;
                  }
               }
            }
         }
      }
      for(int core = 14; core < 24; core++){
         for(double thickness = 0.1; thickness < 1.1;){
            thickness = thickness + 0.1;
            layerList.add(new CoreLayer(core, thickness, layerID));
            layerID++;
         }
      }
      System.out.println("pause");
   }
   
   public void layerDump(PrintStream output){
      for(Layer layer:layerList){
         output.println("Name = " + layer.name + ", ID = " + layer.id);
         output.println("Modulus = " + layer.modulus + ", Thickness = " + layer.thickness + ", Cost = " + layer.cost); 
      }
   }
   public Layer getLayer(int id){
      return layerList.get(id);
   }
   
   public CoreLayer getCoreLayer(int id){
      return (CoreLayer)layerList.get(id);
   }
   
   public FiberLayer getFiberLayer(int id){
      return (FiberLayer)layerList.get(id);
   }
}