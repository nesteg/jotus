import com.google.gson.Gson;
import org.junit.jupiter.api.Test;

import ru.otus.hw08.Child;
import ru.otus.hw08.JsonConverter;
import ru.otus.hw08.Root;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class JsonTest {

    @Test
    void ChildTest() throws IllegalAccessException {
        int[] numbers = {11,21,99};
        Child child = new Child(numbers,7.77E+1f,null,'A');
        var gson = new Gson();
        var converter = new JsonConverter();
        String json = converter.toJson(child);
        Child child1 = gson.fromJson(json,Child.class);
        assertEquals(child,child1);
    }


    @Test
    void RootTest() throws IllegalAccessException {
        int[] numbersOne = {2054,6789,18765};
        int[] numbersTwo = {11,21,99};
        Child childOne = new Child(numbersOne,7.77E+1f,"RUS",'A');
        Child childTwo = new Child(numbersTwo,5.39E+1f,null,'A');
        Root root = new Root();
        root.setId(1L);
        root.setChilds(Arrays.asList(childOne,childTwo));
        root.setName("Main root");
        root.setBalance(1.000E+3d);
        root.setZip((short)30123);
        var gson = new Gson();
        var converter = new JsonConverter();
        String json = converter.toJson(root);
        Root root1 = gson.fromJson(json,Root.class);
        assertEquals(root,root1);
    }

}
