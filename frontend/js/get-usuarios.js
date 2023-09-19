const tableUsuarios = document.querySelector("#usuarios");

async function consultaUsuarios() {
  const retorno = await fetch("http://localhost:8081/usuarios");
  const usuarios = await retorno.json();
  preencheTela(usuarios);
}

function preencheTela(usuarios) {
  usuarios.forEach(usuario => {
    const usuarioHTML = `
        <tr>
            <td>${usuario.name}</td>
            <td>${usuario.email}</td>
            <td><button id="edit">Alterar</button></td>
            <td><button id="delete">Deletar</button></td>
        </tr>
        `;

        tableUsuarios.innerHTML = tableUsuarios.innerHTML + usuarioHTML;
  });
}

consultaUsuarios();
