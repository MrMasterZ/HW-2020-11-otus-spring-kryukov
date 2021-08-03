var authorSelect = document.getElementById("author-select");
var genreSelect = document.getElementById("genre-select");

function changeAuthor(){
    var inputList = document.getElementsByClassName("author-input");
    var selectedOption = authorSelect.options[authorSelect.selectedIndex];
    inputList[0].value = selectedOption.text;
    inputList[1].value = selectedOption.text;
}

function changeGenre(){
    var inputList = document.getElementsByClassName("genre-input");
    var selectedOption = genreSelect.options[genreSelect.selectedIndex];
    inputList[0].value = selectedOption.text;
    inputList[1].value = selectedOption.text;
}

authorSelect.addEventListener("focus", changeAuthor);
authorSelect.addEventListener("change", changeAuthor);
genreSelect.addEventListener("focus", changeGenre);
genreSelect.addEventListener("change", changeGenre);