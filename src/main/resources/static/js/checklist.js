var incompleteTaskHolder;

var iter = 0;

function getFreeIndex() {
    return iter++;
}


function createNewTaskElement() {

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

    var listCheckBocks = document.querySelectorAll(".remoute");

    for (let listCheckBock of listCheckBocks) {

        listCheckBock.addEventListener('change', (event) => {
            var checkId = listCheckBock.getAttribute("data-id");
            if (event.target.checked) {
                console.log('checked');
                $.post("/items/" + checkId + "/c");
            } else {
                console.log('not checked');
                $.post("/items/" + checkId + "/u");
            }
        })
    }


    var addButton = document.getElementById("addButton");
    incompleteTaskHolder = document.getElementById("incomplete-tasks");

    if (addButton == null || incompleteTaskHolder == null)
        return;

    iter = incompleteTaskHolder.children.length;

    addButton.addEventListener("click", addTask);

    for (var i = 0; i < incompleteTaskHolder.children.length; i++) {
        bindTaskEvents(incompleteTaskHolder.children[i]);
    }

};