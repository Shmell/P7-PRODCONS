package prod.cons;

import java.util.Random;




public class Consumidor implements Runnable
{
    private final Random aleatorio;
    private final Contenedor contenedor;
    private final int idconsumidor;

   
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
            System.out.println("El consumidor " + idconsumidor + " consume: " + contenedor.get(quitar));
             System.out.println("CONTENEDOR TIENE: "+Contenedor.contenido);
        }
    }
}
