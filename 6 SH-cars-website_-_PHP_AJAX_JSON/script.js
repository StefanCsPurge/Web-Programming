let lastFuel;

function get_filtered_fuel() {
    if (lastFuel !== "choose fuel")
        $("p").eq(0).html("These are the cars with fuel: " + lastFuel);
    else $("p").eq(0).html("These are the cars");
    let fuel = $("select")[0].value;
    $.post("backend/fuel.php",{'fuel': fuel}, function (data, status) {
        if (status === "success") {
            lastFuel = fuel;
            //console.log(data);
            let table = $("table")[0];
            let oldTableBody = $("tbody")[0];
            let newTableBody = document.createElement('tbody');

            let json = jsonParse(data);
            for (let i = 0; i < json.length; i++) {
                let report = json[i];
                let row = newTableBody.insertRow();

                Object.keys(report).forEach(function (key) {
                    // let text;
                    let cell = row.insertCell();
                    let text = report[key];
                    cell.appendChild(document.createTextNode(text));
                })
            }
            table.replaceChild(newTableBody, oldTableBody);
        }
    });
}

$(document).ready(function(){
    $("table").on('click', '.btnDelete', function () {
        let id = $(this).closest('tr')[0].cells[0].innerText;
        $.post("backend/delete_car.php",{'id': id});
        $(this).closest('tr').remove();
    });

    $("table").on('click', '.btnAdd', function () {
        //let id = $(this).closest('tr')[0].cells[0].innerText;
        let tableModel = $(this).closest('tr')[0].cells[1].innerText;
        let tableHp = $(this).closest('tr')[0].cells[2].innerText;
        let tableFuel = $(this).closest('tr')[0].cells[3].innerText;
        let tablePrice = $(this).closest('tr')[0].cells[4].innerText;
        let tableColor = $(this).closest('tr')[0].cells[5].innerText;
        let tableAge = $(this).closest('tr')[0].cells[6].innerText;
        $(".add_form #model").val(tableModel);
        $(".add_form #hp").val(tableHp);
        $(".add_form #fuel").val(tableFuel);
        $(".add_form #price").val(tablePrice);
        $(".add_form #color").val(tableColor);
        $(".add_form #age").val(tableAge);
        if($(".add_form").css("display") === "none")
            $(".add_form").css("display", "inline");
        else
            $(".add_form").css("display", "none");
        //$.post("backend/add_car.php",{'id': id});
    });

    $("table").on('click', '.btnUpdate', function () {
        let tableId = $(this).closest('tr')[0].cells[0].innerText;
        let tableModel = $(this).closest('tr')[0].cells[1].innerText;
        let tableHp = $(this).closest('tr')[0].cells[2].innerText;
        let tableFuel = $(this).closest('tr')[0].cells[3].innerText;
        let tablePrice = $(this).closest('tr')[0].cells[4].innerText;
        let tableColor = $(this).closest('tr')[0].cells[5].innerText;
        let tableAge = $(this).closest('tr')[0].cells[6].innerText;
        $(".update_form #id").val(tableId);
        $(".update_form #model").val(tableModel);
        $(".update_form #hp").val(tableHp);
        $(".update_form #fuel").val(tableFuel);
        $(".update_form #price").val(tablePrice);
        $(".update_form #color").val(tableColor);
        $(".update_form #age").val(tableAge);
        if($(".update_form").css("display") === "none")
            $(".update_form").css("display", "inline");
        else
            $(".update_form").css("display", "none");
        //$.post("backend/update_car.php",{'id': id});
    });
});

function jsonParse(text) {
    let json;
    try {
        json = JSON.parse(text);
    } catch (e) {
        return false;
    }
    return json;
}
