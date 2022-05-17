package Services;

import Entities.Activite;
import utils.DatabaseConnection;
import utils.RelationObject;
import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class ActiviteService {

    private static ActiviteService instance;
    PreparedStatement preparedStatement;
    Connection connection;

    public ActiviteService() {
        connection = DatabaseConnection.getInstance().getConnection();
    }

    public static ActiviteService getInstance() {
        if (instance == null) {
            instance = new ActiviteService();
        }
        return instance;
    }

    public List<Activite> getAll() {
        List<Activite> listActivite = new ArrayList<>();
        try {
            preparedStatement = connection.prepareStatement("SELECT * FROM `activite` AS x RIGHT JOIN `type_activite` AS y ON x.typeact_id = y.id WHERE x.typeact_id = y.id");

            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                listActivite.add(new Activite(
                        resultSet.getInt("id"),
                        new RelationObject(resultSet.getInt("typeact_id"), resultSet.getString("y.nom")),
                        resultSet.getString("nom"),
                        resultSet.getString("lieu"),
                        resultSet.getString("description"),
                        resultSet.getString("image"),
                        resultSet.getString("longitude"),
                        resultSet.getString("lattitude")
                        
                ));
            }
        } catch (SQLException exception) {
            System.out.println("Error (getAll) activite : " + exception.getMessage());
        }
        return listActivite;
    }
     public List<Activite> getByUserId(int userId) {
        List<Activite> listActivite = new ArrayList<>();
        try {
            preparedStatement = connection.prepareStatement("SELECT * FROM `activite` AS x RIGHT JOIN `type_activite` AS y ON x.typeact_id = y.id WHERE x.typeact_id = y.id" + userId );

            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                 listActivite.add(new Activite(
                        resultSet.getInt("id"),
                        new RelationObject(resultSet.getInt("typeact_id"), resultSet.getString("y.nom")),
                        resultSet.getString("nom"),
                        resultSet.getString("lieu"),
                        resultSet.getString("description"),
                        resultSet.getString("image"),
                        resultSet.getString("longitude"),
                        resultSet.getString("lattitude")
                        
                ));
            }
        } catch (SQLException exception) {
            System.out.println("Error (getAll) commande : " + exception.getMessage());
        }
        return  listActivite;
    }
    public List<RelationObject> getAllTypeactivites() {
        List<RelationObject> listTypeactivites = new ArrayList<>();
        try {
            preparedStatement = connection.prepareStatement("SELECT * FROM `type_activite`");
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                listTypeactivites.add(new RelationObject(resultSet.getInt("id"), resultSet.getString("nom")));
            }
        } catch (SQLException exception) {
            System.out.println("Error (getAll) typeactivites : " + exception.getMessage());
        }
        return listTypeactivites;
    }
    
    public boolean add(Activite activite) {
        String request = "INSERT INTO `activite`(`typeact_id`, `nom`, `lieu`, `description`, `image`, `longitude`, `lattitude`) VALUES(?, ?, ?, ?, ?, ?, ?)";
        try {
            preparedStatement = connection.prepareStatement(request);
            
            preparedStatement.setInt(1, activite.getTypeactId().getId());
            preparedStatement.setString(2, activite.getNom());
            preparedStatement.setString(3, activite.getLieu());
            preparedStatement.setString(4, activite.getDescription());
            preparedStatement.setString(5, activite.getImage());
            preparedStatement.setString(6, activite.getLongitude());
            preparedStatement.setString(7, activite.getLattitude());
            
            preparedStatement.executeUpdate();
            System.out.println("Activite added");
            return true;
        } catch (SQLException exception) {
            System.out.println("Error (add) activite : " + exception.getMessage());
        }
        return false;
    }

    public boolean edit(Activite activite) {
        String request = "UPDATE `activite` SET `typeact_id` = ?, `nom` = ?, `lieu` = ?, `description` = ?, `image` = ?, `longitude` = ?, `lattitude` = ? WHERE `id`=" + activite.getId();
        try {
            preparedStatement = connection.prepareStatement(request);

            preparedStatement.setInt(1, activite.getTypeactId().getId());
            preparedStatement.setString(2, activite.getNom());
            preparedStatement.setString(3, activite.getLieu());
            preparedStatement.setString(4, activite.getDescription());
            preparedStatement.setString(5, activite.getImage());
            preparedStatement.setString(6, activite.getLongitude());
            preparedStatement.setString(7, activite.getLattitude());
            
            preparedStatement.executeUpdate();
            System.out.println("Activite edited");
            return true;
        } catch (SQLException exception) {
            System.out.println("Error (edit) activite : " + exception.getMessage());
        }
        return false;
    }

    public boolean delete(int id) {
        try {
            preparedStatement = connection.prepareStatement("DELETE FROM `activite` WHERE `id`=?");
            preparedStatement.setInt(1, id);

            preparedStatement.executeUpdate();
            preparedStatement.close();
            System.out.println("Activite deleted");
            return true;
        } catch (SQLException exception) {
            System.out.println("Error (delete) activite : " + exception.getMessage());
        }
        return false;
    }
    public List<Activite> rechercher(String chercher) throws SQLException {
        //LIST
        List<Activite> activite = new ArrayList<>();
        //request 
        String req ="SELECT * FROM activite where nom like '%"+chercher+"%' OR lieu like '%"+chercher+"%' or description like '%"+chercher+"%' ";

            //insert
            Statement st = connection.createStatement();
            ResultSet rs = st.executeQuery(req);
            while(rs.next())
            {
                activite.add(new Activite(rs.getInt(1),new RelationObject(rs.getInt(2), rs.getString(2)),rs.getString(3),rs.getString(4),rs.getString(5),rs.getString(6),rs.getString(7),rs.getString(8)));
                
            }
          
        return activite;
    }
}
