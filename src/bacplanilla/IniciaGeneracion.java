/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bacplanilla;

/* NOTA: Para actualizaciones en PC de Luciano solamente buscar las variables:
   ruta (en esta clase y vista_1) y root(en esta clase) y listo)
*/
//import java.awt.*;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.LinkedList;
import javax.swing.JFrame;

import javax.swing.*;


/**
 *
 * @author Figueroa
 */
public class IniciaGeneracion extends JFrame{
    
    /*
       Declaración de Variables GLOBALES
    */
       // 1. Para almacenar campos de la Vista  de la Planilla generada       
       LinkedList<String> cedulaEmpleados = new LinkedList();
       LinkedList<String> montoEmpleados = new LinkedList();
       LinkedList<String> nombreEmpleados = new LinkedList();
       
       LinkedList<String> montoDepurado = new LinkedList();
       LinkedList<String> cedulaDepurada = new LinkedList();
       
       boolean errorBD;
       
       String campoDescripcion;
       String ruta = ("/Users/Figueroa/desktop/BacPlanilla/noenvio.txt");
       //String ruta = ("C:\\Users\\oortega\\Desktop\\BacPlanilla\\noenvio.txt");
       //String ruta = ("C:\\Users\\Bryan Maradiaga\\Desktop\\BacPlanilla\\noenvio.txt");
       int    noEmpleados;
       String creditoTotalPlanilla_char;
       String noenvio;
       String literal1B = "B";
       String literal1T = "T";
       String literal2Plan = "123";
       String ano;
       String mes;
       String dia;
       Integer entero_acumulado;
       Integer decimal_acumulado;
       Integer entero_;
       Integer decimal_;
       String  ano_sql;
       String  mes_sql;
       String  codPlanilla_sql;
       
       

    public IniciaGeneracion () {
        
        
             
    }
    
    /* -------------------------------------------------------------------
          Esté método solicitará como único parámetro el campo de 
          descripción de planilla. c.p.ej: "Planilla Mensual Marzo 2017"
    
          1. Recuperará del file noenvio.txt en la carpeta BacPlanilla del 
          escritorio el número de envio último y le incrementará ++1 y 
          actualizará dicho archivo.
    
          2. Recuperá tambien la fecha actual del sistema, que corresponderá
          a la fecha en la que se subirá, a la plataforma de BAC el file prn.
  
        -----------------------------------------------------------------*/
    public boolean solicitaParametros(String variable[]) throws IOException{        
       
       
       
       String fechaString; 
       
       errorBD = true;
       
       codPlanilla_sql = variable[0];
       ano_sql = variable[1];
       mes_sql = variable[2];
       campoDescripcion = variable[3];
       
       System.out.println("Planilla : " + codPlanilla_sql);
       System.out.println("Año      : " + ano_sql);
       System.out.println("Mes      : " + mes_sql);
       System.out.println("Des      : " + campoDescripcion);
           
       fechaString = recuperaFechaActual();
       
       
       
           noenvio = leerNoEnvio(ruta);               
           irBaseDatosOracle();
           noEmpleados = cuentaNoEmpleados();
           if (noEmpleados != 0){               
               // Perfecto continuar 
               depuraMontos(); // y hace la sumatoria del total de Planilla a pagar
               depuraCedula();
               muestraContenidoTablas();                       
               actualizaNoEnvio(ruta);
               armarPrimerRegistro();
               armaSiguientesRegistros();                      
               String creditoTotal = formateaValorTotalPlanilla();
               System.out.println("NoEmpleados = " + noEmpleados + "\nCrédito total: " + creditoTotal + "\nNoEnvio: " + noenvio);
               JOptionPane.showMessageDialog(null," --- PROCESO  EJECUTADO  CORRECTAMENTE ---   \n\nTotal Planilla:      LPS "+ creditoTotal +"\nTotal NoEmpleados:  "+noEmpleados+"\nnoEnvio  Generado :  " + noenvio,"Informativo", JOptionPane.INFORMATION_MESSAGE); 
               System.exit(0);  
           }
           else{
               if (!errorBD){
                   JOptionPane.showMessageDialog(null," --- Conexión Fallida a con la Base de Datos ---\n             Favor contactar a Informática.","ERROR",JOptionPane.ERROR_MESSAGE); 
                   //System.exit(0);
               }
               else{
                   JOptionPane.showMessageDialog(null,"PROCESO CANCELADO\n\nNo se encontro información de Planilla para los datos seleccionados.    \n\nCambie datos de proceso y vuelva a intentar o reporte a Informática.","Advertencia",JOptionPane.ERROR_MESSAGE);
                   errorBD = false;
                   //System.exit(0);                   
               }
               
            }  
           
           return errorBD;
           
               
        
    }
    
        
    /**
     *
     * Recupera la fecha y hora del día y la retorna.
     * 
     * Asigna tambien lo que corresponde a las varibales
     * globlales de ano-mes-dia considerando que en la caso
     * del mes y dia solamente vaya un digito si corresponde.
     * 
     * c.p.ej: Si mes 04 entonces solamente asigna 4
     * 
     * 
     *
     * @return 
     */   
    public  String recuperaFechaActual(){
        
        Date date = new Date();
        
        DateFormat fecha = new SimpleDateFormat("yyyy-MM-dd");
        
        String fechaString = fecha.format(date);  
        
        // Ahora asigna a variables individuales
        
        ano = fechaString.substring(0,4);
        mes = fechaString.substring(5,7);
        dia = fechaString.substring(8,10);
                        
        //System.out.println( ano+ " " + mes + " " + dia);
        
        int var = Integer.parseInt(mes);
        
        mes = Integer.toString(var);
        
        var = Integer.parseInt(dia);
        
        dia = Integer.toString(var);
                    
        //System.out.println( ano+ " " + mes + " " + dia);
        
        if (mes.equals("07")){
            System.out.println("VALOR DE MES = " + mes);            
        }
        
        return fechaString;
        
    }   
    
