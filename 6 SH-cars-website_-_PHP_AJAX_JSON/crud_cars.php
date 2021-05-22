<?php
include ('db/connection.php');
if ($_SERVER['REQUEST_METHOD'] == 'POST'){
    $connection = OpenConnection();
    if(isset($_POST['add'])) {
        $model = $_POST['model'];
        $hp = $_POST['hp'];
        $fuel = $_POST['fuel'];
        $price = $_POST['price'];
        $color = $_POST['color'];
        $age = $_POST['age'];
        $query = "INSERT INTO cars VALUES(null,'$model','$hp','$fuel','$price','$color','$age')";
        $connection->query($query);
    }
    else if(isset($_POST['update'])){
        $id = $_POST['id'];
        $model = $_POST['model'];
        $hp = $_POST['hp'];
        $fuel = $_POST['fuel'];
        $price = $_POST['price'];
        $color = $_POST['color'];
        $age = $_POST['age'];
        $query = "UPDATE cars SET model='$model',hp='$hp',fuel='$fuel',price='$price',color='$color',age='$age' WHERE id='$id'";
        $connection->query($query);
    }

    //header('location:crud_cars.php');
    CloseConnection($connection);
}
?>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Cars processing</title>
    <script src="jquery-3.6.0.min.js"></script>
    <script type="text/javascript" src="script.js"></script>
    <link rel="stylesheet" type="text/css" href="style.css">
</head>
<body>
<button type="button" onclick="location.href='./index.html'" style="margin-left: 20px">Home</button>
<br>
<section style="float: left; display: inline; padding-left: 20px">
    <br>
    <table>
        <thead>
        <th>ID</th>
        <th>Model</th>
        <th>HP</th>
        <th>Fuel</th>
        <th>Price</th>
        <th>Color</th>
        <th>Age</th>
        <th> </th>
        </thead>
        <tbody>
        <?php
        $con = OpenConnection();

        $sql = "SELECT * FROM cars";
        $result_set = $con->query($sql);
        while ($row = mysqli_fetch_array($result_set, MYSQLI_NUM)) {
            echo "<tr><td>$row[0]</td> 
                  <td>$row[1]</td> 
                  <td>$row[2]</td> 
                  <td>$row[3]</td> 
                  <td>$row[4]</td> 
                  <td>$row[5]</td> 
                  <td>$row[6]</td> 
                  <td>  <button class='btnAdd' type='button'>Add</button>
                        <button class='btnUpdate' type='button'>Update</button>
                        <button class='btnDelete' type='button'>Delete</button>
                  </td>
                  </tr>";
        }
        CloseConnection($con);
        ?>
        </tbody>
    </table>
    <p> </p>
    <br>
</section>

<section class="add_form" style="float: left; display: none; padding-left: 50px; padding-top: 12px">
    <form action="crud_cars.php" method="post">
        <input id="model" type="text" name="model" placeholder="model" style="margin: 5px; width: 191px">
        Model<br>
        <input id="hp" type="text" name="hp" placeholder="hp" style="margin: 5px; width: 191px">
        HP<br>
        <input id="fuel" type="text" name="fuel" placeholder="fuel" style="margin: 5px; width: 191px">
        Fuel<br>
        <input id="price" type="text" name="price" placeholder="price" style="margin: 5px; width: 191px">
        Price<br>
        <input id="color" type="text" name="color" placeholder="color" style="margin: 5px; width: 191px">
        Color<br>
        <input id="age" type="text" name="age" placeholder="age" style="margin: 5px; width: 191px">
        Age<br>
        <input id="add" type="submit" name="add" value="Add new car" style="margin: 6px">
    </form>
</section>

<section class="update_form" style="float: left; display: none; padding-left: 50px; padding-top: 12px">
    <form action="crud_cars.php" method="post">
        <input id="id" type="text" name="id" placeholder="id" readonly style="margin: 5px; width: 191px">
        ID<br>
        <input id="model" type="text" name="model" placeholder="model" style="margin: 5px; width: 191px">
        Model<br>
        <input id="hp" type="text" name="hp" placeholder="hp" style="margin: 5px; width: 191px">
        HP<br>
        <input id="fuel" type="text" name="fuel" placeholder="fuel" style="margin: 5px; width: 191px">
        Fuel<br>
        <input id="price" type="text" name="price" placeholder="price" style="margin: 5px; width: 191px">
        Price<br>
        <input id="color" type="text" name="color" placeholder="color" style="margin: 5px; width: 191px">
        Color<br>
        <input id="age" type="text" name="age" placeholder="age" style="margin: 5px; width: 191px">
        Age<br>
        <input id="update" type="submit" name="update" value="Update" style="margin: 6px">
    </form>
</section>

</body>
</html>
