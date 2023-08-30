Feature: Login page feature

  Background:
    Given User is on the login page of spec

  Scenario: Signing up in Spec tool by entering valid details
    Given User clicks on Sign up
    When user enters valid "damanisofia@gmail.com" "Sofia" "Damani" "Sofia1998" "Sofia1998"
    Then User clicks on Continue button
    And User enters "Spec" and "Wellington St"
    And User enters "Canada" "Ontario" "Scarborough" "M3C1S8"
    And User selects Organization type
    And User clicks on Next button
    Then User selects the role
    And User clicks on Sign up button
#    And User validates that Signup is successful
