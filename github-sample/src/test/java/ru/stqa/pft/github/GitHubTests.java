package ru.stqa.pft.github;

import com.google.common.collect.ImmutableMap;
import com.jcabi.github.*;
import org.testng.annotations.Test;

import java.io.IOException;


public class GitHubTests {
  @Test
  public void testCommits() throws IOException {
    Github github = new RtGithub("c91d7ea12f2be339801b0da79c94ef5d3c854761");
    RepoCommits commits = github.repos().get(new Coordinates.Simple("skrabets", "java_pft")).commits();
    for (RepoCommit commit : commits.iterate(new ImmutableMap.Builder<String, String>().build())) {
      System.out.println(new RepoCommit.Smart(commit).message());

    }
  }
}
