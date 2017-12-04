// Netakadémiának van cassandra magyarázó videója, telepítés és használat.
// data nucleus provider tud JPA-t implementálni cassandrahoz.




//var host = 'http://microservice.overon.hu:8080/';
var host = 'http://localhost:8080/';

function init() {
    refreshResourceTypes();
}

function refreshResourceTypes() {
//    var table = document.getElementById('resourcetype-table');
//    table.innerHTML = '';
    getResourceTypes();
}

function refreshPagedResourceTypes() {
//    var table = document.getElementById('resourcetype-table');
    var off = $('#offset');
    var lim = $('#limit');

    var oReq = new XMLHttpRequest();
    oReq.responseType = 'json';
    oReq.addEventListener('load', parsePagedResourceTypes);
    oReq.open('GET', host + 'resourcetype/' + off.val() + '/' + lim.val());
    oReq.send();
}

function parseResourceTypes() {
//    console.log(this.response);
    var table = document.getElementById('resourcetype-table');
    table.innerHTML = '';
    var thead = document.createElement('thead');
    thead.className = 'thead-dark';
    var tbody = document.createElement('tbody');
    table.appendChild(thead);
    table.appendChild(tbody);
    addTableRow(thead, {0: 'Id', 1: 'Név', 2: 'Mértékegység', 3: 'Anyagtulajdonság', 4: 'Leírás'}, 'th');
    for (var x in this.response) {
        addTableRow(tbody, this.response[x], 'td');
    }
}

function parsePagedResourceTypes() {
    var table = document.getElementById('resourcetype-table');
    table.innerHTML = '';
    var thead = document.createElement('thead');
    thead.className = 'thead-dark';
    var tbody = document.createElement('tbody');
    table.appendChild(thead);
    table.appendChild(tbody);
    addTableRow(thead, {0: 'Id', 1: 'Név', 2: 'Mértékegység', 3: 'Anyagtulajdonság', 4: 'Leírás'}, 'th');
    for (var x in this.response.items) {
        addTableRow(tbody, this.response.items[x], 'td');
    }
}


function addTableRow(parent, items, celltype) {
    var tr = document.createElement("tr");
    parent.appendChild(tr);
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
    oReq.open('GET', host + 'resourcetype/all');
    oReq.send();
}

function handleDelete() {
    console.log(this.response);
    refreshResourceTypes();
}

function deleteResourceType() {
    var oReq = new XMLHttpRequest();
    oReq.addEventListener('load', handleDelete);
    var id = document.getElementById('resourceTypeIdToDelete').value;
    var link = host + 'resourcetype/' + id;
    console.log('Deleting ' + id + ' with link: ' + link);
    oReq.open('DELETE', link);
    oReq.send();
}

function handlePost() {
    console.log(this.response);
    refreshResourceTypes();

}
function addResourceType() {
    var name = document.getElementById('resourceTypeName').value;
    var measurement = document.getElementById('resourceTypeMeasurement').value;
    var material = document.getElementById('resourceTypeMaterial').value;
    var description = document.getElementById('resourceTypeDescription').value;
    var resType = resourceType(0, name, measurement, material, description);

    var link = host + 'resourcetype/';
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


function switchPagingTo(id) {
    var all = $('#all-btn');
    var paged = $('#paged-btn');
    if (id == '0') {
        all.addClass('active');
        paged.removeClass('active');
    } else {
        all.removeClass('active');
        paged.addClass('active');
    }
}