package com.bartek.GitHub.model.Dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Repo {
    private String name;
    private String full_name;
    private String description;
}
