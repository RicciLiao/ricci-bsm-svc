(function (commonFunc) {

    commonFunc.popWindow = function (url, size) {
        if (size != null) {
            $('.modal-dialog').addClass(size);
        }
        commonFunc.loadModule($('.modal-body'), url, function () {
            console.log(url)
            $('#modalWindow').modal();
        });
    };

    commonFunc.loadProperties = function () {
        jQuery.i18n.properties({
            name: 'language',
            path: '../bsm/properties/i18n/',
            mode: 'map',
            language: 'zh_CN',
            cache: false,
            encoding: 'UTF-8',
            callback: function () {
                var insertEle = $('[data-i18n]');
                insertEle.each(function () {
                    $(this).html($.i18n.prop($(this).attr('data-i18n')));
                });
                var insertInputEle = $('[data-i18n-input]');
                insertInputEle.each(function () {
                    var selectAttr = $(this).attr('data-i18n-attr');
                    if (!selectAttr) {
                        selectAttr = "value";
                    }
                    $(this).attr(selectAttr, $.i18n.prop($(this).attr('data-i18n-input')));
                });
            }
        });
    };

    commonFunc.replaceI18n = function (target, params) {

        if (arguments.length > 2 && params.constructor !== Array) {
            params = $.makeArray(arguments).slice(1)
        }
        if (params.constructor !== Array) {
            params = [params]
        }
        $.each(params, function (index, param) {
            target = target.replace(new RegExp("\\{" + index + "\\}", "g"), function () {
                return param;
            })
        });
        return target;
    };

    commonFunc.loadModule = function (container, module, callBack) {
        container.load(module, callBack);
        /*var xmlHttp = window.XMLHttpRequest ? new XMLHttpRequest() : new ActiveXObject("Microsoft.XMLHTTP");
        xmlHttp.open("GET", module, false);
        xmlHttp.send();
        if (xmlHttp.readyState == 4) {
            switch (xmlHttp.status) {
                case 200:
                    if (callBack == null) {
                        container.load(module);
                    } else {
                        container.load(module, callBack);
                    }
                    break;
                default:
                    container.load('../html/errorModule/loseModule.html');
                    break;
            }
        }*/
    };

    commonFunc.openPreLoader = function () {
        $('#modalLoader').modal({
            backdrop: false,
            keyboard: false
        });
    };

    commonFunc.closePreLoader = function (callback) {
        $('#modalLoader').one('hidden.bs.modal', callback);
        $('#modalLoader').modal('hide');
    };

    commonFunc.isNullOrSpace = function (target) {
        if (target == null || target.toString().trim() == "") {
            return true;
        } else {
            return false;
        }
    };

    commonFunc.newFormJump = function (url) {
        var body = document.body,
            jumpForm = document.createElement("form");
        jumpForm.setAttribute("action", url);
        jumpForm.setAttribute("method", "post");
        jumpForm.setAttribute("style", "display : none");
        body.appendChild(jumpForm);
        jumpForm.submit();
        body.removeChild(jumpForm);
    };

    commonFunc.getJSON = function () {
        $.getJSON('constants.json')
    }

    commonFunc.ws=function () {
        var websocket = null;
        //判断当前浏览器是否支持WebSocket
        if ('WebSocket' in window) {
            //websocket = new WebSocket("wss://"+document.location.host+"/note_Alpha/websocket");
            websocket = new WebSocket("ws://"+document.location.host+"/ws");
        }
        else {
            alert('当前浏览器 Not support websocket')
        }

        //连接发生错误的回调方法
        websocket.onerror = function () {
            alert("WebSocket连接发生错误");
        };

        //连接成功建立的回调方法
        websocket.onopen = function () {
            console.log("WebSocket连接成功");
        }

        //接收到消息的回调方法
        websocket.onmessage = function (event) {
            webSocketMsg(event.data);
        }

        //连接关闭的回调方法
        websocket.onclose = function () {
            //console("WebSocket连接关闭");
        }

        //监听窗口关闭事件，当窗口关闭时，主动去关闭websocket连接，防止连接还没断开就关闭窗口，server端会抛异常。
        window.onbeforeunload = function () {
            closeWebSocket();
        }

        /*//将消息显示在网页上
        function setMessageInnerHTML(innerHTML) {
            document.getElementById('message').innerHTML += innerHTML + '<br/>';
        }*/

        //关闭WebSocket连接
        function closeWebSocket() {
            websocket.close();
        }
    }

}(commonFunc = {}));