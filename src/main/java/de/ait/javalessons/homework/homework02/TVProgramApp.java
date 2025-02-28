package de.ait.javalessons.homework.homework02;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

public class TVProgramApp {
    private static List<TVProgram> result;

    public static void main(String[] args) {

        List<TVProgram> tvProgramList = TVProgramTestData.getTVProgramList();
        //   result = findTVProgramsAboutRating(tvProgramList, 8.0);
        //   for (TVProgram tvProgram : result) {
        //       System.out.println(tvProgram);
        //   }
        //   transferStringFormat(tvProgramList);
        //   findLivePrograms(tvProgramList);
        //   System.out.println(findLongestProgram(tvProgramList));
        // System.out.println(getAverageRating(tvProgramList));
        //System.out.println(getGroupsByChannel(tvProgramList));
        System.out.println(sortTVProgramsByRating(tvProgramList));

    }

    // Найдите все передачи, рейтинг которых выше заданного порога (например, > 8.0).
    public static List<TVProgram> findTVProgramsAboutRating(List<TVProgram> tvProgramList, double rating) {
        return tvProgramList.stream()
                .filter(tvProgram -> tvProgram.getRating() > rating)
                .collect(Collectors.toList());
    }

    //Преобразуйте объекты TVProgram в удобные для вывода строки. Например, сформируйте строку в формате:
    //"Канал: [channel] | Передача: [programName] | Рейтинг: [rating]"
    public static void transferStringFormat(List<TVProgram> tvProgramList) {
        List<String> collectResult = tvProgramList.stream()
                .map(tvProgram -> String.format("Канал: %s | Передача: %s | Рейтинг: %.1f:", tvProgram.getChannel(), tvProgram.getProgramName(), tvProgram.getRating()))
                .collect(Collectors.toList());

        for (String result : collectResult) {
            System.out.println(result);
        }
    }

    //Узнайте, есть ли среди всех передач хотя бы одна, которая идёт в прямом эфире (isLive == true).
    public static boolean findLivePrograms(List<TVProgram> tvProgramList) {
        boolean isLiveExists = tvProgramList.stream()
                .anyMatch(TVProgram::isLive);
        System.out.println(isLiveExists);
        return isLiveExists;
    }

    //Определите, какая из передач самая длительная (максимальное значение поля duration).
    public static TVProgram findLongestProgram(List<TVProgram> tvProgramList) {
        Optional<TVProgram> longestProgram = tvProgramList.stream()
                .max(Comparator.comparingInt(TVProgram::getDuration));
        return longestProgram.orElse(null);
    }

    //Используйте mapToDouble и average для вычисления среднего рейтинга всех передач.
    public static double getAverageRating(List<TVProgram> tvProgramList) {
        return tvProgramList.stream()
                .mapToDouble(TVProgram::getRating)
                .average()
                .orElse(-1);
    }

    //Используйте Collectors.groupingBy(TVProgram::getChannel) для создания карты, где ключ — это название канала,
    // а значение — список передач этого канала.
    public static Map<String, List<TVProgram>> getGroupsByChannel(List<TVProgram> tvProgramList) {
        Map<String, List<TVProgram>> result = tvProgramList.stream()
                .collect(Collectors.groupingBy(TVProgram::getChannel));
        return result;
    }

    //Отсортируйте список передач по названию канала или по рейтингу (например, в убывающем порядке).
    public static List<TVProgram> sortTVProgramsByRating(List<TVProgram> tvProgramList) {
        return tvProgramList.stream()
                .sorted(Comparator.comparingDouble(TVProgram::getRating).reversed())
                .limit(3)
                .collect(Collectors.toList());
    }

}
