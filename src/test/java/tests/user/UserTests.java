package tests.user;

import com.rest.models.user.requests.CreateUserRequest;
import com.rest.models.user.responses.UserResponse;
import com.rest.step.UserSteps;
import com.rest.utills.StringMan;
import org.testng.annotations.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class UserTests {

    private final UserSteps userSteps = new UserSteps();

    @Test(description = "Create user")
    public void createUser() {
        int id = StringMan.getRandomNumber(3);
        String userName = StringMan.getRandomString(5);
        String email = StringMan.makeUniqueEmail("test@test.com");

        UserResponse response = userSteps.createUser(200, getCreateUserRequest(id, userName, email));
        assertThat(response.getCode()).isEqualTo(200);
    }

    @Test(description = "Create list of users")
    public void createListOfUsers() {
        int firstUserId = StringMan.getRandomNumber(3);
        String firstUserName = StringMan.getRandomString(5);
        String firstUserEmail = StringMan.makeUniqueEmail("test@test.com");

        int secondUserId = StringMan.getRandomNumber(3);
        String secondUserName = StringMan.getRandomString(5);
        String secondUserEmail = StringMan.makeUniqueEmail("test@test.com");

        UserResponse response =
                userSteps.createListOfUsers(200, getCreateUserRequest(firstUserId, firstUserName, firstUserEmail),
                        getCreateUserRequest(secondUserId, secondUserName, secondUserEmail));
        assertThat(response.getCode()).isEqualTo(200);
    }

    @Test(description = "Log in")
    public void logIn() {
        UserResponse response = userSteps.logIn(200, "jon01", "qwerty13");
        assertThat(response.getCode()).isEqualTo(200);
        assertThat(response.getMessage()).contains("logged in user session:");
    }

    @Test(description = "Log out")
    public void logOut() {
        UserResponse response = userSteps.logOut(200);
        assertThat(response.getCode()).isEqualTo(200);
    }

    private CreateUserRequest getCreateUserRequest(int id, String username, String email) {
        return new CreateUserRequest.Builder().setDefaultValues()
                .id(id)
                .userName(username)
                .email(email)
                .build();
    }
}
