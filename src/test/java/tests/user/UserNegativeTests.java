package tests.user;

import com.rest.models.user.requests.CreateUserRequest;
import com.rest.models.user.responses.UserResponse;
import com.rest.step.UserSteps;
import com.rest.utills.StringMan;
import org.testng.annotations.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class UserNegativeTests {

    private final UserSteps userSteps = new UserSteps();

    @Test(description = "Create user with missing email")
    public void createUserWithMissingEmail() {
        int id = StringMan.getRandomNumber(10000);
        String userName = StringMan.getRandomString(5);

        UserResponse response = userSteps.createUser(200, getCreateUserRequest(id, userName));
        assertThat(response.getCode()).isEqualTo(200);
    }

    @Test(description = "Create list of users wit missing emails")
    public void createListOfUsersWithMissingEmails() {
        int firstUserId = StringMan.getRandomNumber(10000);
        String firstUserName = StringMan.getRandomString(5);

        int secondUserId = StringMan.getRandomNumber(10000);
        String secondUserName = StringMan.getRandomString(5);

        UserResponse response =
                userSteps.createListOfUsers(200, getCreateUserRequest(firstUserId, firstUserName),
                        getCreateUserRequest(secondUserId, secondUserName));
        assertThat(response.getCode()).isEqualTo(200);
    }

    @Test(description = "Log in with wrong password")
    public void logInWithMissingEmail() {
        UserResponse response = userSteps.logIn(200, "jon01", "qwerty");
        assertThat(response.getCode()).isEqualTo(200);
    }

    private CreateUserRequest getCreateUserRequest(int id, String username) {
        return new CreateUserRequest.Builder().setDefaultValues()
                .id(id)
                .userName(username)
                .build();
    }
}
