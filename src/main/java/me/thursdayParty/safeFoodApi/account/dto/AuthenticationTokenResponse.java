package me.thursdayParty.safeFoodApi.account.dto;

import me.thursdayParty.safeFoodApi.account.AccountRole;

public class AuthenticationTokenResponse {
    
     private String username;
     private AccountRole role;
     private String token;
    
     public AuthenticationTokenResponse(String username, AccountRole role, String token) {
          this.username = username;
          this.role = role;
          this.token = token;
     }
    
     public String getUsername() {
          return username;
     }
     public void setUsername(String username) {
          this.username = username;
     }
     public AccountRole getAuthorities() {
          return role;
     }
     public void setAuthorities(AccountRole role) {
          this.role = role;
     }
     public String getToken() {
          return token;
     }
     public void setToken(String token) {
          this.token = token;
     }   
}