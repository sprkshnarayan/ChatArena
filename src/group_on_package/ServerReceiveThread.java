package group_on_package;

import com.mysql.jdbc.PreparedStatement;
import static com.sun.org.apache.xerces.internal.util.FeatureState.is;
import static group_on_package.Client1ReceiveThread.FILE_SIZE;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author suraj prakash
 */
public class ServerReceiveThread extends Thread {

    Socket socket;
    public final static int FILE_SIZE = 6022386;

    public ServerReceiveThread(Socket sock) {
        this.socket = sock;

    }

    private static void connectToDatabase() {
        try {
            System.out.println("connecting to database...");
            Class.forName("com.mysql.jdbc.Driver");
            java.sql.Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/profile", "root", "");
            System.out.println("connected");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void run() {
        
        
        
        
        DataInputStream dis = null;
        DataOutputStream dos = null;
        try {
            
            
                            System.out.println("connecting to database...");
                            Class.forName("com.mysql.jdbc.Driver");

                            java.sql.Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/profile", "root", "");
                            System.out.println("connected");
            
            Socket st = null;
            dis = new DataInputStream(socket.getInputStream());
            dos = new DataOutputStream(socket.getOutputStream());
            System.out.println("server waiting for the response\n");
            int flag = 0;
            String msg = "";
            while (flag != 1) {
                msg = dis.readUTF();
                if (Character.toString(msg.charAt(0)).equals("R") && Character.toString(msg.charAt(1)).equals("#")) {
                    flag = 1;
                    String usrname = dis.readUTF();
                    String passwd = dis.readUTF();
                    String mob = dis.readUTF();
                    String email = dis.readUTF();
                    System.out.println(usrname + " " + passwd + " " + mob + " " + email);
                    try {
                       /* System.out.println("connecting to database...");
                        Class.forName("com.mysql.jdbc.Driver");
                        java.sql.Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/profile", "root", "");
                        System.out.println("connected");*///888888888888888888888888888888888888888888888888
                        String Sql = "INSERT INTO group_on_database VALUES (?, ?, ?, ?,?,?)";
                        PreparedStatement stmt = (PreparedStatement) conn.prepareStatement(Sql);
                        stmt.setString(1, usrname);
                        stmt.setString(2, passwd);
                        stmt.setString(3, mob);
                        stmt.setString(4, email);
                        stmt.setString(5,"");
                        stmt.setString(6,"");
                        stmt.executeUpdate();
                        stmt.close();
                         ServerClass.map.put(usrname, socket);
                        dos.writeUTF("1");
                        System.out.println("data base updataed\n");
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                } else {
                    String[] parts = msg.split("#");
                    msg = parts[0];
                    String mmsg = parts[1];

                    try {
                       /* System.out.println("connecting to database...");
                        Class.forName("com.mysql.jdbc.Driver");
                        java.sql.Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/profile", "root", "");
                        System.out.println("connected");*///888888888888888888888888888888888888888888
                        Statement stmt = conn.createStatement();
                        ResultSet rs = stmt.executeQuery("SELECT username,password FROM group_on_database ");

                        try {

                            while (rs.next()) {
                                String id = rs.getString("password");
                                String name = rs.getString("username");
                                try {
                                    if ((id.equals(mmsg)) && msg.equals(name)) {
                                        ServerClass.map.put(msg, socket);
                                        dos.writeUTF("1");
                                        flag = 1;
                                        System.out.println("Authenticated\n");
                                        break;
                                    }
                                } catch (IOException er) {
                                    er.printStackTrace();
                                }

                            }
                        } catch (SQLException ex) {
                            ex.printStackTrace();
                        }

                    } catch (SQLException rf) {
                        System.out.print("sql while authenticated exception occured\n");
                        rf.printStackTrace();
                    } /*catch (ClassNotFoundException ef) {
                        System.out.print("class not found exception occured\n");
                        ef.printStackTrace();
                    }*///8888888888888888888888888888888888888888888888888888888888888888

                    if (flag == 0) {
                        dos.writeUTF("0");

                    }
                }
            }
// code for various action starts
//****************************************************************************************************************
//****************************************************************************************************************
            while (true) {
                String stt = dis.readUTF();
                System.out.println("waiting to read form client");
                if (stt.equals("UPLOAD_STATUS")) {
                    String status = dis.readUTF();
                    String username = dis.readUTF();
                    try {
                        /*System.out.println("connecting to database...");
                        Class.forName("com.mysql.jdbc.Driver");
                        java.sql.Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/profile", "root", "");
                        System.out.println("connected");*///88888888888888888888888888888888888888888888888888
                        String sql = String.format("UPDATE `group_on_database` SET `status` = '%s' WHERE `username` = '%s' ;", status, username);
                        PreparedStatement sttmt = (PreparedStatement) conn.prepareStatement(sql);
                        sttmt.executeUpdate();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                } else if (stt.equals("SHOW_PROFILE_QUERY")) {
                    String username = dis.readUTF();
                    System.out.println("profile query for "+username);
                    String path;

                    try {
                       /* System.out.println("connecting to database...");
                        Class.forName("com.mysql.jdbc.Driver");
                        java.sql.Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/profile", "root", "");
                        System.out.println("connected");*///888888888888888888888888888888888888888888888888888
                        String sql = "SELECT username,mobno,emailid,status,profilepic FROM `group_on_database`";

//                        FileInputStream fis = null;
//                        BufferedInputStream bis = null;
//                        OutputStream os = null;
//                        // String FILE_TO_SEND = 

                        Statement stmt = null;
                        try {
                            stmt = conn.createStatement();
                        } catch (SQLException ex) {
                            Logger.getLogger(ServerReceiveThread.class.getName()).log(Level.SEVERE, null, ex);
                        }

                        try {
                            ResultSet rs = stmt.executeQuery(sql);
                            while (rs.next()) {
                                String usrnme = rs.getString("username");

                                if (usrnme.equals(username)) {
                                    String mobno = rs.getString("mobno");
                                    String emailid = rs.getString("emailid");
                                    String status = rs.getString("status");
                                 //   String FILE_TO_SEND = rs.getString("profilepic");
                                    
                                 //   System.out.println("profile pic at "+FILE_TO_SEND+" is picked for transfer");
                                    
//                                    String extension;
//                                    int i;
//                                    for( i=0;i<FILE_TO_SEND.length();++i){
//                                        if(FILE_TO_SEND.charAt(i)=='.')break;
//                                    }
//                                    extension = FILE_TO_SEND.substring(i, FILE_TO_SEND.length());

//                                    File myFile = new File(FILE_TO_SEND);
//                                    byte[] mybytearray = new byte[(int) myFile.length()];
//                                    fis = new FileInputStream(myFile);
//                                    bis = new BufferedInputStream(fis);

                                    Socket sock = ServerClass.map.get(username);//*************************
                                    
                                 
                                    dos.writeUTF("SHOWING_PROFILE_QUERY");
                                  //  bis.read(mybytearray, 0, mybytearray.length);
                                  //  os = sock.getOutputStream();
                                  ////  System.out.println("Sending " + FILE_TO_SEND + "(" + mybytearray.length + " bytes)");
                                 //   os.write(mybytearray, 0 , mybytearray.length);
                                    System.out.println("file sent by the server");
                                    
                                  //  os.flush();
                                  //  fis.close();os.close();
                               //     System.out.println("the info of the users are "+mobno+" "+emailid+" "+status+" "+username+" "+extension);
                                    if(mobno!=null)
                                    dos.writeUTF(mobno);
                                    else
                                        dos.writeUTF("???");
                                    
                                      System.out.println("all the information sent by the server");
                                    if(emailid!=null)
                                    dos.writeUTF(emailid);
                                    else
                                        dos.writeUTF("???");
                                      System.out.println("all the information sent by the server");
                                    if(status!=null)
                                    dos.writeUTF(status);
                                    else
                                        dos.writeUTF("???");
                                      System.out.println("all the information sent by the server");
                                    
                                    dos.writeUTF(username);
                                 //   if(extension!=null)
                                 //   dos.writeUTF(extension);
                                //    else dos.writeUTF("???");
                                    System.out.println("all the information sent by the server");
                                    

                                }
                            }
                        } catch (SQLException ex) {
                            Logger.getLogger(ServerReceiveThread.class.getName()).log(Level.SEVERE, null, ex);
                        }

                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                } else if (stt.equals("PROVIDE_GROUP_MEMBERS")) {
                    System.out.println("received request to show group members");
                    String groupname = dis.readUTF();
                    String username = dis.readUTF();

                    try {
                        /*System.out.println("connecting to database...");
                        Class.forName("com.mysql.jdbc.Driver");
                        java.sql.Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/profile", "root", "");
                        System.out.println("connected");*///888888888888888888888888888888888888888888888888888888888
                        String sql = String.format("SELECT groupname,users FROM `%s`", "groupmembers");
                        PreparedStatement stmt = null;
                        try {
                            stmt = (PreparedStatement) conn.prepareStatement(sql);
                        } catch (SQLException ex) {
                            Logger.getLogger(ServerReceiveThread.class.getName()).log(Level.SEVERE, null, ex);
                        }
                        try {
                            ResultSet rs = stmt.executeQuery();

                            while (rs.next()) {
                                String grpname = rs.getString("groupname");
                                String memberlist = rs.getString("users");

                                if (grpname.equals(groupname)) {

                                    dos.writeUTF("PROVIDING_GROUP_MEMBERS");
                                    dos.writeUTF(memberlist);

                                    System.out.println("group memberlist sent to the client");

                                    break;
                                }

                            }
                        } catch (SQLException ex) {
                            Logger.getLogger(ServerReceiveThread.class.getName()).log(Level.SEVERE, null, ex);
                        }

                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                } else if (stt.equals("RESPONDING_TO_GROUP_JOIN_REQUEST")) {

                    String response = dis.readUTF();
                    String username = dis.readUTF();
                    String groupname = dis.readUTF();

                    System.out.println("response at the server is " + response + " to " + username + " for " + groupname);

                    if (response.equals("YES")) {
                        try {

                          /*  System.out.println("connecting to database...");
                            Class.forName("com.mysql.jdbc.Driver");

                            java.sql.Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/profile", "root", "");
                            System.out.println("connected");
                            *///8888888888888888888888888888888888888888888888888888888888888888888888888888
                            

                            String sql = String.format("INSERT INTO `%s` (`username`) VALUES ('%s');", groupname, username);
                            PreparedStatement sttmt = (PreparedStatement) conn.prepareStatement(sql);
                            sttmt.executeUpdate();
                            
                            
                             sql = String.format("SELECT `groupname`, `users` FROM `groupmembers` WHERE `groupname` = '%s';",groupname);
                        PreparedStatement stmt = (PreparedStatement) conn.prepareStatement(sql);
                        ResultSet rs = stmt.executeQuery();
                        rs.next();
                        String rest = rs.getString("users");
                        rest+="#"+username;
                            System.out.println(rest);
                        
                         sql = "UPDATE `groupmembers` SET `users` = ? WHERE `groupname` = ? ;";
                        PreparedStatement stttmt = (PreparedStatement) conn.prepareStatement(sql);
                        stttmt.setString(1, rest);
                        stttmt.setString(2, groupname);
                        stttmt.executeUpdate();
                            System.out.println("fgfgtrgtbgbhrbgvfdbrgbnbbbf");

                            Socket skt = ServerClass.map.get(username);
                            DataOutputStream dpt = new DataOutputStream(skt.getOutputStream());
                            dpt.writeUTF("YOUR REQUEST TO JOIN GROUP " + groupname + " APPROVED " + "BY THE ADMIN");
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    } else if (response.equals("NO")) {
                        Socket skt = ServerClass.map.get(username);
                        DataOutputStream dpt = new DataOutputStream(skt.getOutputStream());
                        dpt.writeUTF("YOUR REQUEST TO JOIN GROUP " + groupname + "REJECTED " + "BY THE ADMIN");

                    }
                } else if (stt.equals("REQUEST_TO_JOIN_GROUP")) {
                    String groupname = dis.readUTF();
                    String user = dis.readUTF();

                    try {
                       /* System.out.println("connecting to database...");
                        Class.forName("com.mysql.jdbc.Driver");

                        java.sql.Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/profile", "root", "");
                        System.out.println("connected");
*///88888888888888888888888888888888888888888888888888888888888888888888888888888888888888888888888888
                        Statement stmt = conn.createStatement();

                        ResultSet rs = stmt.executeQuery("SELECT groupname,admin FROM groupadmin");
                        System.out.println("search query executed for user search\n");
                        //returns table containing roll and name as only features in the table
                        String admin = "";
                        try {
                            // Extract data from result set
                            while (rs.next()) {
                                // Retrieve by column name
                                // int id = rs.getInt("roll");    // regno is column-name/attribute
                                String grname = rs.getString("groupname");
                                admin = rs.getString("admin");

                                if (grname.equals(groupname)) {
                                    System.out.println("the admin of " + groupname + " is " + admin);
                                    break;
                                }

                            }
                            Socket skt = ServerClass.map.get(admin);

                            DataOutputStream DOS = new DataOutputStream(skt.getOutputStream());
                            DOS.writeUTF("REQUEST_TO_JOIN_GROUP");
                            DOS.writeUTF("BY@" + user);
                            DOS.writeUTF(groupname);

                            System.out.println("request sent to admin");

                        } catch (SQLException ex) {
                            Logger.getLogger(ServerReceiveThread.class.getName()).log(Level.SEVERE, null, ex);
                            System.out.print("sql exception xxxxxxxxxxxxxxxxxxoccured\n");
                        }

                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                } else if (stt.contains("JOIN@")) {
                    String client = dis.readUTF();
                    String grpname = dis.readUTF();
                    String adminname = dis.readUTF();
                    System.out.println("join response arrived at the server " + client + " " + grpname + " " + adminname);

                    try {
                        /*System.out.println("connecting to database...");
                        Class.forName("com.mysql.jdbc.Driver");
                        java.sql.Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/profile", "root", "");
                        System.out.println("connected");*///88888888888888888888888888888888888888888888888888
                        String sql = String.format("INSERT INTO `%s` (`username`) VALUES ('%s');", grpname, client);
                        PreparedStatement sttmtt = null;
                        try {
                            sttmtt = (PreparedStatement) conn.prepareStatement(sql);
                        } catch (SQLException ex) {
                            Logger.getLogger(ServerReceiveThread.class.getName()).log(Level.SEVERE, null, ex);
                        }

                        try {
                            sttmtt.executeUpdate();

                            System.out.println("join request doneeeeeeeeeeeeee");
                        } catch (SQLException ex) {
                            Logger.getLogger(ServerReceiveThread.class.getName()).log(Level.SEVERE, null, ex);
                        }
                        sql = String.format("SELECT `groupname`, `users` FROM `groupmembers` WHERE `groupname` = '%s';",grpname);
                        PreparedStatement stmt = (PreparedStatement) conn.prepareStatement(sql);
                        ResultSet rs = stmt.executeQuery();
                        rs.next();
                        String rest = rs.getString("users");
                        rest+="#"+client;
                        
                         sql = "UPDATE `groupmembers` SET `users` = ? WHERE `groupname` = ? ;";
                        PreparedStatement sttmt = (PreparedStatement) conn.prepareStatement(sql);
                        sttmt.setString(1, rest);
                        sttmt.setString(2, grpname);
                        sttmt.executeUpdate();
                        
                        
                        
                        
                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                } else if (stt.equals("PROFILE_PIC_COMING")) {
                    System.out.println("PROFILE PIC arrived at the server \n");
                    String username = dis.readUTF();
                    String extension = dis.readUTF();
                    System.out.println("profile pic is for " + username);

                    byte[] mybytearray = new byte[FILE_SIZE];
                    InputStream in = socket.getInputStream();
                    int bytesread = in.read(mybytearray, 0, mybytearray.length);
                    System.out.println("fiel read by the server");

                    FileOutputStream fos = new FileOutputStream("F:\\SURAJ PARAPHERNELIA\\" + username + extension);

                    String path = "F:\\SURAJ PARAPHERNELIA\\" + username + extension;

                    BufferedOutputStream bos = new BufferedOutputStream(fos);
                    bos.write(mybytearray, 0, bytesread);
                    bos.flush();in.close();
                    bos.close();
                    System.out.println("File downloaded by the server");
                    try {
                       /* System.out.println("connecting to database...");
                        Class.forName("com.mysql.jdbc.Driver");
                        java.sql.Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/profile", "root", "");
                        System.out.println("connected");
*///888888888888888888888888888888888888888888888888888888888888888888888888888888888888888888888888888888888888888888
                        String sql = "UPDATE `group_on_database` SET `profilepic` = ? WHERE `username` = ? ;";
                        PreparedStatement sttmt = (PreparedStatement) conn.prepareStatement(sql);
                        sttmt.setString(1, path);
                        sttmt.setString(2, username);
                        sttmt.executeUpdate();

                        System.out.println("path stored in database");
                        // PreparedStatement   stmt = null;

                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                } else if (stt.equals("FILE_COMING")) {
                    System.out.println("file coming\n");
                    String read1 = dis.readUTF();

                    System.out.println(read1 + "\n");

                    if (read1.equals("FOR_USER")) {
                        String username = dis.readUTF();
                        String format = dis.readUTF();
                        System.out.println("file is for " + username);
                        Socket socc = ServerClass.map.get(username);

                        if (socc == null) {
                            System.out.println("socket is null");
                        }

                        byte[] mybytearray = new byte[FILE_SIZE];
                        InputStream is = socket.getInputStream();
                        int bytesread = is.read(mybytearray, 0, mybytearray.length);
                        
                      int  current = bytesread;

  /*    do {
         bytesread =
            is.read(mybytearray, current, (mybytearray.length-current));
         if(bytesread >= 0) current += bytesread;
      } while(bytesread > -1);
    */                    
                        System.out.println("file read by the server and its size is " + current);
                        DataOutputStream doss = new DataOutputStream(socc.getOutputStream());
                        doss.writeUTF("FILE_COMING");
                        doss.writeUTF(format);
                        doss.writeUTF(Integer.toString(current));
                        

                        OutputStream os = null;
                        try {
                            os = socc.getOutputStream();
                            os.write(mybytearray, 0, current);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        System.out.println("file written by the server");
                        os.flush();
                   //     is.close();
                      //  os.close();
                        System.out.println("Done...");

                    } else if (read1.equals("FOR_GROUP")) {
                        String groupName = dis.readUTF();
                    }

                } else if (stt.equals("GRP_MES#")) {
                    String mess = dis.readUTF();
                    String[] grpname = mess.split("#");
                    String[] u = grpname[1].split("@");
                    try {
                      /*  System.out.println("connecting to database...");
                        Class.forName("com.mysql.jdbc.Driver");
                        java.sql.Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/profile", "root", "");
                        System.out.println("connected");*///8888888888888888888888888888888888888888888
                        String sql = String.format("SELECT username FROM `%s`", grpname[0]);
                        PreparedStatement stmt = (PreparedStatement) conn.prepareStatement(sql);
                        ResultSet rs = stmt.executeQuery();

                        try {
                            String resltset = "";
                            while (rs.next()) {
                                String name = rs.getString("username");
                                System.out.println("group members are " + name + "\n");
                                Socket socca = ServerClass.map.get(name);
                                if (name.equals(u[0])) {
                                    continue;
                                }
                                if (socca != null) {
                                    DataOutputStream dossa = new DataOutputStream(socca.getOutputStream());
                                    dossa.writeUTF(grpname[1]);
                                    System.out.println("messages are not being sent to " + u[0]);
                                    System.out.println("messages are being sent to " + name);

                                }
                            }
                            //  System.out.println(resltset);
                            // dos.writeUTF("SQforGROUPSA#");
                            // dos.writeUTF(resltset);
                        } catch (SQLException ex) {
                            Logger.getLogger(ServerReceiveThread.class.getName()).log(Level.SEVERE, null, ex);
                            System.out.print("sql exceptionrgrgrgfg occured\n");
                        }
                        stmt.close();
                    } catch (SQLException rf) {
                        System.out.print("sql while authenticated exception occured\n");
                    } /*catch (ClassNotFoundException ef) {
                        System.out.print("class not found exception occured\n");
                    }*///888888888888888888888888888888888888888888888888888888888888888888888888888
                } else if (stt.equals("SQforGROUPS#")) {
                    String groupnamee = dis.readUTF();
                    System.out.println("SQforGROUPS# done");
                    try {
                       /* System.out.println("connecting to database...");
                        Class.forName("com.mysql.jdbc.Driver");
                        java.sql.Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/profile", "root", "");
                        System.out.println("connected");*///8888888888888888888888888888888888888888888
                        Statement stmt = conn.createStatement();

                        ResultSet rs = stmt.executeQuery("SELECT groupname FROM groupadmin");
                        System.out.println("result set fetched");
                        try {
                            String resltset = "";
                            while (rs.next()) {
                                String name = rs.getString("groupname");
                                if (name.contains(groupnamee)) {
                                    resltset += "#" + name;
                                    System.out.println("cond sat" + name);

                                }
                            }
                            System.out.println(resltset);
                            dos.writeUTF("SQforGROUPSA#");
                            dos.writeUTF(resltset);
                        } catch (SQLException ex) {
                            Logger.getLogger(ServerReceiveThread.class.getName()).log(Level.SEVERE, null, ex);
                            System.out.print("sql exceptionrgrgrgfg occured\n");
                        }
                        stmt.close();
                    } catch (SQLException rf) {
                        System.out.print("sql while authenticated exception occured\n");
                    }/* catch (ClassNotFoundException ef) {
                        System.out.print("class not found exception occured\n");
                    }*/

                } else if (stt.equals("GROUP_INVITES#")) {
                    System.out.println("invites arrived at the server#\n");
                    String list_of_users = dis.readUTF();  //check wether admin is inviting or not
                    String username = dis.readUTF();
                    String groupname = dis.readUTF();
                    //  System.out.println("\n"+groupname);

                    String[] result = list_of_users.split("\n");

                    for (int i = 0; i < result.length; ++i) {
                        String user = result[i];
                        Socket socc = ServerClass.map.get(user);//get socket corresponding to the user
                        if (socc != null) {
                            DataOutputStream doss = new DataOutputStream(socc.getOutputStream());
                            doss.writeUTF("GROUP_INVITES");
                            doss.writeUTF(username);
                            doss.writeUTF(groupname);
                            //doss.writeUTF("GROUP INVITE by@" + username + " " + "To join group: " + groupname + " RESPOND WITH: JOIN@"+groupname);
                            // DataInputStream diss = new DataInputStream(socc.getInputStream());
                        }
                    }

                } else if (stt.equals("SQF#")) {
                    ArrayList<String> arr = new ArrayList<>();
                    stt = dis.readUTF();
                    //  ResultSet rs = stmt.

                    try {
                      /*  System.out.println("connecting to database...");
                        Class.forName("com.mysql.jdbc.Driver");

                        // conn = null;
                        java.sql.Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/profile", "root", "");
                        System.out.println("connected");*///888888888888888888888888888888888
                        Statement stmt = conn.createStatement();

                        ResultSet rs = stmt.executeQuery("SELECT username FROM group_on_database");
                        System.out.println("search query executed for user search\n");
                        //returns table containing roll and name as only features in the table

                        try {
                            // Extract data from result set
                            while (rs.next()) {
                                // Retrieve by column name
                                // int id = rs.getInt("roll");    // regno is column-name/attribute
                                String name = rs.getString("username");

                                if (name.contains(stt)) {
                                    System.out.println("user name are " + name + "\n");
                                    arr.add(name);
                                }

                            }
                            dos.writeUTF("SQAF#");
                            System.out.println("searched item added to array list\n");
                            ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
                            oos.writeObject(arr);
                            System.out.println("object written\n");

                        } catch (SQLException ex) {
                            Logger.getLogger(ServerReceiveThread.class.getName()).log(Level.SEVERE, null, ex);
                            System.out.print("sql exception xxxxxxxxxxxxxxxxxxoccured\n");
                        }
                    } catch (SQLException rf) {
                        System.out.print("sql exceptionzzzzzzzzzzzzzzzz occured\n");
                    } /*catch (ClassNotFoundException ef) {
                        System.out.print("class not found exception occured\n");
                    }*///88888888888888888888888888

                } else if (stt.equals("GC#")) {  // creating new group
                    String groupname = dis.readUTF();
                    String admin = dis.readUTF();
                    System.out.println(groupname + " " + admin);
                    try {
                        /*System.out.println("connecting to database...");
                        Class.forName("com.mysql.jdbc.Driver");

                        // conn = null;
                        java.sql.Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/profile", "root", "");
                        System.out.println("connected");*///8888888888888888888888888888888888
                        //  PreparedStatement stmt=(PreparedStatement) conn.createStatement(); 

                        String sql = String.format("CREATE TABLE %s (username VARCHAR(255) not NULL)", groupname);
                        PreparedStatement stmt = (PreparedStatement) conn.prepareStatement(sql);
//                    stmt.setString(1, groupname);
                        System.out.println(sql);
                        stmt.executeUpdate();

                        sql = String.format("INSERT INTO `groupadmin` (`groupname`,`admin`) VALUES ('%s','%s');", groupname, admin);
                        stmt = (PreparedStatement) conn.prepareStatement(sql);
                        stmt.executeUpdate();
                        
                        sql =  String.format("INSERT INTO `groupmembers` (`groupname`,`users`) VALUES ('%s','%s');", groupname, "#"+admin);
                        stmt = (PreparedStatement)conn.prepareStatement(sql);
                        stmt.executeUpdate();
                                
                       
                        
                        sql = String.format("INSERT INTO `%s` (`username`) VALUES ('%s');", groupname, admin);
                        stmt = (PreparedStatement) conn.prepareStatement(sql);
                        stmt.executeUpdate();
                        System.out.println("group created successfully\n");

                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                } else if (stt.equals("LG#")) {
                    String sst = dis.readUTF();
                    System.out.println(sst + " logging out");

                    System.out.println("client logging out");
                    dos.writeUTF("LG#");
                    ServerClass.map.remove(sst);

                    break;

                } else if (stt.equals("SQ#")) {
                    System.out.println("search query arrived for users\n");
                    //System.out.println("SQ################################");
                    ArrayList<String> arr = new ArrayList<>();
                    ArrayList<String> arr1 = new ArrayList<>();
                    stt = dis.readUTF();
                    //  ResultSet rs = stmt.

                    try {
                       /* System.out.println("connecting to database...");
                        Class.forName("com.mysql.jdbc.Driver");

                        // conn = null;
                        java.sql.Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/profile", "root", "");
                        System.out.println("connected");*///888888888888888888888888888888888888888888888888888
                        Statement stmt = conn.createStatement();

                        ResultSet rs = stmt.executeQuery("SELECT username FROM group_on_database");
                        System.out.println("search query executed");
                        //returns table containing roll and name as only features in the table

                        try {
                            // Extract data from result set
                            while (rs.next()) {
                                // Retrieve by column name
                                // int id = rs.getInt("roll");    // regno is column-name/attribute
                                String name = rs.getString("username");

                                if (name.contains(stt)) {
                                    System.out.println(name + "\n");
                                    arr.add(name);
                                    Socket socv = ServerClass.map.get(name);
                                    if (socv != null) {
                                        arr1.add("online");

                                    } else {
                                        arr1.add("offline");
                                    }
                                }

                            }
                            dos.writeUTF("SQA#");
                            System.out.println("searched item added to array list\n");
                            ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
                            oos.writeObject(arr);
                            oos.writeObject(arr1);

                            System.out.println("object written\n");

                        } catch (SQLException ex) {
                            Logger.getLogger(ServerReceiveThread.class.getName()).log(Level.SEVERE, null, ex);
                            System.out.print("sql exception occured\n");
                        }
                    } catch (SQLException rf) {
                        System.out.print("sql exception occured\n");
                    } /*catch (ClassNotFoundException ef) {
                        System.out.print("class not found exception occured\n");
                    }*/

                } else {
                    // send message to specific client
                    String[] t = stt.split("#");
                    String to = t[0];
                    System.out.println("in the else part");
                    String message = t[1];
                    Socket socc = ServerClass.map.get(to);//get socket corresponding to the user
                    DataOutputStream dosa = new DataOutputStream(socc.getOutputStream());
                    dosa.writeUTF(message);
                }

            }
            System.out.println("breaking out\n");
        } catch (Exception exb) {
            System.out.print("sql exceptionvvvvvvvvvvvvvvvvvvvvvvv occured\n");
            exb.printStackTrace();
        }

    }

}
