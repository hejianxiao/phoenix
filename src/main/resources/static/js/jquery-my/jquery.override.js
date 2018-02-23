/**
 * 给JQuery对象添加namespace函数
 * @param ns
 */
$.namespace = function(ns) {
    if (typeof ns !== 'string') {
        throw new Error('namespace must be a string');
    }
    var ns_arr = ns.split('.'),s = ns_arr[0], pre_s,i = 1;
    window[s] = window[s] || {};
    pre_s = window[s];
    for (len = ns_arr.length; i < len; ++i) {
        s = ns_arr[i];
        pre_s[s] = pre_s[s] || {};
        pre_s = pre_s[s];
    }
};
