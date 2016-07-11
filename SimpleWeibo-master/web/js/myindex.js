// JavaScript Document



function color() {
    var d = document.getElementById("ficon_search");
    d.style.backgroundColor = "#338CE6";
}

function colorout() {
    var b = document.getElementById("ficon_search");
    b.style.backgroundColor = "#459DF5";
} /*实现文字颜色变换*/


//
// var circleoff;
// var circleon;
// if (document.images) {
//     circleoff = new Image(50, 50);
//     circleoff.src = "../images/person1.png";
//     circleon = new Image(50, 50);
//     circleon.src = "../images/person.png";
// }
//
// function On(name) {
//     if (document.images) {
//         document.images['img' + name].src = circleon.src;
//     }
// }
//
// function Off(name) {
//     if (document.images) {
//         document.images['img' + name].src = circleoff.src;
//     }
// }
// /*实现图片的切换*/



var circleoffp;
var circleonp;
if (document.images) {
    circleoffp = new Image(50, 50);
    circleoffp.src = "../images/home.png";
    circleonp = new Image(50, 50);
    circleonp.src = "../images/home1.png";
}

function Onp(name) {
    if (document.images) {
        document.images['img' + name].src = circleonp.src;
    }
}

function Offp(name) {
    if (document.images) {
        document.images['img' + name].src = circleoffp.src;
    }
}
/*personal实现图片的切换*/