    /**
     *
     * Valida que el file de control (noenvio.txt) exista en 
     * carpeta BacPlanilla del escritorio.
     * 
     *
     */   
    private static boolean isValidaFile(String ruta){         
        
        
            File fileIsv = new File(ruta);
            if(fileIsv.exists()){
              // System.out.println("Archivo noenvio.txt EXISTE");
               return true;
            }else{
                
                System.out.println("Archivo noenvio.txt NO EXISTE------");
                return false;                
            }     
        
        
    }
    
    
    /**
     *
     * Recuepera último noenvio
     * 
     *
     */   
    private static String leerNoEnvio(String ruta) throws FileNotFoundException, IOException{
        
        
        String cadena;   
        String cadenaB="";
        
        FileReader f = new FileReader(ruta);
           try (BufferedReader b = new BufferedReader(f)) {
               while((cadena = b.readLine())!=null) {
                   // System.out.println("Número de envio ==> " + cadena);
                   cadenaB = cadena;
               }  }        
        
        int lastNoEnvio = Integer.parseInt(cadenaB);
        
        ++lastNoEnvio;
        
        String currentNoEnvio = Integer.toString(lastNoEnvio);
      
      return currentNoEnvio;     
        
    }
    
       
    /**
     *
     * Actualiza noenvio en file c:/Desktop/BacPlanilla/noenvio.txt
     *
     * @param ruta
     * @throws java.io.IOException
     */    
    public void actualizaNoEnvio(String ruta) throws IOException{ 
        
        BufferedWriter rc;
        rc = null;
        try  
        { 
            rc = new BufferedWriter(new FileWriter(ruta, true));
            //System.out.println("Pasa");
            rc.newLine();
            rc.write(noenvio);            
       
        } catch (IOException e) { 
            // error       
        } 
        finally{
            if (rc != null){
              //  System.out.println("Close");
                rc.close();
            }                
                
        }         
        
    }//Fin de método
    
   
    
    
    /* -----------------------------------------------------------
        
       1. Prepara sentencia SQL
       2. Llama método query para hacer la conexión a la BD y
          recuperar los pagos.
       3. Recupera lista de empleados planilla y los coloca en otra lista concatenando
          campo por campo.
       4. Retorna lista registro al llamador       
       ------------------------------------------------------------
    */
    
