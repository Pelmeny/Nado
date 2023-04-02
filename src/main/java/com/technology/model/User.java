package com.technology.model;

import lombok.Value;
import lombok.experimental.NonFinal;

@Value
@NonFinal
public class User {

  Long id;
  String name;
  String password;

}
