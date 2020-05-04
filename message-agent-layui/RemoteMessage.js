/**
 * 远程消息
 * @author gyv12345@163.com
 * @version 0.1
 */

layui.define(['layer'],function (exports) {
    var r={
        render:function (options) {
            var layer=layui.layer;
            if (options.userKey){
                layer.msg('userKey is must')
                return
            }
            if (options.url){
                layer.msg('url is must')
                return;
            }
            var ws = new WebSocket("ws://"+options.url+"?useKey="+options.userKey);
            ws.onopen = function (evt) {
                console.log("Connection open ...");
                ws.send("Hello WebSockets!");
            };

            ws.onmessage = function (event) {
                console.log(event)
                var message = JSON.parse(event.data);
                console.log(message)
                layer.msg(message.message);
            };

            ws.onclose = function (evt) {
                console.log("Connection closed.");
            };

            ws.onerror = function (event) {
            };
        }
    };
    exports('RemoteMessage',r);
});