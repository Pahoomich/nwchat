$(document).ready(function () {
    $('.select-two').select2({
        width: '100%'
    });




    var ll = [];
    var hideList = document.getElementById("hts");
    var refList = function () {
        hideList.innerHTML = "";

        var i = 0;

        if (typeof ll === 'string') {
            var editInput = document.createElement("input");
            editInput.type = "text";

            editInput.name = 'userIds[' + (i++) + ']';
            editInput.value = ll;

            hideList.appendChild(editInput);
        } else {
            for (let llElement of ll) {
                var editInput = document.createElement("input");
                editInput.type = "text";

                editInput.name = 'userIds[' + (i++) + ']';
                editInput.value = llElement;

                hideList.appendChild(editInput);
            }
        }
    };


    ll = $(".select-two.select-two-ml").select2("val");
    refList();
    $('.select-two.select-two-ml').on('select2:select', function (e) {
        ll = $(".select-two.select-two-ml").select2("val");
        refList();
    });
    $('.select-two.select-two-ml').on('select2:unselect', function (e) {
        ll = $(".select-two.select-two-ml").select2("val");
        refList();
    });
});