package com.bartek.GitHub.client;


import com.bartek.GitHub.model.Dtos.*;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@FeignClient(name = "repository-details", url = "${api.github.url}")
public interface GitHubClient {
    @RequestMapping(method = RequestMethod.GET, value = "/repos/{owner}/{repo}/branches")
    List<Branch> showAllBranches(@PathVariable String owner, @PathVariable String repo);

    @RequestMapping(method = RequestMethod.GET, value = "/repos/{owner}/{repo}")
    Repo showUserOneRepo(@PathVariable String owner, @PathVariable String repo);

    @RequestMapping(method = RequestMethod.GET, value = "/users/{username}")
    User showUserDetails(@PathVariable String username);

    @RequestMapping(method = RequestMethod.GET, value = "/repos/{owner}/{repo}/subscribers")
    List<Subs> showAllSubscribers(@PathVariable String owner, @PathVariable String repo);

    @RequestMapping(method = RequestMethod.GET, value = "/users/{username}/repos")
    List<Repo> showUserRepos(@PathVariable String username);
}
