package Services;

import Entities.Type_activite;
import utils.DatabaseConnection;


import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Type_activiteService {

    private static Type_activiteService instance;
    PreparedStatement preparedStatement;
    Connection connection;

    public Type_activiteService() {
        connection = DatabaseConnection.getInstance().getConnection();
    }

    public static Type_activiteService getInstance() {
        if (instance == null) {
            instance = new Type_activiteService();
        }
        return instance;
    }

    public List<Type_activite> getAll() {
        List<Type_activite> listType_activite = new ArrayList<>();
        try {
             preparedStatement = connection.prepareStatement("SELECT * FROM `type_activite`");

            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                listType_activite.add(new Type_activite(
                        resultSet.getInt("id"),
                        resultSet.getString("nom")
                        
                ));
            }
        } catch (SQLException exception) {
            System.out.println("Error (getAll) type_activite : " + exception.getMessage());
        }
        return listType_activite;
    }
    
    public boolean add(Type_activite type_activite) {
        String request = "INSERT INTO `type_activite`(`nom`) VALUES(?)";
        try {
            preparedStatement = connection.prepareStatement(request);
            
            preparedStatement.setString(1, type_activite.getNom());
            
            preparedStatement.executeUpdate();
            System.out.println("Type_activite added");
            return true;
        } catch (SQLException exception) {
            System.out.println("Error (add) type_activite : " + exception.getMessage());
        }
        return false;
    }

    public boolean edit(Type_activite type_activite) {
        String request = "UPDATE `type_activite` SET `nom` = ? WHERE `id`=" + type_activite.getId();
        try {
            preparedStatement = connection.prepareStatement(request);

            preparedStatement.setString(1, type_activite.getNom());
            
            preparedStatement.executeUpdate();
            System.out.println("Type_activite edited");
            return true;
        } catch (SQLException exception) {
            System.out.println("Error (edit) type_activite : " + exception.getMessage());
        }
        return false;
    }

    public boolean delete(int id) {
        try {
            preparedStatement = connection.prepareStatement("DELETE FROM `type_activite` WHERE `id`=?");
            preparedStatement.setInt(1, id);

            preparedStatement.executeUpdate();
            preparedStatement.close();
            System.out.println("Type_activite deleted");
            return true;
        } catch (SQLException exception) {
            System.out.println("Error (delete) type_activite : " + exception.getMessage());
        }
        return false;
    }
}
