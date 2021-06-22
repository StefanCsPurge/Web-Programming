
function showFatherLine(username){
    $.get("FamilyController",
        { action: "getFatherLine", username: username },
        function (data, status) {
            $("#father-line").html(data);
        }
    );
}

function showMotherLine(username){
    $.get("FamilyController",
        { action: "getMotherLine", username: username },
        function (data, status) {
            $("#mother-line").html(data);
        }
    );
}
