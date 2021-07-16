var bookRows = "";
var selectAuthor;
var authorList = [];
var selectGenre;
var genreList = [];
getAllBooks();

function doEditableBookTable() {
    var tds = document.querySelectorAll('td');
    for (var i = 0; i < tds.length; i++) {
        switch(tds[i].getAttribute("id")) {
            case "del-button":
                tds[i].addEventListener('click', del);
                break;
            case "edit-button":
                tds[i].addEventListener('click', edit);
                break;
            case "create-button":
                tds[i].addEventListener('click', create);
                break;
            default:
                tds[i].addEventListener('click', editTd);
        }
    }
}

function editTd() {
    var input = document.createElement('input');
    input.value = this.innerHTML;
    this.innerHTML = '';
    this.appendChild(input);
    if(this.getAttribute("class") === "td-author")
        this.appendChild(selectAuthor);
    if(this.getAttribute("class") === "td-genre")
        this.appendChild(selectGenre);
    var td = this;
    input.addEventListener('blur', function () {
        td.innerHTML = this.value;
        td.addEventListener('click', editTd);
    });
    this.removeEventListener('click', editTd);
}

function changeTd(){
    var selectedOption = this.options[this.selectedIndex];
    var td = this.parentNode;
    td.innerHTML = selectedOption.text;
    td.addEventListener('click', editTd);
}

function startAjax(){
    if(window.XMLHttpRequest){ request = new XMLHttpRequest(); return true; }
    else if(window.ActiveXObject){ request = new ActiveXObject("Microsoft.XMLTYPE"); return true; }
    else { return false; }
}

function POSTAjax(url, clientJsonString, method){
    return new Promise(function(resolve, reject) {
        if(!startAjax()) reject(new Error("not correct browser"));
        request.addEventListener("load", function() {
            if(request.status==200){ resolve(request.responseText.replace(/\r?\n/g, "")); }
            else if(request.status==404){ reject(new Error("not found script")); }
            else { reject(new Error("status: "+ request.status)); }
        })
        request.addEventListener("error", function() {
            reject(new Error("Network error"));
        });
        request.open(method, url, true);
        request.setRequestHeader("Content-type","application/json");
        request.send(clientJsonString);
    });
}

function GETAjax(url, method){
    return new Promise(function(resolve, reject) {
        if(!startAjax()) reject(new Error("not correct browser"));

        request.addEventListener("load", function() {
            if(request.status==200){ resolve(request.responseText.replace(/\r?\n/g, "")); }
            else if(request.status==404){ reject(new Error("not found script")); }
            else { reject(new Error("status: "+ request.status)); }
        })
        request.addEventListener("error", function() {
            reject(new Error("Network error"));
        });
        request.open(method, url, true);
        request.send();
    });
}

function formBookRow(book) {
    bookRows += "<tr>" +
        "<td class=\"td-title\">" + book.title + "</td>" +
        "<td class=\"td-author\">" + book.author + "</td>" +
        "<td class=\"td-genre\">" + book.genre + "</td>" +
        "<td id=\"edit-button\" abbr=\"" + book.id + "\">edit</td>" +
        "<td id=\"del-button\" abbr=\"" + book.id + "\">del</td>" +
        "</tr>"
}

function formAuthorOption(book) {
    var author = book.author;
    if( authorList.indexOf(author) == -1 ) {
        authorList.push(author);
        var option = document.createElement('option');
        option.setAttribute("value", author);
        option.innerHTML = author;
        selectAuthor.appendChild(option);
    }
}

function formAuthorSelect(books) {
    selectAuthor = document.createElement('select');
    selectAuthor.addEventListener("change", changeTd);
    var option = document.createElement('option');
    selectAuthor.appendChild(option);
    Array.prototype.forEach.call(books, book => formAuthorOption(book));
}

function formGenreOption(book) {
    var genre = book.genre;
    if( genreList.indexOf( book.genre ) == -1 ) {
        genreList.push(genre);
        var option = document.createElement('option');
        option.setAttribute("value", genre);
        option.innerHTML = genre;
        selectGenre.appendChild(option);
    }
}

function formGenreSelect(books) {
    selectGenre = document.createElement('select');
    selectGenre.addEventListener("change", changeTd);
    var option = document.createElement('option');
    selectGenre.appendChild(option);
    Array.prototype.forEach.call(books, book => formGenreOption(book));
}

