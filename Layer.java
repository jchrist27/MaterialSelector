import java.io.*;
import java.util.*;

public class Layer{
   public static final MaterialManager materials = new MaterialManager();
   private static final int WIDTH = 3;
   private static final int HEIGHT = 9;
   
   public double modulus;
   public double thickness; // Thickness of layer in fractional inches
   public String materialType; // Fiber, or Core
   public Material material; // Material reference for properties
   public String name; // Name of material
   public double volume; // Volume of layer
   public boolean isEmpty; // true for empty layer
   public double cost; // cost of the layer to produce
   public int id; // unique ID
   
   public Layer(int material){
      this.material = materials.getMat(material);
      isEmpty = false;
   }   
   
   public void setID(int id){
      this.id = id;
   }
   
   public void setType(String type){
      this.materialType = type;
   }
   
   public String getType(){
      return materialType;
   }
   
   public void updateName(String name){
      this.name = name;
   }
   
   public void setThickness(double thickness){
      this.thickness = thickness;
      updateVolume();
   }
   
   public Material getMaterial(){
      return material;
   }
   
   public double getVolume(){
      return volume;
   }
   
   public double getThickness(){
      return thickness;
   }
   
   private void updateVolume(){
      volume = WIDTH * HEIGHT * thickness;
   }
   
   public boolean isEmpty(){
      return isEmpty;
   }
   
   public void setEmpty(){
      isEmpty = true;
   }
   
   public boolean isCore(){
      return materialType.equals("Core");
   }
   
   public String idName(){
      return "" + id;
   }
   
   public String toString(){
      return name;
   }
}