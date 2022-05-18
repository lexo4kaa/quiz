function changeSize(element) {
    if (element.value < 100)
        element.value = 100;
    else if (element.value > 500)
        element.value = 500;

    let size = element.value + "x" + element.value;
    let img = document.querySelector("img");
    let link = img.src.slice(0, img.src.lastIndexOf('=') + 1);
    img.src = link + size;
}