    //public LinkedList<String> irBaseDatosOracle(){
    public void irBaseDatosOracle(){
        
        /*
           1 = Mensual - permanentes 
           2 = vacaciones 
           3 = 13 aguinaldo - permanentes
           4 = 14 avo permanentes
           5 = bono marzo
           8 = Mensual- contratos 
           ? = 14 avo Contrato
        */        
               
       // 1. Consulta campo CEDULA
        //String consulta = "select  CEDULA from PL_TRANSF_V where ANO ="+aa+ " and  MES =" + mm+" and COD_PLA =" + pp+ " and MONTO is not NULL order by CEDULA";
        String consulta = "select  CEDULA from PL_TRANSF_V where ANO ="+ano_sql+ " and  MES =" + mes_sql+" and COD_PLA =" + codPlanilla_sql+ " and MONTO != 0 order by CEDULA";
        
       LinkedList<String> resultadoC = query(consulta);
       
       if (errorBD){
       if (resultadoC.isEmpty()){
           System.out.println("---- No hay registros disponibles en BD");           
       }
       else{
           
       
           String cadena = "";         
           Iterator itc = resultadoC.iterator();
           
           while(itc.hasNext()){               
               String var = (itc.next().toString());                              
               cedulaEmpleados.add(var);             
           }
           
       } 
       
       
       // 2. Consulta campo MONTO
       // consulta = "select  MONTO from PL_TRANSF_V where ANO ="+aa+ " and  MES =" + mm+" and COD_PLA =" + pp+ " and MONTO is not NULL order by CEDULA"; 
        consulta = "select  MONTO from PL_TRANSF_V where ANO ="+ano_sql+ " and  MES =" + mes_sql+" and COD_PLA =" +codPlanilla_sql+ " and MONTO != 0 order by CEDULA";
       
                   
       LinkedList<String> resultadoM = query(consulta);
       
       
       if (resultadoM.isEmpty()){
           System.out.println("---- No hay registros disponibles en BD");           
       }
       else{
           
       
           String cadena = "";         
           Iterator itm = resultadoM.iterator();
           
           while(itm.hasNext()){               
               String var = (itm.next().toString());                              
               montoEmpleados.add(var);             
           }
           
       }  
       
       
       // 3. Consulta campo NOMBRE EMPLEADO
       // consulta = "select  EMPLEADO from PL_TRANSF_V where ANO ="+aa+ " and  MES =" + mm+" and COD_PLA =" + pp+" and MONTO is not NULL order by CEDULA";   
        consulta = "select  EMPLEADO from PL_TRANSF_V where ANO ="+ano_sql+ " and  MES =" + mes_sql+" and COD_PLA =" +codPlanilla_sql+" and MONTO != 0 order by CEDULA";
        
       LinkedList<String> resultadoE = query(consulta);
        
       
       if (resultadoE.isEmpty()){
           System.out.println("---- No hay registros disponibles en BD");           
       }
       else{
           
       
           String cadena = "";         
           Iterator ite = resultadoE.iterator();
           
           while(ite.hasNext()){               
               String var = (ite.next().toString());                              
               nombreEmpleados.add(var);             
           }
           
       }
       }
       
        
    }
    
    
    /* -----------------------------------------------------------
       1. Recibe String con la Sentencia SQL
       2. Llama a Statement conn para que haga la conexión a la BD
       3. Ejecuta sentencia SQL 
       4. Coloca resultado en LinkedList<String> y lo retorna.       
       ------------------------------------------------------------
    */
    
    public LinkedList<String> query(String from){
        
        try{
            Statement st = conn();
            ResultSet resultSet = st.executeQuery(from);
            
            LinkedList<String> result = new LinkedList();
            while (resultSet.next()){
                for (int i =1; i <= resultSet.getMetaData().getColumnCount(); i++){
                    result.add(resultSet.getString(i));
                }
            }
            
            return result;          
                        
        }        
        catch (Exception e){
            errorBD = false; // error base de datos
            return null;
        }
    }
    
    /* ---------------------------------------------------------     
                    PRODUCCIÓN ERP PANI
    Métodos de conexión a la BD ORCACLE -  
    
       IP: 192.168.15.102
       Puerto: 1521
       SID: DBPANI
       Esquema: USERS
       Vista: PL_TRANSF_V
       Usuario: CIDE
       Clave: cide2017       
    ----------------------------------------------------------- */
    
