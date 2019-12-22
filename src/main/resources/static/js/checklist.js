var incompleteTaskHolder;

var iter = 0;

function getFreeIndex() {
    return iter++;
}



function check_loginNewTaskElement(taskString) {

    var editInput = document.createElement("input");
    editInput.type = "text";

    editInput.name = 'checkListItemsById[' + getFreeIndex() + '].name';

    var deleteButton = document.createElement("button");
    deleteButton.innerText = "Delete";
    deleteButton.className = "btn delete btn-danger";
    deleteButton.type = "button";

    var listItem = document.createElement("li");
    listItem.appendChild(editInput);
    listItem.appendChild(deleteButton);
    return listItem;
}

function deleteTask() {
    console.log("Delete Task...");

    var listItem = this.parentNode;
    var ul = listItem.parentNode;

    ul.removeChild(listItem);
}


function bindTaskEvents(taskListItem) {
    console.log("bind list item events");

    var deleteButton = taskListItem.querySelector("button.delete");

    deleteButton.onclick = deleteTask;
}

function addTask() {
    console.log("Add Task...");
    var listItem = createNewTaskElement();

    incompleteTaskHolder.appendChild(listItem);

    bindTaskEvents(listItem);
}


window.onload = function () {

    var addButton = document.getElementById("addButton");
    incompleteTaskHolder = document.getElementById("incomplete-tasks");

    iter = incompleteTaskHolder.children.length;

    addButton.addEventListener("click", addTask);

    for (var i=0; i<incompleteTaskHolder.children.length;i++){
        bindTaskEvents(incompleteTaskHolder.children[i]);
    }

};