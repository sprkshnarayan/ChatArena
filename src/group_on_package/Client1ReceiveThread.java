package group_on_package;

import static group_on_package.ServerReceiveThread.FILE_SIZE;
import java.io.BufferedOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;

/**
 *
 * @author suraj
 */
public class Client1ReceiveThread extends Thread {

    Socket socket;
    Client1GUI cg;
    public final static int FILE_SIZE = 6022386;
    //  Thread t ;

    //  Map<Integer,Socket> map;
    public Client1ReceiveThread(Socket sock, Client1GUI obj) {
        // this.map = map;
        //   t = new Thread(this,"Demo thread");

        this.socket = sock;
        cg = obj;
        //  t.start();
    }

    public void run() {
        DataInputStream dis = null;
        //  DataOutputStream dos = null;

        try {
            Socket st = null;

            dis = new DataInputStream(socket.getInputStream());

            while (true) {
                String msg = dis.readUTF();
                if (msg.equals("SHOWING_PROFILE_QUERY")) {

                    
                    System.out.println("PROFILE PIC arrived at the clioent \n");
                   
                   // System.out.println("profile pic is for " + username);

                /*    byte[] mybytearray = new byte[FILE_SIZE];
                    InputStream in = socket.getInputStream();
                    int bytesread = in.read(mybytearray, 0, mybytearray.length);
                    int current = bytesread;*/
        
    
                    System.out.println("image fiel read by the client");
                    System.out.println("bytes read by the client is ");
                    
               
                    String extension ;

                   

                    
                    
                    String mobno = dis.readUTF();
                    String emailid = dis.readUTF();
                    String status = dis.readUTF();
                    String username = dis.readUTF();
                   // extension = dis.readUTF();
                    System.out.println("info of the users sre "+mobno+" "+emailid+" "+status+" "+username);
                    System.out.println("all infoo read by the client");
                  //  FileOutputStream fos = new FileOutputStream("F:\\SURAJ PARAPHERNELIA\\Pictures\\" + username + extension);
                  
                  //  BufferedOutputStream bos = new BufferedOutputStream(fos);
                   // bos.write(mybytearray, 0 , bytesread);
                    
//                    bos.flush();
//                     fos.close();
//                    bos.close();
//                   
                    
//                    System.out.println("profile pic received at the clien side");
                    String sttr = "F:\\SURAJ PARAPHERNELIA\\suraj.jpg";
//                     
//                    ImageIcon imgThisImg = new ImageIcon(sttr);
                   ImageIcon imgThisImg = new ImageIcon(sttr);
                    
                    ProfilePage.jLabel2.setIcon(imgThisImg);
                     System.out.println("profile pic set for "+username);
                   
                    ProfilePage.StatusFeild.setText(status);
                    ProfilePage.PhoneNo.setText(mobno);
                    ProfilePage.EmailId.setText(emailid);
                    ProfilePage.jLabel1.setText(username);
                    
                } else if (msg.equals("PROVIDING_GROUP_MEMBERS")) {
                    String grpmembers = dis.readUTF();
                    String[] members = grpmembers.split("#");
                    DefaultListModel DLM = new DefaultListModel();

                    for (int i = 1; i < members.length; ++i) {
                        DLM.addElement(members[i]);
                    }
                    GroupMembersFrame.jListShowGroupMembers.setModel(DLM);
                    System.out.println("group member list added");

                } else if (msg.equals("GROUP_INVITES")) {
                    String admin = dis.readUTF();
                    String groupname = dis.readUTF();
                    String sttt = Client1GUI.jTextAreaReceivedMessages.getText();
                    String res = sttt + "\n" + "INVITE to join group " + groupname + " BY " + admin + "  RESPOND with JOIN@GROUPNAME";
                    Client1GUI.jTextAreaReceivedMessages.setText(res);
                    Client1GUI.adminInviting = admin;
                    Client1GUI.invitationForGroup = groupname;
                    Client1GUI.pendingInvites++;
                } else if (msg.equals("REQUEST_TO_JOIN_GROUP")) {
                    String[] str = dis.readUTF().split("@");
                    String groupname = dis.readUTF();
                    Client1GUI.requestingForGroup = groupname;
                    String username = str[1];
                    Client1GUI.pendingRequests++;

                    String sttt = Client1GUI.jTextAreaReceivedMessages.getText();
                    String res = sttt + "\n" + "REQUEST_TO_JOIN_GROUP_BY@" + username;
                    Client1GUI.jTextAreaReceivedMessages.setText(res);
                    Client1GUI.userRequesting = username;

                } else if (msg.equals("FILE_COMING")) {

                    String format = dis.readUTF();
                    String size = dis.readUTF();
                  // int filesize = Integer.parseInt(size);
                    byte[] mybytearray = new byte[FILE_SIZE];
                    InputStream is = socket.getInputStream();
                   int bytesread =  is.read(mybytearray, 0, mybytearray.length);
                   
                   int  current = bytesread;

            /*   do {
                         bytesread =
                         is.read(mybytearray, current, (mybytearray.length-current));
                          if(bytesread >= 0) current += bytesread;
                   } while(bytesread > -1);
              */     
                    System.out.println("bytes read by the client is "+current+" actual file size is "+size);
                    Client1GUI.fos = new FileOutputStream("F:\\SURAJ PARAPHERNELIA\\abc" + format);
                    Client1GUI.bos = new BufferedOutputStream(Client1GUI.fos);
                    
                    Client1GUI.bos.write(mybytearray, 0, mybytearray.length);
                    Client1GUI.bos.flush();
                 //   Client1GUI.fos.close();
                   // Client1GUI.bos.close();
                    System.out.println("File downloaded and its size is "+current);
                    String temp =  Client1GUI.jTextAreaReceivedMessages.getText();//.setText("A file received and put in f drive suraj paraphernelia");
                    temp = temp+"\n"+"A file received and put in f drive suraj paraphernelia";
                    Client1GUI.jTextAreaReceivedMessages.setText(temp);
                    
                    
                } else if (msg.equals("SQforGROUPSA#")) {
                    String stt = dis.readUTF();
                    System.out.println(stt);

                    String[] ans = stt.split("#");

                    DefaultListModel DLM = new DefaultListModel();

                    for (int i = 1; i < ans.length; ++i) {

                       
                        DLM.addElement(ans[i]);
                    }
                    

                    Client1GUI.jListSearchedRes.setModel(DLM);
                    DefaultListModel dlm = new DefaultListModel();
                    
                    cg.setterfalse();

                } else if (msg.equals("SQAF#")) {
                    ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
                    ArrayList<String> al = new ArrayList<>();
                    try {
                        al = (ArrayList<String>) ois.readObject();
                    } catch (ClassNotFoundException ex) {
                        Logger.getLogger(Client1ReceiveThread.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    DefaultListModel DLM = new DefaultListModel();

                    for (int i = 0; i < al.size(); ++i) {
                        String temp = al.get(i);
                        System.out.println(temp + "\n");
                        DLM.addElement(temp);
                    }
                    JframeSendInvites.jList_search_res.setModel(DLM);

                } else if (msg.equals("SQA#")) {
                    ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
                    System.out.println("search query arrived\n");
                    ArrayList<String> al = new ArrayList<>();
                    try {
                        al = (ArrayList<String>) ois.readObject();
                    } catch (ClassNotFoundException ex) {
                        Logger.getLogger(Client1ReceiveThread.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    DefaultListModel DLM = new DefaultListModel();

                    for (int i = 0; i < al.size(); ++i) {
                        String temp = al.get(i);
                       
                        DLM.addElement(temp);
                    }

                    Client1GUI.jListSearchedRes.setModel(DLM);

                    try {
                        al = (ArrayList<String>) ois.readObject();
                    } catch (ClassNotFoundException ex) {
                        Logger.getLogger(Client1ReceiveThread.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    DefaultListModel DLMM = new DefaultListModel();

                    for (int i = 0; i < al.size(); ++i) {
                        String temp = al.get(i);
                       
                        DLMM.addElement(temp);
                    }
                   
                    cg.settertrue();

                    System.out.println("setter called for visibility");
                    Client1GUI.jListUsersStatus.setModel(DLMM);
                   

                } else if (msg.equals("LG#")) {
                    System.out.println("server logged me out");
                    break;
                } else {
                    String sttt = Client1GUI.jTextAreaReceivedMessages.getText();
                    String res = sttt + "\n" + msg;
                    Client1GUI.jTextAreaReceivedMessages.setText(res);

                }

                System.out.println(msg);

            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

}
