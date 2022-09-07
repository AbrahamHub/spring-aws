async function login() {
    let datos = {};
    datos.email = document.getElementById('exampleInputEmail').value;
    datos.password = document.getElementById('exampleInputPassword').value;

    const request = await fetch('api/login', {
        method: 'POST',
        headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(datos)
    });

    const response = await request.text();
    if (response != 'FAIL') {
        localStorage.token = response;
        localStorage.email = datos.email;
        window.location.href = 'tables.html'
    } else {
        alert("Las credenciales son incorrectas. Por favor intente nuevamente.");
    }
}