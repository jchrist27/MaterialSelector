import java.util.*;

public class MaterialManager{
   public static Material[] materialList; // Collection of all materials.
   public static double randInt;
   
   public MaterialManager(){
      System.out.println("MaterialManager made!");
      randInt = Math.random();
      System.out.println(randInt);
      materialList = new Material[24];
      materialList[0]  = new ResinMaterial("Polybutadiene",  0.50, 1.32, 250, 140, 4,   20);
      materialList[1]  = new ResinMaterial("Polyester",      0.40, 1.35, 350, 200, 4,   5 );
      materialList[2]  = new ResinMaterial("Epoxy",          0.55, 1.20, 350, 300, 2.5, 10);
      materialList[3]  = new ResinMaterial("Tough Epoxy",    0.45, 1.30, 350, 270, 3,   15);
      materialList[4]  = new ResinMaterial("Cyanate Ester",  0.50, 1.19, 450, 350, 1,   25);
      materialList[5]  = new ResinMaterial("Bismaelimide",   0.60, 1.24, 550, 400, 4,   20);
      materialList[6]  = new ResinMaterial("Polyimide",      0.55, 1.22, 700, 450, 3.5, 50);
      
      materialList[7]  = new Material("Fiber", "E-Glass",    10.5, 2.49, 800, 0,   10 );
      materialList[8]  = new Material("Fiber", "S2-Glass",   12.6, 2.20, 800, 0,   15 );
      materialList[9]  = new Material("Fiber", "Kevlar",     21.0, 1.44, 350, 6,   30 );
      materialList[10] = new Material("Fiber", "Spectra",    17.0, 0.97, 160, 0.2, 40 );
      materialList[11] = new Material("Fiber", "SM Carbon",  33.0, 1.78, 800, 0,   20 );
      materialList[12] = new Material("Fiber", "IM Carbon",  41.0, 1.81, 800, 0,   40 );
      materialList[13] = new Material("Fiber", "HM Carbon",  63.0, 1.84, 800, 0,   100);
      
      materialList[14] = new Material("Core", "Nomex 2",      2.2, 2.00, 250, 4.0, 50 );
      materialList[15] = new Material("Core", "Nomex 3",      3.5, 3.00, 250, 4.0, 60 );
      materialList[16] = new Material("Core", "Nomex 4",      4.7, 4.00, 250, 4.0, 75 );
      materialList[17] = new Material("Core", "Nomex 6",      6.0, 6.00, 250, 4.0, 100);
      materialList[18] = new Material("Core", "Glass 4",      7.0, 4.00, 350, 2.0, 100);
      materialList[19] = new Material("Core", "Glass 5.5",   11.0, 5.50, 350, 2.0, 110);
      materialList[20] = new Material("Core", "Glass 8",     19.0, 8.00, 350, 2.0, 120);
      materialList[21] = new Material("Core", "Alumi 2",     15.0, 2.00, 350, 0.0, 200);
      materialList[22] = new Material("Core", "Alumi 3.1",   19.0, 3.10, 350, 0.0, 220);
      materialList[23] = new Material("Core", "Alumi 4.4",   30.0, 4.40, 350, 0.0, 240);
   }
   
   public Material getMat(int id){
      return materialList[id];
   }
   
   public Material getMat(String name){
      for(int i = 0; i < materialList.length; i++){
         if(name.equals(materialList[i].getName())){
            return materialList[i];
         }
      }
      return null;
   }
   
   public ResinMaterial getResinMat(int id){
      return (ResinMaterial)materialList[id];
   }
}