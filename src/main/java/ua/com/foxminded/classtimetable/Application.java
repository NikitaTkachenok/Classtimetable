package ua.com.foxminded.classtimetable;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import ua.com.foxminded.classtimetable.config.DBConfiguration;

import java.io.IOException;
import java.net.URISyntaxException;
import java.sql.SQLException;

public class Application {

    public static void main(String[] args) throws IOException, URISyntaxException, SQLException {

        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(
                DBConfiguration.class);
        
        

        context.close();
    }
    
}
