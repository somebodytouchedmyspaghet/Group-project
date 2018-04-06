package Controller;
import java.sql.*;
//code for the server to keep track of highscores
public class DBConnection {
    private Connection conn = null;
    public Connection CrearBD(){
       try{
         Class.forName("org.apache.derby.jdbc.EmbeddedDriver");
         conn = DriverManager.getConnection("jdbc:derby:.\\SnakeDB\\Snake.DB;create=true");
         if (conn!=null){
            String creartabla="create table HighScores(name varchar(50), score varchar(50), diff varchar(50))";
            String desc="disconnect;";
            try {
            PreparedStatement pstm = conn.prepareStatement(creartabla);
            pstm.execute();
            pstm.close();
            
            PreparedStatement pstm2 = conn.prepareStatement(desc);
            pstm2.execute();
            pstm2.close();
            
        } catch (SQLException ex) {
                System.out.println(ex);
        }
         }
      }catch(SQLException | ClassNotFoundException e){
           System.out.println(e);
      }
       return conn;
  }
    
     public Connection AccederBD(){
       try{
         Class.forName("org.apache.derby.jdbc.EmbeddedDriver");
         conn = DriverManager.getConnection("jdbc:derby:.\\SnakeDB\\Snake.DB");
         if (conn!=null){
         }
      }catch(SQLException | ClassNotFoundException e){
           System.out.println(e);
      }
       return conn;
  }
     
      public void cerracon (){
        try {
            conn.close();
        } catch (SQLException ex) {
            System.out.println(ex);
        }
   }
     
}