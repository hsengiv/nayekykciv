<html>
<head>
<script src="/js/thirdparties/jquery-3.1.0.min.js"></script>
<script src="/js/Common.js"></script>
<script src="/js/User.js"></script>

<!-- styles -->
<link href="/styles/common.css" rel="stylesheet" type="text/css" />


</head>
<body>
<a href="/signin.knv">SIGNIN</a><br>
USERNAME<input type="text" id="usernametextfield" onfocus="$('#usernamelabel').text('')" onblur="User.validateSignUpForm('username')"></input><label class="redTxt "  id="usernamelabel"></label><br>
PASSWORD<input type="password" id="primarypasswordfield" onfocus="$('#primarypasswordlabel').text('')" onblur="User.validateSignUpForm('password')"></input><label class="redTxt "  id="primarypasswordlabel"/></label><br>
CONFIRM PASSWORD<input type="password" id="confirmpasswordfield" onfocus="$('#confirmpasswordlabel').text('')" onblur="User.validateSignUpForm('confirmpassword')"></input><label class="redTxt "  id="confirmpasswordlabel"/></label><br>
<input type="button" value="SignUp" onclick="User.signUp()" ></input>
</body>
</html>