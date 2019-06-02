<?php

class DBUtils
{
    private $host = 'localhost';
    private $db = 'booking';
    private $user = 'root';
    private $pass = 'vladdavidapps';
    private $charset = 'utf8';

    public function __construct($tab)
    {
        $this->table = $tab;
        $dsn = 'mysql:host=' . $this->host . ';dbname=' . $this->db . ';charset=' . $this->charset;
        $opt = array(PDO::ATTR_ERRMODE => PDO::ERRMODE_EXCEPTION,
            PDO::ATTR_DEFAULT_FETCH_MODE => PDO::FETCH_ASSOC,
            PDO::ATTR_EMULATE_PREPARES => false);
        try {
            $this->pdo = new PDO($dsn, $this->user, $this->pass, $opt);
        } catch (PDOException $e) {
            $this->error = $e->getMessage();
            //echo "Error connecting to DB: " . $this->error . mysqli_connect_error();
        }
    }

    public function selectOneRowQuery($query){
        $stmt = $this->pdo->prepare($query);
        $stmt->execute();
        return $stmt->fetch();
    }

    public function selectAll()
    {
        $stmt = $this->pdo->query("SELECT * FROM $this->table");
        return $stmt->fetchAll(PDO::FETCH_ASSOC);
    }

    public function personalizedSelect($string){
        $stmt = $this->pdo->query("SELECT * FROM $this->table  $string");
        return $stmt->fetchAll(PDO::FETCH_ASSOC);
    }

    public function selectAllWhere($where)
    {
        $stmt = $this->pdo->query("SELECT * FROM $this->table where $where");
        return $stmt->fetchAll(PDO::FETCH_ASSOC);
    }

    public function madeUpSelectQuery($query){
        $stmt=$this->pdo->query($query);
        return $stmt->fetchAll(PDO::FETCH_ASSOC);
    }

    public function selectColumnsWhere($columns, $where)
    {
        if ($where != "")
            $stmt = $this->pdo->query("SELECT $columns FROM $this->table where $where");
        else
            $stmt = $this->pdo->query("SELECT $columns FROM $this->table");
        return $stmt->fetchAll(PDO::FETCH_ASSOC);
    }

    public function insert($columns, $values)
    {
        $affected_rows = $this->pdo->exec("INSERT into $this->table($columns) values($values)");
        return $affected_rows;
    }

    public function insertPersonalized($stmt){
        $affected_rows = $this->pdo->exec($stmt);
        return $affected_rows;
    }

    public function deleteById($id)
    {
        $affected_rows = $this->pdo->exec("DELETE from $this->table where Id=$id");
        return $affected_rows;
    }

    public function update($column, $value, $id)
    {
        $affected_rows = $this->pdo->exec("UPDATE $this->table SET $column=$value where Id=$id");
        return $affected_rows;
    }
}


?>

