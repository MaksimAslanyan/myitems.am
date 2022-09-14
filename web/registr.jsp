<%@ page import="model.User" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Login | Registration</title>
    <head>
        <meta charset="UTF-8">
        <title>AnimaForm</title>
        <link href='https://fonts.googleapis.com/css?family=Roboto' rel='stylesheet' type='text/css'>
    </head>
    <link rel="stylesheet" href="dist/style.css">

</head>
<body>
<!-- partial:index.partial.html -->
<section class="forms-section">
    <h1 class="section-title">Login or Registration</h1>
    <div class="forms">
        <div class="form-wrapper is-active">
            <button type="button" class="switcher switcher-login">
                Login
                <span class="underline"></span>
            </button>
            <form action="/login" method="post" class="form form-login">
                <fieldset>
                    <legend>Please, enter your email and password for login.</legend>
                    <div class="input-block">
                        <label for="login-email">E-mail</label>
                        <input name="email" id="login-email" type="email" required>
                    </div>
                    <div class="input-block">
                        <label for="login-password">Password</label>
                        <input name="password" id="login-password" type="password" required>
                    </div>
                </fieldset>
                <button type="submit" class="btn-login">Login</button>
            </form>
        </div>
        <div class="form-wrapper">
            <button type="button" class="switcher switcher-signup">
                Sign Up
                <span class="underline"></span>
            </button>
            <form action="/registr" method="post" class="form form-signup">
                <fieldset>
                    <legend>Please, enter your email, password and password confirmation for sign up.</legend>
                    <div class="input-block">
                        <label for="signup-name">Name</label>
                        <input name="name" id="signup-name" type="name" required>
                    </div>
                    <div class="input-block">
                        <label for="signup-surname">Surname</label>
                        <input name="surname"  id="signup-surname" type="name" required>
                    </div>
                    <div class="input-block">
                        <label for="signup-email">Email</label>
                        <input name="email"  id="signup-email" type="email" required>
                    </div>
                    <div class="input-block">
                        <label for="signup-password">Password</label>
                        <input name="password"  id="signup-password" type="password" required>
                    </div>
                    <div class="input-block">
                        <label for="signup-password">Re-Password</label>
                        <input name="rePassword"  id="re-password" type="password" required>
                    </div>
        </fieldset>
        <button type="submit" class="btn-signup">Continue</button>
        </form>
    </div>
    </div>
</section>
<!-- partial -->
<script src="dist/script.js"></script>

</body>
</html>