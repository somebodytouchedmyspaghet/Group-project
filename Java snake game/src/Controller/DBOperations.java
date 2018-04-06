package Controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;

public class DBOperations {
    //Lets you sleect from the highscores
     public Object [][] select(String table, String fields, String where,Connection con){
      int registros = 0;      
      String colname[] = fields.split(",");

      String q ="SELECT " + fields + " FROM " + table + " ORDER BY score DESC";
      String q2 = "SELECT count(*) as total FROM " + table;
      if(where!=null)
      {
          q+= " WHERE " + where;
          q2+= " WHERE " + where;
      }
       try{
         PreparedStatement pstm = con.prepareStatement(q2);
         ResultSet res = pstm.executeQuery();
         res.next();
         registros = res.getInt("total");
         res.close();
      }catch(SQLException e){
         JOptionPane.showMessageDialog(null, e.getLocalizedMessage());
      }
    
    Object[][] data = new String[registros][fields.split(",").length];
      try{
         PreparedStatement pstm = con.prepareStatement(q);
         ResultSet res = pstm.executeQuery();
         int i = 0;
         while(res.next()){
            for(int j=0; j<=fields.split(",").length-1;j++){
                data[i][j] = res.getString( colname[j].trim() );
            }
            i++;         }
         res.close();
          }catch(SQLException e){
         System.out.println(e);
    }
    return data;
 }
      public boolean insert(String table, String fields, String values,Connection con) 
    {
        boolean res=false;
        String q=" INSERT INTO " + table + " ( " + fields + " ) VALUES ( " + values + " ) ";
        try {
            PreparedStatement pstm = con.prepareStatement(q);
            pstm.execute();
            pstm.close();
            res=true;

        } catch (SQLException ex) {
        }
        
      return res;
    }
     
     
     //Gets highscores
     public Object[][] getHighScores(Connection con)
    {
        Object[][] res = this.select("HighScores", "name, score, diff", null,con);
        if( res.length > 0)
            return res;
        else
            return null;
    } 
    
    
    
}
