package org.kirill.api.error.test;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Entity
@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class User {

    @Id
    @GeneratedValue
    private Long id;

    @NotBlank
    @Pattern(regexp = "[a-z]{5,20}")
    private String username;

    @NotBlank
    @Size(max = 200)
    private String descriptionOfSomething;

    @Size(max = 100)
    private String status;
}
