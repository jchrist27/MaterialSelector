import java.util.*;

public class PanelManager{
   private static final int MATERIAL_TYPES = 324;
   private static final int LAYER_COUNT = 8;
   private static final double MAX_THICKNESS = 1.25; // Change this plz
   private static final LayerManager layerLookup = new LayerManager();
   private double bestCost;
   List<Panel> panels;
   
   Panel bestPanel;
   
   public void pickMaterials (List<Integer> panelMaterials, int materialsToExploreStart, int materialsToExploreEnd, double currThickness) {
      if (currThickness > MAX_THICKNESS){
         return;
      }
      
      if (panelMaterials.size() == LAYER_COUNT){
         // Finished adding all materials to a panel
         Panel newCombo = new Panel(convIntegerList(panelMaterials));
         if (newCombo.isValid() &&  newCombo.getCost() < bestCost){
            System.out.println("New best panel! Last cost: " + bestCost + ". New cost: " + newCombo.getCost());
            System.out.println("Panel combo: " + newCombo);
            newCombo.calculateDeflection();
            bestPanel = newCombo;
            bestCost = bestPanel.getCost();
            //panels.add(newCombo);
         }
      } else { 
         // Otherwise test new materials we could add
         for (int i = materialsToExploreStart; i <= materialsToExploreEnd; i++){
            panelMaterials.add(i);
            pickMaterials(panelMaterials, materialsToExploreStart, MATERIAL_TYPES, currThickness + layerLookup.getLayer(i).getThickness());
            panelMaterials.remove(panelMaterials.size() - 1);
         }
      }
   }
   
   // 0-arg version of pickMaterials
   public void pickMaterials () {
   
      pickMaterials(new ArrayList<Integer>(42), 0, MATERIAL_TYPES);
   }
   
   /*
   Params:
   -panelMaterials: A list of materials chosen for the given panel. Will explore materials in 
      indexes beyond those give
   -materialsToExplore: Materials already explored in the current span. IE if we've already test 
      materials 0-4000 in index 0, passing in 4001 will skip exploring those panels that start 
      with materials 0-4000
   */
   public void pickMaterials (List<Integer> panelMaterials, int materialsToExploreStart, int materialsToExploreEnd) {
      double currThickness = 0.0;
      for (Integer materialID : panelMaterials){
         currThickness += layerLookup.getLayer(materialID).getThickness();
      }
      pickMaterials(panelMaterials, 0, MATERIAL_TYPES, currThickness);
   }
   
   // I hate this omg but it's fine?
   private int[] convIntegerList (List<Integer> list){
      int[] res = new int[LAYER_COUNT];
      for (int i = 0; i < LAYER_COUNT; i++){
         res[i] = list.get(i);
      }
      return res;
   }
   
   public PanelManager(){   
      bestCost = 9999999;
      panels = new ArrayList<>();
      for(int fID = 1; fID < 325; fID++){
         for(int cID = 325; cID < 435; cID++){
            FiberLayer fLayer = layerLookup.getFiberLayer(fID);
            CoreLayer cLayer = layerLookup.getCoreLayer(cID);
            if(coreDeflection(fLayer, cLayer) < 0.2){
               Panel newPanel = new Panel();
               newPanel.addLayer(fLayer);
               newPanel.addLayer(cLayer);
               newPanel.addLayer(fLayer);
               if(newPanel.getCost() < bestCost){
                  bestPanel = newPanel;
                  bestCost = bestPanel.getCost();
                  System.out.println("New best panel! Last cost: " + bestCost + ". New cost: " + bestPanel.getCost());
                  System.out.println("Panel combo: " + bestPanel);
                  System.out.println("Deflection: " + coreDeflection(fLayer, cLayer));
               }
            }
         }  
      }
      
      
      pickMaterials(); // populate the list of panels with all options
      System.out.println("Best panel:");
      System.out.println(bestPanel);
   }
   
   private double coreDeflection(FiberLayer fLayer, CoreLayer cLayer){
      double D = fLayer.modulus * fLayer.thickness * (fLayer.thickness + cLayer.thickness) * 108 / 2;
      double S = 108 * (fLayer.thickness + cLayer.thickness) * cLayer.modulus;
      double kb = 5.0/384.0;
      double ks = 1.0/8.0;
      double P = 5 * 36; // 5 psi, 36 in.
      double deflection = ((kb * P * Math.pow(36, 3)) / D) + ((ks * P * 36) / S);
      return deflection;
   }
}