package de.ait.javalessons.homework.homework02;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class TVProgramAppTest {

    private static List<TVProgram> tvProgramList =
            Arrays.asList(
                    new TVProgram("Channel One", "Morning News", 30, true, 7.8),
                    new TVProgram("Channel One", "Late Show", 45, false, 8.1),
                    new TVProgram("SportsTV", "Football Match", 120, true, 9.2),
                    new TVProgram("MovieMax", "Action Movie", 110, false, 8.5),
                    new TVProgram("MovieMax", "Romantic Comedy", 100, false, 6.9),
                    new TVProgram("EduChannel", "Science Doc", 60, false, 7.5),
                    new TVProgram("ComedyFun", "Stand-up Special", 90, false, 8.2),
                    new TVProgram("ComedyFun", "Improv Show", 25, true, 7.3)
            );


    @ParameterizedTest
    @ValueSource(doubles = {8.0, 7.5, 9.0})
    @DisplayName("Фильтрация по рейтингу")
    void testFindTVProgramsAboutRating(double rating) {
        TVProgramApp app = new TVProgramApp();
        List<TVProgram> resultList = app.findTVProgramsAboutRating(tvProgramList, rating);
        assertFalse(resultList.isEmpty());
        assertTrue(resultList.stream()
                .allMatch(program -> program.getRating() >= rating));
    }
}
