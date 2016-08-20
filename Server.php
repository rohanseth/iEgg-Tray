<?php
$name = $_GET["name"];
$file = "data/". $name .".txt";
$current = file_get_contents($file);
echo $current;
?>

2.
<?php

$name = $_GET["name"];
$file = "data/". $name . ".txt";
$data = $_GET["val"];
file_put_contents($file, $data);
echo $name. " set to ". $data. " !";

?>
