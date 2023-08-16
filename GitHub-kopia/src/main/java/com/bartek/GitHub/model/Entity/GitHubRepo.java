package com.bartek.GitHub.model.Entity;

import com.bartek.GitHub.model.Dtos.*;
import lombok.*;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class GitHubRepo {
    private Repo details;
    private List<Branch> branches;
    private List<Subs> subscribres;
}
