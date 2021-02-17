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
				new InputStreamResource(getClass().getClassLoader().getResourceAsStream("tablesCreation.sql")));
		ScriptUtils.executeSqlScript(jdbcTemplate.getDataSource().getConnection(),
				new InputStreamResource(getClass().getClassLoader().getResourceAsStream("buildingsFilling.sql")));
		ScriptUtils.executeSqlScript(jdbcTemplate.getDataSource().getConnection(),
				new InputStreamResource(getClass().getClassLoader().getResourceAsStream("classroomsFilling.sql")));
		ScriptUtils.executeSqlScript(jdbcTemplate.getDataSource().getConnection(),
				new InputStreamResource(getClass().getClassLoader().getResourceAsStream("coursesFilling.sql")));
		ScriptUtils.executeSqlScript(jdbcTemplate.getDataSource().getConnection(),
				new InputStreamResource(getClass().getClassLoader().getResourceAsStream("facultiesFilling.sql")));
		ScriptUtils.executeSqlScript(jdbcTemplate.getDataSource().getConnection(),
				new InputStreamResource(getClass().getClassLoader().getResourceAsStream("studentsFilling.sql")));
		ScriptUtils.executeSqlScript(jdbcTemplate.getDataSource().getConnection(),
				new InputStreamResource(getClass().getClassLoader().getResourceAsStream("studentsCoursesFilling.sql")));
		ScriptUtils.executeSqlScript(jdbcTemplate.getDataSource().getConnection(),
				new InputStreamResource(getClass().getClassLoader().getResourceAsStream("teachersFilling.sql")));
		ScriptUtils.executeSqlScript(jdbcTemplate.getDataSource().getConnection(),
				new InputStreamResource(getClass().getClassLoader().getResourceAsStream("teachersCoursesFilling.sql")));
		ScriptUtils.executeSqlScript(jdbcTemplate.getDataSource().getConnection(),
				new InputStreamResource(getClass().getClassLoader().getResourceAsStream("lessonsFilling.sql")));
	}

}
