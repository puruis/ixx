function scanSelTool(action){
    /**
     *  自定义select组件
     * @type {*|jQuery}
     */
    var attrValue = $(".pSelect").attr('pSelect');
    if(attrValue!=null){
        $.ajax({
            type: "GET",
            url: '/dict/getDictByDicyType',
            data: {menuType:attrValue},
            dataType: "json",
            success: function (result) {
                if(result.code == '0'){
                    $(".pSelect").append("<option value=''>请选择</option>");
                    $.each(result.data,function(index,item){
                        $(".pSelect").append("<option value="+item.value+">"+item.name+"</option>"); //为Select追加一个Option(下拉项)
                    });
                    if(action == 'edit'){
                        $("#pSelect").val($("#selectValue").val());
                    }
                    form.render();
                }else{
                    pEoor();
                }
            }
        });
    }
    form.render();
}


function updateState(tableId,url,column,status){
    var checkStatus = table.checkStatus(tableId);
    var ids = new Array();
    // 遍历所有选择的行数据，取每条数据对应的ID
    $.each(checkStatus.data, function(i, row) {
        ids[i] = row[column]+"";
    });
    if(ids.length==0){
        layer.msg("至少选择一条记录", {icon: 5});
        return;
    }
    $.ajax({
        type: "POST",
        url: url,
        data: {
            ids : ids,
            status : status
        },
        dataType: "json",
        success: function (result) {
            if(result.code == '0'){
                pAlert();
                //刷新列表
                renderTable();
                closeWin();
            }else{
                pEoor();
            }
        }
    });
}

function closeWin(){
    var  frameindex= parent.layer.getFrameIndex(window.name);
    parent.layer.close(frameindex);
}

/**
 *  成功
 * @param msg
 */
function pAlert(msg) {
    msg=msg?msg:"操作成功";
    parent.layer.msg(msg, {icon: 1});
};

/**
 *  警告
 * @param msg
 */
function pWarn(msg) {
    msg=msg?msg:"操作失败";
    parent.layer.msg(msg, {icon: 7});
};

/**
 * 错误
 * @param msg
 */
function pEoor(msg) {
    msg=msg?msg:"操作失败";
    parent.layer.msg(msg, {icon: 2});
};

/**跳转到添加页面
 * @param url      请求地址
 */
function addPage(url){
    var index = layer.open({
        type : 2,
        title : '添加',
        maxmin : true,
        shade:0,
        shadeClose : true, // 点击遮罩关闭层
        area : [ '893px', '600px' ],
        content : url
    });
    return index;
}
/**打开小窗口
 * @param url      请求地址
 */
function openNewWindow(url,title,id){
    if(id!=null){
        url = url + '?id='+id;
    }
    var index = layer.open({
        type: 2,
        title: title,
        shadeClose: false,
        shade: [0.3, '#000'],
        maxmin: false, //开启最大化最小化按钮
        area: ['893px', '600px'],
        content: url
    });
    return index;
}

function pAjax(url,data){
    $.ajax({
        type: "POST",
        url: url,
        data: data,
        dataType: "json",
        success: function (result) {
            if(result.code == '0'){
                pAlert();
                return result;
            }else{
                pEoor();
                return null;
            }
        }
    });
}
function Obj(){

}
/**
 *  notice 提示
 */
function redirectPage(code){
    // location.href = '/error/'+code;
}