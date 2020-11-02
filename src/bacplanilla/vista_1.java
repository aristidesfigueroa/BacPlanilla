/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bacplanilla;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author Figueroa
 */
public final class vista_1 extends javax.swing.JFrame {
    
    String ruta = ("/Users/Figueroa/desktop/BacPlanilla/noenvio.txt");
    //String ruta = ("C:\\Users\\oortega\\Desktop\\BacPlanilla\\noenvio.txt");
    //String ruta = ("C:\\Users\\Bryan Maradiaga\\Desktop\\BacPlanilla\\noenvio.txt");
    

    /**
     * Creates new form vista_1
     * @throws java.io.IOException
     */
    public vista_1() throws IOException {
        initComponents();
        // Ubicar el formulario al centro de la pantalla
        this.setLocationRelativeTo(null);
        
        // Recuperar Fecha de Proceso y No-envio
        String fechaProceso;
        
        if (isValidaFile(ruta)){
            fechaProceso = recuperaFechaActual();
            this.lblFecha.setText(fechaProceso);  
            this.buttonActualizaNoenvio.setVisible(false);
            this.lblLinea.setVisible(false);
            this.lblLinea2.setVisible(false);
            this.txtNoenvio.setVisible(false);
            this.buttonCancel.setVisible(false);
            this.lblProcesando.setVisible(false);
            String no_envio = leerNoEnvio(ruta);
            this.lblNoenvio.setText(no_envio);            
        }
        else{
            System.out.println(")99999");
            JOptionPane.showMessageDialog(null,"Archivo noenvio.txt en carpeta\nBacPlanilla del Escritorio No existe, \nreportar al administrador del sistema.","Alerta",JOptionPane.ERROR_MESSAGE);             
            System.exit(0);
        }
        
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        boxCodigoPlanilla = new javax.swing.JComboBox();
        boxAno = new javax.swing.JComboBox();
        boxMes = new javax.swing.JComboBox();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        textDescripcion = new javax.swing.JTextField();
        buttonProcesar = new javax.swing.JButton();
        buttonCancelar = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        lblFecha = new javax.swing.JLabel();
        lblNoenvio = new javax.swing.JLabel();
        buttonActualizaNoenvio = new javax.swing.JButton();
        lblLinea = new javax.swing.JLabel();
        lblLinea2 = new javax.swing.JLabel();
        txtNoenvio = new javax.swing.JTextField();
        buttonCancel = new javax.swing.JButton();
        buttonNoenvio = new javax.swing.JButton();
        lblProcesando = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("MÓDULO PLANILLAS PANI");
        setResizable(false);

        jLabel1.setFont(new java.awt.Font("Apple Braille", 1, 18)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(226, 0, 0));
        jLabel1.setText("-- GENERACIÓN ARCHIVO PLANILLA BAC CREDOMATIC --");

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("Ingrese la Información solicitada"));
        jPanel1.setForeground(new java.awt.Color(255, 255, 255));
        jPanel1.setToolTipText("");

        jLabel2.setFont(new java.awt.Font("Apple Braille", 0, 14)); // NOI18N
        jLabel2.setText("Código de Planilla");

