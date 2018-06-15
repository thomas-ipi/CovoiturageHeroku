/**
 * 
 */


function uploadAvatar(evt) {
    var file = document.getElementById("avatar").files[0];
    if (file) {
        getAsImage(file);
    }
    evt.stopPropagation();
    evt.preventDefault();
}

function getAsImage(readFile) {
    var reader = new FileReader();
    reader.readAsDataURL(readFile);
    reader.onload = addImg;
}


function addImg(imgsrc) {
    var img = document.getElementById("imgAvatar");
    img.setAttribute("src", imgsrc.target.result);
    img.setAttribute("width", "256px");
    img.setAttribute("height", "256px");
    
}