    public Statement conn (){
        
        try{
            
            DriverManager.registerDriver(new oracle.jdbc.driver.OracleDriver());
            
            System.out.println("conn: Conectando a BD Oracle..");           
                        
            Connection connection = DriverManager.getConnection(
                    "jdbc:oracle:thin:@192.168.15.102:1521:DBPANITG", "CIDE","pani2017");
            
            Statement statement = connection.createStatement();        
            
            return statement;         
            
        }
        catch (Exception e){
            System.out.println("Conexión Fallida a BD Oracle..: "+ e);          
            return null;
            
        }
    }
    
    /**
     *
     * Recorre lista cedulaEmpleados y retorna la catidad de estos.
     * Tambien separa los campos de valor
     * 
     *
     * @return 
     */     
    
    public int cuentaNoEmpleados(){
        
        int rc =0;        
        if (!cedulaEmpleados.isEmpty()){           
           Iterator se = cedulaEmpleados.iterator();
           
           
           while(se.hasNext()){
               ++rc;
               String cadena = se.next().toString();  
               System.out.println(cadena);
           }         
        }      
        return rc;   
    }
    
    /**
     *
     * Crea file .prn para BAC.
     * Formato: No.+ noenvio + descripción + .prn
     *
     * @param linea
     */   
    public void crearFilePrn(String linea){
        
                            
        String root = ("/Users/Figueroa/desktop/BacPlanilla/No." + noenvio + " "+ campoDescripcion +".prn");
        //String root = ("C:\\Users\\oortega\\Desktop\\BacPlanilla\\No." + noenvio + " " + campoDescripcion + ".prn");
        //String root = ("C:\\Users\\Bryan Maradiaga\\Desktop\\BacPlanilla\\No." + noenvio + " " + campoDescripcion + ".prn");
         
        
        try{
            File file = new File(root);
            if(file.exists()){
              // System.out.println("Archivo log ya existe :");
               llenarSiguienteLinea(root,linea);               
            }else{
                if(file.createNewFile()){
               // System.out.println("Archivo prn creado" );
                llenarPrimeraLinea(root,linea);                                
            }
                
            }             
            
        } catch(IOException e){
            System.out.println("No se ha podido crear el Archivo Log" + e);            
            
        }     
        
    } 
    
    /**
     *
     * Llena primer registro en el .prn.
     *
     */    
    private static void llenarPrimeraLinea(String root, String linea) throws IOException{
        
        
        
        
        BufferedWriter out;
        out = null;
        try  
        { 
            out = new BufferedWriter(new FileWriter(root, true));            
            out.write(linea);
            //System.out.println("actualiza primer registro..");
            
       
        } catch (IOException e) { 
            // error
       
        } 
        finally{
            if (out != null){
                //System.out.println("Close file..");
                out.close();
            }                
                
        }        
        
    }
    
    /**
     *
     * Llena Sub-siguientes registros en el .prn.
     *
     */    
    private static void llenarSiguienteLinea(String root, String linea) throws IOException{
        
        
        
        BufferedWriter rc;
        rc = null;
        try  
        { 
            rc = new BufferedWriter(new FileWriter(root, true));
            //System.out.println("Actualiza siguiente registro..");
            rc.newLine();
            rc.write(linea);            
       
        } catch (IOException e) { 
            // error       
        } 
        finally{
            if (rc != null){
                //System.out.println("Close file..");
                rc.close();
            }                
                
        }         
        
    }//Fin de método
    
    
    
    
    /**
     *
     * Arma primer registro de control del prn.
     * 1. Llama método para recorrer lista montoEmpleados donde
     *    formatea los montos y regista en lista montoDepurado
     *    y suma total de montos para primer registro.
     *    Total de montos es el pago total de la planilla.
     * 2. Luego arma trama primer registro.
     * 3. Llama método crearFilePrn para crear file .prn y registrar
     *    primer registro de control
     *
     */     
    
