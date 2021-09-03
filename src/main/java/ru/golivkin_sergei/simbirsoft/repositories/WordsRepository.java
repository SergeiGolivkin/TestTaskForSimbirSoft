package ru.golivkin_sergei.simbirsoft.repositories;

import ru.golivkin_sergei.simbirsoft.model.Words;

import java.sql.*;

public class WordsRepository implements CrudRepository<Words> {

    //language=SQL
    private static final String SQL_INSERT_WORDS = "insert into words(word, wordCount) values (?, ?)";


    // Создаем connection
    private Connection getConnection() {
        Connection connection = null;
        try {
            connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres",
                    "postgres", "123456");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return connection;
    }


    // Реализуем  метод сохранения данных в БД
    @Override
    public void save(Words entity) {

        Connection connection = getConnection();
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement(SQL_INSERT_WORDS);
            statement.setString(1, entity.getWord());
            statement.setInt(2, entity.getWordCount());

            int affectedRows = statement.executeUpdate();

            if (affectedRows != 1) {
                throw new SQLException("User save not executed");
            }

        } catch (SQLException e) {
            throw new IllegalStateException(e);
        } finally {
            if (statement != null) {
                try {
                    statement.close();
                } catch (SQLException ignored) {
                }
            }

        }

    }
}
