
package acelerometro;

import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.comm.*;
import javax.swing.JOptionPane;
import javax.swing.Timer;


public class frmAcelerometro extends javax.swing.JFrame 
{

    private CommPortIdentifier idPort;
    private SerialPort puertoSerial;
    public OutputStream salida;
    public InputStream entrada;
    private String nombre;
    public int  nBytes;
    int contador;
    public String dato;
    StringBuilder palabra;
   
    
    
   
    
 Timer temporizador =  new Timer(500,new ActionListener()    
{
    public void actionPerformed(ActionEvent e)
    {
        try 
        {
            salida.write(1);
            
        } 
        catch( IOException a ) {}
         byte[] bufferLectura = new byte[13];
      
        try
        {
          while( entrada.available() > 0 )
          {
            nBytes = entrada.read( bufferLectura );
           dato = new String (bufferLectura).trim();
            
            if (dato.substring(0,1).equals("X")  &&  dato.indexOf("Y")   >  dato.indexOf("X")  &&  dato.indexOf("Z")   >  dato.indexOf("Y")  &&   dato.indexOf("T")  >  dato.indexOf("Z"))
            { 
                txtx.setText(dato.substring(1,dato.indexOf("Y")));
                txty.setText(dato.substring(5,dato.indexOf("Z")));
                txtz.setText(dato.substring(9,dato.indexOf("T")));
            }
        
          }
          System.out.print( "*"+ nBytes + "*\n" );
          
         
        
        } 
        catch( IOException b ) {}  

    }
    }
 );
    
    
    
    public frmAcelerometro() 
    {
        initComponents();
    }

    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel5 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        txtz = new javax.swing.JTextField();
        txtx = new javax.swing.JTextField();
        txty = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        btnIniciar = new javax.swing.JButton();
        btnRecibir = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(51, 0, 255));
        jLabel1.setText("ACELEROMETRO ");
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(44, 23, -1, -1));

        txtz.setFont(new java.awt.Font("Tahoma", 1, 48)); // NOI18N
        txtz.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtzActionPerformed(evt);
            }
        });
        getContentPane().add(txtz, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 370, 340, 90));

        txtx.setFont(new java.awt.Font("Tahoma", 1, 48)); // NOI18N
        txtx.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtxActionPerformed(evt);
            }
        });
        getContentPane().add(txtx, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 120, 330, 90));

        txty.setFont(new java.awt.Font("Tahoma", 1, 48)); // NOI18N
        getContentPane().add(txty, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 240, 340, 90));

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 36)); // NOI18N
        jLabel2.setText("Y");
        getContentPane().add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 260, -1, -1));

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 36)); // NOI18N
        jLabel3.setText("X");
        getContentPane().add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 140, -1, -1));

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 36)); // NOI18N
        jLabel4.setText("Z");
        getContentPane().add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 390, 30, -1));

        btnIniciar.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        btnIniciar.setText("INICIAR");
        btnIniciar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnIniciarActionPerformed(evt);
            }
        });
        getContentPane().add(btnIniciar, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 10, -1, -1));

        btnRecibir.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        btnRecibir.setText("RECIBIR");
        btnRecibir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRecibirActionPerformed(evt);
            }
        });
        getContentPane().add(btnRecibir, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 50, -1, -1));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txtxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtxActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtxActionPerformed

    private void txtzActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtzActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtzActionPerformed

    private void btnIniciarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnIniciarActionPerformed
       try
       {
            nombre= "COM5";//Este es el nombre del puerto del arduino debe ser cambiado seg?n corresponda
            idPort = CommPortIdentifier.getPortIdentifier(nombre);
            puertoSerial=(SerialPort) idPort.open("Comunicacion Serial", 2000);
            entrada = puertoSerial.getInputStream();
            salida=puertoSerial.getOutputStream();
            System.out.println("Puerto " + nombre + " iniciado ...");
     
            try
            {//los valores que se encuentran a continuaci?n son los parámetros de la comunicaci?n serial, deben ser los mismos en el arduino
                puertoSerial.setSerialPortParams( 9600,
                SerialPort.DATABITS_8,
                SerialPort.STOPBITS_1,
                SerialPort.PARITY_NONE );
            } 
            catch( UnsupportedCommOperationException e ) {}
    
        } 
    catch (Exception e) 
    {
            System.out.println("Error en iniciarPuerto() \n"+e);
    } 
       
       
// TODO add your handling code here:
    }//GEN-LAST:event_btnIniciarActionPerformed

    private void btnRecibirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRecibirActionPerformed
temporizador.start(); 
                // TODO add your handling code here:
    }//GEN-LAST:event_btnRecibirActionPerformed

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
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(frmAcelerometro.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(frmAcelerometro.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(frmAcelerometro.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(frmAcelerometro.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new frmAcelerometro().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnIniciar;
    private javax.swing.JButton btnRecibir;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JTextField txtx;
    private javax.swing.JTextField txty;
    private javax.swing.JTextField txtz;
    // End of variables declaration//GEN-END:variables
}
