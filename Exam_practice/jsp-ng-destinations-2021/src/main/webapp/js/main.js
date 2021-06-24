function showAssets(assets) {
    let content = "<table border='1'><tr><th>Id</th><th>Name</th><th>Description</th><th>Value</th></tr>";
    for (let asset of assets) {
        let color = "";
        if (asset.value > 10)
            color = "style=\"background-color:#FF0000\"";
        content += "<tr " + color + "><td>" + asset.id + "</td>" +
            "<td>" + asset.name + "</td>" +
            "<td>" + asset.description + "</td>" +
            "<td>" + asset.value + "</td>" + "</tr>";
    }
    console.log(content);
    content += "</table>";
    $("#all-assets").html(content);
}

function getAssets(id){
    // let assets = [];
    $.getJSON(
        "/asset", {action: "getAssets", userId: id}, function (data, status) {
            console.log(data);
            showAssets(data["assets"]);
        }
    )
}


$(document).ready(function () {
    if (JSON.parse(sessionStorage["currentUser"]) !== null) {
        // Array.prototype.pushArray = function (arr) {
        //     this.push.apply(this, arr);
        // };
        let id = JSON.parse(sessionStorage["currentUser"])["userId"];
        getAssets(id);

        let newAssets = [];

        $("#push-button").click(function () {
            newAssets.push({
                userId: id,
                name: $("#name").val(),
                description: $("#description").val(),
                value: $("#value").val()
            });
            $("#name").val("");
            $("#description").val("");
            $("#value").val("");

            let content = "<h4>Assets to add:<br>";
            for (let tempAsset of newAssets) {
                content += "<b>" + tempAsset["name"] + ",</b> "
            }
            content += "</h4>";
            $("#assets-array").html(content);
        });

        $("#send-button").click(function () {
            $.post(
                "/asset",
                {action: "addAssets", newAssetsToAdd: JSON.stringify(newAssets)},
                function (data) {
                    newAssets = [];
                    getAssets(id);
                }
            );
            $("#assets-array").val("");
        });
    }
    else {
        location.href = "login.jsp";
    }
});
