var fs = require('fs'),
    http = require('http'),
    socketio = require('socket.io');
var server = http.createServer(function(req, res) {
    res.writeHead(200, {
        'Content-type': 'text/html'
    });
    res.end(fs.readFileSync(__dirname + '/index.html'));
}).listen(3001, function() {
    console.log('Listening at: http://127.0.0.1:3001/');
});
socketio.listen(server).on('connection', function(socket) {
    socket.on('message', function(msg) {
        console.log('Message Received: ', msg);
        socket.broadcast.emit('message', msg);
    });
});
