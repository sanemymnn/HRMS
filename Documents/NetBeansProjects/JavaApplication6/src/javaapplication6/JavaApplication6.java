import java.sql.Connection;
import java.sql.DriverManager;


/**
 *
 * @author 1BestCsharp
 */
public class JavaApplication6 {
    
    
    // create a function to connect with mysql database
    
    public static Connection getConnection(){
     
        Connection con = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost/java_login_register", "root", "");
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
        
        return con;
    }
    
}    





// function to check if the username already exist in database table
    public boolean checkUsername(String username)
    {
        PreparedStatement ps;
        ResultSet rs;
        boolean checkUser = false;
        String query = "SELECT * FROM `the_app_users` WHERE `u_uname` =?";
        
        try {
            ps = MyConnection.getConnection().prepareStatement(query);
            ps.setString(1, username);
            
            rs = ps.executeQuery();
            
            if(rs.next())
            {
                checkUser = true;
            }
        } catch (SQLException ex) {
            Logger.getLogger(RegisterForm.class.getName()).log(Level.SEVERE, null, ex);
        }
         return checkUser;
    }




// on the register button click
private void jButton_Register_ActionPerformed(java.awt.event.ActionEvent evt) {                                                  
        String fname = jTextField_FN.getText();
        String lname = jTextField_LN.getText();
        String uname = jTextField_UN.getText();
        String pass = String.valueOf(jPasswordField_PASS.getPassword());
        String re_pass = String.valueOf(jPasswordField_REPASS.getPassword());
        String bdate = null;
        String address = jTextArea_ADDRESS.getText();
                
        if(uname.equals(""))
        {
            JOptionPane.showMessageDialog(null, "Add A Username");
        }
        
        else if(pass.equals(""))
        {
            JOptionPane.showMessageDialog(null, "Add A Password");
        }
        else if(!pass.equals(re_pass))
        {
            JOptionPane.showMessageDialog(null, "Retype The Password Again");
        }
        
        else if(checkUsername(uname))
        {
            JOptionPane.showMessageDialog(null, "This Username Already Exist");
        }
        
        else{
        
        if(jDateChooser_BDATE.getDate() != null)
        {
            SimpleDateFormat dateformat = new SimpleDateFormat("yyyy-MM-dd");
            bdate = dateformat.format(jDateChooser_BDATE.getDate());
        }
            
        PreparedStatement ps;
        String query = "INSERT INTO `the_app_users`(`u_fname`, `u_lname`, `u_uname`, `u_pass`, `u_bdate`, `u_address`) VALUES (?,?,?,?,?,?)";
        
        try {
            ps = MyConnection.getConnection().prepareStatement(query);
            
            ps.setString(1, fname);
            ps.setString(2, lname);
            ps.setString(3, uname);
            ps.setString(4, pass);
            
            if(bdate != null)
            {
             ps.setString(5, bdate);
            }else{
                ps.setNull(5, 0);
            }
            ps.setString(6, address);
            
            if(ps.executeUpdate() > 0)
            {
                JOptionPane.showMessageDialog(null, "New User Add");
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(RegisterForm.class.getName()).log(Level.SEVERE, null, ex);
        }
        }
    }                                                 

    



// on the login button click
private void jButton_LOGINActionPerformed(java.awt.event.ActionEvent evt) {                                 
        PreparedStatement ps;
        ResultSet rs;
        String uname = jTextField1.getText();
        String pass = String.valueOf(jPasswordField1.getPassword());
        
        String query = "SELECT * FROM `the_app_users` WHERE `u_uname` =? AND `u_pass` =?";
        
        try {
            ps = MyConnection.getConnection().prepareStatement(query);
            
            ps.setString(1, uname);
            ps.setString(2, pass);
            
            rs = ps.executeQuery();
            
            if(rs.next())
            {
                    HOME_JFrame mf = new HOME_JFrame();
                    mf.setVisible(true);
                    mf.pack();
                    mf.setLocationRelativeTo(null);
                    mf.setExtendedState(JFrame.MAXIMIZED_BOTH);
                    mf.jLabel1.setText("Welcome < "+uname+" >");
                    
                    this.dispose();
            }
            else{
                    JOptionPane.showMessageDialog(null, "Incorrect Username Or Password", "Login Failed", 2);
                }
            
        } catch (SQLException ex) {
            Logger.getLogger(LoginForm.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
    }                                             
