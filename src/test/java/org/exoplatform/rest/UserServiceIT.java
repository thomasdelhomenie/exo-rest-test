package org.exoplatform.rest;

import org.testng.annotations.Test;

import static com.jayway.restassured.RestAssured.expect;
import static com.jayway.restassured.RestAssured.get;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.nullValue;

@Test(groups = "integration")
public class UserServiceIT {
  @Test
  public void testUserFetchesSuccess() {
    expect().
            body("id", equalTo("12")).
            body("firstName", equalTo("Tim")).
            body("lastName", equalTo("Tester")).
            body("birthday", equalTo("1970-01-16T07:56:49.871+01:00")).
            when().
            get("/user/id/12");
  }

  @Test
  public void testUserNotFound() {
    expect().
            body(nullValue()).
            when().
            get("/user/id/666");
  }
}
