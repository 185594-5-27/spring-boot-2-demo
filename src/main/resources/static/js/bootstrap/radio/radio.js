/**
 * 实现从后台加载radio集合
 */
$(function () {
    $.fn.radioLoad = function(options){
        var opts = $.extend({}, $.fn.radioLoad.defaults, options);
        $.each(dictData,function(index,info){
            if(opts.type==info.type){
                if(opts.checkedValue==info.value){
                    $("#"+opts.id).append("<label  class='radio-inline'><input type='radio' name='"+opts.name+"' value='"+info.value+"' checked='checked' /> "+info.text+"</label>");
                }else{
                    $("#"+opts.id).append("<label  class='radio-inline'><input type='radio' name='"+opts.name+"' value='"+info.value+"' /> "+info.text+"</label>");
                }
            }
        })
    }
    $.fn.radioLoad.defaults = {}
})