    public void armarPrimerRegistro(){
        
        
        String primerRegistro;
        String primerRegistaa;
        String mmes;
        String ddia; 
        String empleadosNo;
        String noenvio_;
        int len;
        
        
        // String totalMontoPlanilla = sumaMonto();
        if (mes.length() ==1){
            mmes = " "+mes;            
        }
        else{
            mmes = mes;
        }
        
        if (dia.length() ==1){
            ddia = " "+dia;
        }
        else{
            ddia = dia;
        }
        
        // Formatea el NoEmpleados a 3 posicones  
        empleadosNo = Integer.toString(noEmpleados);
        len = empleadosNo.length();
        switch (len){             
            case 1:                       
                empleadosNo = "  " + empleadosNo;                
                break;
            case 2:
                empleadosNo = " " + empleadosNo;                
                break;         
        }
        // Formatea a 4 el noEnvio
        noenvio_ = noenvio;
        len = noenvio_.length();
        switch (len){             
            case 1:                       
                noenvio_ = "   " + noenvio_;                
                break;
            case 2:
                noenvio_ = "  " + noenvio_;                
                break; 
            case 3:
                noenvio_ = " " + noenvio_;                
                break; 
        }
        
        
        primerRegistro = literal1B+" "+literal2Plan+" "+noenvio_+" " +"                        " +ano + mmes + ddia + creditoTotalPlanilla_char +"  "+ empleadosNo;
        
        primerRegistaa = literal1B+" "+literal2Plan+"  "+noenvio+" " +"123456789012345678901234" +ano + mmes + ddia + "123456789012345" + Integer.toString(noEmpleados);
        
        
        crearFilePrn(primerRegistro);
        
    }
    
    /**
     *
     * Arma Sub-siguientes registros en el prn.
     * 
     *
     */     
    
    public void armaSiguientesRegistros(){
        
        
        String siguienteRegistro;
        String siguienteRegistaa;
        String mmes;
        String ddia; 
        String corr;
        String des;
        String noenvio_;
        int len;
        
        // String totalMontoPlanilla = sumaMonto();
        if (mes.length() ==1){
            mmes = " "+mes;            
        }
        else{
            mmes = mes;
        }
        
        if (dia.length() ==1){
            ddia = " "+dia;
        }
        else{
            ddia = dia;
        }
        
        int rc =0;
        if (!cedulaDepurada.isEmpty()){           
           Iterator si = cedulaDepurada.iterator();
           Iterator sm = montoDepurado.iterator();
           Iterator sn = nombreEmpleados.iterator();
                      
           while(si.hasNext()){
               sm.hasNext();
               sn.hasNext();
               ++rc;           
               String cedula     = si.next().toString();
               String monto      = sm.next().toString();
               String name       = sn.next().toString();
               // Formatea a 4 el noEnvio
               noenvio_ = noenvio;
               len = noenvio_.length();
               switch (len){             
                   case 1:                       
                       noenvio_ = "   " + noenvio_;                
                       break;
                   case 2:
                       noenvio_ = "  " + noenvio_;                
                       break; 
                   case 3:
                       noenvio_ = " " + noenvio_;                
                       break; 
        }
               // Formatea el correlativo a 3 posicones
               corr = Integer.toString(rc);
               len = corr.length();
               switch (len){                   
                   case 1:                       
                       corr = "  " + corr;                
                       break;
                   case 2:
                       corr = " " + corr;                
                       break;         
               } 
               // Formatea el monto a 13 posicones
               len = monto.length();        
               switch (len){
                   case 1:
                       monto = "            " + monto;                
                       break;
                   case 2:
                       monto = "           " + monto;                
                       break;
                   case 3:
                       monto = "          " + monto;                
                       break;
                   case 4:                
                       monto = "         " + monto;
                       break;
                   case 5:
                       monto = "        " + monto;                
                       break;
                   case 6:
                       monto = "       " + monto;                
                       break;
                   case 7:
                       monto = "      " + monto;                
                       break;
                   case 8:
                      monto = "     " + monto;                
                      break;
                   case 9:
                      monto = "    " + monto;                
                      break;
                   case 10:
                      monto = "   " + monto;                
                      break;
                   case 11:
                      monto = "  " + monto;                
                      break;
                   case 12:
                      monto = " " + monto;                
                      break;          
                } 
               
               // Formatea campo Descripción de Planilla
               des = campoDescripcion;
               len = des.length();  
               if (len >29){
                   des = des.substring(0, 30);                   
               }
               switch (len){
                   case 10:
                       des =  des + "                    ";                       
                       break;
                   case 11:
                       des = des  + "                   ";                        
                       break;
                   case 12:
                       des = des  + "                  ";                       
                       break;
                   case 13:                
                       des = des  + "                 ";                       
                       break;
                   case 14:
                       des = des  + "                ";                       
                       break;
                   case 15:
                       des = des  + "               ";                       
                       break;
                   case 16:
                       des = des  + "              ";                       
                       break;
                   case 17:
                       des = des  + "             ";                       
                       break;
                   case 18:
                       des = des  + "            ";                                      
                       break;
                   case 19:
                       des = des  + "           ";                       
                       break;
                   case 20:
                       des = des  + "          ";                       
                       break;
                   case 21:
                       des = des  + "         ";                       
                       break;
                   case 22:
                       des = des  + "        ";                       
                       break;
                   case 23:
                       des = des  + "       ";                       
                       break;
                   case 24:
                       des = des  + "      ";                        
                       break;
                   case 25:
                       des = des  + "     ";                       
                       break;
                   case 26:
                       des = des  + "    ";                       
                       break;
                   case 27:
                       des = des  + "   ";                       
                       break;
                   case 28:
                       des = des  + "  ";                       
                       break;
                   case 29:
                       des = des  + " ";                       
                       break;
                } 
                       
               siguienteRegistro = literal1T+" "+literal2Plan+" "+noenvio_+cedula+"         "+  corr  + ano + mmes + ddia + monto +"     "+ des +" "+ name;            
               crearFilePrn(siguienteRegistro);
           }         
        }
        
        
    }
    
    
    
