package ua.com.foxminded.classtimetable.database;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.SQLException;
import java.util.stream.Collectors;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

@Component
public class DBManager {

	private final JdbcTemplate jdbcTemplate;

	public DBManager(DataSource dataSource) {
		jdbcTemplate = new JdbcTemplate(dataSource);
	}

	public void createTables() throws IOException, URISyntaxException {
		String query = Files.lines(Paths.get(ClassLoader.getSystemResource("tablesCreation.sql").toURI()))
				.collect(Collectors.joining());

		jdbcTemplate.execute(query);
	}

	public void fillTables() throws IOException, URISyntaxException, SQLException {
		DataGenerator generatorOfEntities = new DataGenerator();
		ScheduleGenerator generatorOfSchedule = new ScheduleGenerator();
		generatorOfEntities.fillTables();
		generatorOfSchedule.formSchedule();
	}
}
