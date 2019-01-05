Feature: Create New Event
  Ensure that event is created successfully

  Scenario Outline: Create Event
    Given User Login with credentials
    And Go to create event page
    When Fill Event Form with "<title>" , "<description>" , "<recipients>","<date>","<From>","<To>","<ReservationNeeded>", "<EventFor>","<ReplyDeadLine>",
    Then I should find the event in calendar with date "<date>"
    And  event details should have "<date>" , "<From>" , "<To>" ,"<ReplyDeadLine>" and "<recipients>"

    Examples:
      | title       | description | recipients                                                 | date     | From     | To       | ReservationNeeded | EventFor      | ReplyDeadLine |
      | test_event  | automation  | Kids Palace (Children and staff),AFB Class (Children only) | 1/4/2019 | 01:30 PM | 05:30 PM | true              | Children Only | 1/3/2019      |
