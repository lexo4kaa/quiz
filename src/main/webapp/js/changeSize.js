function changeSize(element) {
    if (element.value < element.min)
        element.value = element.min;
    else if (element.value > element.max)
        element.value = element.max;

    let size = element.value + "x" + element.value;
    let img = document.querySelector("img");
    let link = img.src.slice(0, img.src.lastIndexOf('=') + 1);
    img.src = link + size;
}