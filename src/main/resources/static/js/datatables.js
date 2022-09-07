$(document).ready(function() {
  $('#dataTable').DataTable();
  loadUsers();
});

async function loadUsers() {
  const rawResponse = await fetch('api/users',{
    method: 'GET',
    headers: getHeaders()
  });
  const users = await rawResponse.json();

  let listHTML = '';

  for (let user of users){
    let btnDelete = '<a href="#" onclick="deleteUser('+user.id+')" class="btn btn-danger btn-circle btn-sm">' +
        '<i class="fas fa-trash"></i></a>'
    let userHTML = '<tr><td>'+user.id+'</td><th>'+user.nombre+''+user.apellido+'</th>' +
        '<th>'+user.email+'</th> <th>'+user.telefono+'</th>' +
        '<th>'+btnDelete+'</th></tr>'
    listHTML += userHTML;
    console.log(user)
  }
  document.querySelector('#dataTable tbody').outerHTML = listHTML;
}
function getHeaders(){
    return {
      'Accept': 'application/json',
      'Content-Type': 'application/json',
      'Authorization': localStorage.token
  }
}
async function deleteUser(id) {
  if (!confirm('Desea Eliminar el usuario?')){
    return;
  }
  const rawResponse = await fetch('api/users/' +id, {
    method: 'DELETE',
    headers: getHeaders()
  });
  location.reload();
}