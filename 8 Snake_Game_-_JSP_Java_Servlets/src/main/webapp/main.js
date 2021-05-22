function validateUsername(){
    const username = document.getElementById("username").value;
    const nonNullRegex = /[a-zA-Z]/g;
    console.log(username.match(nonNullRegex));
    return username.match(nonNullRegex)!=null;
}

function validateSearch(){
    if(!validateUsername()){
        alert("Invalid parameters, can't search!");
        location.reload();
    }
    else {
        document.getElementById("searchForm").submit();
    }
}

function validateRegister() {
    if(!validateUsername())
        alert("Invalid parameters, can't register!");
    else
    {
        document.getElementById("registerForm").submit();
    }
}

function validateUpdateInfo() {
    if(!validateUsername())
        alert("Invalid parameters, can't update!");
    else {
        if (confirm('Are you sure you want to update?'))
            document.getElementById("updateForm").submit();
    }

}
