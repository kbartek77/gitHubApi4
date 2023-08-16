package com.bartek.GitHub.model.Entity;

import com.bartek.GitHub.model.Dtos.Repo;
import com.bartek.GitHub.model.Dtos.User;
import lombok.*;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GitHubUser {
    private User details;
    private List<Repo> repos;
}
