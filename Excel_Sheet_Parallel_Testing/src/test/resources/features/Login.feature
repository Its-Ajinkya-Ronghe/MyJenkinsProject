Feature: DemoQA Login Test with Excel

  Scenario Outline: Login Test Cases
    Given user opens the browser
    When user navigates to "https://demoqa.com/login"
    And user enters username "<username>" and password "<password>"
    Then login should be "<status>"

    Examples:
      | username     | password    | status  |
      | excel        | excel       | excel   |