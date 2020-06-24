import com.google.gson.Gson;
import org.junit.jupiter.api.Test;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import ru.otus.hw08.Model.Child;
import ru.otus.hw08.Converter.JsonConverter;
import ru.otus.hw08.Model.Root;

import java.util.Arrays;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class JsonTest {

    @Test
    void ChildTest() throws IllegalAccessException {
        int[] numbers = {11,21,99};
        Child child = new Child(numbers,7.77E+1f,null,'A',89d);
        var gson = new Gson();
        var converter = new JsonConverter();
        String jsonConverter = converter.toJson(child);
        String jsonGson = gson.toJson(child);
        assertEquals(jsonConverter,jsonGson);
    }


    @Test
    void RootTest() throws IllegalAccessException {
        int[] numbersOne = {2054,6789,18765};
        int[] numbersTwo = {11,21,99};
        Child childOne = new Child(numbersOne,7.77E+1f,"RUS",'A',78d);
        Child childTwo = new Child(numbersTwo,5.39E+1f,null,'A',34d);
        Root root = new Root();
        root.setId(1L);
        root.setChilds(Arrays.asList(childOne,childTwo));
        root.setName("Main root");
        root.setBalance(1.000E+3d);
        root.setZip((short)30123);
        var gson = new Gson();
        var converter = new JsonConverter();
        String jsonConverter = converter.toJson(root);
        String jsonGson  = gson.toJson(root);
        assertEquals(jsonConverter,jsonGson);
    }

    @ParameterizedTest
    @MethodSource("generateDataForCustomTest")
    void customTest(Object o) throws IllegalAccessException {
        var gson = new Gson();
        var converter = new JsonConverter();
        assertEquals(gson.toJson(o), converter.toJson(o));
    }

    private static Stream<Arguments> generateDataForCustomTest() {
        return Stream.of(
                null,
                Arguments.of(true), Arguments.of(false),
                Arguments.of((byte)1), Arguments.of((short)2),
                Arguments.of(3), Arguments.of(4L), Arguments.of(5f), Arguments.of(6d),
                Arguments.of("aaa"), Arguments.of('b')
        );
    }

}
