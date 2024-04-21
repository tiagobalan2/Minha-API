 const url = "http://localhost:8080/usuarios";
 fetch(url)
     .then (function (res) {return res.json() })

     .then (function (data) {
        console.log(data)
        console.log(data.length)
        const quantidade = document.querySelector("#quantidade");
        quantidade.innerHTML = `
            Quantidade total de usuarios: ${data.length}
        `
         const table_body = document.querySelector("table tbody");

         data.forEach(function (usuario) {
             const row = document.createElement("tr");
             row.innerHTML = `
             <td>${usuario.id}</td>
             <td>${usuario.nome}</td>
             <td>${usuario.email}</td>
             <td>${usuario.telefone}</td>
             `;
             table_body.appendChild(row);
         });
     })


     .catch (function (error) {console.error(error)})







