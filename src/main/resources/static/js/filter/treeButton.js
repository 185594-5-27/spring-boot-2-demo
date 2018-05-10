$(function () {

    $.fn.treeButton = function(options){
        var opts = $.extend({}, $.fn.treeButton.defaults, options);
        $.post(opts.url,function(r){
            if(r.result){
                $.each(r.data,function (index,info) {
                    treeButtonData[index] = info.code;
                })
            }
        });
    }
    $.fn.treeButton.defaults = {};
    treeButtonData = parent.treeButtonData
    if(treeButtonData==undefined){
        treeButtonData = new Array();
    }
});

function getTreeButton(code){
    for(var i=0;i<treeButtonData.length;i++){
        if(treeButtonData[i]==code){
            return true;
        }
    }
    return false;
}