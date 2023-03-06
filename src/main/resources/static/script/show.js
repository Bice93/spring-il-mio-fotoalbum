const UrlParams = new URLSearchParams(window.location.search);
const photoId = UrlParams.get('id');
const btn = document.getElementById('myBtnComment');
let photo;
let categories;
let comments;
showPhoto(photoId);

function showPhoto(photoId) {
    const url = `http://localhost:8080/api/photos/+${photoId}`;
    axios.get(url).then(response => {
        //console.log("richiesta ok", response);
       	photo = response.data;
        categories = photo.categories;
        comments = photo.comments;
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
        //console.log("richiesta errata", error);
    })
}

function addComment(photoId) {
  const textComment = document.querySelector('#text').value;
 
  axios.post(`http://localhost:8080/api/comments/create/${photoId}`, {
    text: textComment
  })
    .then((res) => {
      console.log(res.data);
      window.location.reload(); //Aggiorna/ricarica la pagina corrente,
    })
    .catch((err) => {
      console.error('Errore nella richiesta', err.response.data.errors);
     	document.getElementById('text_err').innerHTML = '';
     	showValidationErrors(err.response.data.errors)
    });
}

btn.onclick = function(){addComment(photoId)};

function showValidationErrors(error) {
	const errorList = error;
	errorList.forEach(error => {
		document.getElementById('text_err').innerHTML += "<li>" + error.defaultMessage + "</li>";
	})

}


