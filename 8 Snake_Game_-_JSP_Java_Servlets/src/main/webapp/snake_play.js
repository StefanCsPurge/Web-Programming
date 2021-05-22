window.onload = function(){
    // Constants
    const KEY_UP = 38;
    const KEY_LEFT = 37;
    const KEY_DOWN = 40;
    const KEY_RIGHT = 39;
    const DIR_UP = 38;
    const DIR_LEFT = 37;
    const DIR_DOWN = 40;
    const DIR_RIGHT = 39;
    const ground_width = window.innerWidth - 40;
    const ground_height = window.innerHeight - 99;
    const snake_width = 15;
    const snake_audio = new Audio("bruh.mp3");
    const game_over_audio = new Audio("wasted.mp3")
    const snake_speed = 99;
    const snake_initial_length = ini_len;
    const no_of_obstacles = 10;
    let game_level = level;
    const speed_increment = 9;
    let game_score = 0;
    const user = user_name;
    const isEqual = (first, second) => {
        return JSON.stringify(first) === JSON.stringify(second);
    }

    const body = document.getElementsByTagName('body')[0];
    body.style.padding = "0px";
    body.style.margin = "10px";

    // Snake ground
    const snake_ground = document.createElement("div");
    snake_ground.style.background = "#000000";
    snake_ground.style.display = "block";
    snake_ground.style.width = ground_width + "px";
    snake_ground.style.height = ground_height + "px";
    snake_ground.style.margin = "10px";
    snake_ground.style.padding = "0px";
    snake_ground.style.position = "relative";
    snake_ground.style.overflow = "hidden";

    // Add snake ground
    body.appendChild(snake_ground);

    let obstacles = []
    // Food block
    const food = document.createElement("div");
    const food_position = [0, 0];
    const boundary = 50;
    const max1 = ground_width - boundary;
    const max2 = ground_height - boundary;
    const min1 = snake_width + boundary;
    const min2 = snake_width + boundary;
    const map_food_position = function () {
        // console.log('(' + min1 + ',' + max1 + ') , (' + min2 + ', ' + max2 + ')');
        food_position[0] = Math.floor((Math.random() * (max1 - min1) + min1) / min1) * min1;
        food_position[1] = Math.floor((Math.random() * (max2 - min2) + min2) / min2) * min2;
        //console.log(food_position, obstacles);
        while(obstacles.some(o => isEqual(o, food_position))){
            food_position[0] = Math.floor((Math.random() * (max1 - min1) + min1) / min1) * min1;
            food_position[1] = Math.floor((Math.random() * (max2 - min2) + min2) / min2) * min2;
        }

    };
    map_food_position();
    food.style.background = "#FF0000";
    food.style.display = "block";
    food.style.width = snake_width + "px";
    food.style.height = snake_width + "px";
    food.style.position = "absolute";
    food.style.left = food_position[0] + "px";
    food.style.top = food_position[1] + "px";
    food.style.border = "2px solid #000000";
    // Add food block to ground
    snake_ground.appendChild(food);

    // Game stats
    const game_stat_right = document.createElement('div');
    game_stat_right.style.display = 'block';
    game_stat_right.style.position = 'absolute';
    game_stat_right.style.right = '10px';
    game_stat_right.style.top = '10px';
    game_stat_right.style.font = 'bold 15px Monospace';
    game_stat_right.style.color = '#FFFFFF';

    const game_stat_left = document.createElement('div');
    game_stat_left.style.display = 'block';
    game_stat_left.style.position = 'absolute';
    game_stat_left.style.left = '10px';
    game_stat_left.style.top = '10px';
    game_stat_left.style.font = 'bold 15px Monospace';
    game_stat_left.style.color = '#FFFFFF';

    // Add game stats to ground
    snake_ground.appendChild(game_stat_right);
    snake_ground.appendChild(game_stat_left);

    // Snake
    let snake = [];

    // Define first two blocks or elements of snake
    snake[0] = [Math.floor(ground_width/(2*snake_width)) * snake_width, Math.floor(ground_height/(2 * snake_width)) * snake_width];
    for(let i = 1; i <= snake_initial_length; i++)
        snake[i] = [snake[0][0] - i * snake_width, snake[0][1]];

    // Set initial direction to right
    let snake_direction = DIR_RIGHT;

    // Obstacles
    for(let i = 0; i < no_of_obstacles; i++)
    {
        obstacles[i] = [Math.floor((Math.random() * (max1 - min1) + min1) / min1) * min1,
            Math.floor((Math.random() * (max2 - min2) + min2) / min2) * min2];

        let obstacle_elem = document.createElement("div");
        obstacle_elem.setAttribute("id", "obstacle_" + i);
        obstacle_elem.style.position = "absolute";
        obstacle_elem.style.display = "block";
        obstacle_elem.style.width = snake_width + "px";
        obstacle_elem.style.height = snake_width + "px";
        obstacle_elem.style.background = "#00b7ff";
        obstacle_elem.style.border = "2px solid #000000";
        snake_ground.appendChild(obstacle_elem);
        obstacle_elem.style.left = obstacles[i][0] + "px";
        obstacle_elem.style.top = obstacles[i][1] + "px";
    }


    // Variable to track game position
    let game_over = false;

    const trigger_keydown_event = function (key_code) {
            let evObj;
            // Create and dispatch a keyEvent to move snake's head towards snake_direction
            if (window.KeyEvent) {
                evObj = document.createEvent("KeyEvents");
                evObj.initKeyEvent("keydown", true, true, window, false, false, false, false, key_code, 0);
            } else {
                evObj = document.createEvent("UIEvents");
                evObj.initUIEvent("keydown", true, true, window, 1);
                evObj.keyCode = key_code;
            }
            document.dispatchEvent(evObj);
        };

    // Loop for as long as needed
    const game_loop = function () {
            let gameover_dialog_button;
            if (!game_over) {
                trigger_keydown_event(snake_direction);
                window.setTimeout(game_loop, snake_speed - speed_increment * game_level);
            }
            else {
                game_over_audio.play().then();
                let gameOverText = "GAME OVER!";
                if(game_score > high_score){
                    gameOverText += "<br>New high score: " + game_score;
                    high_score = game_score; // update high score in DB for the current user
                    const url = 'http://localhost:8080/UpdateScoreServlet';
                    const otherParam={headers:{"content-type": "application/json; charset=UTF-8"},
                                        body: JSON.stringify({score: high_score}), method:"POST"};
                    fetch(url, otherParam).then(
                        // res => {console.log(res)}
                    );
                }
                const gameover_dialog = document.createElement("div");
                gameover_dialog.style.display = "block";
                gameover_dialog.style.position = "absolute";
                gameover_dialog.style.width = "200px";
                gameover_dialog.style.height = "200px";
                gameover_dialog.style.font = "bold 20px Arial";
                gameover_dialog.style.color = "#FF0000";
                gameover_dialog.style.left = (ground_width / 2 - 80) + "px";
                gameover_dialog.style.top = (ground_height / 2 - 80) + "px";
                gameover_dialog.style.textAlign = "center";
                gameover_dialog.innerHTML = gameOverText;
                gameover_dialog_button = document.createElement("button");
                gameover_dialog_button.style.display = "inline-block";
                gameover_dialog_button.innerHTML = "Click To Play Again";
                gameover_dialog_button.onclick = function () {document.location.reload(false);}
                gameover_dialog.appendChild(gameover_dialog_button);
                body.appendChild(gameover_dialog);
                gameover_dialog_button.focus();
            }
        };
    window.setTimeout(game_loop, snake_speed);

    // Experimental: Time wrap (the hack)
    document.onclick = function(e){ snake[0][0] = e.clientX; snake[0][1] = e.clientY; }

    // Add keyDown event handler
    document.onkeydown = function(e){
        //console.log(user, snake[0]); // save this in DB
        const url = 'http://localhost:8080/AddMoveServlet';
        const Data = {username: user, x: snake[0][0], y: snake[0][1]}
        const otherParam={headers:{"content-type": "application/json; charset=UTF-8"},
                        body: JSON.stringify(Data), method:"POST"};
        fetch(url, otherParam).then(
            // res => {console.log(res)}
        );

        let i; // Ignore keyDown events if game is over
        if(game_over) return null;

        // Prevent snake from moving in reverse direction
        if(snake_direction === DIR_UP && e.keyCode === KEY_DOWN) return null;
        if(snake_direction === DIR_RIGHT && e.keyCode === KEY_LEFT) return null;
        if(snake_direction === DIR_LEFT && e.keyCode === KEY_RIGHT) return null;
        if(snake_direction === DIR_DOWN && e.keyCode === KEY_UP) return null;

        // Store position of last block, will be used when adding new tail block i.e. when snake's head eats food block
        let last_x_position = snake[snake.length - 1][0];
        let last_y_position = snake[snake.length - 1][1];

        // If one of the four navigation keys was pressed
        if(e.keyCode === KEY_UP || e.keyCode === KEY_LEFT || e.keyCode === KEY_DOWN || e.keyCode === KEY_RIGHT){
            // Update every element to move to position of block ahead
            for(i = snake.length - 1; i > 0 ; i--){
                snake[i][0] = snake[i-1][0];
                snake[i][1] = snake[i-1][1];
            }

            // If UP key was pressed ( basically released )
            if(e.keyCode === KEY_UP){
                snake[0][1] -= snake_width;
                snake_direction = DIR_UP;
            }
            // If LEFT key was pressed
            if(e.keyCode === KEY_LEFT){
                snake[0][0] -= snake_width;
                snake_direction = DIR_LEFT;
            }
            // If DOWN key was pressed
            if(e.keyCode === KEY_DOWN){
                snake[0][1] += snake_width;
                snake_direction = DIR_DOWN;
            }
            // If RIGHT key was pressed
            if(e.keyCode === KEY_RIGHT){
                snake[0][0] += snake_width;
                snake_direction = DIR_RIGHT;
            }

            // Wrap the snake at right edge
            if(snake[0][0] > ground_width) snake[0][0] = 0;
            // Wrap the snake at bottom edge
            if(snake[0][1] > ground_height) snake[0][1] = 0;
            // Wrap the snake at left edge
            if(snake[0][0] < 0) snake[0][0] = ground_width;
            // Wrap the snake at top edge
            if(snake[0][1] < 0) snake[0][1] = ground_height;

            // check if the snake hit an obstacle
            for(i = 0; i < no_of_obstacles; i++)
                if(Math.abs(snake[0][0] - obstacles[i][0]) < snake_width &&
                    Math.abs(snake[0][1] - obstacles[i][1]) < snake_width)
                    game_over = true;

            // check if the snake tries to eat itself
            for(i = 1; i < snake.length; i++)
                if(snake[0][0] === snake[i][0] && snake[0][1] === snake[i][1])
                    game_over = true;
        }

        // If snake's head has approached a food block
        if( Math.abs(snake[0][0] - food_position[0]) < snake_width &&
            Math.abs(snake[0][1] - food_position[1]) < snake_width) {
            // Play the audio
            snake_audio.play().then();

            // Add a new tail block
            snake[snake.length] = [last_x_position, last_y_position];

            game_score++;
            if(game_score !== 0 && game_score%10 === 0 && game_level !== 10)
                game_level++;

            // Find and update food block's new position
            map_food_position();
            food.style.left = food_position[0] + 'px';
            food.style.top = food_position[1] + 'px';
        }

        game_stat_left.innerHTML = 'Score: ' + game_score;
        game_stat_right.innerHTML = 'Level: ' + (game_level + 1);

        // Add or modify snake blocks on each event
        for(i = 0; i < snake.length; i++){
            let snake_elem = document.getElementById("snake_" + i);
            if(snake_elem == null){
                snake_elem = document.createElement("div");
                snake_elem.setAttribute("id", "snake_" + i);
                snake_elem.style.position = "absolute";
                snake_elem.style.display = "block";
                snake_elem.style.width = snake_width + "px";
                snake_elem.style.height = snake_width + "px";
                snake_elem.style.background = "#00FF00";
                snake_elem.style.border = "2px solid #000000";
                snake_ground.appendChild(snake_elem);
            }
            snake_elem.style.left = snake[i][0] + "px";
            snake_elem.style.top = snake[i][1] + "px";
        }
    };
}
