package ru.otus;

public class ExampleTest {
    @Before
    public void StartUpOne() {
    }

    @Before
    public void StartUpTwo() {
    }

    @After
    public void PullUp() {
    }

    @Test
    public void MethodSucess() {
    }

    @Test
    public void MethodSucessTwo() {
    }

    @Test
    public void MethodFailure() {
        if (0==0) throw new IllegalArgumentException();
        System.out.println("");
    }
}