function formBookHTML() {
    return "<p class=\"title\">Library</p>" +
    "    <table>" +
    "        <thead>" +
    "            <tr>" +
    "                <th>title</th>" +
    "                <th>author</th>" +
    "                <th>genre</th>" +
    "            </tr>" +
    "        </thead>" +
    "        <tbody>" +
    bookRows +
    "            <tr>" +
    "                <td class=\"td-title\"></td>" +
    "                <td class=\"td-author\"></td>" +
    "                <td class=\"td-genre\"></td>" +
    "                <td id=\"create-button\">create</td>" +
    "            </tr>" +
    "        </tbody>" +
    "    </table>"
}

function formBookTable(books) {
    var books = JSON.parse(books);
    Array.prototype.forEach.call(books, book => formBookRow(book));
    var root = document.getElementById("root");
    root.innerHTML = formBookHTML();
    formAuthorSelect(books);
    formGenreSelect(books);
    doEditableBookTable();
}

function getAllBooks() {
    GETAjax("book", "GET").then(
        response => formBookTable(`${response}`),
        error => drawError(`${error}`)
    );
}

function del() {
    GETAjax("book/" + this.getAttribute("abbr"), "DELETE")
        .then(
            response => deleteRow(this,`${response}`),
            error => drawError(`${error}`)
        );
}

function deleteRow(element, status) {
    if(status == "ok") {
        element.closest("tr").remove();
        clearError
    }
}

function edit() {
    var book = formBookObject(this);
     POSTAjax("book/" + this.getAttribute("abbr"), JSON.stringify(book), "PATCH")
        .then(
            response => updateRow(this.closest("tr"), JSON.parse(`${response}`)),
            error => drawError(`${error}`)
        );
}

function create() {
    var book = formBookObject(this);
    POSTAjax("book", JSON.stringify(book), "POST")
        .then(
            response => createRow(this, `${response}`),
            error => drawError(`${error}`)
        );
}

function formBookObject(element) {
    return {
        id:     element.getAttribute("abbr"),
        title:  element.closest("tr").getElementsByClassName("td-title")[0].innerText,
        author: getValueTd(element, "td-author"),
        genre:  getValueTd(element, "td-genre")
    }
}

function getValueTd(element, className) {
    var valueTd = "";
    var elementTd = element.closest("tr").getElementsByClassName(className)[0];
    if(!elementTd.contains(selectAuthor) && !elementTd.contains(selectGenre))
        valueTd = elementTd.innerText;
    return valueTd;
}

function updateRow(tr, book) {
    tr.getElementsByClassName("td-title")[0].innerText = book.title;
    tr.getElementsByClassName("td-author")[0].innerText = book.author;
    tr.getElementsByClassName("td-genre")[0].innerText = book.genre;
    clearError();
}

function createRow (element, book) {
    if(book.includes("RuntimeException"))
        formRuntimeException(book);
    else {
        var book = JSON.parse(book);
        updateRow(element.closest("tr"), book);
        reformBookTable(element, book.id);
        formAuthorOption(book);
        formGenreOption(book);
    }
}

function reformBookTable(element, id) {
    var tr = element.closest("tr");
    var tbody = element.closest("tbody");
    tr.removeChild(element);
    var editButton = createTdButton("edit-button", id, "edit", edit);
    tr.appendChild(editButton);
    var delButton = createTdButton("del-button", id, "del", del);
    tr.appendChild(delButton);
    var newTr = document.createElement('tr');
    var tdTitle = createTd("td-title");
    newTr.appendChild(tdTitle);
    var tdAuthor = createTd("td-author");
    newTr.appendChild(tdAuthor);
    var tdGenre = createTd( "td-genre");
    newTr.appendChild(tdGenre);
    var createButton = createTdButton("create-button", "", "create", create);
    newTr.appendChild(createButton);
    tbody.appendChild(newTr);
}

function createTdButton(id, abbr, text, functionName) {
    var button = document.createElement('td');
    button.setAttribute("id", id);
    button.setAttribute("abbr", abbr);
    button.innerText = text;
    button.addEventListener('click', functionName);
    return button;
}

function createTd(className) {
    var td = document.createElement('td');
    td.setAttribute("class", className)
    td.addEventListener('click', editTd)
    return td;
}

function formRuntimeException(error) {
    drawError(error.split("RuntimeException: ")[1]);
}

function drawError(errorText) {
    var errorElement = document.getElementById("error");
    errorElement.innerText = errorText;
}

function clearError() {
    var errorElement = document.getElementById("error");
    errorElement.innerText = '';
}