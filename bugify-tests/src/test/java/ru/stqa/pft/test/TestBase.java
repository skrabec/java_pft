package ru.stqa.pft.test;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;
import io.restassured.RestAssured;
import org.testng.SkipException;
import ru.stqa.pft.model.Issue;

import java.util.Set;


public class TestBase {
  public Issue issueByID(int issueId) {
    String json = RestAssured.get(String.format("http://bugify.stqa.ru/api/issues/%s.json", issueId)).asString();
    JsonElement issues = new JsonParser().parse(json).getAsJsonObject().get("issues");
    Set<Issue> issueSet = new Gson().fromJson(issues, new TypeToken<Set<Issue>>() {
    }.getType());
    Issue issue = issueSet.iterator().next();
    return issue;
  }

  public boolean isIssueOpen(int issueId) {
    Issue issue = issueByID(issueId);
    String status = issue.getState_name();
    return !status.equals("Deleted") && !status.equals("Closed");
  }

  public void skipIfNotFixed(int issueId) {
    if (isIssueOpen(issueId)) {
      throw new SkipException("Ignored because of issue " + issueId);
    }
  }

}
