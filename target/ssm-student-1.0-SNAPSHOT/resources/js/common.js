// 获取form表单项的值并封装为json对象
//selector :选择器，如：#addForm
function formDataObj(selector) {
    var arr =$(selector).serializeArray();
    var retObj={};
    $.each(arr,function (i,ele) {
        if(retObj[ele["name"]]){
            retObj[ele["name"]] += "," + ele["value"];
        }else {
            retObj[ele["name"]] = ele["value"];
        }
    });
    return retObj;
}