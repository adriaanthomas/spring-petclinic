Feature: find veterinarian
  As a pet owner
  I want to be able to search for a vet by name
  so that I can quickly find my vet in case I need him

  Scenario: find a vet
    # note: we are taking a shortcut here in that we don't control the test data, as it is stored in an in-memory
    # database - in real life we would set the test data up here
    Given a vet with name "Linda Douglas"
    When a user searches for a vet by typing "doug" in the search box
    Then this vet should be the only result