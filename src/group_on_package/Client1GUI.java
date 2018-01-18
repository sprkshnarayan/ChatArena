/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package group_on_package;

//import static group_on_package.Client2GUI.dos;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPopupMenu;
import javax.swing.SwingUtilities;

/**
 *
 * @author SURAJ PRAKASH
 */
public class Client1GUI extends javax.swing.JFrame {

    final JPopupMenu pop = new JPopupMenu();
    final JPopupMenu pop2 = new JPopupMenu();

    static Socket socket;
    static DataOutputStream dos;
    static DataInputStream dis;
    static String st;
    static String username;
    static int i = 0;
    static String prevmess = "";
    static int pendingRequests = 0;
    static String adminInviting;
    static String invitationForGroup;
    static int pendingInvites = 0;

    static String file_location;
    static FileInputStream fis = null;
    static BufferedInputStream bis = null;
    static OutputStream os = null;
    static FileOutputStream fos = null;
    static BufferedOutputStream bos = null;
    static File f;
    static String userRequesting;
    static String requestingForGroup;
    private int startconv = 0;

    /**
     * Creates new form group_on_GUI
     */
    public Client1GUI() {
        initComponents();
        addpopup();
        // fileoperations();

    }

    private void fileoperations() {

        File f = new File("F:\\" + username + ".txt");

        if (f.exists() && !f.isDirectory()) {

            BufferedReader br = null;
            FileReader fr = null;

            try {
                fr = new FileReader("F:\\" + username + ".txt");

                br = new BufferedReader(fr);

                String sCurrentLine;

                while ((sCurrentLine = br.readLine()) != null) {
                    String temp = jTextAreaReceivedMessages.getText();
                    String newmessage = temp + sCurrentLine + "\n";
                    jTextAreaReceivedMessages.setText(newmessage);
                    System.out.println(sCurrentLine + " ");

                }
                br.close();
                fr.close();

            } catch (IOException e) {

                e.printStackTrace();

            }

        } else {
            PrintWriter writer = null;
            try {
                writer = new PrintWriter("F:\\" + username + ".txt", "UTF-8");

                writer.close();
            } catch (FileNotFoundException ex) {
                Logger.getLogger(Client1GUI.class.getName()).log(Level.SEVERE, null, ex);
            } catch (UnsupportedEncodingException ex) {
                Logger.getLogger(Client1GUI.class.getName()).log(Level.SEVERE, null, ex);
            } finally {
                writer.close();
            }
        }

    }

    public void settertrue() {
        jScrollPane3.setVisible(true);
    }

    public void setterfalse() {
        jScrollPane3.setVisible(false);

    }

    public void socc(Socket soc, DataInputStream dis, DataOutputStream dos, String user) {
        this.socket = soc;
        this.dos = dos;
        this.dis = dis;
        this.username = user;
    }

