package org.exoplatform.rest;

import org.testng.Assert;
import org.testng.annotations.Test;

public class UserServiceTest {
  @Test
  public void testUserFetchesSuccess() {
    UserService userService = new UserService();
    User user = userService.findById(123l);
    Assert.assertNotNull(user);
    Assert.assertEquals(user.getId().longValue(), 123l);
    Assert.assertEquals(user.getFirstName(), "Tim");
  }

  @Test
  public void testUserNotFound() {
    UserService userService = new UserService();
    User user = userService.findById(666l);
    Assert.assertNull(user);
  }
}
