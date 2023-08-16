package com.bartek.GitHub.controller;

import com.bartek.GitHub.model.Entity.GitHubRepo;
import com.bartek.GitHub.model.Entity.GitHubUser;
import com.bartek.GitHub.service.GitHubService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/github-details")
public class GitHubController {
    private final GitHubService gitHubService;

    @Operation(summary = "Get user data", tags = "User")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK",
                    content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = GitHubUser.class))}),
            @ApiResponse(responseCode = "404", description = "User Not Found", content = @Content),
            @ApiResponse(responseCode = "500", description = "Server github dosent work", content = @Content)
    })
    @GetMapping("/user/{username}")
    public GitHubUser getUserRepos(@PathVariable String username) {
        return gitHubService.showUserRepos(username);
    }

    @Operation(summary = "Get repo data from user", tags = "Repo")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK",
                    content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = GitHubRepo.class))}),
            @ApiResponse(responseCode = "404", description = "Repo or user not found", content = @Content)
    })
    @GetMapping("/repos/{owner}/{repo}")
    public GitHubRepo getReposDetails(@PathVariable String owner, @PathVariable String repo) {
        return gitHubService.showReposDetails(owner, repo);
        }
    }






