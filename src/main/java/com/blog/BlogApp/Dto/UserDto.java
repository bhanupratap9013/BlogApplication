/**
 * 
 */
package com.blog.BlogApp.Dto;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.hibernate.validator.constraints.Length;

import com.blog.BlogApp.entities.Post;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author bhanu
 *
 */


@Data // Generates getters, setters, toString, equals, and hashCode methods
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {
    private Long userId;
    
    
    @Size(min=5, max = 10, message = "Username cannot exceed 10 characters")
    private String userName;
    
    
    @Email
    private String userEmail;
    
    
    @Size(min=8, max = 100, message = "Password cannot exceed 100 characters")
    private String userPassword;
    
    @NotEmpty
    private String about;
    
    private Set<RoleDto> roles = new HashSet();
}







