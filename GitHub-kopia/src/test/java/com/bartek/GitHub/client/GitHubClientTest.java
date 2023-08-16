package com.bartek.GitHub.client;

import static com.github.tomakehurst.wiremock.client.WireMock.aResponse;
import static com.github.tomakehurst.wiremock.client.WireMock.get;
import static com.github.tomakehurst.wiremock.client.WireMock.stubFor;
import static com.github.tomakehurst.wiremock.client.WireMock.urlEqualTo;


import com.bartek.GitHub.model.Dtos.Branch;
import com.bartek.GitHub.model.Dtos.Repo;
import com.bartek.GitHub.model.Dtos.Subs;
import com.bartek.GitHub.model.Dtos.User;
import com.bartek.GitHub.model.Entity.GitHubRepo;
import com.bartek.GitHub.model.Entity.GitHubUser;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.tomakehurst.wiremock.WireMockServer;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cloud.contract.wiremock.AutoConfigureWireMock;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;


import java.util.Arrays;
import java.util.List;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT, properties = ("server.port=8282"))
@AutoConfigureWireMock(port = 8888)
public class GitHubClientTest {
    @Autowired
    ObjectMapper objectMapper;
    @Autowired
    RestTemplate restTemplate;
    @Autowired
    WireMockServer mockServer;

    @Test
    void showUserDetails_dataOk_userReturned() throws Exception {
        String username = "kbartek77";

        User user1 = new User();
        List<Repo> repos = List.of(new Repo());
        GitHubUser gitHubUser = GitHubUser.builder().details(user1).repos(repos).build();

        mockServer.stubFor(get(urlEqualTo("/users/"+username))
                .willReturn(aResponse()
                        .withHeader("Content-Type", "Application/json")
                        .withBody(objectMapper.writeValueAsString(gitHubUser))));

        mockServer.stubFor(get(urlEqualTo("/users/"+ username +"/repos"))
                        .willReturn(aResponse().
                                withHeader("Content-Type", "Application/json")
                                .withBody(objectMapper.writeValueAsString(repos))));

        ResponseEntity<GitHubUser> responseEntity = restTemplate.exchange("http://localhost:8282/github-details/user/" + username, HttpMethod.GET, null, new ParameterizedTypeReference<>() {
        });

        Assertions.assertEquals(gitHubUser, responseEntity.getBody());
    }
    @Test
    void showRepoDetails_dataOk_repoReturend() throws Exception {
        String owner = "kbartek77";
        String repo = "Medical";

        Repo repoDetails = new Repo();
        List<Branch> branches = Arrays.asList(new Branch());
        List<Subs> subscribers = Arrays.asList(new Subs());
        GitHubRepo gitHubRepo = GitHubRepo.builder().details(repoDetails).branches(branches).subscribres(subscribers).build();

        mockServer.stubFor(get(urlEqualTo("/repos/" + owner + "/" + repo))
                .willReturn(aResponse()
                        .withHeader("Content-Type", "Application/json")
                        .withBody(objectMapper.writeValueAsString(repoDetails))));

        mockServer.stubFor(get(urlEqualTo("/repos/" + owner +"/" + repo +"/branches"))
                .willReturn(aResponse().
                        withHeader("Content-Type", "Application/json")
                        .withBody(objectMapper.writeValueAsString(branches))));

        mockServer.stubFor(get(urlEqualTo("/repos/"+owner+"/"+ repo + "/subscribers"))
                .willReturn(aResponse().
                        withHeader("Content-Type", "Application/json")
                        .withBody(objectMapper.writeValueAsString(subscribers))));

        ResponseEntity<GitHubRepo> responseEntity = restTemplate.exchange("http://localhost:8282/github-details/repos/" + owner + "/" + repo, HttpMethod.GET, null, new ParameterizedTypeReference<>() {
        });
        Assertions.assertEquals(gitHubRepo, responseEntity.getBody());
    }
}

