package ru.otus.hw09.jdbc.mapper;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.util.*;
import java.util.stream.Collectors;

import ru.otus.hw09.jdbc.mapper.Interfaces.EntityClassMetaData;
import ru.otus.hw09.jdbc.mapper.annotations.Id;

public class EntityClassMetaDataImpl<T> implements EntityClassMetaData<T> {
    private Class<T> clazz;
    private String name;
    private Constructor<T> ctor;
    private Field id;
    private List<Field> fldswid;

    public EntityClassMetaDataImpl(Class<T> clazz) throws NoSuchMethodException {
        if (Objects.isNull(clazz)) throw new NullPointerException("clazz can't null");
        this.clazz=clazz;
        name = clazz.getSimpleName().toLowerCase();
        ctor = clazz.getDeclaredConstructor();
        LinkedList<Field> ids = getAllFields().stream()
                .filter(e -> e.isAnnotationPresent(Id.class)).collect(Collectors.toCollection(LinkedList::new));
        id = ids.getFirst();
        fldswid = getAllFields().stream()
                .filter(e->!e.isAnnotationPresent(Id.class))
                .collect(Collectors.toList());
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public Constructor<T> getConstructor()  {
        return ctor;
    }

    @Override
    public Field getIdField() {
        return id;
    }

    @Override
    public List<Field> getAllFields() {
        return Arrays.asList(clazz.getDeclaredFields());
    }

    @Override
    public List<Field> getFieldsWithoutId() {
        return fldswid;
    }
}
