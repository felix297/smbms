let path = $("#path").val();
let imgYes = "<img width='15px' src='" + path + "/images/y.png' alt='right'/>";
let imgNo = "<img width='15px' src='" + path + "/images/n.png' alt='wrong'/>";
/**
 * 提示信息显示
 * element:显示提示信息的元素（font）
 * css：提示样式
 * tipString:提示信息
 * status：true/false --验证是否通过
 */
function validateTip(element,css,tipString,status){
	element.css(css);
	element.html(tipString);
	
	element.prev().attr("validateStatus",status);
}
let referer = $("#referer").val();