package com.bartek.GitHub.service;

import com.bartek.GitHub.client.GitHubClient;
import com.bartek.GitHub.model.Dtos.Branch;
import com.bartek.GitHub.model.Dtos.Repo;
import com.bartek.GitHub.model.Dtos.Subs;
import com.bartek.GitHub.model.Dtos.User;
import com.bartek.GitHub.model.Entity.GitHubRepo;
import com.bartek.GitHub.model.Entity.GitHubUser;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class GitHubServiceTest {
    @InjectMocks
    GitHubService gitHubService;
    @Mock
    GitHubClient gitHubClient;

    @Test
    public void showUserRepos_dataFound_dataReturned() {
        String username = "kbartek77";
        User userDetails = new User();
        List<Repo> userRepos = Arrays.asList(new Repo(), new Repo());
        when(gitHubClient.showUserDetails(username)).thenReturn(userDetails);
        when(gitHubClient.showUserRepos(username)).thenReturn(userRepos);

        GitHubUser result = gitHubService.showUserRepos(username);

        assertEquals(userDetails, result.getDetails());
        assertEquals(userRepos, result.getRepos());
    }

    @Test
    public void showReposDetails_dataFound_dataReturned() {
        String owner = "kbartek77";
        String repoName = "Medical";
        Repo repoDetails = new Repo();
        List<Branch> branches = Arrays.asList(new Branch());
        List<Subs> subscribers = Arrays.asList(new Subs());

        when(gitHubClient.showUserOneRepo(owner, repoName)).thenReturn(repoDetails);
        when(gitHubClient.showAllBranches(owner, repoName)).thenReturn(branches);
        when(gitHubClient.showAllSubscribers(owner, repoName)).thenReturn(subscribers);

        GitHubRepo result = gitHubService.showReposDetails(owner, repoName);

        assertEquals(repoDetails, result.getDetails());
        assertEquals(branches, result.getBranches());
        assertEquals(subscribers, result.getSubscribres());
    }
}
