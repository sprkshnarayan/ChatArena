/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package group_on_package;

import static group_on_package.Client1GUI.socket;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author SURAJ PRAKASH
 */
public class StatusFrame extends javax.swing.JFrame {
  private Socket socc;
  private DataOutputStream dos;
  private String username;
    /**
     * Creates new form StatusFrame
     */
    public StatusFrame() {
        initComponents();
    }
    public void cons(Socket soc,DataOutputStream dos,String userString){
        socc = soc;
        this.dos = dos; 
        username  = userString;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        StutusUploadFeild = new javax.swing.JTextField();
        StatusUpload = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        StatusUpload.setText("Upload");
        StatusUpload.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                StatusUploadActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(StutusUploadFeild)
                .addContainerGap())
            .addGroup(layout.createSequentialGroup()
                .addGap(73, 73, 73)
                .addComponent(StatusUpload)
                .addContainerGap(80, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(StutusUploadFeild, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(StatusUpload))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void StatusUploadActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_StatusUploadActionPerformed
        // TODO add your handling code here:
       String st =  StutusUploadFeild.getText();
       if(st!=null){
           
           try {
               dos.writeUTF("UPLOAD_STATUS");
               dos.writeUTF(st);
               dos.writeUTF(username);
               
           } catch (IOException ex) {
               Logger.getLogger(StatusFrame.class.getName()).log(Level.SEVERE, null, ex);
           }
           
               
           
       }
       dispose();
    }//GEN-LAST:event_StatusUploadActionPerformed

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
            java.util.logging.Logger.getLogger(StatusFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(StatusFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(StatusFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(StatusFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new StatusFrame().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton StatusUpload;
    private javax.swing.JTextField StutusUploadFeild;
    // End of variables declaration//GEN-END:variables
}
