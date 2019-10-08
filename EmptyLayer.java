public class EmptyLayer extends Layer{

   public EmptyLayer(){
      super(1);
      setThickness(0);
      updateName("Empty Layer");
      setType("Blank");
      setEmpty();
   }
}