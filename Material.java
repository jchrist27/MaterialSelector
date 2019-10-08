public class Material{
   private double modulus; // Young's modulus in million Psi
   public double density; // Density in g/cm3
   public int maxUseTemp; // Max useage temp in F
   private double moistureUptake; // Moisture update in wt% (at 100% RH???)
   public int costPerPound; // Cost per pound in USD
   private String materialName; // Name of the material
   private String type; // Type of material
   public Material(String type, String materialName, double modulus, double density, int maxUseTemp, double moistureUptake, int costPerPound){
      this.modulus = modulus;
      this.density = density;
      this.maxUseTemp = maxUseTemp;
      this.moistureUptake = moistureUptake;
      this.costPerPound = costPerPound;
      this.materialName = materialName;
   }
   
   public double getModulus(){
      return modulus;
   }
   
   public double getDensity(){
      return density;
   }
   
   public int getMaxUseTemp(){
      return maxUseTemp;
   }
   
   public double getMoistureUptake(){
      return moistureUptake;
   }
   
   public int getCostPerPound(){
      return costPerPound;
   }
   
   public String getName(){
      return materialName;
   }
   
   public String getType(){
      return type;
   }
   public String toString(){
      return materialName;
   }
}