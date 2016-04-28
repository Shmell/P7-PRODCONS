package prod.cons;




import java.util.Random;



public class Productor implements Runnable
{
    private final Random aleatorio;
    private final Contenedor contenedor;
    private final int idproductor;
    private final int TIEMPOESPERA = 2000;

   
    public Productor(Contenedor contenedor, int idproductor) 
    {
        this.contenedor = contenedor;
        this.idproductor = idproductor;
        aleatorio = new Random();
    }

   
    public void run() 
    {
        while(Boolean.TRUE)
        {
            int poner = aleatorio.nextInt(75);
            contenedor.put(poner,idproductor);
            
           
            
            try 
            {
                Thread.sleep(TIEMPOESPERA);
            } 
            catch (InterruptedException e) 
            {
                System.err.println("Productor " + idproductor + ": Error en run -> " + e.getMessage());
            }
        }
    }
}
