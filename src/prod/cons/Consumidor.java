package prod.cons;

import java.util.Random;




public class Consumidor implements Runnable
{
    private final Random aleatorio;
    private final Contenedor contenedor;
    private final int idconsumidor;
    private final int TIEMPOESPERA = 1000;
   
    public Consumidor(Contenedor contenedor, int idconsumidor) 
    {
        this.contenedor = contenedor;
        this.idconsumidor = idconsumidor;
        aleatorio = new Random();
        
        
    }

    
    public void run() 
    {
        while(Boolean.TRUE)
        {
            int quitar = aleatorio.nextInt(50);
            contenedor.get(quitar,idconsumidor);
            
             try 
            {
                Thread.sleep(TIEMPOESPERA);
            } 
            catch (InterruptedException e) 
            {
                System.err.println("Productor " + idconsumidor+ ": Error en run -> " + e.getMessage());
            }
            
            
            
        }
    }
}
