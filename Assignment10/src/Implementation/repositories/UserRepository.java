package Implementation.repositories;

import Implementation.Connector;
import Implementation.dtos.UserDTO;
import Implementation.repositories.IUserRepository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserRepository implements IUserRepository
{

    private static final String TABLE_USERS = "users";
    private static final String COLUMN_LOGIN = "user_login";
    private static final String COLUMN_PASSWORD = "user_password";
    private static final String COLUMN_ID = "user_id";


    private Connection connection;
    private Savepoint savepoint;


    public UserRepository()
    {
        getConnection();
    }

    public static String getPK(){
        return COLUMN_ID;
    }

    @Override
    public List<UserDTO> findByName(String username)
    {
        List<UserDTO> byName = null;
        try
        {
            byName = new ArrayList<>();
            final String query = "SELECT " +COLUMN_ID + "," +COLUMN_LOGIN + ","+ COLUMN_PASSWORD+
                    " FROM " +TABLE_USERS + " WHERE  "+ COLUMN_LOGIN + " LIKE ?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, username);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next())
            {
                byName.add(
                        new UserDTO(
                                resultSet.getInt(1),
                                resultSet.getString(2),
                                resultSet.getString(3)
                        )
                );
            }
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
        return byName;
    }

    @Override
    public UserDTO findById(int id)
    {
        UserDTO user = null;
        try
        {
            final String query = "SELECT "+COLUMN_ID + "," +COLUMN_LOGIN + ","+ COLUMN_PASSWORD+
                    "  FROM " + TABLE_USERS + " WHERE " + COLUMN_ID + " = ?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next())
            {
                user = new UserDTO(
                        resultSet.getInt(1),
                        resultSet.getString(2),
                        resultSet.getString(3)
                );
            }
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
        return user;
    }


    @Override
    public Connection getConnection()
    {
        if (connection != null) {
            return connection;
        } else {
            connection = Connector.getConnection();
            return connection;
        }
    }

    @Override
    public void add(UserDTO dto)
    {
        PreparedStatement statement = null;
        try
        {
            final String query = "INSERT INTO " + TABLE_USERS +"("
                    +COLUMN_ID+", "+COLUMN_LOGIN+", "+COLUMN_PASSWORD+
                    " ) " + "VALUES (?,?,?)";
            statement = connection.prepareStatement(query);
            statement.setInt(1,dto.getId());
            statement.setString(2,dto.getLogin());
            statement.setString(3,dto.getPassword());
            statement.execute();
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
    }

    @Override
    public void update(UserDTO dto)
    {
        PreparedStatement preparedStatement = null;
        try
        {
            preparedStatement = connection.prepareStatement("UPDATE "+TABLE_USERS+" SET " +
                    COLUMN_LOGIN + "= ?" + " ," +
                    COLUMN_PASSWORD + "= ?" + " " +
                    "WHERE "+COLUMN_ID+" = ?");
            preparedStatement.setString(1, dto.getLogin());
            preparedStatement.setString(2, dto.getPassword());
            preparedStatement.setInt(3, dto.getId());
            preparedStatement.execute();
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
    }

    @Override
    public void addOrUpdate(UserDTO dto)
    {
        if (exists(dto)) {
            update(dto);
        } else {
            add(dto);
        }
    }

    @Override
    public void delete(UserDTO dto)
    {
        PreparedStatement statement = null;
        try
        {
            statement = connection.prepareStatement("DELETE FROM "+TABLE_USERS+" WHERE " +
                    COLUMN_ID+" = ? AND " +
                    COLUMN_LOGIN+"= ? AND " +
                    COLUMN_PASSWORD+"= ?");
            statement.setInt(1, dto.getId());
            statement.setString(2, dto.getLogin());
            statement.setString(3, dto.getPassword());
            statement.execute();
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
    }



    @Override
    public void beginTransaction()
    {
        try
        {
            connection.setAutoCommit(false);
            savepoint = getConnection().setSavepoint();
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
    }

    @Override
    public void commitTransaction()
    {
        try
        {
            connection.commit();
            connection.setAutoCommit(true);
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
    }

    @Override
    public void rollbackTransaction()
    {
        try
        {
            connection.rollback(savepoint);
            connection.setAutoCommit(true);
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
    }

    @Override
    public int getCount()
    {
        PreparedStatement statement = null;
        try
        {
            final String query = "SELECT COUNT (*) FROM " + TABLE_USERS;
            statement = connection.prepareStatement(query);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next())
            {
                return resultSet.getInt(1);
            }
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
        return -1;
    }

    @Override
    public boolean exists(UserDTO dto)
    {
        boolean exists = false;
        if(dto.hasExistingId()){
            UserDTO entity = findById(dto.getId());
            exists = entity != null;
        }
        return exists;
    }


}