    public void addpopup2() {
        JMenuItem status = new JMenuItem("show status");
        pop2.add(status);

        status.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    // throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
                    // JOptionPane.showMessageDialog(null, "showed"+st);
                    GroupMembersFrame gmf = new GroupMembersFrame();
                    gmf.setVisible(true);

                    System.out.println("sending requests to provide group members");

                    dos.writeUTF("PROVIDE_GROUP_MEMBERS");
                    dos.writeUTF(st);//sending groupname
                    dos.writeUTF(username);

                } catch (IOException ex) {
                    Logger.getLogger(Client1GUI.class.getName()).log(Level.SEVERE, null, ex);
                }

            }
        });
    }

    public void addpopup() {
        JMenuItem show = new JMenuItem("group members");
        pop.add(show);

        show.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    // throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
                    // JOptionPane.showMessageDialog(null, "showed"+st);
                    GroupMembersFrame gmf = new GroupMembersFrame();
                    gmf.setVisible(true);

                    System.out.println("sending requests to provide group members");

                    dos.writeUTF("PROVIDE_GROUP_MEMBERS");
                    dos.writeUTF(st);//sending groupname
                    dos.writeUTF(username);
                } catch (IOException ex) {
                    Logger.getLogger(Client1GUI.class.getName()).log(Level.SEVERE, null, ex);
                }

            }
        });

    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jTextFieldSearch = new javax.swing.JTextField();
        jButtonStartConversation = new javax.swing.JButton();
        jButtonSearchUsers = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTextAreaReceivedMessages = new javax.swing.JTextArea();
        jSendButton = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTextAreaSendMessages = new javax.swing.JTextArea();
        jSeparator1 = new javax.swing.JSeparator();
        jButtonViewProfile = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jButtonLogout = new javax.swing.JButton();
        jButtonSearchGroups = new javax.swing.JButton();
        jButtonCreateGroup = new javax.swing.JButton();
        jTextFieldGroupName = new javax.swing.JTextField();
        jScrollPane4 = new javax.swing.JScrollPane();
        jListSearchResGroups = new javax.swing.JList<>();
        jLabel2 = new javax.swing.JLabel();
        jButtonSendInvites = new javax.swing.JButton();
        jBrowseButton = new javax.swing.JButton();
        jTextFieldBrowsedFile = new javax.swing.JTextField();
        jButtonSendFile = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();
        jScrollPane5 = new javax.swing.JScrollPane();
        jListSearchedRes = new javax.swing.JList<>();
        jScrollPane3 = new javax.swing.JScrollPane();
        jListUsersStatus = new javax.swing.JList<>();
        jLabel3 = new javax.swing.JLabel();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jButtonProfilePic = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosed(java.awt.event.WindowEvent evt) {
                formWindowClosed(evt);
            }
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
        });

        jButtonStartConversation.setText("start conversation");
        jButtonStartConversation.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonStartConversationActionPerformed(evt);
            }
        });

        jButtonSearchUsers.setText("search users");
        jButtonSearchUsers.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonSearchUsersActionPerformed(evt);
            }
        });

        jTextAreaReceivedMessages.setEditable(false);
        jTextAreaReceivedMessages.setColumns(20);
        jTextAreaReceivedMessages.setRows(5);
        jScrollPane1.setViewportView(jTextAreaReceivedMessages);

        jSendButton.setText("send");
        jSendButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jSendButtonActionPerformed(evt);
            }
        });

        jTextAreaSendMessages.setColumns(20);
        jTextAreaSendMessages.setRows(5);
        jScrollPane2.setViewportView(jTextAreaSendMessages);

        jSeparator1.setOrientation(javax.swing.SwingConstants.VERTICAL);

        jButtonViewProfile.setText("view profile");
        jButtonViewProfile.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonViewProfileActionPerformed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Sitka Heading", 0, 18)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 0, 0));

        jButtonLogout.setText("logout");
        jButtonLogout.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonLogoutActionPerformed(evt);
            }
        });

        jButtonSearchGroups.setText("search groups");
        jButtonSearchGroups.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonSearchGroupsActionPerformed(evt);
            }
        });

        jButtonCreateGroup.setText("create group");
        jButtonCreateGroup.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonCreateGroupActionPerformed(evt);
            }
        });

        jScrollPane4.setViewportView(jListSearchResGroups);

        jLabel2.setText("Your Groups");

        jButtonSendInvites.setText("send invites");
        jButtonSendInvites.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonSendInvitesActionPerformed(evt);
            }
        });

        jBrowseButton.setText("browse");
        jBrowseButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBrowseButtonActionPerformed(evt);
            }
        });

        jButtonSendFile.setText("send file");
        jButtonSendFile.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonSendFileActionPerformed(evt);
            }
        });

        jButton1.setText("join group");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jListSearchedRes.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        jListSearchedRes.setPreferredSize(new java.awt.Dimension(33, 80));
        jListSearchedRes.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jListSearchedResMouseClicked(evt);
            }
        });
        jListSearchedRes.addListSelectionListener(new javax.swing.event.ListSelectionListener() {
            public void valueChanged(javax.swing.event.ListSelectionEvent evt) {
                jListSearchedResValueChanged(evt);
            }
        });
        jScrollPane5.setViewportView(jListSearchedRes);

        jListUsersStatus.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        jScrollPane3.setViewportView(jListUsersStatus);

        jLabel3.setText("                        Received messages");

        jButton2.setText("upload status");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jButton3.setText("My Profile");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jButtonProfilePic.setText("profile pic");
        jButtonProfilePic.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonProfilePicActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 175, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(jTextFieldSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 175, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(117, 117, 117))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(37, 37, 37)
                                        .addComponent(jButtonSearchUsers, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(27, 27, 27)
                                        .addComponent(jButtonSearchGroups, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(48, 48, 48)
                                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 184, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                        .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(layout.createSequentialGroup()
                                                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(101, 101, 101)
                                                .addComponent(jButtonStartConversation)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(jTextFieldGroupName, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(52, 52, 52)
                                                .addComponent(jButtonLogout))
                                            .addComponent(jButtonViewProfile)
                                            .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addGroup(layout.createSequentialGroup()
                                                .addComponent(jTextFieldBrowsedFile, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(5, 5, 5)
                                                .addComponent(jBrowseButton)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                .addComponent(jButtonSendFile, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE))
                                            .addComponent(jButtonSendInvites)
                                            .addComponent(jButton2)))
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(214, 214, 214)
                                        .addComponent(jSendButton))
                                    .addGroup(layout.createSequentialGroup()
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 474, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(layout.createSequentialGroup()
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 474, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(105, 105, 105)
                                        .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 234, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jButtonCreateGroup)
                                .addGap(56, 56, 56)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jButtonProfilePic)
                                    .addComponent(jButton3))))
                        .addContainerGap())))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jTextFieldSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jButtonStartConversation, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jTextFieldGroupName, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jButtonLogout)))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(6, 6, 6)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jButtonCreateGroup)
                                    .addComponent(jButton3))
                                .addGap(7, 7, 7)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jButton1)
                                    .addComponent(jButtonProfilePic))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jButtonViewProfile)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jButtonSendInvites)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jButton2)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jButtonSendFile)
                                    .addComponent(jTextFieldBrowsedFile, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jBrowseButton))
                                .addGap(18, 18, 18)
                                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(7, 7, 7)
                                .addComponent(jButtonSearchUsers)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jButtonSearchGroups)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jScrollPane5, javax.swing.GroupLayout.DEFAULT_SIZE, 144, Short.MAX_VALUE)
                                    .addComponent(jScrollPane3))))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 117, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jSendButton))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 215, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(11, 11, 11))))
                    .addComponent(jSeparator1))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButtonStartConversationActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonStartConversationActionPerformed
        //startconv++;
        if (startconv == 0) {
           // fileoperations();
            Client1ReceiveThread receivingThread = new Client1ReceiveThread(socket, this);
            //Starting thread that will continuosly listen for any input from server
            receivingThread.start();
            jLabel1.setText(username);
        }
        startconv++;

    }//GEN-LAST:event_jButtonStartConversationActionPerformed

    private void jButtonSearchUsersActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonSearchUsersActionPerformed
        // TODO add your handling code here:
        // DefaultListModel dlm = new DefaultListModel();
        i = 1;

        String st = jTextFieldSearch.getText();
        String stt = "SQ#";
        try {
            dos.writeUTF(stt);
            dos.writeUTF(st);

        } catch (IOException ex) {
            Logger.getLogger(Client1GUI.class.getName()).log(Level.SEVERE, null, ex);
        }


    }//GEN-LAST:event_jButtonSearchUsersActionPerformed

    private void jSendButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jSendButtonActionPerformed
        // TODO add your handling code here:
        String newtext = jTextAreaSendMessages.getText();
        String msgToSend = newtext.substring(prevmess.length(), newtext.length());
        if (msgToSend.length() > 0 && msgToSend.charAt(0) == '\n') {

            String fg = msgToSend.substring(1, msgToSend.length());
            msgToSend = fg;

        }
        prevmess = newtext;

        if (msgToSend.equals("JOIN@" + invitationForGroup) && pendingInvites > 0) {
            System.out.println("sending message to join group " + msgToSend);
            try {
                dos.writeUTF("JOIN@");
                dos.writeUTF(username);
                dos.writeUTF(invitationForGroup);
                dos.writeUTF(adminInviting);
                pendingInvites--;
            } catch (IOException ex) {
                Logger.getLogger(Client1GUI.class.getName()).log(Level.SEVERE, null, ex);
            }

        } else if (msgToSend.equals("#YES") || msgToSend.equals("#NO") && pendingRequests > 0) {
            try {
                System.out.println("responding " + msgToSend + " to the request");
                dos.writeUTF("RESPONDING_TO_GROUP_JOIN_REQUEST");
                if (msgToSend.equals("#YES") || msgToSend.equals("\n#YES")) {
                    dos.writeUTF("YES");
                    dos.writeUTF(userRequesting);
                    dos.writeUTF(requestingForGroup);
                } else {
                    dos.writeUTF("NO");
                    dos.writeUTF(userRequesting);
                    dos.writeUTF(requestingForGroup);
                }

                pendingRequests--;
                System.out.println("pending requests with the admin is " + pendingRequests);
            } catch (IOException ex) {
                Logger.getLogger(Client1GUI.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {

            System.out.println("sending general message");
            //  System.out.println(st.length());
            System.out.println(st);
            if (st == null) {

            } else {

                msgToSend = st + "#" + username + "@" + msgToSend;
                if (i == 2) {
                    try {
                        dos.writeUTF("GRP_MES#");
                    } catch (IOException ex) {
                        Logger.getLogger(Client1GUI.class.getName()).log(Level.SEVERE, null, ex);
                    }

                }
                try {
                    dos.writeUTF(msgToSend);
                } catch (IOException ex) {
                    Logger.getLogger(Client1GUI.class.getName()).log(Level.SEVERE, null, ex);
                }

            }
        }
    }//GEN-LAST:event_jSendButtonActionPerformed

    private void jButtonLogoutActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonLogoutActionPerformed
        // TODO add your handling code here:

        try {
            // TODO add your handling code here:
            dos.writeUTF("LG#");
            dos.writeUTF(username);

        } catch (IOException ex) {
            // Logger.getLogger(Client2GUI.class.getName()).log(Level.SEVERE, null, ex);
        }

        try {
            FileWriter fw = new FileWriter("F:\\" + username + ".txt");
            String stt = jTextAreaReceivedMessages.getText();
            System.out.println("message written to the file was " + stt);
            fw.write(stt);
            fw.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        }

        dispose();
    }//GEN-LAST:event_jButtonLogoutActionPerformed

    private void jButtonCreateGroupActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonCreateGroupActionPerformed
        // TODO add your handling code here:
        String st = jTextFieldGroupName.getText();
        try {
            dos.writeUTF("GC#");
            dos.writeUTF(st);    // sending group name
            dos.writeUTF(username);//sending username

        } catch (IOException ex) {
            Logger.getLogger(Client1GUI.class.getName()).log(Level.SEVERE, null, ex);
        }

    }//GEN-LAST:event_jButtonCreateGroupActionPerformed

    private void jButtonSendInvitesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonSendInvitesActionPerformed
        // TODO add your handling code here:
        JframeSendInvites jsi = new JframeSendInvites();
        jsi.setVisible(true);
        jsi.ini(socket, dos, username);

    }//GEN-LAST:event_jButtonSendInvitesActionPerformed

    private void jButtonSearchGroupsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonSearchGroupsActionPerformed
        // TODO add your handling code here:

        i = 2;
        String st = jTextFieldSearch.getText();
        String stt = "SQforGROUPS#";
        try {
            dos.writeUTF(stt);
            dos.writeUTF(st);

        } catch (IOException ex) {
            Logger.getLogger(Client1GUI.class.getName()).log(Level.SEVERE, null, ex);
        }

    }//GEN-LAST:event_jButtonSearchGroupsActionPerformed

    private void jBrowseButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBrowseButtonActionPerformed
        // TODO add your handling code here:
        JFileChooser chooser = new JFileChooser();
        chooser.showOpenDialog(jLabel1);
        f = chooser.getSelectedFile();
        String st = f.getAbsolutePath();
        jTextFieldBrowsedFile.setText(st);


    }//GEN-LAST:event_jBrowseButtonActionPerformed

    private void jButtonSendFileActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonSendFileActionPerformed
        try {                                                
            try {
                // TODO add your handling code here:
                dos.writeUTF("FILE_COMING");
                if (i == 1) {
                    dos.writeUTF("FOR_USER");
                    dos.writeUTF(st);
                } else if (i == 2) {
                    dos.writeUTF("FOR_GROUP");
                    dos.writeUTF(st);
                    
                }
            } catch (IOException ex) {
                ex.printStackTrace();
            }
            file_location = jTextFieldBrowsedFile.getText();
            System.out.println("the actual size of file is "+f.length());
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
            
            System.out.println("Sending...");
            int i;
            for (i=0;i < file_location.length();++i){
                if(file_location.charAt(i)=='.'){
                    break;
                }
                
            }
            String format = file_location.substring(i,file_location.length());
            dos.writeUTF(format);
            
            
            
            try {
                os.write(mybytearray, 0, mybytearray.length);
            } catch (IOException ex) {
                Logger.getLogger(Client1GUI.class.getName()).log(Level.SEVERE, null, ex);
            }
            try {
                os.flush();
              //  bis.close();
                //os.close();
            } catch (IOException ex) {
                Logger.getLogger(Client1GUI.class.getName()).log(Level.SEVERE, null, ex);
            }
            System.out.println("Done.");
            
            
        } catch (IOException ex) {
            Logger.getLogger(Client1GUI.class.getName()).log(Level.SEVERE, null, ex);
        }


    }//GEN-LAST:event_jButtonSendFileActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        if (st !=null && i == 2) {
            try {
                dos.writeUTF("REQUEST_TO_JOIN_GROUP");
                dos.writeUTF(st);
                dos.writeUTF(username);
            } catch (IOException ex) {
                Logger.getLogger(Client1GUI.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

    }//GEN-LAST:event_jButton1ActionPerformed

    private void jListSearchedResValueChanged(javax.swing.event.ListSelectionEvent evt) {//GEN-FIRST:event_jListSearchedResValueChanged
        // TODO add your handling code here:
        st = jListSearchedRes.getSelectedValue();

    }//GEN-LAST:event_jListSearchedResValueChanged

    private void jListSearchedResMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jListSearchedResMouseClicked
        // TODO add your handling code here:

        jListSearchedRes.setSelectedIndex(jListSearchedRes.locationToIndex(evt.getPoint()));
        st = jListSearchedRes.getSelectedValue();
        int index = jListSearchedRes.getSelectedIndex();
        if (SwingUtilities.isRightMouseButton(evt) && jListSearchedRes.locationToIndex(evt.getPoint()) == index) {
            if (!jListSearchedRes.isSelectionEmpty()) {
                pop.show(jListSearchedRes, 60 + getX(), 60 + getY());
            }

        }
    }//GEN-LAST:event_jListSearchedResMouseClicked

    private void formWindowClosed(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosed
        // TODO add your handling code here:
        try {
            // TODO add your handling code here:

            System.out.println("sending logout event to server");
            dos.writeUTF("LG#");
            dos.writeUTF(username);

        } catch (IOException ex) {
            // Logger.getLogger(Client2GUI.class.getName()).log(Level.SEVERE, null, ex);
        }

    }//GEN-LAST:event_formWindowClosed

    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
        // TODO add your handling code here:
        try {
            // TODO add your handling code here:

            System.out.println("sending logout event to server");
            dos.writeUTF("LG#");
            dos.writeUTF(username);

        } catch (IOException ex) {
            // Logger.getLogger(Client2GUI.class.getName()).log(Level.SEVERE, null, ex);
        }

        try {
            FileWriter fw = new FileWriter("F:\\" + username + ".txt");
            String stt = jTextAreaReceivedMessages.getText();
            System.out.println("message written to the file was " + stt);
            fw.write(stt);
            fw.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }//GEN-LAST:event_formWindowClosing

    private void jButtonViewProfileActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonViewProfileActionPerformed
        // TODO add your handling code here:
        ProfilePage pp = new ProfilePage();
        pp.setVisible(true);

        if (st != null && i == 1) {
            try {
                dos.writeUTF("SHOW_PROFILE_QUERY");
                dos.writeUTF(st);
            } catch (IOException ex) {
                Logger.getLogger(Client1GUI.class.getName()).log(Level.SEVERE, null, ex);
            }

        }

    }//GEN-LAST:event_jButtonViewProfileActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:

        StatusFrame sf = new StatusFrame();
        sf.cons(socket, dos, username);
        sf.setVisible(true);

    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        // TODO add your handling code here:
        ProfilePage pp = new ProfilePage();
        pp.setVisible(true);
        ImageIcon img = new ImageIcon("C:\\Users\\SURAJ PRAKASH\\Desktop\\softablitz2k17\\placeholder.png");
        ProfilePage.jLabel2.setIcon(img);

        try {
           
            dos.writeUTF("SHOW_PROFILE_QUERY");
            dos.writeUTF(username);
            
        } catch (IOException ex) {
            Logger.getLogger(Client1GUI.class.getName()).log(Level.SEVERE, null, ex);
        }


    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButtonProfilePicActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonProfilePicActionPerformed
        // TODO add your handling code here:
        UploadProfilePic upp = new UploadProfilePic();
        upp.setVisible(true);
        upp.ini(socket, dos, username);
        

    }//GEN-LAST:event_jButtonProfilePicActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) throws IOException {
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
            java.util.logging.Logger.getLogger(Client1GUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Client1GUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Client1GUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Client1GUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Client1GUI().setVisible(true);
            }
        });

        // jTextField1.setText("fdf");
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jBrowseButton;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButtonCreateGroup;
    private javax.swing.JButton jButtonLogout;
    private javax.swing.JButton jButtonProfilePic;
    private javax.swing.JButton jButtonSearchGroups;
    private javax.swing.JButton jButtonSearchUsers;
    private javax.swing.JButton jButtonSendFile;
    private javax.swing.JButton jButtonSendInvites;
    private javax.swing.JButton jButtonStartConversation;
    private javax.swing.JButton jButtonViewProfile;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    public static javax.swing.JList<String> jListSearchResGroups;
    public static javax.swing.JList<String> jListSearchedRes;
    public static javax.swing.JList<String> jListUsersStatus;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JButton jSendButton;
    private javax.swing.JSeparator jSeparator1;
    public static javax.swing.JTextArea jTextAreaReceivedMessages;
    private javax.swing.JTextArea jTextAreaSendMessages;
    private javax.swing.JTextField jTextFieldBrowsedFile;
    private javax.swing.JTextField jTextFieldGroupName;
    public static javax.swing.JTextField jTextFieldSearch;
    // End of variables declaration//GEN-END:variables
}
