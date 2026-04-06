Feature: Open different web pages

  Scenario: Open Example homepage
    Given user opens the browser
    When user navigates to "https://example.com"
    Then page title should contain "Example"

  Scenario: Open Google homepage
    Given user opens the browser
    When user navigates to "https://www.google.com"
    Then page title should contain "Google"

  Scenario: Open Wikipedia homepage
    Given user opens the browser
    When user navigates to "https://www.wikipedia.org"
    Then page title should contain "Wikipedia"