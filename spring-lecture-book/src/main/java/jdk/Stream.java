package jdk;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Stream;

class BeforeStream {
    public static void main(String[] args){
        // stream 사용 전
        String[] marvelMovies = {"IronMan", "Captain", "Hulk", "Thor"};
        List<String> marvelMovieList = Arrays.asList(marvelMovies);

        // 원본 데이터를 직접 정렬함
        Arrays.sort(marvelMovies);
        Collections.sort(marvelMovieList);

        for (String str : marvelMovieList){
            System.out.println("marvelMovie (array) : " + str);
        }

        for (String str : marvelMovies){
            System.out.println("marvelMovie (list) : " + str);
        }
    }
}

class AfterStream {
    public static void main(String[] args){
        String[] marvelMovies = {"IronMan", "Captain", "Hulk", "Thor"};
        List<String> marvelMovieList = Arrays.asList(marvelMovies);

        // 원본 데이터를 변경하지 않고 별도의 Stream을 생성함
        Stream<String> nameStream = marvelMovieList.stream();
        Stream<String> arrayStream = Arrays.stream(marvelMovies);

        nameStream.sorted().forEach(n -> System.out.println(n));
        arrayStream.sorted().forEach(System.out::println);
    }
}