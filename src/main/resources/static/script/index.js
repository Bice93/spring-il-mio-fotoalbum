let photos;
let tablePhoto = document.getElementById("photo_table");

function photoList() {
    axios.get('http://localhost:8080/api/photos').then((response) => {
        //console.log("richiesta ok", response.data);
        photos = response.data;
        tablePhoto.innerHTML='';
        photos.forEach(photo => {
            //console.log(photo);
            tablePhoto.innerHTML += `
            <tr>
                <td>${photo.id}</td>
                <td><a href="/show?id=${photo.id}">${photo.title}</a></td>
                <td>${photo.tag}</td>
                <td>${photo.description}</td>
                <td>${photo.url}</td>
                <td>${photo.isVisible}</td>
          </tr>`;
        });

    }).catch((error) => {
        //console.log("richiesta errata", error)
    })
};

photoList();