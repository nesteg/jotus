package ru.otus;

public class Tv implements Media {
    @Override
    public void Open(String resource) {

    }

    @Override
    public void Start(int start,float velocity) {

    }

    @Override
    public void Close() {

    }

    @Override
    public void Stop(boolean forced) {

    }

    @Override
    public String Info(){
        return("TV Hight @Resolution 2560 x 1440");
    }
}
