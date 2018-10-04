Feature: As a End user of the api I want to verify the json response



  Scenario Outline: Verify the api gives response as per the request parameters
    When I send http query request using <httpRequest> <resource> and <input_fields> as <input_values>
    Then I validate <expected_fields> values as <expected_values> in response of webservice
    And I count <field> occurrence in response of webservice as  <expValue>


    Examples:
      |httpRequest|resource|input_fields|input_values|expected_fields         |expected_values                                                                       |field|expValue|
      |Api_Url    |comments|postId      |1           |postId__id__name__email |1__1__id labore ex et quam laborum__Eliseo@gardner.biz                                |postId|5      |
      |Api_Url    |comments|postId      |2           |postId__id__name__email |2__6__et fugit eligendi deleniti quidem qui sint nihil autem__Presley.Mueller@myrl.com|postId|5      |