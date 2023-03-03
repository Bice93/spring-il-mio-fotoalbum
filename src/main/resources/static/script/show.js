const UrlParams = new URLSearchParams(window.location.search);
const photoId = UrlParams.get('id');
const url = `http://localhost:8080/api/photos/+${photoId}`;

axios.get(url).then(response => {
    console.log("richiesta ok", response);
    const photo = response.data;
    let categories = photo.categories;
    let comments = photo.comments;
    console.log(photo);
    console.log(categories);
    console.log(comments);
    document.getElementById("photo_id").innerHTML = photo.id;
    document.getElementById("photo_title").innerHTML = photo.title;
    document.getElementById("photo_tag").innerHTML = photo.tag;
    document.getElementById("photo_decsription").innerHTML = photo.description;
    document.getElementById("photo_url").innerHTML = photo.url;
    if(categories.length > 0){
        document.getElementById("photo_categories").innerHTML = "Categorie: ";
        categories.forEach(category => {
            document.getElementById("photo_category").innerHTML += `<li>${category.name}</li>`;
        });
    };
    if(comments.length > 0){
        document.getElementById("photo_comments").innerHTML = "Commenti: ";
        comments.forEach(comment => {
            document.getElementById("photo_comment").innerHTML += `
             <li>${comment.text}</li>
            `;
        });
    };
}).catch(error => {
    console.log("richiesta errata", error);
})
