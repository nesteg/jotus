package ru.otus;

public interface Media {
    @Log
    void Open(String resource);
    @Log
    void Start(int start,float velocity);
    @Log
    void Close();
    @Log
    void Stop(boolean forced);

    String Info();
}

