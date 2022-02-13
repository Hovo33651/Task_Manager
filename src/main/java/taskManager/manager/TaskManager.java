package taskManager.manager;

import taskManager.db.DBConnectionProvider;
import taskManager.model.Task;
import taskManager.typeStatus.TaskStatus;

import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class TaskManager {
    private final Connection connection = DBConnectionProvider.getInstance().getConnection();


    private final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
    private final UserManager userManager = new UserManager();

    public boolean addTask(Task task) {
        String sql = "INSERT INTO task_manager.tasks (task_description,deadline,assigned_user_id) " +
                "VALUES (?,?,?)";
        try {
            PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, task.getDescription());
            statement.setString(2, sdf.format(task.getDeadline()));
            statement.setLong(3, task.getUser().getId());
            statement.executeUpdate();
            ResultSet rs = statement.getGeneratedKeys();
            if (rs.next()) {
                task.setId(rs.getLong(1));
            }
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }


    public boolean delete(long id) {
        String sql = "DELETE FROM task_manager.tasks WHERE id = ?";
        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setLong(1, id);
            statement.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }


    public boolean update(long id, TaskStatus status) {
        String sql = "UPDATE task_manager.tasks SET status = ? WHERE id = ?";
        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, status.name());
            statement.setLong(2, id);
            statement.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }


    public List<Task> getByUserId(long id) {
        String sql = "SELECT * FROM task_manager.tasks WHERE assigned_user_id = ?";
        List<Task> tasks = new ArrayList<>();
        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setLong(1, id);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                tasks.add(getTaskFromResultSet(resultSet));
            }
            return tasks;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }

    }

    public Task getById(long id) {
        String sql = "SELECT * FROM task_manager.tasks WHERE id = ?";
        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setLong(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return (getTaskFromResultSet(resultSet));
            }
        } catch (SQLException e) {
            e.printStackTrace();

        }
        return null;
    }

    public List<Task> getAllTasks() {
        String sql = "SELECT * FROM task_manager.tasks";
        List<Task> tasks = new ArrayList<>();
        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                tasks.add(getTaskFromResultSet(resultSet));
            }
            return tasks;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }

    }


    private Task getTaskFromResultSet(ResultSet resultSet) {
        try {
            return Task.builder()
                    .id(resultSet.getLong(1))
                    .description(resultSet.getString(2))
                    .taskStatus(TaskStatus.valueOf(resultSet.getString(3)))
                    .deadline(sdf.parse(resultSet.getString(4)) != null ? sdf.parse(resultSet.getString(4)) : null)
                    .user(userManager.getById(resultSet.getLong(5)))
                    .build();
        } catch (SQLException | ParseException e) {
            e.printStackTrace();
            return null;
        }
    }

    public boolean changeUser(Task task, long id) {
        String sql = "UPDATE task_manager.tasks SET assigned_user_id = ? WHERE id = ?";
        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setLong(1, id);
            statement.setLong(2, task.getId());
            statement.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;

    }

}