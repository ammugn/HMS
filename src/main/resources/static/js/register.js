
function validateForm(){
    const formElement = document.getElementById("registerform");
    const formMessageElement = document.getElementById("form-error");
    const passwordInp = document.getElementById("password");
    const passwordRepeatInp = document.getElementById("rpassword");
    console.log("In validateForm1");
    const passwordText = passwordInp.value;
    const passwordTextRpt = passwordRepeatInp.value;
    let validationText = comparePasswords(passwordText, passwordTextRpt);
    if (validationText != null){
       console.log(validationText);
        // update the DOM with an error message
         formMessageElement.innerText = validationText;
       console.log("In validateForm2");

    } else {

       alert("Successfully registered");
       document.registerform.action = "/index.html";
       console.log("In validateForm3");
    }
}

function comparePasswords(passwordInput1, passwordInput2){
    console.log("P1: ", passwordInput1, ", P2", passwordInput2);
    if (passwordInput1.length < 8){
        return "Password must be at least 8 characters";
    }

    if(passwordInput2 !== passwordInput1){
        return "Passwords do no match";
    } else {
        return null;
    }
}