package ru.otus.hw09.core.model;

import ru.otus.hw09.jdbc.mapper.annotations.Id;

/**
 * @author sergey
 * created on 03.02.19.
 */
public class User {
  @Id
  private final long id;
  private final String name;

  public User(){
    this.id =0;
    this.name = "";
  }

  public User(long id, String name) {
    this.id = id;
    this.name = name;
  }

  public long getId() {
    return id;
  }

  public String getName() {
    return name;
  }

  @Override
  public String toString() {
    return "User{" +
        "id=" + id +
        ", name='" + name + '\'' +
        '}';
  }
}
