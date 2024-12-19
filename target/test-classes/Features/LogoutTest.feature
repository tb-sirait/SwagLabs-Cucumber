Feature: Logout to Sauce Demo Apps

    Background:
    As a user
    I want to be able to log out from the application
    So that I can end my session securely

    Scenario: User successfully logs out from the application
        Given User is on the login page "https://www.saucedemo.com/"
        When the user logs in with valid credentials with username "standard_user" and password "secret_sauce"
        Then the user is redirected to the inventory page
        When the user clicks the burger button on the top left corner
        And the user clicks the logout button
        Then the user is redirected to the login page