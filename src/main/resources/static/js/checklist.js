var incompleteTaskHolder;

var createNewTaskElement = function (taskString) {
    var editInput = document.createElement("input");
    editInput.type = "text";

    var deleteButton = document.createElement("button");
    deleteButton.innerText = "Delete";
    deleteButton.className = "btn delete btn-danger";
    deleteButton.type = "button";

    var listItem = document.createElement("li");
    listItem.appendChild(editInput);
    listItem.appendChild(deleteButton);
    return listItem;
};

var deleteTask = function () {
    console.log("Delete Task...");

    var listItem = this.parentNode;
    var ul = listItem.parentNode;

    ul.removeChild(listItem);
};


var bindTaskEvents = function (taskListItem) {
    console.log("bind list item events");

    var deleteButton = taskListItem.querySelector("button.delete");

    deleteButton.onclick = deleteTask;
};

var addTask = function () {
    console.log("Add Task...");
    var listItem = createNewTaskElement();

    incompleteTaskHolder.appendChild(listItem);

    bindTaskEvents(listItem);
};


window.onload = function () {

    var addButton = document.getElementById("addButton");
    incompleteTaskHolder = document.getElementById("incomplete-tasks");

    addButton.addEventListener("click", addTask);

};