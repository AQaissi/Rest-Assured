# Rest-Assured

API tests:

- create a user using: POST https://reqres.in/api/users
Payload: { "name": “NAME”, "job": "JOB" } -> extract the id from the returned response ->  print it out

- get the data of the user where id = 7 using: GET https://reqres.in/api/users/
-> extract the first and last names of the returned user and print it out

- negative test that will get the data of a invalid id using the same API
