async function registerUser() {
    let data = {};
    data.nombre = document.getElementById('exampleFirstName').value;
    data.apellido = document.getElementById('exampleLastName').value;
    data.email = document.getElementById('exampleInputEmail').value;
    data.password = document.getElementById('exampleInputPassword').value;

    let repetPassword = document.getElementById('exampleRepeatPassword').value;

    if (repetPassword != data.password) {
        alert('La contraseña que escribiste es diferente.');
        return;
    }
    const rawResponse = await fetch('api/users', {
        method: 'POST',
        headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(data)
    });
    alert("La cuenta se creo con exito");
    window.location.href = 'login.html'
}