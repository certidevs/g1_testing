package com.demo;

import com.demo.model.Movie;
import com.demo.model.Room;
import com.demo.model.enums.ScreenType;
import com.demo.repository.MovieRepository;
import com.demo.repository.RoomRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import java.util.List;

@SpringBootApplication
public class G1TestingApplication {

    public static void main(String[] args) {
        var context = SpringApplication.run(G1TestingApplication.class, args);
    }
}
