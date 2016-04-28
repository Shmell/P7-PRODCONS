package prod.cons;

import java.awt.Color;
import javax.swing.JTextArea;




public class Contenedor 
{
    static int contenido;
    private boolean contenedorlleno = Boolean.FALSE;
    
    
    public Contenedor()
    {
     Thread update=new Thread(new Thread(){
        public void run()
        {
            while(true)
            {
               Ventana.Buffer.setValue(contenido/2); 
               
               if(contenido <= 0)
                {
                 Ventana.bufferTam.setText("VACIO");
                 
                 Ventana.jLabel2.setForeground(Color.black);
                 Ventana.jLabel2.setText("INACTIVO");
                }   
               else if(contenido >= 200)
                        {
                         Ventana.bufferTam.setText("Lleno");
                         
                         Ventana.jLabel1.setForeground(Color.black);
                         Ventana.jLabel1.setText("INACTIVO");
                        }
                      else
                         Ventana.bufferTam.setText(Integer.toString(contenido/2)+"%");
               
            }
        }
    }); 
     
    update.start(); 
    }

   
 
    public synchronized int get(int value,int idConsumidor)
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
         
         
          System.out.println("El consumidor " + idConsumidor + " consume: "+value);
          Ventana.textoC.append("El consumidor " + idConsumidor + " consume: "+value+"\n");
          
          
          System.out.println("CONTENEDOR TIENE: "+Contenedor.contenido);
          //Ventana.Buffer.setValue(contenido);
          System.out.println(Ventana.Buffer.getValue());
          
          
           Ventana.jLabel1.setForeground(Color.red);
         Ventana.jLabel1.setText("Actividad de los productores");
        
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
        
        System.out.println("\nEl productor " + " pone: " + value);
        Ventana.textoP.append("El productor " + " pone: " + value+"\n");
        
        
        System.out.println("CONTENEDOR TIENE: "+Contenedor.contenido);
         //Ventana.Buffer.setValue(contenido);
        System.out.println(Ventana.Buffer.getValue());
        
        
         Ventana.jLabel2.setForeground(Color.red);
         Ventana.jLabel2.setText("Actividad de los consumidores");
        
        
        notify();
    }
}