        boxCodigoPlanilla.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "01 - Mensual permanentes", "02 - Vacaciones", "03 - 13 avo mes permanentes", "04 - 14 avo mes permanentes", "05 - Bono Marzo", "06 - 14 avo mes contratos", "07", "08 - Mensual Contratos", "09", "10", "11 - Bono Morazán permanentes", "12 - Bono Morazán contrato", "13 - Complementaria -1 ", "14 - Otras 1", "15 - Otras 2", "16 - Otras 3" }));
        boxCodigoPlanilla.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                boxCodigoPlanillaActionPerformed(evt);
            }
        });

        boxAno.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "2017", "2018", "2019", "2020", "2021", "2022", "2023", "2024", "2025", "2026", "2027" }));

        boxMes.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Enero", "Febrero", "Marzo", "Abril", "Mayo", "Junio", "Julio", "Agosto", "Septiembre", "Octubre", "Noviembre", "Diciembre" }));

        jLabel3.setFont(new java.awt.Font("Apple Braille", 0, 14)); // NOI18N
        jLabel3.setText("Año");

        jLabel4.setFont(new java.awt.Font("Apple Braille", 0, 14)); // NOI18N
        jLabel4.setText("Mes");

        jLabel5.setFont(new java.awt.Font("Apple Braille", 0, 14)); // NOI18N
        jLabel5.setText("Descripción de Planilla");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel5)
                            .addComponent(textDescripcion, javax.swing.GroupLayout.PREFERRED_SIZE, 303, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2)
                            .addComponent(jLabel3)
                            .addComponent(jLabel4))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 34, Short.MAX_VALUE)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(boxAno, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(boxCodigoPlanilla, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(boxMes, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(27, 27, 27))))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(boxCodigoPlanilla, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(boxAno, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(boxMes, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel5)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(textDescripcion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(45, Short.MAX_VALUE))
        );

        buttonProcesar.setForeground(new java.awt.Color(204, 0, 51));
        buttonProcesar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/001-flechas.png"))); // NOI18N
        buttonProcesar.setText("GENERAR ARCHIVO");
        buttonProcesar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        buttonProcesar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonProcesarActionPerformed(evt);
            }
        });

        buttonCancelar.setForeground(new java.awt.Color(204, 0, 0));
        buttonCancelar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/002-cerrar.png"))); // NOI18N
        buttonCancelar.setText("SALIR");
        buttonCancelar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        buttonCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonCancelarActionPerformed(evt);
            }
        });

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder("Datos de Proceso"));

        jLabel6.setText("Fecha:");

        jLabel7.setText("No-Envío:");

        lblFecha.setText("2017-10-11");

        lblNoenvio.setText("222");

        buttonActualizaNoenvio.setForeground(new java.awt.Color(204, 0, 51));
        buttonActualizaNoenvio.setText("Actualiza No-Envío");
        buttonActualizaNoenvio.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        buttonActualizaNoenvio.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonActualizaNoenvioActionPerformed(evt);
            }
        });

        lblLinea.setText("-------------------------");

        lblLinea2.setText("Ingrese nuevo No-Envío");

        buttonCancel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/002-cerrar.png"))); // NOI18N
        buttonCancel.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        buttonCancel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonCancelActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(34, 34, 34)
                        .addComponent(lblLinea2))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(64, 64, 64)
                        .addComponent(txtNoenvio, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(lblLinea, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(jPanel2Layout.createSequentialGroup()
                                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jLabel6)
                                        .addComponent(jLabel7))
                                    .addGap(28, 28, 28)
                                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(lblFecha, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(lblNoenvio, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE))))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(buttonActualizaNoenvio, javax.swing.GroupLayout.PREFERRED_SIZE, 152, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(buttonCancel)))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(lblFecha))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(lblNoenvio, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(lblLinea)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblLinea2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(txtNoenvio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(buttonCancel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(buttonActualizaNoenvio, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        buttonNoenvio.setForeground(new java.awt.Color(204, 0, 51));
        buttonNoenvio.setText("Cambiar No-Envío");
        buttonNoenvio.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        buttonNoenvio.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonNoenvioActionPerformed(evt);
            }
        });

        lblProcesando.setFont(new java.awt.Font("Lucida Grande", 0, 16)); // NOI18N
        lblProcesando.setForeground(new java.awt.Color(226, 0, 0));
        lblProcesando.setText("Procesando Planilla, Aguarde por favor.........");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(44, 44, 44)
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(76, 76, 76)
                        .addComponent(buttonNoenvio, javax.swing.GroupLayout.PREFERRED_SIZE, 166, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 73, Short.MAX_VALUE)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(60, 60, 60))
            .addGroup(layout.createSequentialGroup()
                .addGap(222, 222, 222)
                .addComponent(jLabel1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(buttonProcesar)
                        .addGap(94, 94, 94)
                        .addComponent(buttonCancelar)
                        .addGap(124, 124, 124))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(lblProcesando)
                        .addGap(239, 239, 239))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(37, 37, 37)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(44, 44, 44)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(buttonProcesar)
                            .addComponent(buttonCancelar)))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(buttonNoenvio)))
                .addGap(18, 18, 18)
                .addComponent(lblProcesando)
                .addContainerGap(34, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void buttonProcesarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonProcesarActionPerformed
        
        /* Desactivar campos de actualización de No-Envío x si quedaron abiertos
           al momento de Procesar la Planilla
        */
        
        //jPanel2.setVisible(false);
        this.buttonActualizaNoenvio.setVisible(false);
        this.buttonCancel.setVisible(false);
        this.lblLinea.setVisible(false);
        this.lblLinea2.setVisible(false);        
        this.txtNoenvio.setVisible(false);
        this.buttonNoenvio.setVisible(false);
        this.lblProcesando.setVisible(true);
        this.buttonCancelar.setVisible(false);
        this.buttonProcesar.setVisible(false);
        this.paintAll(this.getGraphics());
                 
        
        // Código para Combo BOX - Planilla, Mes y Año 
        String CodigoPlanilla_box = (String)boxCodigoPlanilla.getSelectedItem();
        String ano_box = (String)boxAno.getSelectedItem();
        String mes_box = (String)boxMes.getSelectedItem();
        String codPlanilla_out;
        String ano_out = ano_box;
        String mes_out;       
        
        codPlanilla_out = CodigoPlanilla_box.substring(0,2);
        
        
        switch (mes_box){
            case "Enero":
                mes_out = "01";
                break;
            case "Febrero":
                mes_out = "02";
                break;
            case "Marzo":
                mes_out = "03";
                break;
            case "Abril":
                mes_out = "04";
                break;
            case "Mayo":
                mes_out = "05";
                break;
            case "Junio":
                mes_out = "06";
                break;
            case "Julio":
                mes_out = "07";
                break;
            case "Agosto":
                mes_out = "08";
                break;
            case "Septiembre":
                mes_out = "09";
                break;
            case "Octubre":
                mes_out = "10";
                break;
            case "Noviembre":
                mes_out = "11";
                break;
            case "Diciembre":
                mes_out = "12";
                break;   
            default:
                mes_out = "??";
        }
        
        // Código capturar y validar la Descripción de Planilla
        String  descripcion_out;
        
        descripcion_out = textDescripcion.getText();
        
        if (textDescripcion.getText().equals("")){
            //this.buttonNoenvio.setVisible(true);
            this.lblProcesando.setVisible(false); 
            this.buttonNoenvio.setVisible(true);
            this.buttonCancelar.setVisible(true);
            this.buttonProcesar.setVisible(true);
            JOptionPane.showMessageDialog(null,"Llenar campo Descripción de Planilla", "Alerta",JOptionPane.ERROR_MESSAGE);
        }
        else{
            System.out.println("Aqui paso");  
            
                         
             if (llamaIniciarGeneracion(codPlanilla_out,ano_out,mes_out,descripcion_out)){
                 this.lblProcesando.setVisible(false);                 
                 this.textDescripcion.setText("");
                                  
             }
             else{
                 //System.out.println("fallo proceso");
                 this.lblProcesando.setVisible(false);
                 this.buttonNoenvio.setVisible(true);
                 this.buttonCancelar.setVisible(true);
                 this.buttonProcesar.setVisible(true);
             }        
            
            
        }     
        
        
        
    }//GEN-LAST:event_buttonProcesarActionPerformed

    private void buttonCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonCancelarActionPerformed
        // TODO add your handling code here:
        System.exit(0);
    }//GEN-LAST:event_buttonCancelarActionPerformed

    private void buttonNoenvioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonNoenvioActionPerformed
        
        this.txtNoenvio.setText("");
        this.buttonNoenvio.setVisible(false);
        this.buttonActualizaNoenvio.setVisible(true);
        this.lblLinea.setVisible(true);
        this.lblLinea2.setVisible(true);
        this.txtNoenvio.setVisible(true);
        this.buttonCancel.setVisible(true);
        
        txtNoenvio.addKeyListener(new KeyAdapter()
        {
          @Override
          public void keyTyped(KeyEvent e)
            {
              char caracter = e.getKeyChar();

             // Verificar si la tecla pulsada no es un digito
                if(((caracter < '0') ||
                    (caracter > '9')) &&
                    (caracter != '\b' /*corresponde a BACK_SPACE*/))
                {
                      e.consume();  // ignorar el evento de teclado
                }
            }
        });
    }//GEN-LAST:event_buttonNoenvioActionPerformed

    private void buttonActualizaNoenvioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonActualizaNoenvioActionPerformed
        
        String noEnvio_out = txtNoenvio.getText();        
        
        if (txtNoenvio.getText().equals("")){
            JOptionPane.showMessageDialog(null,"Ingresar nuevo No-Envió", "Alerta",JOptionPane.ERROR_MESSAGE);
        }
        else{
            this.buttonNoenvio.setVisible(true);
            this.buttonActualizaNoenvio.setVisible(false);
            this.lblLinea.setVisible(false);
            this.lblLinea2.setVisible(false);
            this.txtNoenvio.setVisible(false);
            this.buttonCancel.setVisible(false);
            
            this.lblNoenvio.setText(noEnvio_out);
            int lastNoEnvio = Integer.parseInt(noEnvio_out);
            lastNoEnvio = lastNoEnvio -1;        
            noEnvio_out = Integer.toString(lastNoEnvio);
            
            try {
                actualizaNoEnvio(ruta,noEnvio_out);
            } catch (IOException ex) {
                Logger.getLogger(vista_1.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }//GEN-LAST:event_buttonActualizaNoenvioActionPerformed

    private void buttonCancelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonCancelActionPerformed
        // TODO add your handling code here:
            this.buttonNoenvio.setVisible(true);
            this.buttonActualizaNoenvio.setVisible(false);
            this.lblLinea.setVisible(false);
            this.lblLinea2.setVisible(false);
            this.txtNoenvio.setVisible(false);
            this.buttonCancel.setVisible(false);
    }//GEN-LAST:event_buttonCancelActionPerformed

    private void boxCodigoPlanillaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_boxCodigoPlanillaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_boxCodigoPlanillaActionPerformed

    /**
     *
     * Instancia clase IniciarGeneración y llama a método Solicitar parámetros.
     * 
     
     * @return 
     */   
    private  boolean llamaIniciarGeneracion(String codPlanilla, String ano_out, String mes_out, String descripcion_out){
        
        String [] variables = new String [] {codPlanilla,ano_out,mes_out,descripcion_out};  
        boolean respuesta;
            
            IniciaGeneracion iniciar = new IniciaGeneracion();
            try {                
                respuesta = iniciar.solicitaParametros(variables);
                return respuesta;
            } catch (IOException ex) {
                Logger.getLogger(vista_1.class.getName()).log(Level.SEVERE, null, ex); 
                return false;
            }       
        
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
    private  String recuperaFechaActual(){
        
        Date date = new Date();
        
        DateFormat fecha = new SimpleDateFormat("yyyy-MM-dd");
        
        String fechaString = fecha.format(date);     
        
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
               System.out.println("Archivo noenvio.txt EXISTE");
               return true;
            }else{
                
                System.out.println("Archivo noenvio.txt NO EXISTEññññññ");
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
     * @param noenvio
     * @throws java.io.IOException
     */    
    private void actualizaNoEnvio(String ruta, String noenvio) throws IOException{ 
        
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
    
    
    
    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(vista_1.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                try {
                    new vista_1().setVisible(true);
                } catch (IOException ex) {
                    Logger.getLogger(vista_1.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox boxAno;
    private javax.swing.JComboBox boxCodigoPlanilla;
    private javax.swing.JComboBox boxMes;
    private javax.swing.JButton buttonActualizaNoenvio;
    private javax.swing.JButton buttonCancel;
    private javax.swing.JButton buttonCancelar;
    private javax.swing.JButton buttonNoenvio;
    private javax.swing.JButton buttonProcesar;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JLabel lblFecha;
    private javax.swing.JLabel lblLinea;
    private javax.swing.JLabel lblLinea2;
    private javax.swing.JLabel lblNoenvio;
    private javax.swing.JLabel lblProcesando;
    private javax.swing.JTextField textDescripcion;
    private javax.swing.JTextField txtNoenvio;
    // End of variables declaration//GEN-END:variables
}