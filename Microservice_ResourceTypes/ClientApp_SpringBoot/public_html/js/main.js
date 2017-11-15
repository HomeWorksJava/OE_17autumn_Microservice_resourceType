//var host = 'http://microservice.overon.hu:8080/';
var host = 'http://localhost:8080/';

function init() {
    refreshResourceTypes();
}

function refreshResourceTypes() {
    var table = document.getElementById('resourcetype-table');
    table.innerHTML = '';
    getResourceTypes();
}

function parseResourceTypes() {
    console.log(this.response);
    var table = document.getElementById('resourcetype-table');
    addTableRow(table, {0: 'Id', 1: 'Név', 2: 'Mértékegység', 3: 'Anyagtulajdonság', 4: 'Leírás'}, 'th');
    for (var x in this.response) {
        addTableRow(table, this.response[x], 'td');
    }
}

function addTableRow(table, items, celltype) {
    var tr = document.createElement("tr");
    table.appendChild(tr);
    for (var item in items) {
        var td = document.createElement(celltype);
        td.textContent = items[item];
        tr.appendChild(td);
    }
}

function getResourceTypes() {
    var oReq = new XMLHttpRequest();
    oReq.responseType = 'json';
    oReq.addEventListener('load', parseResourceTypes);
    oReq.open('GET', host + 'resourcetypes');
    oReq.send();
}

function handleDelete() {
    console.log(this.response);
    refreshResourceTypes();
}

function deleteResourceType() {
    var oReq = new XMLHttpRequest();
    oReq.addEventListener('load', handleDelete);
    var id = document.getElementById('delete-id').value;
    var link = host + 'deleteresourcetype/' + id;
    console.log('Deleting ' + id + ' with link: ' + link);
    oReq.open('DELETE', link);
    oReq.send();
}

function handlePost() {
    console.log(this.response);
    refreshResourceTypes();

}
function postResourceType() {
    var name = document.getElementById('new-name').value;
    var measurement = document.getElementById('new-measurement').value;
    var material = document.getElementById('new-material').value;
    var description = document.getElementById('new-description').value;
    var resType = resourceType(0, name, measurement, material, description);

    var link = host + 'postresourcetype';
    var postReq = new XMLHttpRequest();
    postReq.open("POST", link);
    postReq.setRequestHeader('Content-type', 'application/x-www-form-urlencoded');
    postReq.addEventListener('load', handlePost);
    postReq.send(resType.toParamString());

    console.log(link)
}

function resourceType(id, name, measurement, material, description) {
    return {
        id: id,
        name: name,
        measurement: measurement,
        material: material,
        description: description,
        toParamString: function () {
            return 'name=' + name + '&measurement=' + measurement + '&material=' + material + '&description=' + description;
        }
    }
}