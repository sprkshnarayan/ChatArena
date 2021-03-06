/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package group_on_package;

import static group_on_package.Client1GUI.dos;
import static group_on_package.Client1GUI.f;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;

/**
 *
 * @author SURAJ PRAKASH
 */
public class UploadProfilePic extends javax.swing.JFrame {

    private String file_location;
    private FileInputStream fis = null;
    private BufferedInputStream bis = null;
    private OutputStream os = null;
    private FileOutputStream fos = null;
    private BufferedOutputStream bos = null;
    private File f;
    private Socket socket;
    private DataOutputStream dos;
    private String username;

    /**
     * Creates new form UploadProfilePic
     */
    public UploadProfilePic() {
        initComponents();
    }

    public void ini(Socket socc, DataOutputStream dos, String username) {
        this.socket = socc;
        this.dos = dos;
        this.username = username;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jTextFieldBrowsedFile = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        ProfilePic = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jButton1.setText("Browse");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setText("upload");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jTextFieldBrowsedFile, javax.swing.GroupLayout.PREFERRED_SIZE, 198, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton2)
                .addContainerGap(53, Short.MAX_VALUE))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(ProfilePic, javax.swing.GroupLayout.DEFAULT_SIZE, 405, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(244, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextFieldBrowsedFile, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton1)
                    .addComponent(jButton2))
                .addContainerGap())
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addComponent(ProfilePic, javax.swing.GroupLayout.PREFERRED_SIZE, 231, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 47, Short.MAX_VALUE)))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:

        JFileChooser chooser = new JFileChooser();
        chooser.showOpenDialog(ProfilePic);
        f = chooser.getSelectedFile();
        String st = f.getAbsolutePath();
        jTextFieldBrowsedFile.setText(st);
        ImageIcon imgThisImg = new ImageIcon(st);
        ProfilePic.setIcon(imgThisImg);
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:

        try {
            // TODO add your handling code here:
            dos.writeUTF("PROFILE_PIC_COMING");
            dos.writeUTF(username);
            String ssss = jTextFieldBrowsedFile.getText();
            int i;
            for( i=0;i<ssss.length();++i){
                if(ssss.charAt(i)=='.'){
                    break;
                }
            }
            String extension = ssss.substring(i, ssss.length());
            dos.writeUTF(extension);
            

        } catch (IOException ex) {
            Logger.getLogger(Client1GUI.class.getName()).log(Level.SEVERE, null, ex);
        }
        file_location = jTextFieldBrowsedFile.getText();
        
        byte[] mybytearray = new byte[(int) f.length()];
        try {

            fis = new FileInputStream(f);

        } catch (FileNotFoundException ex) {
            Logger.getLogger(Client1GUI.class.getName()).log(Level.SEVERE, null, ex);
        }
        bis = new BufferedInputStream(fis);
        try {

            bis.read(mybytearray, 0, mybytearray.length);

        } catch (IOException ex) {
            Logger.getLogger(Client1GUI.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {

            os = socket.getOutputStream();

        } catch (IOException ex) {
            Logger.getLogger(Client1GUI.class.getName()).log(Level.SEVERE, null, ex);
        }

        System.out.println("Sending  ");
        try {
            os.write(mybytearray, 0, mybytearray.length);
        } catch (IOException ex) {
            Logger.getLogger(Client1GUI.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            os.flush();
        } catch (IOException ex) {
            Logger.getLogger(Client1GUI.class.getName()).log(Level.SEVERE, null, ex);
        }
        System.out.println("Done.");

    }//GEN-LAST:event_jButton2ActionPerformed

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
            java.util.logging.Logger.getLogger(UploadProfilePic.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(UploadProfilePic.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(UploadProfilePic.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(UploadProfilePic.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new UploadProfilePic().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel ProfilePic;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JTextField jTextFieldBrowsedFile;
    // End of variables declaration//GEN-END:variables
}
