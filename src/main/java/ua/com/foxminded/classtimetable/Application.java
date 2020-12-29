package ua.com.foxminded.classtimetable;

import java.io.IOException;
import java.net.URISyntaxException;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import ua.com.foxminded.classtimetable.config.DBConfiguratoin;
import ua.com.foxminded.classtimetable.dao.BuildingDao;
import ua.com.foxminded.classtimetable.database.DBManager;
import ua.com.foxminded.classtimetable.entities.Building;

public class Application {

	public static void main(String[] args) throws IOException, URISyntaxException {

		try (AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(
				DBConfiguratoin.class);) {
			DBManager manager = context.getBean(DBManager.class);
			manager.createTables();
			BuildingDao bDao = context.getBean(BuildingDao.class);
			Building building = new Building();
			building.setBuildingName("First corpus");
			bDao.save(building);
			System.out.println(bDao.getById(1));
			building.setId(1);
			building.setBuildingName("Second corpus");
			bDao.update(building);
			System.out.println(bDao.getById(1));
			bDao.delete(building);
			System.out.println(bDao.getAll());
		}
	}

}
