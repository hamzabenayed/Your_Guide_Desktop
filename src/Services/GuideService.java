package Services;

import Entities.Guide;
import utils.DatabaseConnection;
import utils.RelationObject;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class GuideService {

    private static GuideService instance;
    PreparedStatement preparedStatement;
    Connection connection;

    public GuideService() {
        connection = DatabaseConnection.getInstance().getConnection();
    }

    public static GuideService getInstance() {
        if (instance == null) {
            instance = new GuideService();
        }
        return instance;
    }

    public List<Guide> getAll() {
        List<Guide> listGuide = new ArrayList<>();
        try {
            preparedStatement = connection.prepareStatement("SELECT * FROM `guide` AS x RIGHT JOIN `activite` AS y ON x.activite_id = y.id WHERE x.activite_id = y.id");

            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                listGuide.add(new Guide(
                        resultSet.getInt("id"),
                        new RelationObject(resultSet.getInt("activite_id"), resultSet.getString("y.nom")),
                        resultSet.getString("nom"),
                        resultSet.getInt("tel"),
                        resultSet.getString("image")
                        
                ));
            }
        } catch (SQLException exception) {
            System.out.println("Error (getAll) guide : " + exception.getMessage());
        }
        return listGuide;
    }
    
    public List<RelationObject> getAllActivites() {
        List<RelationObject> listActivites = new ArrayList<>();
        try {
            preparedStatement = connection.prepareStatement("SELECT * FROM `activite`");
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                listActivites.add(new RelationObject(resultSet.getInt("id"), resultSet.getString("nom")));
            }
        } catch (SQLException exception) {
            System.out.println("Error (getAll) activites : " + exception.getMessage());
        }
        return listActivites;
    }
    
    public boolean add(Guide guide) {
        String request = "INSERT INTO `guide`(`activite_id`, `nom`, `tel`, `image`) VALUES(?, ?, ?, ?)";
        try {
            preparedStatement = connection.prepareStatement(request);
            
            preparedStatement.setInt(1, guide.getActiviteId().getId());
            preparedStatement.setString(2, guide.getNom());
            preparedStatement.setInt(3, guide.getTel());
            preparedStatement.setString(4, guide.getImage());
            
            preparedStatement.executeUpdate();
            System.out.println("Guide added");
            return true;
        } catch (SQLException exception) {
            System.out.println("Error (add) guide : " + exception.getMessage());
        }
        return false;
    }

    public boolean edit(Guide guide) {
        String request = "UPDATE `guide` SET `activite_id` = ?, `nom` = ?, `tel` = ?, `image` = ? WHERE `id`=" + guide.getId();
        try {
            preparedStatement = connection.prepareStatement(request);

            preparedStatement.setInt(1, guide.getActiviteId().getId());
            preparedStatement.setString(2, guide.getNom());
            preparedStatement.setInt(3, guide.getTel());
            preparedStatement.setString(4, guide.getImage());
            
            preparedStatement.executeUpdate();
            System.out.println("Guide edited");
            return true;
        } catch (SQLException exception) {
            System.out.println("Error (edit) guide : " + exception.getMessage());
        }
        return false;
    }

    public boolean delete(int id) {
        try {
            preparedStatement = connection.prepareStatement("DELETE FROM `guide` WHERE `id`=?");
            preparedStatement.setInt(1, id);

            preparedStatement.executeUpdate();
            preparedStatement.close();
            System.out.println("Guide deleted");
            return true;
        } catch (SQLException exception) {
            System.out.println("Error (delete) guide : " + exception.getMessage());
        }
        return false;
    }
}
