
$(document).ready(function(){
    getAllPosts();
    setInterval(checkForNotifications, 500);
});

function getAllPosts(){
    $.get("PostsController",
        { action: "getAllPosts" },
        function (data, status) {
            $("#posts-section").html(data);
        }
    );
}

function checkForNotifications(){
    $.ajax({
        url: "PostsController",
        type: "GET",
        data: { action: "checkNotification", user: username },
        success: function( notification ) {
            //Check if any notifications are returned - if so then display alert
            if(notification)
            {
                getAllPosts();
                alert(notification);
            }
        },
        error: function(data){
            console.log(data);
        }
    });
}

function updatePost(the_user){
    $.post(
        "PostsController",
        {   action: "updatePost",
            user: the_user,
            postid: $("#post-id").val(),
            topicname: $("#topic-name").val(),
            text: $("#text").val(),
            date: Date.now()
        },
        function (data) {
            getAllPosts();
            // $("#journal-name").val("");
            // $("#summary").val("");
        }
    );
}

function addPost(the_user){
    $.post(
        "PostsController",
        {   action: "addPost",
            user: the_user,
            topicname: $("#topic-name").val(),
            text: $("#text").val(),
            date: Date.now()
        },
        function (data) {
            getAllPosts();
            $("#post-id").val("");
            $("#topic-name").val("");
            $("#text").val("");
        }
    );
}
