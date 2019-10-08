import java.util.*;

public class Panel{
   private ArrayList<Layer> layers;
   private static final LayerManager layerMan = new LayerManager();
   private double thickness;
   private static double bestCost;
   private static final int WIDTH = 36;
   private static final int HEIGHT = 108;
   public double cost;
   
   public Panel(int[] layerSet){
      boolean hasCore = false;
      thickness = 0;
      layers = new ArrayList<Layer>(5);
      for(int layerNo = 0; layerNo < layerSet.length; layerNo++){
         int layerID = layerSet[layerNo];
         Layer layerTarget = layerMan.getLayer(layerID);
         if(!layerTarget.isEmpty()){
            if(hasCore && !layerTarget.isCore()){
               layers.add(layerTarget);
            } else if(!hasCore){
               layers.add(layerTarget);
            }
         }
      }
      generateThickness();
   }
   
   public Panel(){
      thickness = 0;
      layers = new ArrayList<Layer>(1);
   }
   
   public void addLayer(Layer layer){
      if(!layer.isEmpty()){
         layers.add(layer);
      }
      generateThickness(); 
   }
   
   public void removeLayer(int id){
      layers.remove(id);
   }
   
   public String toString(){
      String output = "";
      for(Layer layer:layers){
         output += layer.toString() + ", ";
      }
      return output;
   }
   
   private void generateThickness(){
      for(int i = 0; i < layers.size(); i++){
         thickness += layers.get(i).getThickness();
      }
   }
   
   public boolean isValid(){ // thickness and deformation
      if (thickness == 0.0 || thickness > 1.25){
         return false;
      } if(calculateDeflection() > 0.2){
         return false;
      } if(layers.get(0).isCore()  || layers.get(layers.size() - 1).isCore()){
         return false;
      }
      return true;        
   }
   
   public double calculateDeflection(){
      double omega = 5 * HEIGHT; // 5 PSI * HEIGHT IN -> lb/in
      double E = 0;
      for(Layer currLayer : layers){
         E += currLayer.modulus * (currLayer.thickness / this.thickness) * 1000000;
      }
      double I = HEIGHT * (Math.pow(this.thickness,3))/12;
      double deflection = (double)(5.0/384.0)*omega*Math.pow(WIDTH, 4)/(E*I); // fix this when conrad is awake.
      return deflection;
   }
   public boolean isViable(){
      return true;
   }
   
   public double getCost(){
      double cost = 0.0;
      ArrayList<Integer> materialIDs = new ArrayList<Integer>(1);
      for (Layer currLayer : layers){
         cost += currLayer.cost;
         if(!materialIDs.contains(currLayer.id)){
            materialIDs.add(currLayer.id);
            cost += 41.7;
         }
      }
      return cost;
   }
}