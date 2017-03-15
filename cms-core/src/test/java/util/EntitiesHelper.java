package util;

import org.junit.Assert;
import org.bvvy.cms.model.Role;
import org.bvvy.cms.model.User;

import java.util.List;


public class EntitiesHelper {
	private static User baseUser = new User(1,"admin1","admin1","123","110","admin1@admin.com",1);
	
	public static void assertUser(User expected,User actual) {
		Assert.assertNotNull(expected);
		Assert.assertEquals(expected.getId(), actual.getId());
		Assert.assertEquals(expected.getUsername(), actual.getUsername());
		Assert.assertEquals(expected.getNickname(), actual.getNickname());
		Assert.assertEquals(expected.getPassword(), actual.getPassword());
		Assert.assertEquals(expected.getEmail(), actual.getEmail());
		Assert.assertEquals(expected.getPhone(), actual.getPhone());
		Assert.assertEquals(expected.getStatus(), actual.getStatus());
	}
	public static void assertRole(Role expected,Role actual) {
		Assert.assertNotNull(expected);
		Assert.assertEquals(expected.getId(), actual.getId());
		Assert.assertEquals(expected.getName(), actual.getName());
		Assert.assertEquals(expected.getRoleType(), actual.getRoleType());
	}

	public static void assertUsers(List<User> expected,List<User> actuals) {
		for(int i=0;i<expected.size();i++) {
			User eu = expected.get(i);
			User au = actuals.get(i);
			assertUser(eu, au);
		}
	}
	public static void assertRoles(List<Role> expected,List<Role> actuals) {
		for(int i=0;i<expected.size();i++) {
			Role eu = expected.get(i);
			Role au = actuals.get(i);
			assertRole(eu, au);
		}
	}
	
	public static void assertUser(User expected) {
		assertUser(expected, baseUser);
	}
}
