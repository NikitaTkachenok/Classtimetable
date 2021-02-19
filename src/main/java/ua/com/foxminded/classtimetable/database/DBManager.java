package ua.com.foxminded.classtimetable.database;

import java.io.IOException;
import java.net.URISyntaxException;
import java.sql.SQLException;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;

import org.springframework.core.io.InputStreamResource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.init.ScriptException;
import org.springframework.jdbc.datasource.init.ScriptUtils;
import org.springframework.stereotype.Component;

@Component
public class DBManager {

	private final JdbcTemplate jdbcTemplate;

	public DBManager(DataSource dataSource) {
		jdbcTemplate = new JdbcTemplate(dataSource);
	}

	@PostConstruct
	private void createAndFillTables() throws IOException, URISyntaxException, ScriptException, SQLException {
		ScriptUtils.executeSqlScript(jdbcTemplate.getDataSource().getConnection(),
				new InputStreamResource(getClass().getClassLoader().getResourceAsStream("DBSripts/tablesCreation.sql")));
		ScriptUtils.executeSqlScript(jdbcTemplate.getDataSource().getConnection(),
				new InputStreamResource(getClass().getClassLoader().getResourceAsStream("DBSripts/buildingsFilling.sql")));
		ScriptUtils.executeSqlScript(jdbcTemplate.getDataSource().getConnection(),
				new InputStreamResource(getClass().getClassLoader().getResourceAsStream("DBSripts/classroomsFilling.sql")));
		ScriptUtils.executeSqlScript(jdbcTemplate.getDataSource().getConnection(),
				new InputStreamResource(getClass().getClassLoader().getResourceAsStream("DBSripts/coursesFilling.sql")));
		ScriptUtils.executeSqlScript(jdbcTemplate.getDataSource().getConnection(),
				new InputStreamResource(getClass().getClassLoader().getResourceAsStream("DBSripts/facultiesFilling.sql")));
		ScriptUtils.executeSqlScript(jdbcTemplate.getDataSource().getConnection(),
				new InputStreamResource(getClass().getClassLoader().getResourceAsStream("DBSripts/studentsFilling.sql")));
		ScriptUtils.executeSqlScript(jdbcTemplate.getDataSource().getConnection(),
				new InputStreamResource(getClass().getClassLoader().getResourceAsStream("DBSripts/studentsCoursesFilling.sql")));
		ScriptUtils.executeSqlScript(jdbcTemplate.getDataSource().getConnection(),
				new InputStreamResource(getClass().getClassLoader().getResourceAsStream("DBSripts/teachersFilling.sql")));
		ScriptUtils.executeSqlScript(jdbcTemplate.getDataSource().getConnection(),
				new InputStreamResource(getClass().getClassLoader().getResourceAsStream("DBSripts/teachersCoursesFilling.sql")));
		ScriptUtils.executeSqlScript(jdbcTemplate.getDataSource().getConnection(),
				new InputStreamResource(getClass().getClassLoader().getResourceAsStream("DBSripts/lessonsFilling.sql")));
	}

}
