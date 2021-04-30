package ua.com.foxminded.classtimetable.database;

import org.springframework.core.io.InputStreamResource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.init.ScriptException;
import org.springframework.jdbc.datasource.init.ScriptUtils;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;
import java.io.IOException;
import java.net.URISyntaxException;
import java.sql.SQLException;

@Component
public class DBManager {

    private final JdbcTemplate jdbcTemplate;

    public DBManager(DataSource dataSource) {
        jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @PostConstruct
    private void createAndFillTables() throws IOException, URISyntaxException, ScriptException, SQLException {
        ScriptUtils.executeSqlScript(jdbcTemplate.getDataSource().getConnection(),
                new InputStreamResource(getClass().getClassLoader().getResourceAsStream("db_scripts/buildingsFilling.sql")));
        ScriptUtils.executeSqlScript(jdbcTemplate.getDataSource().getConnection(),
                new InputStreamResource(getClass().getClassLoader().getResourceAsStream("db_scripts/classroomsFilling.sql")));
        ScriptUtils.executeSqlScript(jdbcTemplate.getDataSource().getConnection(),
                new InputStreamResource(getClass().getClassLoader().getResourceAsStream("db_scripts/coursesFilling.sql")));
        ScriptUtils.executeSqlScript(jdbcTemplate.getDataSource().getConnection(),
                new InputStreamResource(getClass().getClassLoader().getResourceAsStream("db_scripts/facultiesFilling.sql")));
        ScriptUtils.executeSqlScript(jdbcTemplate.getDataSource().getConnection(),
                new InputStreamResource(getClass().getClassLoader().getResourceAsStream("db_scripts/studentsFilling.sql")));
        ScriptUtils.executeSqlScript(jdbcTemplate.getDataSource().getConnection(),
                new InputStreamResource(getClass().getClassLoader().getResourceAsStream("db_scripts/studentsCoursesFilling.sql")));
        ScriptUtils.executeSqlScript(jdbcTemplate.getDataSource().getConnection(),
                new InputStreamResource(getClass().getClassLoader().getResourceAsStream("db_scripts/teachersFilling.sql")));
        ScriptUtils.executeSqlScript(jdbcTemplate.getDataSource().getConnection(),
                new InputStreamResource(getClass().getClassLoader().getResourceAsStream("db_scripts/teachersCoursesFilling.sql")));
        ScriptUtils.executeSqlScript(jdbcTemplate.getDataSource().getConnection(),
                new InputStreamResource(getClass().getClassLoader().getResourceAsStream("db_scripts/lessonsFilling.sql")));
    }

}
