/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bacplanilla;

import java.io.IOException;





/**
 *
 * @author Figueroa
 */
public class BacPlanilla {

    /**
     * @param args the command line arguments
     * @throws java.io.IOException
     * 
     * 
     */
    public static void main(String[] args) throws IOException   {
        /* 1. Intancia clase IniciaGeneración en varialble iniciar
           2. Llama a método solicitaParametros()
        */
        
        vista_1 form = new vista_1();
        form.setVisible(true);
       
      /* IniciaGeneracion iniciar = new IniciaGeneracion();
        
        iniciar.solicitaParametros(); */
        
        
    }
    
    
    
}