    /**
     *
     * Recorre lista montoEmpleados y la depura a formato solicitado x BAC.
     * 
     * Llama a método privado para depurar el monto recuperado de la lista.
     * 
     *
     */     
    
    public void depuraMontos(){
        
        int rc =0;
        int lon;
        int lonA;        
        entero_acumulado  =0;
        decimal_acumulado =0;
        
        
        String montoParseado_;
         
        if (!montoEmpleados.isEmpty()){           
           Iterator s = montoEmpleados.iterator();           
           
           while(s.hasNext()){
               ++rc;
               String cadena = s.next().toString();
               //if (rc ==3 || rc == 33 || rc == 36){//quitar este if
                   montoParseado_ = parseaMonto(cadena);               
                   montoDepurado.add(montoParseado_);                    
               //}
                     
           }         
        }
        String dec_char = Integer.toString(decimal_acumulado);
        lon = dec_char.length();       
        
        if (lon == 1){ // Decimales solo son 1
            System.out.println("1 decimal");
            String dec_dec    = dec_char;
            creditoTotalPlanilla_char = Integer.toString(entero_acumulado);
            creditoTotalPlanilla_char = creditoTotalPlanilla_char + "0" +dec_dec;            
        }
        if (lon == 2){ // Decimales solo son dos
            System.out.println("2 decimales");
            String dec_dec    = dec_char;
            creditoTotalPlanilla_char = Integer.toString(entero_acumulado);
            creditoTotalPlanilla_char = creditoTotalPlanilla_char + dec_dec;            
        }
        if (lon >= 3){ // Decimales son 3 o mayor
            //System.out.println("Mas de 3 decimales");
            lonA = lon -2;
            String dec_entero = dec_char.substring(0, lonA);
            String dec_dec    = dec_char.substring(lonA, lon);        
            Integer dec_num = Integer.parseInt(dec_entero);     
            entero_acumulado = entero_acumulado + dec_num;    
            creditoTotalPlanilla_char = Integer.toString(entero_acumulado);
            creditoTotalPlanilla_char = creditoTotalPlanilla_char  + dec_dec;            
        }
                
        // Ahora que tengo el creditoTotalPlanilla_char, lo formateo a 13 posiciones
        lon = creditoTotalPlanilla_char.length();
        
        switch (lon){
            case 1:
                creditoTotalPlanilla_char = "            " + creditoTotalPlanilla_char;                
                break;
            case 2:
                creditoTotalPlanilla_char = "           " + creditoTotalPlanilla_char;                
                break;
            case 3:
                creditoTotalPlanilla_char = "          " + creditoTotalPlanilla_char;                
                break;
            case 4:                
                creditoTotalPlanilla_char = "         " + creditoTotalPlanilla_char;
                break;
            case 5:
                creditoTotalPlanilla_char = "        " + creditoTotalPlanilla_char;                
                break;
            case 6:
                creditoTotalPlanilla_char = "       " + creditoTotalPlanilla_char;                
                break;
            case 7:
                creditoTotalPlanilla_char = "      " + creditoTotalPlanilla_char;                
                break;
            case 8:
                creditoTotalPlanilla_char = "     " + creditoTotalPlanilla_char;                
                break;
            case 9:
                creditoTotalPlanilla_char = "    " + creditoTotalPlanilla_char;
                //creditoTotalPlanilla_char = "123456" + creditoTotalPlanilla_char;
                break;
            case 10:
                creditoTotalPlanilla_char = "   " + creditoTotalPlanilla_char;                
                break;
            case 11:
                creditoTotalPlanilla_char = "  " + creditoTotalPlanilla_char;                
                break;
            case 12:
                creditoTotalPlanilla_char = " " + creditoTotalPlanilla_char;                
                break;            
            
        } 
        
             
    
    }
    
