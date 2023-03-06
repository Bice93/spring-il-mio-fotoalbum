const UrlParams = new URLSearchParams(window.location.search);
const photoId = UrlParams.get('id');

showPhoto(photoId);

function showPhoto(photoId) {
    const url = `http://localhost:8080/api/photos/+${photoId}`;
    axios.get(url).then(response => {
        console.log("richiesta ok", response);
        const photo = response.data;
        let categories = photo.categories;
        let comments = photo.comments;
        console.log(photo);
        console.log(categories);
        console.log(comments);
        document.getElementById("img").src = photo.url;
        document.getElementById("img").alt = `${photo.title}'s photo`;
        document.getElementById("photo_title").innerHTML = photo.title;
        document.getElementById("photo_tag").innerHTML = `#${photo.tag}`;
        document.getElementById("photo_decsription").innerHTML = photo.description;
        if (categories.length > 0) {
            document.getElementById("photo_categories").innerHTML = "Categorie: ";
            categories.forEach(category => {
                document.getElementById("photo_category").innerHTML += `<dd>${category.name}</dd>`;
            });
        };
        if (comments.length > 0) {
            document.getElementById("photo_comments").innerHTML = "Commenti: ";
            comments.forEach(comment => {
                document.getElementById("photo_comment").innerHTML += `
                     <dd>${comment.text}</dd>
                    `;
            });
        };
    }).catch(error => {
        console.log("richiesta errata", error);
    })
}


