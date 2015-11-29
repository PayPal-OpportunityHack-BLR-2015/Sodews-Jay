var app = require('express')(); // Express App include
var express = require('express');
var http = require('http').Server(app); // http server
var mysql = require('mysql'); // Mysql include
var bodyParser = require("body-parser"); // Body parser for fetch posted data
var connection = mysql.createConnection({ // Mysql Connection
    host : 'localhost',
    user : 'root',
    password : 'abcd1234',
    database : 'sodew',
});
app.use(bodyParser.urlencoded({ extended: false }));
app.use(bodyParser.json()); // Body parser use JSON data

var server = app.listen(9001, function() {
    console.log('Ready on port %d', server.address().port);
});

app.use(express.static('public'));

app.post('/kidinfo',function(req,res){
    var name = req.body.name;
    var image = req.body.image;
    var age = req.body.age;
    var education = req.body.education;
    var emergency_name = req.body.emergency_name;
    var emergency_numner = req.body.emergency_number;
    var is_donated = req.body.is_donated;
    console.log(JSON.stringify(req.body))
    var data = {
        "error":1,
        "kid":""
    };
    if(1){
        connection.query("INSERT INTO kids_info VALUES(null,?,?,?,?,?,?,?)",[name,image,age,education,emergency_name,emergency_numner,is_donated],function(err, rows, fields){
            console.log([name,image,age,education,emergency_name,emergency_numner,is_donated])
            if(!!err){
                console.log(err);
                console.log(JSON.stringify(req.body));
                data["kid"] = "Error Adding data";
            }else{
                data["error"] = 0;
                data["kid"] = "Book Added Successfully";
            }
            res.json(data);
        });
    }else{
        data["kid"] = "Please provide all required data";
        res.json(data);
    }
});

app.get('/kidinfo',function(req,res){
    var data = {
        "error":1,
        "Books":""
    };
   
    connection.query("SELECT * from kids_info",function(err, rows, fields){
        if(rows.length != 0){
            data["error"] = 0;
            data["kids"] = rows;
            res.json(data);
        }else{
            data["kids"] = 'No books Found..';
            res.json(data);
        }
    });
});

app.get('/expenses',function(req,res){
    var data = {
        "error":1,
        "expenses":""
    };
   
    connection.query("SELECT * from expenses",function(err, rows, fields){
        if(rows.length != 0){
            data["error"] = 0;
            data["expenses"] = rows;
            res.json(data);
        }else{
            data["expenses"] = 'No expenses Found..';
            res.json(data);
        }
    });
});

app.post('/add_expenses',function(req,res){
    var description = req.body.description;
    var expense_value = req.body.expense_value;
    
    var data = {
        "error":1,
        "expenses":""
    };
    if(!!description && !!expense_value){
        connection.query("INSERT INTO expenses VALUES(null,?,?)",[description,expense_value],function(err, rows, fields){
            if(!!err){
                data["expenses"] = "Error Adding data";
            }else{
                data["error"] = 0;
                data["expenses"] = "Book Added Successfully";
            }
            res.json(data);
            console.log(err);
        });
    }else{
        data["expenses"] = "Please provide all required data";
        res.json(data);
    }
});


