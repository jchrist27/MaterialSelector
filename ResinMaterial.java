public class ResinMaterial extends Material{
   public int cureTemp; // Temperature for curing the resin
   public double cureCost; // cost for curing the resin
   
   public ResinMaterial(String materialName, double modulus, double density, int cureTemp, int maxUseTemp, double moistureUptake, int costPerPound){
      super("Resin", materialName, modulus, density, maxUseTemp, moistureUptake, costPerPound);
      this.cureTemp = cureTemp; 
      cureCost = cureTemp * 0.25;
   }
   
   public int getCureTemp(){
      return cureTemp;
   }
}