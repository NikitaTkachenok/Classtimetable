package ua.com.foxminded.classtimetable.database;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Paths;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.springframework.core.io.FileSystemResource;
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

	public void createTables() throws IOException, URISyntaxException, ScriptException, SQLException {
		ScriptUtils.executeSqlScript(jdbcTemplate.getDataSource().getConnection(),
				new FileSystemResource(Paths.get(ClassLoader.getSystemResource("tablesCreation.sql").toURI())));
	}

	public void fillTables() throws IOException, URISyntaxException, SQLException {
		ScriptUtils.executeSqlScript(jdbcTemplate.getDataSource().getConnection(),
				new FileSystemResource(Paths.get(ClassLoader.getSystemResource("buildingsFilling.sql").toURI())));
		ScriptUtils.executeSqlScript(jdbcTemplate.getDataSource().getConnection(),
				new FileSystemResource(Paths.get(ClassLoader.getSystemResource("classroomsFilling.sql").toURI())));
		ScriptUtils.executeSqlScript(jdbcTemplate.getDataSource().getConnection(),
				new FileSystemResource(Paths.get(ClassLoader.getSystemResource("coursesFilling.sql").toURI())));
		ScriptUtils.executeSqlScript(jdbcTemplate.getDataSource().getConnection(),
				new FileSystemResource(Paths.get(ClassLoader.getSystemResource("facultiesFilling.sql").toURI())));
		ScriptUtils.executeSqlScript(jdbcTemplate.getDataSource().getConnection(),
				new FileSystemResource(Paths.get(ClassLoader.getSystemResource("studentsFilling.sql").toURI())));
		ScriptUtils.executeSqlScript(jdbcTemplate.getDataSource().getConnection(),
				new FileSystemResource(Paths.get(ClassLoader.getSystemResource("studentsCoursesFilling.sql").toURI())));
		ScriptUtils.executeSqlScript(jdbcTemplate.getDataSource().getConnection(),
				new FileSystemResource(Paths.get(ClassLoader.getSystemResource("teachersFilling.sql").toURI())));
		ScriptUtils.executeSqlScript(jdbcTemplate.getDataSource().getConnection(),
				new FileSystemResource(Paths.get(ClassLoader.getSystemResource("teachersCoursesFilling.sql").toURI())));
		ScriptUtils.executeSqlScript(jdbcTemplate.getDataSource().getConnection(),
				new FileSystemResource(Paths.get(ClassLoader.getSystemResource("lessonsFilling.sql").toURI())));
	}
}
