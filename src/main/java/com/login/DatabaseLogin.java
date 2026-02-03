package com.login;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.Scanner;
public class DatabaseLogin {
    public static void main(String[] args) throws SQLException {
        Scanner sc = new Scanner(System.in);
        String username= "",gmail="",password="";
        System.out.println("Press 1 for signup and 2 for login");
        int n = sc.nextInt();
        sc.nextLine();
        switch(n) {
            case 1: 
                System.out.println("please enter credentials");
                    System.out.println("Username:-");
                     username = sc.nextLine();
                    System.out.println("Gmail:-");
                    gmail = sc.nextLine();
                    System.out.println("Password:-");
                     password = sc.nextLine();
                    database db = new database(username,gmail,password);
                signup sp = new signup(username,gmail,password);   //for login
                int UsernameExist = sp.check();
                if(UsernameExist==1){
                    System.out.println("Login successfull");
                }
                break;
            case 2:
                    System.out.println("Gmail:-");
                    String gmail1 = sc.nextLine();
                    System.out.println("Password:-");
                    String password1 = sc.nextLine();
                login ln = new login(gmail1,password1);  //for signup 
                int Usercheck  = ln.check();
                if(Usercheck==0){
                    System.out.println("No user found");
                }

                break;
            default:
                System.out.println("please choose the correct option");
            }
        }
    }

class signup{
    private String username;
    private String gmail;
    private String password;
    public signup(String user,String gm,String pass){
        username = user;
        gmail = gm;
        password = pass;
    }
    public int check() throws SQLException{
        try{
        String url = "jdbc:mysql://localhost:3306/world";
        String user = "adi";
        Connection con  = DriverManager.getConnection(url, user, "Minjoadi@23");
       PreparedStatement ps =
            con.prepareStatement("INSERT INTO users (username,gmail,password) VALUES (?, ?, ?)");
            ps.setString(1, username);
            ps.setString(2, gmail);
            ps.setString(3, password);
            int rows = ps.executeUpdate();
            System.out.println(rows);
              ps.close();
              con.close();
            return 1;
        }
        catch(SQLIntegrityConstraintViolationException e){
            System.out.println("Username already exist want to signup ");
            
              return 0;
        }

    }
    
}
class login{
        private String gmail;
        private String password;
       public login(String gmail, String password) {
    this.gmail = gmail.trim();
    this.password = password.trim();
}

 public int check() throws SQLException{
    String url = "jdbc:mysql://localhost:3306/world";
        String user = "adi";
        Connection con = DriverManager.getConnection(url, user, "Minjoadi@23");
          PreparedStatement ps = con.prepareStatement(
                "SELECT username, password FROM users WHERE gmail = ?"
            );
            ps.setString(1, gmail);
            ResultSet  rs = ps.executeQuery();
            String dbUser="";
            String dbpass= "";
            while(rs.next()){
                dbUser  = rs.getString("username");
                dbpass = rs.getString("password");
                if(password.equals(dbpass)){
                System.out.println("Welcome User:- "+ dbUser);
                return 1;
                }
                else{
                    System.out.println("wrong password");
                    return 1;

                }
            }
        return 0;
 }       
        
}
record database(String username,String gmail,String password){}
