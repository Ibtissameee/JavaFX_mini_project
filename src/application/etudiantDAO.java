package application;

import java.sql.Connection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class etudiantDAO {
    public List<etudiant> getAllStudents() throws SQLException {
        List<etudiant> etudiants = new ArrayList<>();

        try (Connection connection = Connection_Class.getConnection();
             PreparedStatement statement = connection.prepareStatement("SELECT * FROM etudiants");
             ResultSet resultSet = statement.executeQuery()) {
            while (resultSet.next()) {
            	etudiant etudiant = new etudiant();
            	
              
            	etudiant.setNom(resultSet.getString("nom"));
            	etudiant.setPrenom(resultSet.getString("prenom"));
            	etudiant.setNaissance(resultSet.getString("naissance"));
            	etudiant.setCNE(resultSet.getString("CNE"));
            	etudiant.setCNI(resultSet.getString("CNI"));
           
                etudiants.add(etudiant);
            }
        }

        return etudiants;
    }
}