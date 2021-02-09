Feature: projectOne log in

	Scenario Outline: Log in works
		Given the guest is on the login page
		When the guest puts in credentials "<username>" and a "<pass>" then hits enter
		Then the guest should be on "<title>"
	
	Examples:
		|	username		|	pass		|	title		|	
		|	BigBoss			|	ssssss		|	Status Page	|