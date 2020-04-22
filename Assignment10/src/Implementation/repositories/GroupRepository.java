package Implementation.repositories;


import Implementation.Connector;
import Implementation.dtos.GroupDTO;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class GroupRepository implements IGroupRepository {

    private static final String TABLE_GROUPS = "groups";
    private static final String COLUMN_ID = "group_id";
    private static final String COLUMN_NAME = "group_name";
    private static final String COLUMN_DESCRIPTION = "group_description";

    private Connection connection;
    private Savepoint savepoint;


    public GroupRepository() {
        getConnection();
    }

    public static String getPK(){
        return COLUMN_ID;
    }

    @Override
    public List<GroupDTO> findByName(String name) {
        List<GroupDTO> byName = null;
        try {
            byName = new ArrayList<>();
            PreparedStatement statement = connection.prepareStatement(
                    "SELECT " +
                            COLUMN_ID +  ", " +
                            COLUMN_NAME + ", " +
                            COLUMN_DESCRIPTION +
                            " FROM " +TABLE_GROUPS +
                            " WHERE  " + COLUMN_NAME + " LIKE ?");
            statement.setString(1, name);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                byName.add(
                        new GroupDTO(
                                resultSet.getInt(1),
                                resultSet.getString(2),
                                resultSet.getString(3)
                        )
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return byName;
    }

    @Override
    public GroupDTO findById(int id) {
        GroupDTO groupDTO = null;
        try{
            final String query = "SELECT "
                    + COLUMN_ID + ", "
                    + COLUMN_NAME + ", "
                    + COLUMN_DESCRIPTION
                    +" FROM " + TABLE_GROUPS +
                    " WHERE " + COLUMN_ID + " = ? ";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                groupDTO =  new GroupDTO(
                        resultSet.getInt(1),
                        resultSet.getString(2),
                        resultSet.getString(3));
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        return groupDTO;
    }

    @Override
    public Connection getConnection() {
        if (connection != null) {
            return connection;
        } else {
            connection = Connector.getConnection();
            return connection;
        }
    }

    @Override
    public void add(GroupDTO dto) {
        PreparedStatement statement = null;
        try {
            final String query = "INSERT INTO " + TABLE_GROUPS +  " ( "
                    + COLUMN_ID + ", " + COLUMN_NAME + ", " + COLUMN_DESCRIPTION
                    +") VALUES (?,?,?)";
            statement = connection.prepareStatement(query);
            statement.setInt(1, dto.getId());
            statement.setString(2, dto.getName());
            statement.setString(3, dto.getDescription());
            statement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(GroupDTO dto) {
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = connection.prepareStatement("UPDATE " + TABLE_GROUPS + " SET " +
                    COLUMN_NAME + " = ?," +
                    COLUMN_DESCRIPTION + " = ?" +
                    "WHERE " + COLUMN_ID + "  = ?");
            preparedStatement.setString(1, dto.getName());
            preparedStatement.setString(2, dto.getDescription());
            preparedStatement.setInt(3, dto.getId());
            preparedStatement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void addOrUpdate(GroupDTO dto) {
        if (exists(dto)) {
            update(dto);
        } else {
            add(dto);
        }
    }

    @Override
    public void delete(GroupDTO dto) {
        try {
            PreparedStatement statement = connection.prepareStatement("DELETE FROM " + TABLE_GROUPS + " WHERE " +
                    COLUMN_ID + " = ? AND " +
                    COLUMN_NAME + "= ? AND " +
                    COLUMN_DESCRIPTION + " = ?");
            statement.setInt(1, dto.getId());
            statement.setString(2, dto.getName());
            statement.setString(3, dto.getDescription());
            statement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }



    @Override
    public void beginTransaction() {
        try {
            connection.setAutoCommit(false);
            savepoint = getConnection().setSavepoint();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void commitTransaction() {
        try {
            connection.commit();
            connection.setAutoCommit(true);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void rollbackTransaction() {
        try {
            connection.rollback(savepoint);
            connection.setAutoCommit(true);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public int getCount() {
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement("SELECT COUNT (*) FROM " + TABLE_GROUPS);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return resultSet.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return -1;
    }

    @Override
    public boolean exists(GroupDTO dto) {
        boolean exists = false;
        if(dto.hasExistingId()){
            GroupDTO entity = findById(dto.getId());
            exists = entity != null;
        }
        return exists;
    }


}