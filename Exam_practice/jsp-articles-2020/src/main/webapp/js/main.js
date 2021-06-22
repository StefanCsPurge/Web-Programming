
$(document).ready(function(){
    setInterval(checkForNotifications, 500);
});


function checkForNotifications(){
    $.ajax({
        url: "ArticlesController",
        type: "GET",
        data: { action: "checkNotification", user: username },
        success: function( notification ) {
            //Check if any notifications are returned - if so then display alert
            if(notification)
               alert(notification);
        },
        error: function(data){
            console.log(data);
        }
    });
}

function findArticles(){
    $.get("ArticlesController",
        { action: "getArticlesForJournal", journalname: $("#journal-name-input").val() },
        function (data, status) {
            $("#journal-articles").html(data);
        }
    );
}

function addArticle(the_user){
    $.post(
        "ArticlesController",
        {   action: "addArticle",
            user: the_user,
            journalname: $("#journal-name").val(),
            journalsummary: $("#summary").val(),
            date: Date.now()
        },
        function (data) {
            findArticles();
            $("#journal-name").val("");
            $("#summary").val("");
        }
    );
}
