package prod.cons;




public class Contenedor 
{
    static int contenido;
    private boolean contenedorlleno = Boolean.FALSE;

 
    public synchronized int get(int value)
    {
        int faltante;
        
        while (!contenedorlleno || (contenido <= 0))
        {
            try 
            {
                wait();
            } 
            catch (InterruptedException e) 
            {
                System.err.println("Contenedor: Error en get -> " + e.getMessage());
            }
        }
        
         contenido = contenido-value;
         
         if(contenido < 0)
            {
             contenedorlleno = Boolean.FALSE;
              faltante=contenido;
              value=value+faltante;
              contenido=0;
            
            }
        else
            contenedorlleno = Boolean.FALSE;
        
        notify();
        
        
        
        return value;
    }

    
    public synchronized void put(int value,int idProductor) 
    {
        int sobrante;
        
        while (contenedorlleno && (contenido >200)) 
        {
            try 
            {
                wait();
            } 
            catch (InterruptedException e) 
            {
                System.err.println("Contenedor: Error en put -> " + e.getMessage());
            }
        }
        
        contenido = contenido+value;
        
        if(contenido > 200)
            {
             contenedorlleno = Boolean.TRUE;
              sobrante=contenido-200;
              value=value-sobrante;
              contenido=200;
            
            }
        else
           contenedorlleno = Boolean.TRUE;
        
        System.out.println("\nEl productor " +(idProductor)+ " pone: " + value);
        System.out.println("CONTENEDOR TIENE: "+Contenedor.contenido);
        
       
        
        
        
        
        
        notify();
    }
}
