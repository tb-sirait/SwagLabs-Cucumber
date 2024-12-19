Feature: Add Cart

    Background:
        Given User access to website "https://www.saucedemo.com/"

        Scenario: User want to Add to User Cart on Sauce Demo
            Given User login with username "standard_user" and password "secret_sauce"
            When User is succesfully login with valid username and password
            Then User is directed to the inventory page

            Given User want to add Sauce Labs Backpack to cart
            When "Sauce Labs Backpack" is available in inventory
            Then add Sauce Labs Backpack into Cart

            Given User want to add Sauce Labs Fleece Jacket to cart
            When "Sauce Labs Fleece Jacket" is available in sauce demo inventory
            Then add Sauce Labs Fleece Jacket into Cart
