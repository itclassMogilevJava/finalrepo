package com.itclass.dto;

import lombok.Data;

@Data
public class LoginDto {

   private String username;

   private String password;
   private boolean admin;
}
