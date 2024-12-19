Feature: Checkout goods from cart

    Background: User wants to buy some items on Sauce Demo Apps.
        Given User get access website "https://www.saucedemo.com/" and login to User Account
        When User is succesfully login with valid credential
        Then User can Access inventory

    Scenario: User want to Checkout goods from cart
        Given User is succesfully Add goods to cart
        When User want to buy goods which is add from Cart
        Then User must Checking Out from cart
        And Click the Checkout Button

        Given User must input shipping form
        When User input First Name, Last Name, and Postal Code
        Then User can submit form and moving to Checkout detail

        Given User checking Checkout Detail and already match with the items checked out
        When User agree to check out the items
        Then User can click Finish button


