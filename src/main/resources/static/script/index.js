let photos;
let boxPhotos = document.getElementById("photo_box");
const input = document.getElementById('value_photo');

function photoList() {
	input.value = '';
    axios.get('http://localhost:8080/api/photos').then((response) => {
        //console.log("richiesta ok", response.data);
        photos = response.data;
        //console.log(photos);
        boxPhotos.innerHTML='';
        photos.forEach(photo => {
            //console.log(photo);
            if(photo.isVisible){
            boxPhotos.innerHTML += `
            <div class="col">
				<div class="rounded-0 card_img">
                <a href="/show?id=${photo.id}">
                    <div id="title_img">
                        <h5>${photo.title}</h5>
                        <p class="m-0">#${photo.tag}</p>
                    </div>
                    <img class="w-100 h-100 img_card" src="${photo.url}">
                    </a>
				</div>
			</div>`;			
			}
        });

    }).catch((error) => {
        //console.log("richiesta errata", error)
    })
};

photoList();

// ----------- Ricerca per titolo o tag -----------------
const element = document.getElementById('myBtnFilter');

function search(){
	const filter = input.value;
    axios.get(`http://localhost:8080/api/photos?search=${filter}`).then((response) =>{
		//console.log(response);
        photos = response.data;
        //console.log(photos);
        boxPhotos.innerHTML='';
        photos.forEach(photo => {
            //console.log(photo);
            if(photo.isVisible){
            boxPhotos.innerHTML += `
            <div class="col">
				<div class="rounded-0 card_img">
                <a href="/show?id=${photo.id}">
                    <div id="title_img">
                        <h5>${photo.title}</h5>
                        <p class="m-0">#${photo.tag}</p>
                    </div>
                    <img class="w-100 h-100 img_card" src="${photo.url}">
                    </a>
				</div>
			</div>`;
			}
    	});
	});
}
element.onclick = function(){search()};

