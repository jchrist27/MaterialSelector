public class CoreLayer extends Layer{
   private double weight;
   
   public CoreLayer(int coreID, double thickness, int id){
      super(coreID);
      setID(id);
      setThickness(thickness);
      weight = getMaterial().getDensity() * getVolume();
      modulus = getMaterial().getModulus();
      updateName(thickness + " in. thick, " + getMaterial().toString());
      setType("Core");
      this.cost = 100 + (material.density * this.volume * material.costPerPound);
   }
}