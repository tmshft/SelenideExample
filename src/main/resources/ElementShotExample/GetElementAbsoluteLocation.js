/**
 * Copyright SHIFT Inc, All Rights Reserved.
 */
// サービス関数。現在のUserAgentからブラウザ種別を得る
const Browser = {
    chrome : 1,
    firefox : 2,
    ie : 3,
    edge: 4,
    safari : 5,
    other : 0
}

function getBrowser() {
    const userAgent = window.navigator.userAgent.toLowerCase();

    if (userAgent.indexOf('msie') !== -1 ||
        userAgent.indexOf('trident') !== -1) {
        return Browser.ie;
    } else if(userAgent.indexOf('edge') !== -1) {
        return Browser.edge;
    } else if(userAgent.indexOf('chrome') !== -1) {
        return Browser.chrome;
    } else if(userAgent.indexOf('safari') !== -1) {
        return Browser.safari;
    } else if(userAgent.indexOf('firefox') !== -1) {
        return Browser.firefox;
    // } else if(userAgent.indexOf('opera') !== -1) { // sorry, opera doesn't support yet
    //     return Browser.opera;
    } else {
        return Browser.other;
    }
}

// iframeの階層を上にたどりながら、要素の絶対座標を得る
// 参考：https://qiita.com/ozoneboy/items/b57bf4e67110b3756390
// ただしこのロジックだとiframe自体のborderが含まれないでずれるので、border (clienttTop / clientLeft）も加算する
const el = arguments[0];
const browser = getBrowser();
var top = el.getBoundingClientRect().top;
var left = el.getBoundingClientRect().left;

var doc = el.ownerDocument;
var childWindow = doc.defaultView;

while (window.top !== childWindow) {
    var frame = childWindow.frameElement;
    top += frame.getBoundingClientRect().top + frame.clientTop;
    left += frame.getBoundingClientRect().left + frame.clientLeft;
    childWindow = childWindow.parent;
}

if (browser === Browser.ie) {
    // IEの場合、サブピクセルレンダリングの結果は四捨五入
    return { top: Math.round(top), left: Math.round(left) };
} else {
    // それ以外のブラウザの場合はサブピクセルレンダリングの結果は切り捨て
    return { top: Math.round(top), left: Math.round(left) };
}