    /**
     *
     * Recibe campo monto para ser validado y parseado.
     * 1. Valida si campo = "0", si es así, no hacemos nada
     * 2. Busca "." si no tiene, agrega dos 00 al contenido del campo y listo
     * 3. Si tiene "." validamos que despúes hayán dos digitos
     * 
     *
     * @param _monto
     * @return 
     */     
    
    public String parseaMonto(String _monto){        
        
        
        int pos;
        int lon;
        int lonA;
        int dif;
        String _montoParseado="";        
        
        pos = _monto.indexOf(".");
        
        lon = _monto.length();    
        
        if (!_monto.equals("0")){ // Monto viene en 0 desde la vista. Si viene 0.00 entonces hay que hacer otra validación
            if (pos == -1){ // monto no trae "." ==> agregamos los dos "00" al final
                _montoParseado = _monto + "00";                 
            }
            else{
                dif = lon - pos;
                if (dif ==3){
                    _montoParseado = _monto.replace(".","");                    
                }
                if (dif ==2){
                    _montoParseado = _monto.replace(".","") + "0";                    
                }            
                //System.out.println("Entero: " + entero_acumulado + " Decimal: "+ decimal_acumulado);
            }            
            lon = _montoParseado.length();
            lonA = lon -2;
            entero_ = Integer.parseInt(_montoParseado.substring(0, lonA));
            decimal_ = Integer.parseInt(_montoParseado.substring(lonA, lon));
            entero_acumulado = entero_acumulado + entero_;
            decimal_acumulado = decimal_acumulado + decimal_;
        }
        else{
            _montoParseado = "0"; // si monto = 0 desde la vista            
        }            
        
        //System.out.println("Monto: "+_monto +" Long: "+_monto.length()+" Pos: " + pos);
        
        
        return _montoParseado;
        
        
    
    }
    
    
    
    /**
     *
     * Recorre lista cedulaEmpleados y la depura a formato solicitado x BAC.
     * Tambien separa los campos de valor
     * 
     *
     */     
    
    public void depuraCedula(){
        
        int rc =0; 
         
        if (!cedulaEmpleados.isEmpty()){           
           Iterator s = cedulaEmpleados.iterator();           
           
           while(s.hasNext()){
               ++rc;
               String cadena = s.next().toString(); 
               cadena = cadena.replace("-","");                              
               cedulaDepurada.add(cadena);       
           }         
        }     
        
    
    }
    
    /**
     *
     * Formatea crédito total de la planilla.
     * Retorna el String.
     * 
     *
     * @return 
     */     
    
