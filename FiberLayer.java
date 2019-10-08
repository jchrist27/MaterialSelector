public class FiberLayer extends Layer{
   public static final int[] gradeArea = {0, 45, 145, 190};

   public ResinMaterial resinMaterial; // Material of the resin
   private int grade; // grade of the fiber
   private int percentFiber; // percent, by volume, of fiber
   private double compositeWeight; // combined weight of fiber and resin
      
   public FiberLayer(int fiberNo, int resinNo, int grade, int percentFiber){
      super(fiberNo);
      resinMaterial = materials.getResinMat(resinNo);
      this.grade = grade;
      this.percentFiber = percentFiber;
      double decimalFiber = (double) percentFiber / 100;
      updateName();
      setThickness();
      setCompositeProperties(decimalFiber);
      setType("Fiber");
      this.cost = 25 + 1.5 * ((material.density * decimalFiber * this.volume * material.costPerPound) + (this.resinMaterial.density * (1-decimalFiber) * this.volume * this.resinMaterial.costPerPound)) + this.resinMaterial.cureCost;
   }
   private void updateName(){
      String generatedName = getMaterial().getName() + "-" + resinMaterial.getName() + " Fiber, Grade " + grade + ", " + percentFiber + "% Fiber";
      super.updateName(generatedName);
   }
   private void setThickness(){
      super.setThickness(gradeArea[grade]/(getMaterial().getDensity() * 10 * percentFiber));
   }
   private void setCompositeProperties(double decimalFiber){
      modulus = (decimalFiber * 0.5 * getMaterial().getModulus()) + ((1 - decimalFiber) * resinMaterial.getModulus());
      compositeWeight = getVolume() * ((decimalFiber * getMaterial().getDensity()) + ((1 - decimalFiber) * resinMaterial.getDensity()));
   }
}