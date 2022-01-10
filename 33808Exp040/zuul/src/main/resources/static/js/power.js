//从前端缓存 sessionStorage 拿到当前用户的权限列表
var powerList = sessionStorage.powerList.split(",");

//拼接style
var htmls = "<style>"

//遍历权限列表 并且拼接
for (var i =0;i<powerList.length;i++){
    htmls+= "[data-power='"+powerList[i]+"'] {visibility: visible}";
}

htmls += "</style>"
console.log(htmls)
$("body").append(htmls);