function Login(){
    const email = document.getElementById("email");
    const password = document.getElementById("password");

    if (email=="admin@mediTech.com" and password=="admin"){

       alert("Successfully logged in");
              document.form.action = "dashboard.html";


    } else {

       alert("In valid user");
    }
}