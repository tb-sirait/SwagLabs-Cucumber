Feature: Login to SauceDemo Apps

  Background:
    Given User is on Sauce Demo page "https://www.saucedemo.com/"

  @ValidCredentials
  Scenario: Login with valid credentials

    When User enters username as "standard_user" and password as "secret_sauce"
    Then User should be able to login sucessfully and new page open

  @InvalidCredentials
  Scenario Outline: Login with invalid credentials

    When User enters username as "<username>" and password as "<password>"
    Then User should be able to see error message "<errorMessage>"

    Examples:
      | username      | password     | errorMessage                                                              |
      | kakao         | kakao123     | Epic sadface: Username and password do not match any user in this service |
      | standard_user | admin123     | Epic sadface: Username and password do not match any user in this service |
      | kekeo         | secret_sauce | Epic sadface: Username and password do not match any user in this service |