package org.exoplatform.rest;

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.testng.Arquillian;
import org.jboss.shrinkwrap.api.ArchivePaths;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.formatter.Formatters;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.testng.annotations.Test;

import java.io.File;

import static com.jayway.restassured.RestAssured.expect;
import static com.jayway.restassured.RestAssured.get;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.nullValue;

@Test(groups = "integration")
public class UserServiceIT extends Arquillian {

  private final static String WEBAPP_CONTEXT = "rest";

  @Deployment
  public static WebArchive createTestableDeployment() {
    final WebArchive war = ShrinkWrap.create(WebArchive.class, WEBAPP_CONTEXT + ".war")
            .addClasses(User.class, UserService.class)
            .setWebXML(new File("src/main/webapp/WEB-INF/web.xml"));

    return war;
  }

  @Test
  public void testUserFetchesSuccess() {
    expect().
            body("id", equalTo("12")).
            body("firstName", equalTo("Tim")).
            body("lastName", equalTo("Tester")).
            body("birthday", equalTo("1970-01-16T07:56:49.871+01:00")).
            when().
            get("/" + WEBAPP_CONTEXT + "/user/id/12");
  }

  @Test
  public void testUserNotFound() {
    expect().
            body(nullValue()).
            when().
            get("/" + WEBAPP_CONTEXT + "/user/id/666");
  }
}
