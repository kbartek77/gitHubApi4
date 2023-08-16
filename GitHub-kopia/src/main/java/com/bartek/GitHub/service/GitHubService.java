package com.bartek.GitHub.service;

import com.bartek.GitHub.client.GitHubClient;
import com.bartek.GitHub.model.Dtos.*;

import com.bartek.GitHub.model.Entity.GitHubRepo;
import com.bartek.GitHub.model.Entity.GitHubUser;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class GitHubService {
    private final GitHubClient gitHubClient;

    public GitHubUser showUserRepos(String user) {
        User userDetails = gitHubClient.showUserDetails(user);
        List<Repo> userRepos = gitHubClient.showUserRepos(user);
        return GitHubUser.builder()
                .details(userDetails)
                .repos(userRepos)
                .build();
    }

    public GitHubRepo showReposDetails(String owner, String repo) {
        Repo repo1 = gitHubClient.showUserOneRepo(owner, repo);
        List<Branch> branches = gitHubClient.showAllBranches(owner, repo);
        List<Subs> subscribers = gitHubClient.showAllSubscribers(owner, repo);
        return GitHubRepo.builder()
                .details(repo1)
                .branches(branches)
                .subscribres(subscribers)
                .build();
    }
}