    public String formateaValorTotalPlanilla(){
        
        String _total= creditoTotalPlanilla_char;
        String _punto= ".";
        String _coma = ",";
        String _entero;
        String _decimal;
        int len;
        
        
        _total = _total.replace(" ","");
        
        len = _total.length();
        
        
        
        //System.out.println("Longitud Total:" + len);
        switch (len){
            case 3:
                _entero  = _total.substring(0,len-2) + _punto;
                _decimal = _total.substring(len-2, len);
                _total = _entero + _decimal;             
                break;
            case 4:
                _entero  = _total.substring(0,len-2) + _punto;
                _decimal = _total.substring(len-2, len);
                _total = _entero + _decimal;             
                break;
            case 5:
                _entero  = _total.substring(0,len-2) + _punto;
                _decimal = _total.substring(len-2, len);
                _total = _entero + _decimal;             
                break;
            case 6:
                _entero  = _total.substring(0,len-2) + _punto;
                _decimal = _total.substring(len-2, len);
                _total = _entero + _decimal;
                
                len = _total.length();
                
                _entero  = _total.substring(0 , len-6) + _coma;
                _decimal = _total.substring(len-6, len);
                _total = _entero + _decimal;                
                break;
            case 7:
                _entero  = _total.substring(0,len-2) + _punto;
                _decimal = _total.substring(len-2, len);
                _total = _entero + _decimal;
                
                len = _total.length();
                
                _entero  = _total.substring(0 , len-6) + _coma;
                _decimal = _total.substring(len-6, len);
                _total = _entero + _decimal;                
                break;
            case 8:
                _entero  = _total.substring(0,len-2) + _punto;
                _decimal = _total.substring(len-2, len);
                _total = _entero + _decimal;
                
                len = _total.length();
                
                _entero  = _total.substring(0 , len-6) + _coma;
                _decimal = _total.substring(len-6, len);
                _total = _entero + _decimal;                
                break;
            case 9:
                _entero  = _total.substring(0,len-2) + _punto;
                _decimal = _total.substring(len-2, len);
                _total = _entero + _decimal;
                
                len = _total.length();
                
                _entero  = _total.substring(0 , len-6) + _coma;
                _decimal = _total.substring(len-6, len);
                _total = _entero + _decimal;  
                
                len = _total.length();
                
                _entero  = _total.substring(0 , len-10) + _coma;
                _decimal = _total.substring(len-10, len);
                _total = _entero + _decimal;
                break;
            case 10:
                _entero  = _total.substring(0,len-2) + _punto;
                _decimal = _total.substring(len-2, len);
                _total = _entero + _decimal;
                
                len = _total.length();
                
                _entero  = _total.substring(0 , len-6) + _coma;
                _decimal = _total.substring(len-6, len);
                _total = _entero + _decimal;  
                
                len = _total.length();
                
                _entero  = _total.substring(0 , len-10) + _coma;
                _decimal = _total.substring(len-10, len);
                _total = _entero + _decimal;
                break;
            case 11:
                _entero  = _total.substring(0,len-2) + _punto;
                _decimal = _total.substring(len-2, len);
                _total = _entero + _decimal;
                
                len = _total.length();
                
                _entero  = _total.substring(0 , len-6) + _coma;
                _decimal = _total.substring(len-6, len);
                _total = _entero + _decimal;  
                
                len = _total.length();
                
                _entero  = _total.substring(0 , len-10) + _coma;
                _decimal = _total.substring(len-10, len);
                _total = _entero + _decimal;
                break;
            
        } 
        
       // System.out.println("Credito:" + _total);
        return _total;
        
    
    }
    
    
    
    /**
     *
     * Muestra contenido de tablas.
     * 
     *
     */     
    
    public void muestraContenidoTablas(){
        
        /* int rc =0;    
         
        if (!cedulaEmpleados.isEmpty()){
           System.out.println("----- Imprimiento los resultados CEDULA -----");
           Iterator se = cedulaEmpleados.iterator();           
           
           while(se.hasNext()){
               ++rc;
               String cadena = se.next().toString();
               System.out.println(cadena);        
           }         
        }        
        System.out.println("NoCedulas: " + rc);
        
        
        rc =0;
        if (!montoEmpleados.isEmpty()){
           System.out.println("----- Imprimiento los resultados MONTO -----");
           Iterator se = montoEmpleados.iterator();           
           
           while(se.hasNext()){
               ++rc;
               String cadena = se.next().toString();
               System.out.println(cadena);         
           }         
        }
        System.out.println("NoMontos: " + rc);
        */
        
        //System.out.println("Entero: " + entero_acumulado + " Decimal: "+ decimal_acumulado);
        //System.out.println("TOTAL PLANILLA : " + creditoTotalPlanilla_char );
        /* */
      /*  int rc =0;
        if (!cedulaDepurada.isEmpty()){
           System.out.println("----- Imprimiento los resultados DEPURADOS -----");
           Iterator si = cedulaDepurada.iterator();
           Iterator sm = montoDepurado.iterator();
           Iterator sn = nombreEmpleados.iterator();
           //++rc;
           
           while(si.hasNext()){
               sm.hasNext();
               sn.hasNext();
               ++rc;           
               String cedula = si.next().toString();
               String monto  = sm.next().toString();
               String name  = sn.next().toString();
               System.out.println(cedula + " "+ monto + " " + name);           
           }         
        }
        System.out.println("Total Empleados Depurados: " + rc);
        
        */
        
    
    }
    
    
    
    
}




