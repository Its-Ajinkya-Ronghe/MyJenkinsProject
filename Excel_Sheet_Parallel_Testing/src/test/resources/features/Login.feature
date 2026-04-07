Feature: DemoQA Login Test with Excel

  Scenario Outline: Login Test Cases
    Given user opens the browser
    When user navigates to "https://demoqa.com/login"
    And user enters username "<username>" and password "<password>"
    Then login should be "<status>"

    Examples:
<<<<<<< HEAD
      | username     | password    | status  |
      | excel        | excel       | excel   |
=======
      | username     | password    | status |
      | validUser1@  | validPass1@ | pass   |
      | validUser2@  | validPass2@ | fail   |
      | validUser3   | validPass3  | fail   |
      | validUser4   | validPass4  | fail   |
      | validUser5   | validPass5  | fail   |
      | validUser6   | validPass6  | fail   |
      | validUser7   | validPass7  | fail   |
      | validUser8   | validPass8  | fail   |
      | invalidUser1 | wrongPass1  | fail   |
      | invalidUser2 | wrongPass2  | fail   |
>>>>>>> cb7c773 (initial commit)
