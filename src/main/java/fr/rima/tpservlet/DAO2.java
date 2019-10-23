/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.rima.tpservlet;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import simplejdbc.DAOException;
import java.util.ArrayList;
import javax.sql.DataSource;
/**
 *
 * @author marie
 */
public class DAO2 extends simplejdbc.DAO{ 
    
    public DAO2(DataSource dataSource) {
     super (dataSource);
}
    /**
	 * Liste des états
	 *
         * @throws java.sql.SQLException
	 *  
	 * @return la liste des états
	 * @throws DAOException
	 */
	public List<String> States() throws DAOException, SQLException {
            String sql = "SELECT DISTINCT state FROM CUSTOMER";
            List<String> states = new ArrayList<>();
            try (Connection connection = myDataSource.getConnection();
			PreparedStatement stmt = connection.prepareStatement(sql)) {
                
                try (ResultSet rs = stmt.executeQuery()) {
				while (rs.next()) { // Tant qu'il y a des enregistrements
                                    states.add(rs.getString("state"));
				}
			}
            }
            return states;
        }
}
