# E-Workers

## Project Description

E-Workers is a home services Android application for hiring workers for day to day tasks. The aim of the app is to make these tasks easy and convenient for the users. Workers can be hired on the app with a few simple clicks. The app will provide an authentication system for login and signup. The user will also verify his phone number for successful profile completion. Every order will contain the location and details of the customer. The admin will be notified of these details and can proceed accordingly to deliver the services that the customer has asked for. Since the app needs to be accessed by various online users, it will use Firebase Firestore as the database and Firebase Authentication for authentication of user credentials.

## Functional requirements and Architectural analysis

* For the Users:
    1. Login
        * Login with email and password.
        * Login using Google account.

    2. Signup
        * Sign up with email and password.
        * Sign up using a Google account.

    3. Order a home service
        * By selecting a type of service.
        * Selecting the area (using google map API) where the service is required.
        * System shall show order completion when it is placed successfully.

    4. View order history
    5. Share app
    6. View profile details

* For the Admin:

    1. View history of ordered services of all customers
    2. Delete order of a particular customer
    3. Display orders in sorted order
    4. View location of a particular order using Google Map API

* Common Functionalities:

    1. Phone Verification
    2. View list of available home services
    3. View details of a particular home service
    4. Logout

## Project Status: In-Progress 🚧