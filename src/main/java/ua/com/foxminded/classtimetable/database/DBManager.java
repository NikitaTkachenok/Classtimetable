package ua.com.foxminded.classtimetable.database;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

@Component
public class DBManager {

	JdbcTemplate jdbcTemplate;

	@Autowired
	public DBManager(DataSource dataSource) {
		jdbcTemplate = new JdbcTemplate(dataSource);
	}

	public void createTables() throws IOException, URISyntaxException {
		List<String> query = Files.lines(Paths.get(ClassLoader.getSystemResource("tablesCreation.sql").toURI()))
				.collect(Collectors.toList());
		StringBuilder builder = new StringBuilder();
		for (String string : query) {
			builder.append(string);
		}
		jdbcTemplate.execute(builder.toString());
	}
}
